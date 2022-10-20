package com.tes.Penjualan.controller;

import com.tes.Penjualan.dto.account.AccountRegisterDTO;
import com.tes.Penjualan.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/loginPage")
    public String loginPage(){
        return "account/login-page";
    }

    // -- ACCESS DENIED IF YOU DONT HAVE ACCESS
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "account/access-denied";
    }

    // -- REGISTER FORM
    @GetMapping("/registerForm")
    public String registerForm(Model model)
    {
        AccountRegisterDTO dto = new AccountRegisterDTO();
        model.addAttribute("account", dto);
        return "account/register-form";
    }

    // -- CUSTOMER REGISTER
    @PostMapping("/register")
    public  String register(@Valid @ModelAttribute("account") AccountRegisterDTO dto,
                            BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()){
            return "account/register-form";
        }
        accountService.registerCustomerAccount(dto);
        return "redirect:/account/loginPage";
    }
}
