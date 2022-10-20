package com.tes.Penjualan.service;

import com.tes.Penjualan.dao.TransDetailRepository;
import com.tes.Penjualan.dto.report.ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransDetailServiceImpl implements TransDetailService{

    private final int rowsInPage = 6;

    @Autowired
    private TransDetailRepository transDetailRepository;

    @Override
    public List<ReportDTO> getAll(Integer page) {
        Pageable pagination = PageRequest.of(page-1, rowsInPage, Sort.by("reportId"));
        List<ReportDTO> listReport = transDetailRepository.getReport(pagination);
        return listReport;
    }

    @Override
    public long getTotalPages() {
        double totalData = (double)(transDetailRepository.count());
        long totalPage = (long)(Math.ceil(totalData / rowsInPage));
        return totalPage;
    }
}
