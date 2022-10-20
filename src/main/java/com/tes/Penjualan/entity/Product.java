package com.tes.Penjualan.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name="Products")
public class Product {

    @Id
    @Column(name = "ProductCode", nullable = false, length = 18)
    private String productCode;

    @Column(name = "ProductName", nullable = false, length = 30)
    private String productName;

    @Column(name = "Price", nullable = false, length = 6)
    private BigDecimal price;

    @Column(name = "Currency", nullable = false, length = 5)
    private String currency;

    @Column(name = "Discount", length = 6)
    private BigDecimal discount;

    @Column(name = "Dimension", length = 50)
    private String dimension;

    @Column(name = "Unit", length = 5)
    private String unit;

    @Column(name="ImagePath")
    private String imagePath;

    public Product(String productCode, String productName, BigDecimal price, String currency, BigDecimal discount, String dimension, String unit) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.currency = currency;
        this.discount = discount;
        this.dimension = dimension;
        this.unit = unit;
    }

}
