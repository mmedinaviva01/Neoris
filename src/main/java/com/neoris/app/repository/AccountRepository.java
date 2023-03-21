package com.neoris.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neoris.app.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
