package com.tes.Penjualan.dao;

import com.tes.Penjualan.dto.transaction.TransactionDTO;
import com.tes.Penjualan.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransRepository extends JpaRepository<Transaction, String> {

    @Query("""
            SELECT trans
            FROM Transaction AS trans
                LEFT JOIN trans.product AS pro
                LEFT JOIN trans.account AS acc
            WHERE acc.username = :username
            """)
    List<Transaction> findByUsername(@Param("username") String username);

    @Query("""
            SELECT new com.tes.Penjualan.dto.transaction.TransactionDTO(
            trans.transactionId, trans.username, trans.quantity, trans.subTotal,
            pro.productCode, pro.productName, pro.unit, pro.imagePath, tdet.transHeaderId.documentCode)
            FROM Transaction AS trans
                LEFT JOIN trans.product AS pro
                LEFT JOIN trans.account AS acc
                LEFT JOIN trans.transDetail AS tdet
            WHERE trans.username = :username
            """)
    List<TransactionDTO> findAllCart(@Param("username") String username,
                                     Pageable pageable);

    @Query("""
            SELECT COUNT(*)
            FROM Transaction AS trans
                LEFT JOIN trans.product AS pro
                LEFT JOIN trans.account AS acc
            WHERE trans.username = :username
            """)
    Long countPages(@Param("username") String username);
}
