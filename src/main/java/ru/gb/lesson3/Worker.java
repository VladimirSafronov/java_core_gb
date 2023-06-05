package ru.gb.lesson3;


public abstract class Worker implements Comparable<Worker> {

  protected String name;
  protected String department;
  protected double salary;

  public Worker(String name, String department) {
    this.name = name;
    this.department = department;
  }

  abstract double calcSalary();

  public int compareTo(Worker o) {
    return this.name.compareTo(o.name);
  }

  public String getName() {
    return name;
  }

  public String getDepartment() {
    return department;
  }

  public double getSalary() {
    return salary;
  }

  @Override
  public String toString() {
    return "Worker{" +
        "name='" + name + '\'' +
        ", department='" + department + '\'' +
        ", salary=" + salary +
        '}';
  }
}
