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
@Table(name = "discards")
public class DiscardMilk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discardMilkID;

    @Column(name = "batch_no",nullable = false)
    private String batchNo;

    @Column(name = "discard_date")
    @NotNull(message = "Date is mandatory.")
    private LocalDate discardDate;

    @Column(name = "operator_name",nullable = false)
    private String operatorName;

    @Column(name = "approved_qr_code", nullable = false)
    @Lob // If storing large QR codes, consider using @Lob annotation
    private String approvedQrCode;

    @Column(name = "discarded_qr_code", nullable = false)
    @Lob // If storing large QR codes, consider using @Lob annotation
    private String discardedQrCode;
}
