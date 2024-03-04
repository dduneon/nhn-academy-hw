package com.nhnacademy.account.backend.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.account.backend.entity.Account;
import jdk.net.SocketFlow.Status;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class AccountRestControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Test
  @Order(1)
  void getAllAccounts() throws Exception{
    mockMvc.perform(get("/accounts"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name", equalTo("김준현")));
  }

  @Test
  @Order(2)
  void getAccount() throws Exception{
    mockMvc.perform(get("/accounts/{id}", 9))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name", equalTo("김준현")));
  }

  @Test
  @Order(3)
  void postAccount() throws Exception{
    ObjectMapper objectMapper = new ObjectMapper();
    Account account = new Account(1L, "테스트용", 10000);
    mockMvc.perform(post("/accounts")
        .content(objectMapper.writeValueAsString(account))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  @Order(4)
  void deleteAccount() throws Exception{
    mockMvc.perform(delete("/accounts/{id}", 1L))
        .andExpect(status().isOk());
  }
}