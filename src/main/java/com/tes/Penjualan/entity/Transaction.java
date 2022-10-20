package com.tes.Penjualan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name="Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionId", nullable = false, length = 6)
    private Long transactionId;

    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Username", insertable = false, updatable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private TransactionDetail transDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductCode")
    private Product product;

    @Column(name = "Quantity", nullable = false, length = 6)
    private int quantity;

    @Column(name = "SubTotal", length = 10)
    private BigDecimal subTotal;

    public Transaction(Long transactionId, String username, Product product, int quantity, BigDecimal subTotal) {
        this.transactionId = transactionId;
        this.username = username;
        this.product = product;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }
}
