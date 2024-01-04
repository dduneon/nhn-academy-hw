package com.nhnacademy.spring.hw;

import com.nhnacademy.spring.hw.sender.MessageSender;
import com.nhnacademy.spring.hw.service.MessageSendService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathXmlMain {
  public static void main(String[] args){
    String name, message;

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      System.out.print("Input your name to send: ");
      name = br.readLine();

      System.out.print("Input message to send: ");
      message = br.readLine();
    } catch (IOException e) {
      throw new RuntimeException("Error occured while read string");
    }

    try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
      MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
      messageSendService.doSendMessage(name, message);
    }

  }
}