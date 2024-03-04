package com.nhnacademy.springmvc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.PasswordNotCorrectException;
import com.nhnacademy.springmvc.exception.UserNotFoundException;
import com.nhnacademy.springmvc.repository.UserRepository;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

class LoginControllerTest {
  private UserRepository userRepository;
  private MockMvc mockMvc;
  private MockHttpSession session;
  private User user;

  @BeforeEach
  void setUp() {
    userRepository = mock(UserRepository.class);
    mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(userRepository)).build();
    session = mock(MockHttpSession.class);

    user = new User("testId", "testPassword", "testName", Role.Customer);
    when(session.getAttribute("userSession")).thenReturn(user);
  }

  @Test
  void alreadyLoginTest() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.get("/cs/login")
        .session(session))
        .andExpect(redirectedUrl("/cs"));
  }

  @Test
  void getLoginPageTest() throws Exception{
    MockHttpSession nullSession = mock(MockHttpSession.class);
    when(nullSession.getAttribute("userSession")).thenReturn(null);

    mockMvc.perform(MockMvcRequestBuilders.get("/cs/login")
            .session(nullSession))
        .andExpect(view().name("signin"));
  }

  @Test
  void doLoginUserNotFoundTest() {
    when(userRepository.isExist("notExistUserId")).thenReturn(false);

    Throwable th = catchThrowable(() -> mockMvc.perform(MockMvcRequestBuilders.post("/cs/login")
        .param("id", "notExistUserId")
        .param("password", "testPassword")
        .session(session)));

    assertThat(th).isInstanceOf(NestedServletException.class)
        .hasCauseInstanceOf(UserNotFoundException.class);
  }

  @Test
  void doLoginPasswordNotCorrectTest() {
    when(userRepository.isExist("testId")).thenReturn(true);
    when(userRepository.findById("testId")).thenReturn(user);

    Throwable th = catchThrowable(() -> mockMvc.perform(MockMvcRequestBuilders.post("/cs/login")
        .param("id", "testId")
        .param("password", "testPasswordNotCorrect")
        .session(session)));

    assertThat(th).isInstanceOf(NestedServletException.class)
        .hasCauseInstanceOf(PasswordNotCorrectException.class);
  }

  @Test
  void loginSuccessTestUser() throws Exception{
    when(userRepository.isExist("testId")).thenReturn(true);
    when(userRepository.findById("testId")).thenReturn(user);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cs/login")
        .param("id", "testId")
        .param("password", "testPassword")
        .session(session))
        .andExpect(redirectedUrl("/cs"))
        .andReturn();

    User actualUser = (User) mvcResult.getRequest().getSession().getAttribute("userSession");
    assertTrue(Objects.nonNull(actualUser));
    assertEquals(user.getId(), actualUser.getId());
  }

  @Test
  void loginSuccessTestAdmin() throws Exception{
    User admin = new User("testId", "testPassword", "testName", Role.Admin);
    when(userRepository.isExist("testId")).thenReturn(true);
    when(userRepository.findById("testId")).thenReturn(admin);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cs/login")
            .param("id", "testId")
            .param("password", "testPassword")
            .session(session))
        .andExpect(redirectedUrl("/cs/admin"))
        .andReturn();

    User actualUser = (User) mvcResult.getRequest().getSession().getAttribute("userSession");
    assertTrue(Objects.nonNull(actualUser));
    assertEquals(user.getId(), actualUser.getId());
  }
}