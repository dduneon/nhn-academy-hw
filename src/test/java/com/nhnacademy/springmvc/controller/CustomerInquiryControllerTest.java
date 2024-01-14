package com.nhnacademy.springmvc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.service.CustomerInquiryService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

class CustomerInquiryControllerTest {
  private CustomerInquiryService customerInquiryService;
  private MockMvc mockMvc;
  private MockHttpSession session;

  @BeforeEach
  void setUp() {
    customerInquiryService = mock(CustomerInquiryService.class);
    mockMvc = MockMvcBuilders.standaloneSetup(new CustomerInquiryController(customerInquiryService)).build();

    session = mock(MockHttpSession.class);
    when(session.getAttribute("userSession")).thenReturn(new User("testId", "testPassword", "testName",
        Role.Customer));
  }

  @Test
  void getInquiryTest() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.get("/cs/inquiry")
        .session(session))
        .andExpect(model().attribute("user", session.getAttribute("userSession")))
        .andExpect(view().name("customer/inquiry"));
  }

  @Test
  void createInquiryValidationTest() {
    Throwable th = catchThrowable(() -> mockMvc.perform(MockMvcRequestBuilders.post("/cs/inquiry")
        .param("author", "")
        .param("title", "")
        .param("category", "")
        .param("content", "")));

    assertThat(th).isInstanceOf(NestedServletException.class)
        .hasCauseInstanceOf(ValidationFailedException.class);
  }

  @Test
  void createInquiryTest() throws Exception{
    doNothing().when(customerInquiryService).addUserInquiry(any(), any());
    mockMvc.perform(MockMvcRequestBuilders.post("/cs/inquiry")
        .param("author", "author")
        .param("title", "title")
        .param("category", "category")
        .param("content", "content"))
        .andExpect(redirectedUrl("/cs"));
  }

  @Test
  void getInquiryDetail() throws Exception{
    Inquiry inquiry = new Inquiry(1, "author", "title", "category", "content", null, LocalDateTime.now(), false);
    Answer answer = new Answer(1, "adminId", "content");

    when(customerInquiryService.getSpecifiedInquiry("testId", 1)).thenReturn(inquiry);
    when(customerInquiryService.getSpecifiedAnswer(1)).thenReturn(answer);

    mockMvc.perform(MockMvcRequestBuilders.get("/cs/inquiry/{inquiryId}", 1)
        .session(session))
        .andExpect(model().attribute("inquiry", inquiry))
        .andExpect(model().attribute("answer", answer))
        .andExpect(view().name("customer/inquiryDetail"));

  }
}