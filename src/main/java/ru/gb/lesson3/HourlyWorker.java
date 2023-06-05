package ru.gb.lesson3;

public class HourlyWorker extends Worker {

  private static final double COEFFICIENT = 20.8 * 8;
  private final double hourRate;

  public HourlyWorker(String name, String department, double hourRate) {
    super(name, department);
    this.hourRate = hourRate;
    this.salary = calcSalary();
  }

  @Override
  double calcSalary() {
    return COEFFICIENT * hourRate;
  }
}
