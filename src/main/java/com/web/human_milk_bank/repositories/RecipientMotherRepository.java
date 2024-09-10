package com.web.human_milk_bank.repositories;

import com.web.human_milk_bank.models.RecipientMother;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientMotherRepository extends JpaRepository<RecipientMother, Long> {
}
