package com.nhnacademy.account.backend.service;

import com.nhnacademy.account.backend.entity.Account;
import java.util.List;

public interface AccountQueryService {

  List<Account> readAllAccounts();

  Account readAccountById(Long accountId);
}
