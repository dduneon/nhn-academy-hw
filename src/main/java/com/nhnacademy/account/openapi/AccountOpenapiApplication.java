package com.nhnacademy.account.openapi;

import com.nhnacademy.account.openapi.domain.AccountClientService;
import com.nhnacademy.account.openapi.domain.AccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@Slf4j
@ConfigurationPropertiesScan
@SpringBootApplication
public class AccountOpenapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountOpenapiApplication.class, args);
	}

	@Autowired
	AccountClientService accountClientService;


	@Bean
	ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener() {
		return event -> {
			accountClientService.createAccount(new AccountRequest("김준현", 100000));
			accountClientService.createAccount(new AccountRequest("김민서", 100000));
			accountClientService.createAccount(new AccountRequest("김갑식", 100000));
			accountClientService.createAccount(new AccountRequest("김철수", 100000));
			accountClientService.getAccounts()
					.forEach(it -> log.info("multi: {}", it));
			// id는 Auto increment 속성을 적용하여 1부터 차례차례 올라갑니당..!
			accountClientService.deleteAccount(1L);
			accountClientService.deleteAccount(2L);
			accountClientService.deleteAccount(3L);
			accountClientService.deleteAccount(4L);
		};
	}
}
