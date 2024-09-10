package com.web.human_milk_bank.repositories;

import com.web.human_milk_bank.models.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CultureRepository extends JpaRepository<Culture, Long> {
}
