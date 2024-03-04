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
			// id는 Auto increment 속성을 적용하여 1부터 차례차례 올라가게 설정해뒀기 때문에, 데이터베이스에서 확인 후 delete문을 테스트해주시면 감사하겠습니다.
			// 현재는 9, 10, 11, 12 라는 ID값을 가진 계좌가 데이터베이스에 존재하는 상태입니다.
			accountClientService.deleteAccount(9L);
			accountClientService.deleteAccount(10L);
			accountClientService.deleteAccount(11L);
			accountClientService.deleteAccount(12L);
			log.info("after delete --------------");
			accountClientService.getAccounts()
					.forEach(it -> log.info("multi: {}", it));
		};
	}
}
