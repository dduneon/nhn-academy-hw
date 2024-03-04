package com.nhnacademy.spring.hw.sender.impl;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.spring.hw.annotation.CheckExecutionTime;
import com.nhnacademy.spring.hw.sender.MessageSender;
import java.util.Objects;

public class DoorayMessageSender implements MessageSender {
  private DoorayHookSender doorayHookSender;

  public DoorayMessageSender(DoorayHookSender doorayHookSender) {
    this.doorayHookSender = doorayHookSender;
  }

  @CheckExecutionTime
  @Override
  public boolean sendMessage(String name, String message) {
    if(Objects.isNull(name) || Objects.isNull(message) || name.isEmpty() || message.isEmpty()) {
      throw new IllegalArgumentException("Name or Message must not be null or empty string");
    }

    doorayHookSender.send(DoorayHook.builder()
        .botName(name)
        .text(message)
        .build());

    return true;
  }
}
