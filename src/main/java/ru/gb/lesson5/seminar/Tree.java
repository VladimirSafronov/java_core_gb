package ru.gb.lesson5.seminar;

import java.io.File;

public class Tree {

  /**
   * распечатываем в консоль иерархию папок и файлов
   * @param file начальный файл
   * @param indent отступ от края экрана
   * @param isLast является ли файл листом дерева
   */
  public static void print(File file, String indent, boolean isLast) {
    System.out.print(indent);
    if (isLast) {
      System.out.print("└─");
      indent += " ";
    } else {
      System.out.print("├─");
      indent += "│ ";
    }
    System.out.println(file.getName());

    File[] files = file.listFiles();
    if (files == null) {
      return;
    }

    int subDirTotal = 0;
    for (File f : files) {
      if (f.isDirectory()) {
        subDirTotal++;
      } else if (f.isFile()) { // не пропускаем, и рисуем обычные файлы
        print(f, indent, true);
      }
    }

    int subDirCounter = 0;
    for (File f : files) {
      if (f.isDirectory()) {
        print(f, indent, subDirCounter == subDirTotal - 1);
        subDirCounter++;
      }
    }
  }
}
