package ru.gb.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Program {

  private static final int WIN_COUNT = 4;
  private static final int DANGER_COUNT = WIN_COUNT - 2;
  private static final char DOT_HUMAN = 'X';
  private static final char DOT_AI = '0';
  private static final char DOT_EMPTY = '.';

  private static final Scanner SCANNER = new Scanner(System.in);

  private static char[][] field;

  private static final Random random = new Random();

  //размерность игрового поля
  private static int fieldSizeX;
  private static int fieldSizeY;

  public static void main(String[] args) {
    while (true) {
      initialize();
      printField();
      while (true) {
        humanTurn();
        printField();
        if (gameCheck(DOT_HUMAN, "Вы победили!")) {
          break;
        }
        aiTurn();
        printField();
        if (gameCheck(DOT_AI, "Компьютер победил!")) {
          break;
        }
      }
      System.out.println("Желаете сыграть еще раз? (Y - да)");
      if (!SCANNER.next().equalsIgnoreCase("Y")) {
        break;
      }
    }
  }

  private static void initialize() {
    fieldSizeX = 5;
    fieldSizeY = 5;
    field = new char[fieldSizeX][fieldSizeY];
    for (int x = 0; x < fieldSizeX; x++) {
      for (int y = 0; y < fieldSizeY; y++) {
        field[x][y] = DOT_EMPTY;
      }
    }
  }

  private static void printField() {
    //header
    System.out.print("+");
    for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
      System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
    }
    System.out.println();

    for (int i = 0; i < fieldSizeX; i++) {
      System.out.print(i + 1 + "|");
      for (int j = 0; j < fieldSizeY; j++) {
        System.out.print(field[i][j] + "|");
      }
      System.out.println();
    }

    //footer
    for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
      System.out.print("-");
    }
    System.out.println();
  }

  /**
   * обработка хода игрока
   */
  private static void humanTurn() {
    int x, y;
    do {
      System.out.printf("Введите координаты хода X и Y (от 1 до %d) через пробел >>>", fieldSizeY);
      x = SCANNER.nextInt() - 1;
      y = SCANNER.nextInt() - 1;
    } while (!isCellValid(x, y) || !isCellEmpty(x, y));
    field[x][y] = DOT_HUMAN;
  }

  /**
   * является ли ячейка пустой
   *
   * @param x
   * @param y
   * @return
   */
  private static boolean isCellEmpty(int x, int y) {
    return field[x][y] == DOT_EMPTY;
  }

  /**
   * Проверка корректности ввода (координаты ввода не должны превышать размерность массива, игрового
   * поля)
   *
   * @param x
   * @param y
   * @return
   */
  private static boolean isCellValid(int x, int y) {
    return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
  }

  /**
   * обработка хода компьютера
   */
  private static void aiTurn() {
//    if (checkDanger(DOT_HUMAN)) {
//      int[] coordinates = utilCheckWinOrDanger(DOT_HUMAN);
//      int dangerX = coordinates[0];
//      int dangerY = coordinates[1];
//
//      if (checkAiMoveRight(dangerX, dangerY, DOT_HUMAN)) {
//        if (dangerY > 0 && field[dangerX][dangerY - 1] == DOT_EMPTY) {
//          field[dangerX][dangerY - 1] = DOT_AI;
//        } else if (dangerY + 1 < fieldSizeY && field[dangerX][dangerY + 1] == DOT_EMPTY) {
//          field[dangerX][dangerY + 1] = DOT_AI;
//        }
//        return;
//      }
//
//      if (checkAiMoveDownRight(dangerX, dangerY, DOT_HUMAN)) {
//        if (dangerY > 0 && dangerX > 0
//            && field[dangerY - 1][dangerX - 1] == DOT_EMPTY) {
//          field[dangerY - 1][dangerX - 1] = DOT_AI;
//        } else if (dangerY + 1 < fieldSizeY && dangerX + 1 < fieldSizeX
//            && field[dangerY + 1][dangerX + 1] == DOT_EMPTY) {
//          field[dangerY + 1][dangerX + 1] = DOT_AI;
//        }
//        return;
//      }
//
//      if (checkAiMoveDown(dangerX, dangerY, DOT_HUMAN)) {
//        if (dangerX > 0 && field[dangerX - 1][dangerY] == DOT_EMPTY) {
//          field[dangerX - 1][dangerY] = DOT_AI;
//        } else if (dangerX + 1 < fieldSizeX && field[dangerX + 1][dangerY] == DOT_EMPTY) {
//          field[dangerX + 1][dangerY] = DOT_AI;
//        }
//        return;
//      }
//
//      if (checkAiMoveDownLeft(dangerX, dangerY, DOT_HUMAN)) {
//        if (dangerX + 1 < fieldSizeX && dangerY - 1 >= 0
//            && field[dangerX + 1][dangerY - 1] == DOT_EMPTY) {
//          field[dangerX + 1][dangerY - 1] = DOT_AI;
//        } else if (dangerX - 1 >= 0 && dangerY + 1 < fieldSizeY
//            && field[dangerX - 1][dangerY + 1] == DOT_EMPTY) {
//          field[dangerX - 1][dangerY + 1] = DOT_AI;
//        }
//      }
//      return;
//    }

    int x, y;
    do {
      x = random.nextInt(fieldSizeX);
      y = random.nextInt(fieldSizeY);
    } while (!isCellEmpty(x, y));
    field[x][y] = DOT_AI;
  }

  /**
   * Вспомогательный метод роверки победы или опасности проигрыша
   *
   * @param c
   * @return
   */
  private static int[] utilCheckWinOrDanger(char c) {
    for (int x = 0; x < fieldSizeX; x++) {
      for (int y = 0; y < fieldSizeY; y++) {
        if (field[x][y] == c) {
          return new int[]{x, y};
        }
      }
    }
    return new int[]{fieldSizeX,
        fieldSizeY}; //если символ не найден, возвращает невалидные координаты
  }

  /**
   * Проверка победы
   *
   * @param c
   * @return
   */
  private static boolean checkWin(char c) {
    int[] coordinates = utilCheckWinOrDanger(c);
    int x = coordinates[0];
    int y = coordinates[1];

    if (x < fieldSizeX) {
      return checkWinDown(x, y, c)
          || checkWinDownLeft(x, y, c)
          || checkWinDownRight(x, y, c)
          || checkWinRight(x, y, c);
    }
    return false;
  }

  /**
   * Проверка опасности для компьютера
   *
   * @param c
   * @return
   */
  private static boolean checkDanger(char c) {
    int[] coordinates = utilCheckWinOrDanger(c);
    int x = coordinates[0];
    int y = coordinates[1];

    if (x < fieldSizeX) {
      return checkAiMoveDown(x, y, c)
          || checkAiMoveDownLeft(x, y, c)
          || checkAiMoveDownRight(x, y, c)
          || checkAiMoveRight(x, y, c);
    }
    return false;
  }

  /**
   * Вспомогательный метод проверки по диагонали вниз и влево
   */
  private static boolean checkDownLeft(int x, int y, char c, int count) {
    while (x < fieldSizeX && y >= 0) {
      if (field[x][y] == c) {
        count--;
        if (count == 0) {
          return true;
        }
      } else {
        return false;
      }
      x++;
      y--;
    }
    return false;
  }

  /**
   * Вспомогательный метод проверки выигрыша по диагонали вниз и влево
   */
  private static boolean checkWinDownLeft(int x, int y, char c) {
    return checkDownLeft(x, y, c, WIN_COUNT);
  }

  /**
   * Вспомогательный метод проверки по диагонали вниз и влево (для компьютера)
   */
  private static boolean checkAiMoveDownLeft(int x, int y, char c) {
    return checkDownLeft(x, y, c, DANGER_COUNT);
  }

  /**
   * Вспомогательный метод проверки по вертикали вниз
   */
  private static boolean checkDown(int x, int y, char c, int count) {
    while (x < fieldSizeX) {
      if (field[x][y] == c) {
        count--;
        if (count == 0) {
          return true;
        }
      } else {
        return false;
      }
      x++;
    }
    return false;
  }

  /**
   * Вспомогательный метод проверки выигрыша по вертикали вниз
   */
  private static boolean checkWinDown(int x, int y, char c) {
    return checkDown(x, y, c, WIN_COUNT);
  }

  /**
   * Вспомогательный метод проверки по вертикали вниз (для компьютера)
   */
  private static boolean checkAiMoveDown(int x, int y, char c) {
    return checkDown(x, y, c, DANGER_COUNT);
  }

  /**
   * Вспомогательный метод проверки по диагонали вниз и вправо
   */
  private static boolean checkDownRight(int x, int y, char c, int count) {
    while (x < fieldSizeX && y < fieldSizeY) {
      if (field[x][y] == c) {
        count--;
        if (count == 0) {
          return true;
        }
      } else {
        return false;
      }
      x++;
      y++;
    }
    return false;
  }

  /**
   * Вспомогательный метод проверки выигрыша по диагонали вниз и вправо
   */
  private static boolean checkWinDownRight(int x, int y, char c) {
    return checkDownRight(x, y, c, WIN_COUNT);
  }

  /**
   * Вспомогательный метод проверки по вертикали вниз и вправо (для компьютера)
   */
  private static boolean checkAiMoveDownRight(int x, int y, char c) {
    return checkDownRight(x, y, c, DANGER_COUNT);
  }

  /**
   * Вспомогательный метод проверки по горизонтали вправо
   */
  private static boolean checkRight(int x, int y, char c, int count) {
    while (y < fieldSizeY) {
      if (field[x][y] == c) {
        count--;
        if (count == 0) {
          return true;
        }
      } else {
        return false;
      }
      y++;
    }
    return false;
  }

  /**
   * Вспомогательный метод проверки выигрыша по горизонтали вправо
   */
  private static boolean checkWinRight(int x, int y, char c) {
    return checkRight(x, y, c, WIN_COUNT);
  }

  /**
   * Вспомогательный метод проверки по горизонтали вправо (для компьютера)
   */
  private static boolean checkAiMoveRight(int x, int y, char c) {
    return checkRight(x, y, c, DANGER_COUNT);
  }

  /**
   * Проверка на ничью
   */
  private static boolean checkDraw() {
    for (int x = 0; x < fieldSizeX; x++) {
      for (int y = 0; y < fieldSizeY; y++) {
        if (isCellEmpty(x, y)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Проверка состояния игры
   *
   * @param c
   * @param str
   * @return
   */
  private static boolean gameCheck(char c, String str) {
    if (checkWin(c)) {
      System.out.println(str);
      return true;
    }
    if (checkDraw()) {
      System.out.println("Ничья!");
      return true;
    }
    return false;
  }
}
