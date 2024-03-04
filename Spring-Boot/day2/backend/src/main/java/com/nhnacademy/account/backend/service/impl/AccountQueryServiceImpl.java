package com.nhnacademy.account.backend.service.impl;

import com.nhnacademy.account.backend.entity.Account;
import com.nhnacademy.account.backend.exception.AccountNotFoundException;
import com.nhnacademy.account.backend.repository.AccountRepository;
import com.nhnacademy.account.backend.service.AccountQueryService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountQueryServiceImpl implements AccountQueryService {
  private final AccountRepository accountRepository;

  @Override
  public List<Account> readAllAccounts() {
    return accountRepository.findAll();
  }

  @Override
  public Account readAccountById(Long accountId) {
    Optional<Account> account = accountRepository.findById(accountId);
    if(account.isEmpty())
      throw new AccountNotFoundException(String.format("존재하지 않는 Account Id(%d)입니다", accountId));

    return account.get();
  }
}
