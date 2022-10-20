package com.tes.Penjualan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@ToString
@Table(name="TransactionDetails")
public class TransactionDetail {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "DetailId", nullable = false)
//    private Long detailId;
//
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private Transaction transaction;
//
//    @Column(name = "ProductCode", nullable = false, length = 18)
//    private String productCode;
//
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ProductCode", insertable = false, updatable = false)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private Product product;

//    @Column(name = "TransactionId", nullable = false, length = 6)
//    private String transactionId;

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "TransactionId", insertable = false, updatable = false)
//    private Transaction transaction;

//    @Column(name = "ProductCode", nullable = false, length = 18)
//    private String productCode;
//
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ProductCode", insertable = false, updatable = false)
//    private Product product;

    @EmbeddedId
    private TransHeaderId transHeaderId;

    @Column(name = "Total", nullable = false, length = 10)
    private BigDecimal totalPrice;

    @Column(name = "Date", nullable = false)
    private LocalDate date;

}
