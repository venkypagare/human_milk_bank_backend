package com.web.human_milk_bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "donors")
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donorId;

    @Column(name = "e_date", nullable = false)
    @NotNull(message = "Date is mandatory.")
    private LocalDate edate;

    @Column(name = "hospital_name",nullable = false)
    private String hospitalName;

    @Column(name = "mobile_no", nullable = false)
    private String mobileNo;

    @Column(name = "dob", nullable = false)
    @NotNull (message = "Date of birth is mandatory.")
    private LocalDate dob;

    @Column(name = "mother_name",nullable = false)
    private String motherName;

    @Column(name = "father_name",nullable = false)
    private String fatherName;

    @Column(name = "surname",nullable = false)
    private String surname;

    @Column(name = "batch_no",nullable = false)
    private String batchNo;

    @Column(name = "milk",nullable = false)
    @NotNull (message = "Field Milk is mandatory.")
    private Integer milk;

    @Column(name = "hiv",nullable = false)
    private String hiv;

    @Column(name = "vrdl", nullable = false)
    private String vrdl;

    @Column(name = "hbsag", nullable = false)
    private String hbsag;

    @Column(name = "qr_code", nullable = false)
    @Lob // If storing large QR codes, consider using @Lob annotation
    private String qrCode;

    @Column(name = "r_type", nullable = false)
    @NotNull (message = "Field rType is mandatory.")
    private LocalDate rtype;
}