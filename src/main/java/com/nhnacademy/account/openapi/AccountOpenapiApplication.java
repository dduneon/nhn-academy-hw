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
			accountClientService.createAccount(new AccountRequest("3333058861", 100000));
			accountClientService.getAccounts()
					.forEach(it -> log.info("multi: {}", it));

			log.info("single: {}", accountClientService.getAccount(2L));
		};
	}
}
