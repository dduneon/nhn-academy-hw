package com.nhnacademy.springmvc.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Setter
@Getter
public class InquiryPostRequest {
  @NotBlank
  private String author;
  @NotBlank
  @Length(min=2, max=200)
  private String title;
  @NotBlank
  private String category;
  @NotBlank
  @Length(min=0, max=40000)
  private String comment;
  private MultipartFile attachment;

}
