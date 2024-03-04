package com.nhnacademy.spring.hw.sender;


import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.spring.hw.sender.impl.DoorayMessageSender;
import com.nhnacademy.spring.hw.service.MessageSendService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DoorayMessageSenderTest {

  @InjectMocks
  private DoorayMessageSender doorayMessageSender;

  @Mock
  private DoorayHookSender doorayHookSender;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testDoorayMessageSender() {
    Assertions.assertTrue(doorayMessageSender.sendMessage("name", "message"));
  }

  @Test
  void testSendMessageWithNullParameters() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> doorayMessageSender.sendMessage(null, null));
  }

  @Test
  void testSendMessageWithEmptyParameters() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> doorayMessageSender.sendMessage("", ""));
  }

  @Test
  void testSendMethodCall() {
    doorayMessageSender.sendMessage("test", "test");
    Mockito.verify(doorayHookSender, Mockito.atLeastOnce()).send(Matchers.any());
  }
}