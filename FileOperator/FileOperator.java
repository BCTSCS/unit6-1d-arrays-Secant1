package FileOperator;

import java.io.*;
import java.util.*;

public class FileOperator {

    public static ArrayList<String> toStringArray(String filename) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(new File(filename));
            while (myReader.hasNextLine()) {
                arr.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return arr;
    }

    public static ArrayList<Integer> toIntArray(String filename) {
        ArrayList<Integer> arr = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(new File(filename));
            while (myReader.hasNextLine()) {
                arr.add(myReader.nextInt());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return arr;
    }

    public static ArrayList<Double> toDoubleArray(String filename) {
        ArrayList<Double> arr = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(new File(filename));
            while (myReader.hasNextLine()) {
                arr.add(myReader.nextDouble());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return arr;
    }

    public static int getStringIndex(String[] arr, String value) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(value)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public static int getDoubleIndex(double[] arr, double value) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                index = i;
                break;
            }
        }
        return index;
    }
    public static int getIntIndex(int[] arr, int value) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                index = i;
                break;
            }
        }
        return index;
    }
    
    

    // public static void main(ArrayList<String> args) {
    //     // Initialize FileOperator with the provided file
    //     FileOperator fileOp = new FileOperator("FileOperator/array.txt");
    //     int arraySize = 14;

    //     // Test toStringArray
    //     ArrayList<String> stringArray = fileOp.toStringArray(arraySize);
    //     System.out.println("String Array:");
    //     System.out.println(Arrays.toString(stringArray));

    //     // Test toIntArray
    //     int[] intArray = fileOp.toIntArray(arraySize);
    //     System.out.println("Integer Array:");
    //     System.out.println(Arrays.toString(intArray));

    //     // Test toDoubleArray
    //     double[] doubleArray = fileOp.toDoubleArray(arraySize);
    //     System.out.println("Double Array:");
    //     System.out.println(Arrays.toString(doubleArray));

    //     // Convert integer array to a 2D String array for other operations
    //     ArrayList<String>[] data = new ArrayList<String>arraySize][1];
    //     for (int i = 0; i < arraySize; i++) {
    //         data[i][0] = String.valueOf(intArray[i]);
    //     }

    //     System.out.println("Minimum value: " + FileOperator.findMin(data, 0));
    //     System.out.println("Maximum value: " + FileOperator.findMax(data, 0));
    //     System.out.println("Average value: " + FileOperator.calculateAverage(data, 0));

    //     FileOperator.bubbleSort(data, 0, true);
    //     System.out.println("Sorted (Ascending):");
    //     for (ArrayList<String> row : data) {
    //         System.out.println(row[0]);
    //     }

    //     FileOperator.bubbleSort(data, 0, false);
    //     System.out.println("Sorted (Descending):");
    //     for (ArrayList<String> row : data) {
    //         System.out.println(row[0]);
    //     }

    //     System.out.println("Greater Than 5:");
    //     for (int y : FileOperator.greaterThan(fileOp.toIntArray(arraySize), 5)) {
    //         System.out.println(y);
    //     }
    // }
}





// public static String[] toStringArray(String filename, int arraySize) {
//     String[] arr = new String[arraySize];
//     try {
//         Scanner myReader = new Scanner(new File(filename));
//         int i = 0;
//         while (myReader.hasNextLine() && i < arraySize) {
//             arr[i++] = myReader.nextLine();
//         }
//         myReader.close();
//     } catch (FileNotFoundException e) {
//         System.out.println("File not found.");
//     }
//     return arr;
// }

// public static int[] toIntArray(String filename, int arraySize) {
//     int[] arr = new int[arraySize];
//     try {
//         Scanner myReader = new Scanner(new File(filename));
//         int i = 0;
//         while (myReader.hasNextInt() && i < arraySize) {
//             arr[i++] = myReader.nextInt();
//         }
//         myReader.close();
//     } catch (FileNotFoundException e) {
//         System.out.println("File not found.");
//     }
//     return arr;
// }

// public static double[] toDoubleArray(String filename, int arraySize) {
//     double[] arr = new double[arraySize];
//     try {
//         Scanner myReader = new Scanner(new File(filename));
//         int i = 0;
//         while (myReader.hasNextDouble() && i < arraySize) {
//             arr[i++] = myReader.nextDouble();
//         }
//         myReader.close();
//     } catch (FileNotFoundException e) {
//         System.out.println("File not found.");
//     }
//     return arr;
// }