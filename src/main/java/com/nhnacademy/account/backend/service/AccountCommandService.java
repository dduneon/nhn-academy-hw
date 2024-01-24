package com.nhnacademy.account.backend.service;

import com.nhnacademy.account.backend.domain.AccountRequest;
import com.nhnacademy.account.backend.entity.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountCommandService {
  Account createAccount(AccountRequest request);
  void deleteAccount(Long accountId);
}
