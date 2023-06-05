package ru.gb.lesson3;

public class AllWorkers {

  Worker[] allWorkers = new Worker[]{new FixWorker("Petr", "developer", 500),
      new FixWorker("Anna", "HR", 400),
      new FixWorker("Gleb", "analytic", 550),
      new HourlyWorker("Victoria", "HR", 1.8),
      new HourlyWorker("Elizaveta", "analytic", 2.5),
      new HourlyWorker("Ivan", "developer", 3.1)};

  public void printWorkers(Worker[] workers) {
    for (Worker worker : workers) {
      System.out.println(worker.toString());
    }
  }
}
