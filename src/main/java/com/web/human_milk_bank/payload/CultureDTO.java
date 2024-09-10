package com.web.human_milk_bank.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CultureDTO {
    private Long cultureId;

    @Column(name = "culture_date")
    @NotNull(message = "Date is mandatory.")
    private LocalDate cultureDate;

    @Column(name = "batch_no",nullable = false)
    @NotBlank(message = "Batch number is mandatory.")
    private String batchNo;

    @Column(name = "operator_name",nullable = false)
    @NotBlank(message = "Operator name is mandatory.")
    @Size(min = 2, max = 50)
    private String operatorName;

    @Column(name = "no_of_samples", nullable = false)
    @NotNull(message = "No of Samples is mandatory.")
    private Long noOfSamples;

    @Column(name = "total_milk",nullable = false)
    @NotNull (message = "Field Milk is mandatory.")
    private Integer totalMilk;
}
