package ru.gb.lesson3;

public class FixWorker extends Worker {

  public FixWorker(String name, String department, double salary) {
    super(name, department);
    this.salary = salary;
  }

  @Override
  double calcSalary() {
    return salary;
  }
}
