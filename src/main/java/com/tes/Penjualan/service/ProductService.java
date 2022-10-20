package com.tes.Penjualan.service;

import com.tes.Penjualan.dto.product.InsertProductDTO;
import com.tes.Penjualan.dto.product.ProductGridDTO;
import com.tes.Penjualan.dto.product.UpdateProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductGridDTO> getGrid(Integer page, String productCode);

    Long getTotalPages(String productCode);

    UpdateProductDTO getProduct(String productCode);

    void updateProduct(UpdateProductDTO dto);

    void insertProduct(InsertProductDTO dto);

    ProductGridDTO getProductById(String productCode);
}
