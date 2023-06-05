package ru.gb.lesson3;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Worker> {

  @Override
  public int compare(Worker o1, Worker o2) {
    return (int) (o1.salary - o2.salary);
  }
}
