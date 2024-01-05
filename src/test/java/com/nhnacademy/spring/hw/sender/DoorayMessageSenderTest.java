package com.nhnacademy.spring.hw.sender;


import static org.mockito.Mockito.when;

import com.nhnacademy.spring.hw.service.MessageSendService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DoorayMessageSenderTest {

  @InjectMocks
  private MessageSendService messageSendService;

  @Mock
  private MessageSender messageSender;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testDoorayMessageSender() {
    when(messageSender.sendMessage("name", "message")).thenReturn(true);
    boolean actual = messageSendService.doSendMessage("name", "message");
    Assertions.assertThat(actual).isEqualTo(true);
  }

  @Test
  void testSendMessageWithNullParameters() {
    // messageSender.sendMessage가 특정 매개변수에 대해 RuntimeException을 throw하도록 설정
    when(messageSender.sendMessage(null, null)).thenThrow(new RuntimeException("Invalid parameters"));

    boolean actual = messageSendService.doSendMessage(null, null);
    Assertions.assertThat(actual).isEqualTo(false);
  }

  @Test
  void testSendMessageWithEmptyParameters() {
    // messageSender.sendMessage가 특정 매개변수에 대해 RuntimeException을 throw하도록 설정
    when(messageSender.sendMessage("", "")).thenThrow(new RuntimeException("Invalid parameters"));

    boolean actual = messageSendService.doSendMessage("", "");
    Assertions.assertThat(actual).isEqualTo(false);
  }
}
