package ru.gb.lesson4.seminar.hw;

public class Program {

  private static final int CORRECT_SIZE = 4;

  public static int sumAllElements(String[][] arr)
      throws MyArraySizeException, MyArrayDataException {
    checkSize(arr);

    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        try {
          int number = Integer.parseInt(arr[i][j]);
          sum += number;
        } catch (NumberFormatException e) {
          throw new MyArrayDataException("В ячейке [" + i + "][" + j + "] некорректные данные.");
        }
      }
    }
    return sum;
  }

  private static void checkSize(String[][] arr) throws MyArraySizeException {
    if (arr[1].length != CORRECT_SIZE || arr[0].length != CORRECT_SIZE) {
      throw new MyArraySizeException("Неверный размер массива.");
    }
  }

  public static void main(String[] args) {
    String[][] array = new String[][]{
        new String[]{"1", ".", "3", "4"},
        new String[]{"5", "6", "4", "8"},
        new String[]{"1", "2", "3", "4"},
        new String[]{"5", "6", "4", "8"}
    };

    int sum;
    try {
      sum = sumAllElements(array);
      System.out.println(sum);
    } catch (MyArraySizeException | MyArrayDataException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
