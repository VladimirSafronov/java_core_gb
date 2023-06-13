package ru.gb.lesson4.seminar;

public abstract class Animal {
  protected final String name;
  protected int distance;

  public Animal(String name, int distance) {
    this.name = name;
    this.distance = distance;
  }

  abstract void swim(int distance) throws AnimalSwimException;
  abstract void run(int distance) throws AnimalRunException;
}
