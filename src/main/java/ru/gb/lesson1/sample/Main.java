package ru.gb.lesson1.sample;

import ru.gb.lesson1.regular.Decorator;
import ru.gb.lesson1.regular.OtherClass;

public class Main {

  public static void main(String[] args) {
    System.out.println(Decorator.decorate(OtherClass.sum(6, 2)));
    System.out.println(Decorator.decorate(OtherClass.sub(6, 2)));
    System.out.println(Decorator.decorate(OtherClass.div(6, 2)));
    System.out.println(Decorator.decorate(OtherClass.mult(6, 2)));
  }
}
