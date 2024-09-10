package com.web.human_milk_bank.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SecurityQuestionnaireDTO {
    private Long questionnaireId;

    @NotBlank(message = "Please add security question")
    private String question;

    @NotBlank(message = "Please add security answer")
    @Size(min = 2, max = 50)
    private String answer;
}
