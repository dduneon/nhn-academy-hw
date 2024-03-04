package com.nhnacademy.springmvc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.service.AdminInquiryService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

class AdminMainControllerTest {
  private MockMvc mockMvc;
  private AdminInquiryService adminInquiryService;

  @BeforeEach
  void setUp(){
    adminInquiryService = mock(AdminInquiryService.class);

    mockMvc = MockMvcBuilders.standaloneSetup(new AdminMainController(adminInquiryService))
        .build();
  }

  @Test
  void getAdminMain() throws Exception {
    MockHttpSession session = mock(MockHttpSession.class);
    when(session.getAttribute("userSession")).thenReturn(new User("testId", "testPassword", "testName",
        Role.Customer));

    List<Inquiry> inquiries = List.of(
        new Inquiry(1, "author", "title", "category", "content", null, LocalDateTime.now(), false),
        new Inquiry(2, "author", "title", "category", "content", null, LocalDateTime.now(), false)
    );
    when(adminInquiryService.getNotRespondInquiries()).thenReturn(inquiries);

    mockMvc.perform(MockMvcRequestBuilders.get("/cs/admin")
            .session(session))
        .andExpect(view().name("admin/main"))
        .andExpect(model().attribute("notRespondInquiries", inquiries));
  }
}