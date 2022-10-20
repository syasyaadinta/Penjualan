package com.tes.Penjualan.controller;

import com.tes.Penjualan.dto.report.ReportDTO;
import com.tes.Penjualan.dto.transaction.TransactionDTO;
import com.tes.Penjualan.service.TransDetailService;
import com.tes.Penjualan.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/")
public class TransactionController {

    @Autowired
    private TransService transService;

    @Autowired
    private TransDetailService transDetailService;

    // -- GET MY RESERVATION
    @GetMapping("/my-cart")
    public String myCart (@RequestParam(defaultValue = "1") Integer page,
                          Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<TransactionDTO> cart = transService.getCart(page, username);
        System.out.println("[My Cart : ] "+cart);

        BigDecimal total = new BigDecimal(0);
        for (TransactionDTO dto: cart) {
            total = total.add(dto.getSubTotal());
        }

        long totalPages = transService.getTotalPages(username);

        model.addAttribute("type", "confirm");
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("breadCrumbs", "Confirm Transaction");

        return "transaction/my-cart";
    }

    @GetMapping("/my-cart/confirm")
    public String confirm(@Valid @ModelAttribute("cart") TransactionDTO dto,
                          @RequestParam(defaultValue = "") BigDecimal total,
                          Model model)
    {
        System.out.println("[Transaction DTO : ]"+dto);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("type", "confirm");
        transService.updateTransaction(dto, username, total);
        return "redirect:/my-cart";
    }

    @GetMapping("/report")
    public String list(@RequestParam(defaultValue = "1")Integer page,
                       Model model){

        List<ReportDTO> grid = transDetailService.getAll(page);
        for(ReportDTO value: grid){
            System.out.println("report dto: "+value);
        }

        long totalPages = transDetailService.getTotalPages();

        model.addAttribute("breadCrumbs","Report");
        model.addAttribute("report",grid);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage", page);

        return "report/report-list";
    }
}
