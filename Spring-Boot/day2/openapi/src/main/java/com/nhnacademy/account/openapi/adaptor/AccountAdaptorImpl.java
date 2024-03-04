package com.nhnacademy.account.openapi.adaptor;

import com.nhnacademy.account.openapi.config.AccountAdaptorProperties;
import com.nhnacademy.account.openapi.domain.Account;
import com.nhnacademy.account.openapi.domain.AccountAdaptor;
import com.nhnacademy.account.openapi.domain.AccountRequest;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AccountAdaptorImpl implements AccountAdaptor {
  private final RestTemplate restTemplate;
  private final AccountAdaptorProperties accountAdaptorProperties;

  public AccountAdaptorImpl(RestTemplate restTemplate,
      AccountAdaptorProperties accountAdaptorProperties) {
    this.restTemplate = restTemplate;
    this.accountAdaptorProperties = accountAdaptorProperties;
  }

  public List<Account> getAccounts() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    ResponseEntity<List<Account>> exchange = restTemplate.exchange(
        accountAdaptorProperties.getAddress() + "/accounts",
        HttpMethod.GET,
        requestEntity,
        new ParameterizedTypeReference<List<Account>>() {

        });

    if(exchange.getStatusCode() != HttpStatus.OK)
      throw new RuntimeException();
    return exchange.getBody();
  }

  public Account getAccount(Long id) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    ResponseEntity<Account> exchange = restTemplate.exchange(
        accountAdaptorProperties.getAddress() + "/accounts/" + id,
        HttpMethod.GET,
        requestEntity,
        new ParameterizedTypeReference<Account>() {
        }
    );

    if(exchange.getStatusCode().isError())
      throw new RuntimeException();
    return exchange.getBody();
  }

  public void createAccount(AccountRequest accountRequest) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<AccountRequest> requestEntity = new HttpEntity<>(accountRequest, headers);
    ResponseEntity<String> exchange = restTemplate.exchange(
        accountAdaptorProperties.getAddress() + "/accounts",
        HttpMethod.POST,
        requestEntity,
        new ParameterizedTypeReference<>() {
        }
    );

    if(exchange.getStatusCode().isError())
      throw new RuntimeException();
  }

  public void deleteAccount(Long id) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    ResponseEntity<String> exchange = restTemplate.exchange(
        accountAdaptorProperties.getAddress() + "/accounts/" + id,
        HttpMethod.DELETE,
        requestEntity,
        new ParameterizedTypeReference<String>() {
        }
    );

    if(exchange.getStatusCode().isError())
      throw new RuntimeException();
  }
}
