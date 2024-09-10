package com.web.human_milk_bank.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorDTO {
    // Applying validations on DTO rather than entity due to Separation of Concerns.

    private Long donorId;

    @NotNull(message = "Date is mandatory.") // Changes Default message
    private LocalDate edate;

    @NotBlank(message = "Hospital name is mandatory.")
    @Size(max = 50)
    private String hospitalName;

    @NotBlank (message = "Mobile number should not be blank.")
    @Size(min = 10, max = 15)
    @Pattern(regexp = "^[+]?[0-9\\-\\s]+$", message = "Invalid mobile number")
    private String mobileNo;

    @NotNull (message = "Date of birth is mandatory.")
    private LocalDate dob;

    @Size(min = 2, max = 30)
    @NotBlank (message = "Mother name is mandatory.")
    private String motherName;

    @Size(min = 2, max = 30)
    @NotBlank (message = "Father name is mandatory.")
    private String fatherName;

    @Size(min = 2, max = 20)
    @NotBlank (message = "Surname is mandatory.")
    private String surname;

    @Size(max = 20)
    @NotBlank (message = "Please provide batch number.")
    private String batchNo;

    @NotNull (message = "Field Milk is mandatory.")
    private Integer milk;

    @NotBlank (message = "Field hiv is mandatory.")
    private String hiv;

    @NotBlank (message = "Field vrdl is mandatory.")
    private String vrdl;

    @NotBlank (message = "Field hbsag is mandatory.")
    private String hbsag;

    @NotBlank (message = "QR Code required.")
    private String qrCode;

    @NotNull (message = "Field rType is mandatory.")
    private LocalDate rtype;
}
