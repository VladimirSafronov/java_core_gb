package ru.gb.lesson3;

import java.util.Comparator;

public class DepartmentComparator implements Comparator<Worker> {

  @Override
  public int compare(Worker o1, Worker o2) {
    return o1.department.compareTo(o2.department);
  }
}
