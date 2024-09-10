package com.web.human_milk_bank.payload;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
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
public class DiscardMilkDTO {
    private Long discardMilkID;

    @Column(name = "batch_no",nullable = false)
    @NotBlank (message = "Please provide batch number.")
    private String batchNo;

    @Column(name = "discard_date")
    @NotNull(message = "Date is mandatory.")
    private LocalDate discardDate;

    @Column(name = "operator_name",nullable = false)
    @Size(min = 2, max = 50)
    private String operatorName;

    @Column(name = "approved_qr_code", nullable = false)
    @Lob // If storing large QR codes, consider using @Lob annotation
    @NotBlank (message = "QR Code required.")
    private String approvedQrCode;

    @Column(name = "discarded_qr_code", nullable = false)
    @Lob // If storing large QR codes, consider using @Lob annotation
    @NotBlank(message = "QR Code required.")
    private String discardedQrCode;
}
