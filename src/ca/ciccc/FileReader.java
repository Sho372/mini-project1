package ca.ciccc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FileReader {

  static String getAnswer() {
    ArrayList<String> list = getData();
    Random random = new Random();
    return list.get(random.nextInt(list.size())).toLowerCase();
  }

  static ArrayList<String> getData() {
    ArrayList<String> list = new ArrayList<>();
    try {
      File file = new File("src/ca/ciccc/pokemon_names.txt");
      Scanner input = new Scanner(file);
      while (input.hasNextLine()) {
        String line = input.nextLine();
        list.add(line);
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found!");
    }
    return list;
  }
}
