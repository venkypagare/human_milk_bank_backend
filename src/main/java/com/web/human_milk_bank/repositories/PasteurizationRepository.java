package com.web.human_milk_bank.repositories;

import com.web.human_milk_bank.models.Pasteurization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasteurizationRepository extends JpaRepository<Pasteurization, Long> {
}
