package com.tes.Penjualan.dto.transDetail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InsertTransDetailDTO {
    @Schema(description = "Document Number PK.")
    private String documentCode;

    @Schema(description = "Document Number PK.")
    private String documentNumber;

    @Schema(description = "Customer username.")
    private String username;

    @Schema(description = "Total price all item.")
    private BigDecimal totalPrice;

    @Schema(description = "Transaction Date.")
    private LocalDate date;
}
