package com.nhnacademy.spring.hw.service;

import com.nhnacademy.spring.hw.sender.MessageSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MessageSendServiceTest {
  @InjectMocks
  MessageSendService messageSendService;

  @Mock
  MessageSender messageSender;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void doSendMessageReturnTrueTest() {
    Mockito.when(messageSender.sendMessage("test", "test")).thenReturn(true);
    Assertions.assertTrue(messageSendService.doSendMessage("test", "test"));
  }

  @Test
  void doSendMessageReturnFalseTest() {
    Mockito.when(messageSender.sendMessage("test", "test")).thenReturn(false);
    Assertions.assertFalse(messageSendService.doSendMessage("test", "test"));
  }

  @Test
  void doSendMessageThrowExceptionTest() {
    Mockito.when(messageSender.sendMessage("test", "test")).thenThrow(IllegalArgumentException.class);
    Assertions.assertFalse(messageSendService.doSendMessage("test", "test"));
  }
}