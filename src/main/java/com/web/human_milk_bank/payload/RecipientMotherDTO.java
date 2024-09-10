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
public class RecipientMotherDTO {
    private long recipientId;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 20)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Husband name is mandatory")
    @Size(min = 2, max = 20)
    @Column(name = "husband_name", nullable = false)
    private String husbandName;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 2, max = 20)
    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "mobile_no", nullable = false)
    @Size(min = 10, max = 15)
    @Pattern(regexp = "^[+]?[0-9\\-\\s]+$", message = "Invalid mobile number")
    private String mobileNo;

    @Column(name = "milk", nullable = false)
    @NotNull(message = "Field milk is mandatory.")
    private Integer milk;

    @Column(name = "dob", nullable = false)
    @NotNull(message = "Date of birth is mandatory.")
    private LocalDate dob;
}
