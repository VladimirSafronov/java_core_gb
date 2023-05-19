package ru.gb.lesson1.regular;

/**
 * Декоратор. Он декорирует, то есть, накладывает на результат декорации.
 */


public class Decorator {

  /**
   * Метод декорирует число, добавляя к нему строку при помощи функции форматирования строк
   */
  public static String decorate(int a) {

    return String.format("Here is your number: %d.", a);
  }
}
