package com.nhnacademy.account.backend.repository;

import com.nhnacademy.account.backend.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
