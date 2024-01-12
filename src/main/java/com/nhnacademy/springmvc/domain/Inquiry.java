package com.nhnacademy.springmvc.domain;

import java.time.LocalDateTime;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
public class Inquiry {
  @NotBlank
  @Length(min=2, max=200)
  private String title;
  @NotBlank
  private String category;
  @NotBlank
  @Length(min=0, max=40000)
  private String comment;
  @NotBlank
  private LocalDateTime created;
  @NotBlank
  private String writer;
  private String attachment;

  private boolean isResponse;
  // todo change type
}
