package com.nhnacademy.springmvc.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.springmvc.domain.User;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class LogoutControllerTest {
  private MockMvc mockMvc;
  private MockHttpSession session;
  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(new LogoutController()).build();
    session = mock(MockHttpSession.class);
    User user = mock(User.class);
    session.setAttribute("userSession", user);
  }

  @Test
  void getLogoutTest() throws Exception{
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/cs/logout")
        .session(session))
        .andReturn();

    HttpSession actualSession = mvcResult.getRequest().getSession();
    assertTrue(Objects.isNull(actualSession.getAttribute("userSession")));
  }
}