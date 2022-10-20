package com.tes.Penjualan.controller;

import com.tes.Penjualan.dto.product.InsertProductDTO;
import com.tes.Penjualan.dto.product.ProductGridDTO;
import com.tes.Penjualan.dto.product.UpdateProductDTO;
import com.tes.Penjualan.dto.transaction.InsertTransactionDTO;
import com.tes.Penjualan.service.ProductService;
import com.tes.Penjualan.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private TransService transService;

    // -- GET ALL PRODUCT
    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String productCode,
                        Model model)
    {
        List<ProductGridDTO> grid = productService.getGrid(page, productCode);
        long totalPages = productService.getTotalPages(productCode);

        model.addAttribute("grid", grid);
        model.addAttribute("currentPage", page);
        model.addAttribute("productCode", productCode);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("breadCrumbs", "Product Index");
        return "product/product-index";
    }

    // -- GET PRODUCT BEFORE UPDATE
    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) String productCode,
                             Model model) {
        System.out.println("[Product Code UpsertForm : ] "+productCode);

        if (productCode != null){
            UpdateProductDTO dto = productService.getProduct(productCode);
            System.out.println("[DTO : ] "+dto);

            model.addAttribute("product",dto);
            model.addAttribute("type", "update");
            model.addAttribute("breadCrumbs", "Product Index / Update Product");
        } else {
            InsertProductDTO dto = new InsertProductDTO();
            model.addAttribute("product",dto);
            model.addAttribute("type", "insert");
            model.addAttribute("breadCrumbs", "Product Index / Insert Product");
        }
        return "product/product-form";
    }

    // -- UPDATE PRODUCT
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("product") UpdateProductDTO dto,
                         BindingResult bindingResult,
                         Model model)
    {
        System.out.println("[DTO update :] "+dto);
        if (bindingResult.hasErrors()){
            model.addAttribute("type", "update");
            model.addAttribute("breadCrumbs", "Product Index / Update Product");
            return "product/product-form";
        } else {
            productService.updateProduct(dto);
            return "redirect:/product/index";
        }
    }

    // -- INSERT NEW PRODUCT
    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("product") InsertProductDTO dto,
                         BindingResult bindingResult,
                         Model model)
    {
        System.out.println("[DTO insert :] "+dto);
        if (bindingResult.hasErrors()) {
            model.addAttribute("type", "insert");
            model.addAttribute("breadCrumbs", "Product Index / Insert Product");
            return "product/product-form";
        } else {
            productService.insertProduct(dto);
            return "redirect:/product/index";
        }
    }

    // -- GET PRODUCT DETAIL
    @GetMapping("/detail")
    public String detail(@RequestParam String productCode,
                         Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ProductGridDTO productGrid = productService.getProductById(productCode);
        InsertTransactionDTO newTrans = new InsertTransactionDTO();
        newTrans.setUsername(username);
        newTrans.setProductCode(productGrid.getProductCode());

        model.addAttribute("productGrid", productGrid);
        model.addAttribute("newTrans", newTrans);
        model.addAttribute("type", "buy");
        model.addAttribute("breadCrumbs", "Product Detail");
        return "product/product-detail";
    }

    @PostMapping("/buy")
    public String buy(@Valid @ModelAttribute("productGrid") InsertTransactionDTO dto,
                          BindingResult bindingResult,
                          Model model) {
        System.out.println("[ProductGridDTO DTO : ]"+dto);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

            model.addAttribute("type", "buy");
            model.addAttribute("breadCrumbs", "Buy Product");
            transService.buyProduct(dto, username);
            return "redirect:/product/index";
    }
}
