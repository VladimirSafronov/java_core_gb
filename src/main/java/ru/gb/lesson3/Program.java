package ru.gb.lesson3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Program {

  public static void main(String[] args) {
    List<Worker> workers;
    workers = new ArrayList<>();
    workers.add(new FixWorker("Petr", "developer", 500));
    workers.add(new FixWorker("Anna", "HR", 400));
    workers.add(new FixWorker("Gleb", "analytic", 550));
    workers.add(new HourlyWorker("Victoria", "HR", 2.1));
    workers.add(new HourlyWorker("Elizaveta", "analytic", 2.5));
    workers.add(new HourlyWorker("Ivan", "developer", 3.1));

    printList(workers);
    System.out.println("-----------");
    Collections.sort(workers);
    printList(workers);

    System.out.println("-----------");
    workers.sort(new DepartmentComparator());
    printList(workers);

    System.out.println("-----------");
    workers.sort(new SalaryComparator());
    printList(workers);

    System.out.println("-----------");
    AllWorkers obj = new AllWorkers();
    obj.printWorkers(obj.allWorkers);
  }

  public static void printList(List<Worker> list) {
    for (Worker worker : list) {
      System.out.println(worker);
    }
  }
}
