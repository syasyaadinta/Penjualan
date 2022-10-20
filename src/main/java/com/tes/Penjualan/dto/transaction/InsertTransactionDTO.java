package com.tes.Penjualan.dto.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InsertTransactionDTO {

    private Long transactionId;
    private String username;
    private String productCode;
    private int quantity;
    private BigDecimal subTotal;

}
