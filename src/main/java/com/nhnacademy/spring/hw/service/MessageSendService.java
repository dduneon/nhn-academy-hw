package com.nhnacademy.spring.hw.service;

import com.nhnacademy.spring.hw.sender.MessageSender;

public class MessageSendService {
  private MessageSender messageSender;

  public MessageSendService(MessageSender messageSender) {
    this.messageSender = messageSender;
  }

  public void doSendMessage(String name, String message) {
    System.out.println("---Sending ...---");
    messageSender.sendMessage(name, message);
    System.out.println("---Send Success---");
  }
}
