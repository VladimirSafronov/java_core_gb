package ru.gb.lesson4.seminar;

import java.util.Random;

public class Program {

  public static void main(String[] args) {
    Animal animal = new Cat("Vaska", 100);
    Random random = new Random();

    for (int i = 0; i < 10; i++) {
      int j = random.nextInt(2);
      try {
        switch (j) {
          case 0:
            animal.swim(i * 10);
            break;
          case 1:
            animal.run(i * 10);
            System.out.printf("Кошка пробежала %d метров", i * 10);
            break;
        }
      } catch (AnimalRunException ex) {
        System.out.printf("Ошибка при попытке %s пробежать %d метров \n %s\n",
            ex.getName(), ex.getDistance(), ex.getMessage());
      } catch (AnimalSwimException ex) {
        System.out.printf("Ошибка при попытке %s проплыть %d метров \n %s\n",
            ex.getName(), ex.getDistance(), ex.getMessage());
      }
    }
  }
}
