package com.tes.Penjualan.service;

import com.tes.Penjualan.configuration.ApplicationUserDetails;
import com.tes.Penjualan.configuration.MvcSecurityConfig;
import com.tes.Penjualan.dao.AccountRepository;
import com.tes.Penjualan.dto.account.AccountRegisterDTO;
import com.tes.Penjualan.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerCustomerAccount(AccountRegisterDTO dto) {
        String hashPassword = passwordEncoder.encode(dto.getPassword());
        Account account = new Account(
                dto.getUsername(),
                hashPassword,
                "Customer");
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> nullableEntity = accountRepository.findById(username);
        Account account = nullableEntity.get();
        return new ApplicationUserDetails(account);
    }
}
