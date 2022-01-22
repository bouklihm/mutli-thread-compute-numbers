package com.marou.computenumbers.model;


import lombok.Getter;

public class ConcurrentStatistic {

  private static final Object LOCK = new Object();

  @Getter
  private DataStatistic data = new DataStatistic(0, 0.0, 0L);

  public void plus(double x, int y) {
    synchronized (LOCK){
      data = data.plus(x, y);
    }
  }

  public void minus(double x, int y) {
    synchronized (LOCK){
      data = data.minus(x, y);
    }
  }
}
