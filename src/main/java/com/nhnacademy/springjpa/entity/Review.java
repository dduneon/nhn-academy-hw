package com.nhnacademy.springjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Review")
public class Review {
  @Id
  @Column(name = "review_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long reviewId;
  @Column(name = "user_id")
  private String userId;
  @Column(name = "product_id")
  private long productId;
  @Column(name = "Rating")
  private int rating;
  @Column(name = "Comment")
  private String comment;

}
