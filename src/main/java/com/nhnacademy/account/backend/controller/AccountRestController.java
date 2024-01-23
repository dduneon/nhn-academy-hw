package com.nhnacademy.account.backend.controller;

import com.nhnacademy.account.backend.domain.AccountRequestDTO;
import com.nhnacademy.account.backend.entity.Account;
import com.nhnacademy.account.backend.exception.AccountNotFoundException;
import com.nhnacademy.account.backend.service.AccountCommandService;
import com.nhnacademy.account.backend.service.AccountQueryService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountRestController {
  private final AccountCommandService accountCommandService;
  private final AccountQueryService accountQueryService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Account> getAllAccounts() {
    List<Account> accounts = accountQueryService.readAllAccounts();
    for(Account a: accounts) {
      log.debug("getAllAccounts(): account -> {}, {}", a.getId(), a.getName());
    }
    return accountQueryService.readAllAccounts();
  }

  @GetMapping("/{accountId}")
  @ResponseStatus(HttpStatus.OK)
  public Account getAccount(@PathVariable(name = "accountId") Long accountId) {
    Account account = accountQueryService.readAccountById(accountId);
    log.debug("getAccount(): account -> {}, {}", account.getId(), account.getName());
    return accountQueryService.readAccountById(accountId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void postAccount(@Valid @RequestBody AccountRequestDTO request, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      throw new ValidationException("입력값 검증이 실패하였습니다");
    }

    accountCommandService.createAccount(request);
  }

  @DeleteMapping("/{accountId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteAccount(@PathVariable(name = "accountId") Long accountId) {
    accountCommandService.deleteAccount(accountId);
  }

  @ExceptionHandler(AccountNotFoundException.class)
  public ModelMap handleException(Exception ex, HttpServletResponse response) {
    ModelMap modelMap = new ModelMap("error", ex.getMessage());
    response.setStatus(404);
    return modelMap;
  }

  @ExceptionHandler(ValidationException.class)
  public ModelMap handleExceptionValidate(Exception ex, HttpServletResponse response) {
    ModelMap modelMap = new ModelMap("error", ex.getMessage());
    response.setStatus(403);
    return modelMap;
  }
}
