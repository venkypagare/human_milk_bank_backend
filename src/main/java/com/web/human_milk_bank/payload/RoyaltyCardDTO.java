package com.web.human_milk_bank.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoyaltyCardDTO {
    private Long cardId;

    @NotBlank(message = "First name is mandatory.")
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank(message = "Middle name is mandatory.")
    @Size(min = 2, max = 50)
    private String middleName;

    @NotBlank(message = "Surname is mandatory.")
    @Size(min = 2, max = 50)
    private String surname;

    @NotBlank(message = "Mobile number is mandatory.")
    @Size(min = 10, max = 15)
    @Pattern(regexp = "^[+]?[0-9\\-\\s]+$", message = "Invalid mobile number")
    private String mobileNo;

    @Column(name = "dob", nullable = false)
    @NotNull(message = "Date of birth is mandatory.")
    private LocalDate dob;

//    @NotNull
    private LocalDate expiryDate;

    private boolean active;
}
