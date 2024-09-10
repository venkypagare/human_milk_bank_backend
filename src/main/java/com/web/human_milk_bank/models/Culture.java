package com.web.human_milk_bank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "culture")
public class Culture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cultureId;

    @Column(name = "culture_date")
    @NotNull(message = "Date is mandatory.")
    private LocalDate cultureDate;

    @Column(name = "batch_no",nullable = false)
    private String batchNo;

    @Column(name = "operator_name",nullable = false)
    private String operatorName;

    @Column(name = "no_of_samples", nullable = false)
    @NotNull(message = "No of Samples is mandatory.")
    private Long noOfSamples;

    @Column(name = "total_milk",nullable = false)
    @NotNull (message = "Field Milk is mandatory.")
    private Integer totalMilk;
}
