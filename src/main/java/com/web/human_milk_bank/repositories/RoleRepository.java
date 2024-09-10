package com.web.human_milk_bank.repositories;

import com.web.human_milk_bank.models.Role;
import com.web.human_milk_bank.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
