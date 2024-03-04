package com.nhnacademy.springmvc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.InquiryNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.service.AdminAnswerService;
import com.nhnacademy.springmvc.service.AdminInquiryService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

class AdminAnswerControllerTest {
  private MockMvc mockMvc;
  private AdminInquiryService adminInquiryService;
  private AdminAnswerService adminAnswerService;

  @BeforeEach
  void setUp() {
    adminInquiryService = mock(AdminInquiryService.class);
    adminAnswerService = mock(AdminAnswerService.class);

    AdminAnswerController controller = new AdminAnswerController(adminInquiryService, adminAnswerService);

    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

@Test
  void getAdminAnswerNotFoundTest() throws Exception{
    when(adminInquiryService.getInquiryById(anyLong())).thenReturn(null);
    Throwable th = catchThrowable(() ->
        mockMvc.perform(MockMvcRequestBuilders.get("/cs/admin/answer/{inquiryId}", anyLong())));

    assertThat(th).isInstanceOf(NestedServletException.class)
        .hasCauseInstanceOf(InquiryNotFoundException.class);
  }

  @Test
  void getAdminAnswerSuccessTest() throws Exception {
    Inquiry inquiry = new Inquiry(1, "author", "title", "category", "content", null, LocalDateTime.now(), false);
    when(adminInquiryService.getInquiryById(1)).thenReturn(inquiry);
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/cs/admin/answer/{inquiryId}", 1)).andExpect(view().name("admin/answer"))
            .andReturn();

    Optional<Inquiry> resultInquiry = Optional.ofNullable(mvcResult.getModelAndView())
            .map(ModelAndView::getModel)
                .map(m -> m.get("inquiry"))
                    .map(Inquiry.class::cast);

    assertThat(resultInquiry).isPresent();
    assertThat(resultInquiry.get().getId()).isEqualTo(inquiry.getId());
  }

  @Test
  void postAdminAnswerValidationFailedTest() {
    MockHttpSession session = mock(MockHttpSession.class);
    when(session.getAttribute("userSession")).thenReturn(new User("testId", "testPassword", "testName", Role.Customer));
    Throwable th = catchThrowable(() ->
        mockMvc.perform(MockMvcRequestBuilders.post("/cs/admin/answer")
            .param("author", "")
            .param("inquiryId", "")
            .param("answer", "")
                .session(session))
            .andDo(print()));

    assertThat(th).isInstanceOf(NestedServletException.class)
        .hasCauseInstanceOf(ValidationFailedException.class);
  }

  @Test
  void postAdminAnswerTest() throws Exception{
    MockHttpSession session = mock(MockHttpSession.class);
    when(session.getAttribute("userSession")).thenReturn(new User("testId", "testPassword", "testName", Role.Customer));

    Mockito.doNothing().when(adminInquiryService).modifyAnsweredStatus(1, "testId");
    Mockito.doNothing().when(adminAnswerService).addInquiryAnswer(1, ArgumentMatchers.eq(any()));


    mockMvc.perform(MockMvcRequestBuilders.post("/cs/admin/answer")
            .param("author", "testAuthor")
            .param("inquiryId", "1")
            .param("answer", "testAnswer")
            .session(session))
        .andExpect(redirectedUrl("/cs/admin"))
        .andReturn();
  }

  @Test
  void downloadFile() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.post("/cs/admin/answer/download")
        .param("downloadFilename", "yourFileName")
        .param("downloadInquiryId", "1")
        .param("downloadInquiryAuthor", "yourAuthor"))
        .andExpect(status().isOk());
  }
}