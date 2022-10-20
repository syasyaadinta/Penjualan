package com.tes.Penjualan.dto.product;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductGridDTO {

    private String productCode;
    private String productName;
    private BigDecimal price;
    private String currency;
    private BigDecimal discount;
    private String dimension;
    private String unit;
    private String imagePath;
    private int quantity;

    public BigDecimal hargaAkhir(){
        BigDecimal diskon = (this.discount).divide(new BigDecimal(100)).multiply(this.price);
        BigDecimal harga = (this.price).subtract(diskon);
        return harga;
    }

    public ProductGridDTO(String productCode, String productName, BigDecimal price, String currency, BigDecimal discount, String dimension, String unit, String imagePath) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.currency = currency;
        this.discount = discount;
        this.dimension = dimension;
        this.unit = unit;
        this.imagePath = imagePath;
    }
}
