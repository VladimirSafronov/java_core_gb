package ru.gb.lesson5.seminar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import ru.gb.lesson5.seminar.exception.NotDirectoryException;

public class Program {

  private static final Random random = new Random();

  //промежутки символов для генерации текста
  private final static int CHAR_BOUND_LOW = 65;
  private final static int CHAR_BOUND_HIGH = 90;
  private final static String WORD_FOR_SEARCH = "GeekBrains";

  public static void main(String[] args) throws IOException, NotDirectoryException {
//    writeFileContents("sample01.txt", 30);
//    System.out.println(isContainsInFile("sample01.txt", WORD_FOR_SEARCH));
//    writeFileContents("sample02.txt", 30, 5);
//    System.out.println(isContainsInFile("sample02.txt", WORD_FOR_SEARCH));
//    concatenateFiles("sample01.txt", "sample02.txt", "sampleConc.txt");
//    System.out.println(isContainsInFile("sampleConc.txt", WORD_FOR_SEARCH));
//
//    String[] fileNames = new String[10];
//    for (int i = 1; i < 6; i++) {
//      fileNames[i] = "file_" + i + ".txt";
//      writeFileContents(fileNames[i], 100, 4);
//      System.out.printf("Файл %s создан\n", fileNames[i]);
//    }
//
//    List<String> res = searchMatch(fileNames, WORD_FOR_SEARCH);
//
//    for (String file : res) {
//      System.out.printf("Файл %s содержит искомое слово '%s'\n", file, WORD_FOR_SEARCH);
//    }
//
//    Tree.print(new File("."), "", true);

    copyFiles(new File("/Users/vladimirsafronov/Desktop/it"));
  }

  /**
   * Метод генерации произвольной строки
   *
   * @param amount количество символов в строке
   * @return сгенерированная строка
   */
  private static String generateString(int amount) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < amount; i++) {
      stringBuilder.append((char) rnd(CHAR_BOUND_LOW, CHAR_BOUND_HIGH + 1));
    }
    return stringBuilder.toString();
  }

  private static int rnd(int min, int max) {
    max -= min;
    return (int) (Math.random() * ++max) + min;
  }

  /**
   * Записать последовательность символов в файл
   *
   * @param fileName имя файла
   * @param length   длина
   */
  private static void writeFileContents(String fileName, int length) {
    try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
      fileOutputStream.write(generateString(length).getBytes());
    } catch (IOException e) {
      System.out.println("Ошибка ввода/вывода");
    }
  }

  /**
   * Записать последовательность символов в файл с вероятностью записи слова для поиска
   *
   * @param fileName имя файла
   * @param words    количество слов для поиска
   * @param length   длина
   */
  private static void writeFileContents(String fileName, int length, int words) {
    try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
      for (int i = 0; i < words; i++) {
        if (random.nextInt(5) == 5 / 2) {
          fileOutputStream.write(WORD_FOR_SEARCH.getBytes());
        } else {
          fileOutputStream.write(generateString(length).getBytes());
        }
        fileOutputStream.write(' ');
      }
    } catch (IOException e) {
      System.out.println("Ошибка ввода/вывода");
    }
  }

  /**
   * Последовательно записать содержимое из двух файлов в один
   *
   * @param fileIn1 первый файл
   * @param fileIn2 второй файл
   * @param fileOut результирующий файл
   * @throws IOException
   */
  private static void concatenateFiles(String fileIn1, String fileIn2, String fileOut)
      throws IOException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(fileOut)) {
      int c;
      try (FileInputStream inputStream = new FileInputStream(fileIn1)) {
        while ((c = inputStream.read()) != -1) {
          fileOutputStream.write(c);
        }
      }

      try (FileInputStream inputStream = new FileInputStream(fileIn2)) {
        while ((c = inputStream.read()) != -1) {
          fileOutputStream.write(c);
        }
      }
    }
  }

  /**
   * @param fileName файл поиска
   * @param str      искомое слово
   * @return имеется ли слово в файле
   */
  private static boolean isContainsInFile(String fileName, String str) throws IOException {
    try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
      int c;
      int i = 0;
      byte[] searchData = str.getBytes();
      while ((c = fileInputStream.read()) != -1) {
        if (c == searchData[i]) {
          i++;
        } else {
          i = 0;
          if (c == searchData[i]) {
            i++;
          }
        }
        if (i == searchData.length) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Поиск слова в папке с файлами
   *
   * @param files адрес папки
   * @param word  искомое слово
   * @return список файлов содержащих слово
   * @throws IOException
   */
  private static List<String> searchMatch(String[] files, String word) throws IOException {
    List<String> list = new ArrayList<>();
    File path = new File(new File(".").getCanonicalPath());
    File[] dir = path.listFiles();
    assert dir != null;
    for (File file : dir) {
      if (!file.isDirectory()) {
        for (String s : files) {
          if (file.getName().equals(s)) {
            if (isContainsInFile(file.getName(), word)) {
              list.add(file.getName());
              break;
            }
          }
        }
      }
    }
    return list;
  }

  /**
   * Функция, создающая резервную копию всех файлов в директории(без поддиректорий) во вновь
   * созданную папку ./backup
   *
   * @param file директория, чьи файлы копируются
   * @throws NotDirectoryException
   */
  private static void copyFiles(File file) throws NotDirectoryException, IOException {
    if (!file.isDirectory()) {
      throw new NotDirectoryException();
    }
    Path directoryForCopy = Paths.get("./backup");
    if (!Files.exists(directoryForCopy)) {
      Files.createDirectory(directoryForCopy);
    }
    for (File src : Objects.requireNonNull(file.listFiles())) {
      if (src.isFile()) {
        Path dest = Paths.get(directoryForCopy + "/" + src.getName());
        Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
      }
    }
  }
}
