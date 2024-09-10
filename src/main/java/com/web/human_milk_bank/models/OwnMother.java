package com.web.human_milk_bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "own_mother")
public class OwnMother {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long ownMotherId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "husband_name", nullable = false)
    private String husbandName;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "mobile_no", nullable = false)
    private String mobileNo;

    @Column(name = "milk", nullable = false)
    @NotNull(message = "Field milk is mandatory.")
    private Integer milk;

    @Column(name = "dob", nullable = false)
    @NotNull(message = "Date of birth is mandatory.")
    private LocalDate dob;
}
