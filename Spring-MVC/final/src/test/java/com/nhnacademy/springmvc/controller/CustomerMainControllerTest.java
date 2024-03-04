package com.nhnacademy.springmvc.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.service.CustomerInquiryService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class CustomerMainControllerTest {
  private CustomerInquiryService customerInquiryService;
  private MockMvc mockMvc;
  private MockHttpSession userSession;
  private MockHttpSession adminSession;
  private User user;
  private User admin;
  @BeforeEach
  void setUp() {
    customerInquiryService = mock(CustomerInquiryService.class);
    mockMvc = MockMvcBuilders.standaloneSetup(new CustomerMainController(customerInquiryService)).build();

    userSession = mock(MockHttpSession.class);
    user = new User("testId", "testPassword", "testName", Role.Customer);
    when(userSession.getAttribute("userSession")).thenReturn(user);

    adminSession = mock(MockHttpSession.class);
    admin = new User("testId", "testPassword", "testName", Role.Admin);
    when(adminSession.getAttribute("userSession")).thenReturn(admin);
  }
  @Test
  void getInquiryAdminAccessDeniedTest() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.get("/cs")
        .param("category", "전체 보기")
        .session(adminSession))
        .andExpect(redirectedUrl("/cs/admin"));
  }

  @Test
  void getInquiryTest() throws Exception{
    List<Inquiry> inquiries = List.of(
        new Inquiry(1, "author", "title", "category", "content", null, LocalDateTime.now(), false),
        new Inquiry(2, "author", "title", "category", "content", null, LocalDateTime.now(), false)
    );
    when(customerInquiryService.getUserInquiriesByCategory("testId", "전체 보기")).thenReturn(inquiries);
    mockMvc.perform(MockMvcRequestBuilders.get("/cs")
            .param("category", "전체 보기")
            .session(userSession))
        .andExpect(model().attribute("currentCategory", "전체 보기"))
        .andExpect(model().attribute("user", user))
        .andExpect(model().attribute("inquiries", inquiries))
        .andExpect(view().name("main"));

  }

}