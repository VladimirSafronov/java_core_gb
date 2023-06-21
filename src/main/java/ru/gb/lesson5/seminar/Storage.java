package ru.gb.lesson5.seminar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Предположить, что числа в исходном массиве из 9 элементов имеют диапазон[0, 3], и представляют
 * собой, например, состояния ячеек поля для игры в крестикинолики, где 0 – это пустое поле, 1 – это
 * поле с крестиком, 2 – это поле с ноликом, 3 – резервное значение. Такое предположение позволит
 * хранить в одном числе типа int всё поле 3х3. Записать в файл 9 значений так, чтобы они заняли три
 * байта
 */

public class Storage {

  /**
   * 2. Предположить, что числа в исходном массиве из 9 элементов имеют диапазон [0, 3], и
   * представляют собой, например, состояния ячеек поля для игры в крестики-нолики, где 0 – это
   * пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом, 3 – резервное значение. Такое
   * предположение позволит хранить в одном числе типа int всё поле 3х3. Записать в файл 9 значений
   * так, чтобы они заняли три байта
   *
   * @throws IOException Ошибка записи данных в файл
   */
  static void saveData() throws IOException {
    int[] array = {0, 1, 2, 3, 0, 1, 2, 3, 0};
    try (FileOutputStream fos = new FileOutputStream("homework_save1.out")) {
      for (int b = 0; b < 3; b++) { // write to 3 bytes
        byte wr = 0;
        for (int v = 0; v < 3; v++) { // write by 3 values in each
          wr += (byte) (array[3 * b + v] << (v * 2));
        }
        fos.write(wr);
      }
    }
  }

  /**
   * 3. Прочитать числа из файла, полученного в результате выполнения задания 2
   *
   * @throws IOException Ошибка чтения данных из файла
   */
  static void readData() throws IOException {
    int[] ar20 = new int[9];
    try (FileInputStream fis = new FileInputStream("homework_save1.out")) {
      int b;
      int i = 0;
      while ((b = fis.read()) != -1) {
        for (int v = 0; v < 3; ++v) { // 3 values of four possible
          ar20[i++] = b >> (v * 2) & 0x3;
        }
      }
    }
    System.out.println(Arrays.toString(ar20));
  }

  public static void main(String[] args) throws IOException {
    saveData();
    readData();
  }
}
