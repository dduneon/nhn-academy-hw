package com.nhnacademy.spring.hw;

import com.nhnacademy.spring.hw.sender.MessageSender;
import com.nhnacademy.spring.hw.service.MessageSendService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathXmlMain {
  public static void main(String[] args) {

    try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
      MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
      messageSendService.doSendMessage();
    }

  }
}