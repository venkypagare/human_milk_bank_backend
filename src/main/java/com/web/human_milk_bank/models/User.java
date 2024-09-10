package com.web.human_milk_bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password")
    private String password;

//    @Column(name = "question", nullable = false)
//    @NotBlank(message = "Please add security question")
//    private String question;
//
//    @Column(name = "answer", nullable = false)
//    @NotBlank(message = "Please add security answer")
//    @Size(min = 2, max = 50)
//    private String answer;

    public User(Long userId, String username, String password, Set<Role> roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
               joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(@NotBlank @Size(min = 3, max = 20) String username, String password) {
        this.username = username;
        this.password = password;
    }
}
