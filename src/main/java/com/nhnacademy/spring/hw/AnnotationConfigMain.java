package com.nhnacademy.spring.hw;

import com.nhnacademy.spring.hw.service.MessageSendService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationConfigMain {
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

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.spring.hw.config");
    MessageSendService service = context.getBean("messageSendService", MessageSendService.class);
    service.doSendMessage(name, message);
  }
}