package com.nhnacademy.account.backend.service;

import com.nhnacademy.account.backend.domain.AccountRequestDTO;
import com.nhnacademy.account.backend.entity.Account;
import com.nhnacademy.account.backend.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface AccountCommandService {
  Account createAccount(AccountRequestDTO request);
  void deleteAccount(Long accountId);
}
