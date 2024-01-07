package com.nhnacademy.spring.hw.model;

public class WaterTariff {
  private int seq;
  private String city;
  private String sector;
  private int level;
  private int sectionStart;
  private int sectionEnd;
  private int unitPrice;
  private int defaultFee;

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

  public int getDefaultFee() {
    return defaultFee;
  }

  public boolean isIncludeSection(int usage) {
    return (sectionStart <= usage && sectionEnd >= usage);
  }
}
