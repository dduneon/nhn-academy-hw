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
}
