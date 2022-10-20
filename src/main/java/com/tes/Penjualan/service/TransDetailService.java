package com.tes.Penjualan.service;

import com.tes.Penjualan.dto.report.ReportDTO;

import java.util.List;

public interface TransDetailService {

    List<ReportDTO> getAll(Integer page);

    long getTotalPages();
}
