package com.tes.Penjualan.dao;

import com.tes.Penjualan.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
