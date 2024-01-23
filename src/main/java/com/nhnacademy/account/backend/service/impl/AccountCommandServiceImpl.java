package com.nhnacademy.account.backend.service.impl;

import com.nhnacademy.account.backend.domain.AccountRequestDTO;
import com.nhnacademy.account.backend.entity.Account;
import com.nhnacademy.account.backend.exception.AccountNotFoundException;
import com.nhnacademy.account.backend.repository.AccountRepository;
import com.nhnacademy.account.backend.service.AccountCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountCommandServiceImpl implements AccountCommandService {
  private final AccountRepository accountRepository;

  @Override
  public Account createAccount(AccountRequestDTO request) {
    Account account = Account.builder()
        .name(request.getName())
        .balance(request.getBalance())
        .build();

    return accountRepository.save(account);
  }

  @Override
  public void deleteAccount(Long accountId) {
    if(!accountRepository.existsById(accountId))
      throw new AccountNotFoundException(String.format("존재하지 않는 Account Id(%d)입니다", accountId));

    accountRepository.deleteById(accountId);
  }
}
