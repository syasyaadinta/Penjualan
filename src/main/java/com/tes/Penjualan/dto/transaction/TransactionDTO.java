package com.tes.Penjualan.dto.transaction;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionDTO {

    private Long transactionId;
    private String username;
    private int quantity;
    private BigDecimal subTotal;
    private String productCode;
    private String productName;
    private String unit;
    private String imagePath;
    private String documentCode;

}
