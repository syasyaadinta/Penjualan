package com.tes.Penjualan.service;

import com.tes.Penjualan.dao.ProductRepository;
import com.tes.Penjualan.dto.product.InsertProductDTO;
import com.tes.Penjualan.dto.product.ProductGridDTO;
import com.tes.Penjualan.dto.product.UpdateProductDTO;
import com.tes.Penjualan.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final int rowsInPage = 6;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductGridDTO> getGrid(Integer page, String productCode) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("productCode"));
        List<ProductGridDTO> grid = productRepository.findAllProduct(productCode, pagination);
        return grid;
    }

    @Override
    public Long getTotalPages(String productCode) {
        double totalData = (double)(productRepository.countPages(productCode));
        long totalPage = (long)(Math.ceil(totalData / this.rowsInPage));
        return totalPage;
    }

    @Override
    public UpdateProductDTO getProduct(String productCode) {
        Product product = productRepository.findById(productCode).get();
        UpdateProductDTO updateProduct = new UpdateProductDTO(
                product.getProductCode(),
                product.getProductName(),
                product.getPrice(),
                product.getCurrency(),
                product.getDiscount(),
                product.getDimension(),
                product.getUnit()
        );
        return updateProduct;
    }

    @Override
    public void updateProduct(UpdateProductDTO dto) {
        Product update = new Product(
                dto.getProductCode(),
                dto.getProductName(),
                dto.getPrice(),
                dto.getCurrency(),
                dto.getDiscount(),
                dto.getDimension(),
                dto.getUnit()
        );
        productRepository.save(update);
        System.out.println("[Update Product : ] "+update);
    }

    @Override
    public void insertProduct(InsertProductDTO dto) {
        Product insert = new Product(
                dto.getProductCode(),
                dto.getProductName(),
                dto.getPrice(),
                dto.getCurrency(),
                dto.getDiscount(),
                dto.getDimension(),
                dto.getUnit()
        );
        productRepository.save(insert);
        System.out.println("[Insert Product : ] "+insert);
    }

    @Override
    public ProductGridDTO getProductById(String productCode) {
        Product getProduct = productRepository.findById(productCode).get();
        ProductGridDTO product = new ProductGridDTO(
                getProduct.getProductCode(),
                getProduct.getProductName(),
                getProduct.getPrice(),
                getProduct.getCurrency(),
                getProduct.getDiscount(),
                getProduct.getDimension(),
                getProduct.getUnit(),
                getProduct.getImagePath()
        );
        return product;
    }
}
