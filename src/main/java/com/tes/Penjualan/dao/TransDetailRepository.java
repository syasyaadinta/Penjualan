package com.tes.Penjualan.dao;

import com.tes.Penjualan.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransDetailRepository extends JpaRepository<TransactionDetail, String> {

}
