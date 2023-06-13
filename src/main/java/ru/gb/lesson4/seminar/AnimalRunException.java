package ru.gb.lesson4.seminar;

public class AnimalRunException extends AnimalException {

  public AnimalRunException(String message, String name, int distance) {
    super(message, name, distance);
  }
}
