package FileOperator;

import java.io.*;
import java.util.*;

public class FileOperator {
    private String filename;

    public FileOperator(String fn) {
        filename = fn;
    }

    public String[] toStringArray(int arraySize) {
        String[] arr = new String[arraySize];
        try {
            Scanner myReader = new Scanner(new File(filename));
            int i = 0;
            while (myReader.hasNextLine() && i < arraySize) {
                arr[i++] = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return arr;
    }

    public int[] toIntArray(int arraySize) {
        int[] arr = new int[arraySize];
        try {
            Scanner myReader = new Scanner(new File(filename));
            int i = 0;
            while (myReader.hasNextInt() && i < arraySize) {
                arr[i++] = myReader.nextInt();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return arr;
    }

    public double[] toDoubleArray(int arraySize) {
        double[] arr = new double[arraySize];
        try {
            Scanner myReader = new Scanner(new File(filename));
            int i = 0;
            while (myReader.hasNextDouble() && i < arraySize) {
                arr[i++] = myReader.nextDouble();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return arr;
    }

    public static String[][] filterData(String[][] data, int columnIndex, String keyword) {
        int count = 0;
        for (String[] row : data) {
            if (row[columnIndex].contains(keyword)) {
                count++;
            }
        }
        
        String[][] filteredData = new String[count][];
        int index = 0;
        for (String[] row : data) {
            if (row[columnIndex].contains(keyword)) {
                filteredData[index++] = row;
            }
        }
        return filteredData;
    }

    public static double findMin(String[][] data, int columnIndex) {
        double min = Double.MAX_VALUE;
        for (String[] row : data) {
            min = Math.min(min, Double.parseDouble(row[columnIndex]));
        }
        return min;
    }

    public static double findMax(String[][] data, int columnIndex) {
        double max = Double.MIN_VALUE;
        for (String[] row : data) {
            max = Math.max(max, Double.parseDouble(row[columnIndex]));
        }
        return max;
    }

    public static double calculateAverage(String[][] data, int columnIndex) {
        double sum = 0;
        int count = 0;
        for (String[] row : data) {
            sum += Double.parseDouble(row[columnIndex]);
            count++;
        }
        return count > 0 ? sum / count : 0;
    }

    public static void bubbleSort(String[][] data, int columnIndex, boolean ascending) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                double val1 = Double.parseDouble(data[j][columnIndex]);
                double val2 = Double.parseDouble(data[j + 1][columnIndex]);
                if ((ascending && val1 > val2) || (!ascending && val1 < val2)) {
                    String[] temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    public static int[] greaterThan(int[] data, int limit) {
        int[] arr = new int[data.length];
        int j = 0;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] >= limit) {
                arr[i] = data[i];
                j++;
            } else {
                arr[i] = -1;
            }
        }
        int[] out = new int[j];
        j = 0;
        for (int i = 0; i < data.length - 1; i++) {
            if (arr[i] != -1) {
                out[j] = arr[i];
                j++;
            }
        }
        return out;
    }

    public static void main(String[] args) {
        // Initialize FileOperator with the provided file
        FileOperator fileOp = new FileOperator("FileOperator/array.txt");
        int arraySize = 14;

        // Test toStringArray
        String[] stringArray = fileOp.toStringArray(arraySize);
        System.out.println("String Array:");
        System.out.println(Arrays.toString(stringArray));

        // Test toIntArray
        int[] intArray = fileOp.toIntArray(arraySize);
        System.out.println("Integer Array:");
        System.out.println(Arrays.toString(intArray));

        // Test toDoubleArray
        double[] doubleArray = fileOp.toDoubleArray(arraySize);
        System.out.println("Double Array:");
        System.out.println(Arrays.toString(doubleArray));

        // Convert integer array to a 2D String array for other operations
        String[][] data = new String[arraySize][1];
        for (int i = 0; i < arraySize; i++) {
            data[i][0] = String.valueOf(intArray[i]);
        }

        System.out.println("Minimum value: " + FileOperator.findMin(data, 0));
        System.out.println("Maximum value: " + FileOperator.findMax(data, 0));
        System.out.println("Average value: " + FileOperator.calculateAverage(data, 0));

        FileOperator.bubbleSort(data, 0, true);
        System.out.println("Sorted (Ascending):");
        for (String[] row : data) {
            System.out.println(row[0]);
        }

        FileOperator.bubbleSort(data, 0, false);
        System.out.println("Sorted (Descending):");
        for (String[] row : data) {
            System.out.println(row[0]);
        }

        System.out.println("Greater Than 5:");
        for (int y : FileOperator.greaterThan(fileOp.toIntArray(arraySize), 5)) {
            System.out.println(y);
        }
    }
}
