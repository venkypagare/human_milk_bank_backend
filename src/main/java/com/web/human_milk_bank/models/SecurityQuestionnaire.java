package com.web.human_milk_bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "security_questionnaire")
public class SecurityQuestionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionnaireId;

    @Column(name = "question", nullable = false)
    @NotBlank(message = "Please add security question")
    private String question;

    @Column(name = "answer", nullable = false)
    @NotBlank(message = "Please add security answer")
    @Size(min = 2, max = 50)
    private String answer;
}
