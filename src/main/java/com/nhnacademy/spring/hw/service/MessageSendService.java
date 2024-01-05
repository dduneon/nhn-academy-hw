package com.nhnacademy.spring.hw.service;

import com.nhnacademy.spring.hw.sender.MessageSender;

public class MessageSendService {
  private MessageSender messageSender;

  public MessageSendService(MessageSender messageSender) {
    this.messageSender = messageSender;
  }

  public boolean doSendMessage(String name, String message) {
    System.out.println(">>>>> Sending ...");
    boolean result;
    try {
      result = messageSender.sendMessage(name, message);
    } catch (RuntimeException e) {
      System.out.println(">>>>> Send Failed (Name or Message is null or empty)");
      return false;
    }
    if (result)
      System.out.println(">>>>> Send Success");
    else {
      System.out.println(">>>>> Send Failed");
    }
    return result;
  }
}
