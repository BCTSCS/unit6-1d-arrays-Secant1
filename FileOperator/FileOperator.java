package FileOperator;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class FileOperator {
  public static void main(String[] args) {
    FileOperator filop = new FileOperator("array.txt");

    int[] arr = filop.toIntArray(5);
    for (int st : arr) {
      System.out.println(st);
    }
  }

  public FileOperator(String fn) {
    filename = fn;
  }

  private String filename;

  public String[] toStringArray(int arraySize) {
    String[] arr = new String[arraySize];
    try {
      File myObj = new File(filename);
      Scanner myReader = new Scanner(myObj);
      int i = 0;
      while (myReader.hasNextLine()) {
        arr[i] = myReader.nextLine();
        i++;
      }
      myReader.close();
      return arr;
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
    }
    return null;
  }

  public int[] toIntArray(int arraySize) {
    int[] arr = new int[arraySize];
    try {
      File myObj = new File(filename);
      Scanner myReader = new Scanner(myObj);
      int i = 0;
      while (myReader.hasNextLine()) {
        arr[i] = myReader.nextInt();
        i++;
      }
      myReader.close();
      return arr;
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
    }
    return null;
  }

  public double[] toDoubleArray(int arraySize) {
    double[] arr = new double[arraySize];
    try {
      File myObj = new File(filename);
      Scanner myReader = new Scanner(myObj);
      int i = 0;
      while (myReader.hasNextLine()) {
        arr[i] = myReader.nextDouble();
        i++;
      }
      myReader.close();
      return arr;
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
    }
    return null;
  }
}