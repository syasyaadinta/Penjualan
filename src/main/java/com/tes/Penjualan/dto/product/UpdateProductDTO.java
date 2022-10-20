package com.tes.Penjualan.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateProductDTO {

    @Schema(description = "Product PK.")
    private String productCode;

    @Schema(description = "Product Name maximum 30 characters.")
    @NotBlank(message="Product Name is required!")
    @Size(max=30, message="Product Name can't be more than 30 characters.")
    private String productName;

    @Schema(description = "Product price maximum 12 digit and 2 desimal points.")
    @NotNull(message="product price is required.")
    @Digits(integer=12, fraction=2, message="Must be a decimal value with 2 decimal points.")
    private BigDecimal price;

    @Schema(description = "Currency money.")
    private String currency;

    @Schema(description = "Discount maximum 3 digit and 2 desimal points.")
    @NotNull(message="product price is required.")
    @Digits(integer=3, fraction=2, message="Must be a decimal value with 2 decimal points.")
    private BigDecimal discount;

    @Schema(description = "Product Dimension maximum 50 characters.")
    @NotBlank(message="Product Dimension is required!")
    @Size(max=50, message="Product Dimension can't be more than 50 characters.")
    private String dimension;

    @Schema(description = "Product Unit maximum 5 characters.")
    @NotBlank(message="Product Unit is required!")
    @Size(max=5, message="Product Unit can't be more than 5 characters.")
    private String unit;

    @Schema(description = "Image Path.")
    @Column(name="ImagePath")
    private String imagePath;

    public UpdateProductDTO(String productCode, String productName, BigDecimal price, String currency, BigDecimal discount) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.currency = currency;
        this.discount = discount;
    }

    public UpdateProductDTO(String productCode, String productName, BigDecimal price, String currency, BigDecimal discount, String dimension, String unit) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.currency = currency;
        this.discount = discount;
        this.dimension = dimension;
        this.unit = unit;
    }
}
