package com.web.human_milk_bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "royalty_cards")
public class RoyaltyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is mandatory.")
    private String firstName;

    @Column(name = "middle_name", nullable = false)
    @NotBlank(message = "Middle name is mandatory.")
    private String middleName;

    @Column(name = "surname", nullable = false)
    @NotBlank(message = "Surname is mandatory.")
    private String surname;

    @Column(name = "mobile_no", nullable = false)
    @NotBlank(message = "Mobile number is mandatory.")
    private String mobileNo;

    @Column(name = "dob", nullable = false)
    @NotNull(message = "Surname is mandatory.")
    private LocalDate dob;

    @Column(name = "expiry_date", nullable = false)
//    @NotNull
    private LocalDate expiryDate;

    @Column(name = "active", nullable = false)
    private boolean active;
}
