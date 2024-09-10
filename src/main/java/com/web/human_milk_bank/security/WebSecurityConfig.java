package com.web.human_milk_bank.security;

import com.web.human_milk_bank.models.AppRole;
import com.web.human_milk_bank.models.Role;
import com.web.human_milk_bank.models.User;
import com.web.human_milk_bank.repositories.RoleRepository;
import com.web.human_milk_bank.repositories.UserRepository;
import com.web.human_milk_bank.security.jwt.AuthEntryPointJwt;
import com.web.human_milk_bank.security.jwt.AuthTokenFilter;
import com.web.human_milk_bank.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.util.Set;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    // Intercepting requests and check for valid JWT token
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    // This is being used to authenticate to users. We want to use of custom UserDetailService so we need this.
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService); // ensures use of our own / custom userDetailsService
        authProvider.setPasswordEncoder(passwordEncoder()); // ensure custom passwordEncoder

        return authProvider;
    }

    // Responsible for managing authentication.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
//                                .requestMatchers("/api/admin/**").permitAll()
//                                .requestMatchers("/api/public/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/api/test/**").permitAll()
                                .requestMatchers("/images/**").permitAll()
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers(headers -> headers.frameOptions(
                frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }

    // If you have non-secure endpoints that ypu want to expose to everyone then you can use this bean.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"));
    }

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Retrieve or create roles
            Role userRole = roleRepository.findByRoleName(AppRole.ROLE_DONOR)
                    .orElseGet(() -> {
                        Role newUserRole = new Role(AppRole.ROLE_DONOR);
                        return roleRepository.save(newUserRole);
                    });

            Role recipientRole = roleRepository.findByRoleName(AppRole.ROLE_RECIPIENT)
                    .orElseGet(() -> {
                        Role newRecipientRole = new Role(AppRole.ROLE_RECIPIENT);
                        return roleRepository.save(newRecipientRole);
                    });

            Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                    .orElseGet(() -> {
                        Role newAdminRole = new Role(AppRole.ROLE_ADMIN);
                        return roleRepository.save(newAdminRole);
                    });

            Set<Role> userRoles = Set.of(userRole);
            Set<Role> RecipientRoles = Set.of(recipientRole);
            Set<Role> adminRoles = Set.of(userRole, recipientRole, adminRole);


            // Create users if not already present
            if (!userRepository.existsByUsername("donor1")) {
                User user1 = new User("donor1", passwordEncoder.encode("password1"));
                userRepository.save(user1);
            }

            if (!userRepository.existsByUsername("recipient1")) {
                User recipient1 = new User("recipient1", passwordEncoder.encode("password2"));
                userRepository.save(recipient1);
            }

            if (!userRepository.existsByUsername("admin")) {
                User admin = new User("admin", passwordEncoder.encode("adminPass"));
                userRepository.save(admin);
            }

            // Update roles for existing users
            userRepository.findByUsername("donor1").ifPresent(donor -> {
                donor.setRoles(userRoles);
                userRepository.save(donor);
            });

            userRepository.findByUsername("recipient1").ifPresent(recipient -> {
                recipient.setRoles(RecipientRoles);
                userRepository.save(recipient);
            });

            userRepository.findByUsername("admin").ifPresent(admin -> {
                admin.setRoles(adminRoles);
                userRepository.save(admin);
            });
        };
    }

}