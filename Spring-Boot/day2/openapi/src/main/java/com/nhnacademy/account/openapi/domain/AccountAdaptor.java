package com.nhnacademy.account.openapi.domain;

import java.util.List;

public interface AccountAdaptor {
  List<Account> getAccounts();
  Account getAccount(Long id);
  void createAccount(AccountRequest accountRequest);
  void deleteAccount(Long id);
}
