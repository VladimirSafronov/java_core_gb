package ru.gb.lesson4.seminar;

public class Cat extends Animal {

  public Cat(String name, int distance) {
    super(name, distance);
  }

  /**
   *
   * @param distance
   * @throws AnimalSwimException
   */
  @Override
  void swim(int distance) throws AnimalSwimException {
    throw new AnimalSwimException("Кот не умеет плавать.", name, distance);
  }

  /**
   *
   * @param distance
   * @throws AnimalRunException
   */
  @Override
  void run(int distance) throws AnimalRunException {
    if (distance < this.distance) {
      this.distance -= distance;
    } else {
      throw new AnimalRunException("Коту тяжело далеко бежать.", name, distance);
    }
  }
}
