package com.tes.Penjualan.dao;

import com.tes.Penjualan.dto.product.ProductGridDTO;
import com.tes.Penjualan.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("""
            SELECT new com.tes.Penjualan.dto.product.ProductGridDTO(
            pro.productCode, pro.productName, pro.price, pro.currency, pro.discount, pro.dimension, pro.unit, pro.imagePath)
            FROM Product AS pro
            WHERE pro.productCode LIKE %:productCode%
            """)
    List<ProductGridDTO> findAllProduct(@Param("productCode") String productCode,
                                        Pageable pageable);

    @Query("""
            SELECT COUNT(*)
            FROM Product AS pro
            WHERE pro.productCode LIKE %:productCode%
            """)
    Long countPages(@Param("productCode") String productCode);

    @Query("""
            SELECT MAX(tdid.documentNumber)
            FROM TransactionDetail as td
                LEFT JOIN td.transHeaderId  AS tdid
            """)
    String getHighestNumber();
}
