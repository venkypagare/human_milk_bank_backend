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
@Table(name = "dispenses")
public class DispenseMilk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dispenseId;

    @Column(name = "batch_no", nullable = false)
    @NotBlank(message = "Field batch number is mandatory.")
    private String batchNo;

    @Column(name = "infant_name", nullable = false)
    @NotBlank(message = "Infant name is mandatory.")
    private String infantName;

    @Column(name = "dispense_date", nullable = false)
    @NotNull(message = "Field dispense date is mandatory.")
    private LocalDate dispenseDate;

    @Column(name = "operator_name", nullable = false)
    @NotBlank(message = "Operator name is mandatory.")
    private String operatorName;

    @Column(name = "recipient_name", nullable = false)
    @NotBlank(message = "Recipient name is mandatory.")
    private String recipientName;

    @Column(name = "qr_code", nullable = false)
    @NotBlank(message = "Field QR Code is mandatory.")
    @Lob
    private String qrCode;

    @Column(name = "remark", nullable = false)
    private String remark;
}
