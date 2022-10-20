package com.tes.Penjualan.dao;

import com.tes.Penjualan.dto.report.ReportDTO;
import com.tes.Penjualan.entity.TransactionDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransDetailRepository extends JpaRepository<TransactionDetail, String> {

    @Query("""
            SELECT NEW com.tes.Penjualan.dto.report.ReportDTO(
            )
            FROM Transaction AS tr
             LEFT JOIN tr.transactionDetails AS td
             LEFT JOIN th.account AS acc
             LEFT JOIN th.products AS pro
            """)
    List<ReportDTO> getReport(Pageable pageable);
}
