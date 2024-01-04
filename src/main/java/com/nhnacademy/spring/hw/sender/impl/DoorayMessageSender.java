package com.nhnacademy.spring.hw.sender.impl;

import com.nhnacademy.spring.hw.sender.MessageSender;

public class DoorayMessageSender implements MessageSender {
  @Override
  public boolean sendMessage() {
    System.out.println("send");
    return false;
  }
}
