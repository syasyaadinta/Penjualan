package com.tes.Penjualan.service;

import com.tes.Penjualan.dto.transaction.InsertTransactionDTO;
import com.tes.Penjualan.dto.transaction.TransactionDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransService {
    void buyProduct(InsertTransactionDTO dto, String username);

    List<TransactionDTO> getCart(Integer page, String username);

    void updateTransaction(TransactionDTO dto, String username, BigDecimal total);

    Long getTotalPages(String username);
}
