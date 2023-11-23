package com.jrcodecrew.codeschool.model;

public enum DayOfWeek {
  MONDAY(1),
  TUESDAY(2),
  WEDNESDAY(3),
  THURSDAY(4),
  FRIDAY(5),
  SATURDAY(6),
  SUNDAY(7);

  private final int dayNumber;

  DayOfWeek(int dayNumber) {
    this.dayNumber = dayNumber;
  }

  public int getDayNumber() {
    return dayNumber;
  }

  public static DayOfWeek getDayByNumber(int number) {
    for (DayOfWeek day : DayOfWeek.values()) {
      if (day.getDayNumber() == number) {
        return day;
      }
    }
    throw new IllegalArgumentException("Invalid day number: " + number);
  }
}
