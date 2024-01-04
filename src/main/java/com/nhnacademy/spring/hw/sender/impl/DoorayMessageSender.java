package com.nhnacademy.spring.hw.sender.impl;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.spring.hw.annotation.CheckExecutionTime;
import com.nhnacademy.spring.hw.sender.MessageSender;

public class DoorayMessageSender implements MessageSender {
  private DoorayHookSender doorayHookSender;

  public DoorayMessageSender(DoorayHookSender doorayHookSender) {
    this.doorayHookSender = doorayHookSender;
  }

  @CheckExecutionTime
  @Override
  public boolean sendMessage(String name, String message) {
    doorayHookSender.send(DoorayHook.builder()
        .botName(name)
        .text(message)
        .build());

    return true;
  }
}
