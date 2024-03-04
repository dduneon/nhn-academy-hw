package com.nhnacademy.spring.hw.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WaterTariff {
  @JsonProperty("순번")
  private int seq;
  @JsonProperty("지자체명")
  private String city;
  @JsonProperty("업종")
  private String sector;
  @JsonProperty("단계")
  private int level;
  @JsonProperty("구간시작(세제곱미터)")
  private int sectionStart;
  @JsonProperty("구간끝(세제곱미터)")
  private int sectionEnd;
  @JsonProperty("구간금액(원)")
  private int unitPrice;
  @JsonProperty("단계별 기본요금(원)")
  private int defaultFee;

  public WaterTariff() {
  }

  public WaterTariff(int seq, String city, String sector, int level, int sectionStart,
      int sectionEnd,
      int unitPrice, int defaultFee) {
    this.seq = seq;
    this.city = city;
    this.sector = sector;
    this.level = level;
    this.sectionStart = sectionStart;
    this.sectionEnd = sectionEnd;
    this.unitPrice = unitPrice;
    this.defaultFee = defaultFee;
  }

  public int getSeq() {
    return seq;
  }

  public String getCity() {
    return city;
  }

  public String getSector() {
    return sector;
  }

  public int getLevel() {
    return level;
  }

  public int getSectionStart() {
    return sectionStart;
  }

  public int getSectionEnd() {
    return sectionEnd;
  }

  public int getUnitPrice() {
    return unitPrice;
  }

  public void setDefaultFee(String defaultFee) {
    this.defaultFee = (defaultFee != null && !defaultFee.isEmpty()) ? Integer.parseInt(defaultFee) : 0;
  }

  public boolean isIncludeSection(int usage) {
    return (sectionStart <= usage && sectionEnd >= usage);
  }
}
