package com.nhnacademy.student.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Student")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="student_id")
  private long id;

  @Column(name = "student_name")
  private String name;
  @Column(name = "student_email")
  private String email;
  @Column(name = "student_score")
  private int score;
  @Column(name = "student_comment")
  private String comment;

  @Builder
  public Student(String name, String email, int score, String comment) {
    this.name = name;
    this.email = email;
    this.score = score;
    this.comment = comment;
  }
}
