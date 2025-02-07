import java.io.File;
import java.util.Scanner;

public class DataAnalyzer {

    public static int binaryList(int[] numbers, int target) {
        int minIndex = 0; // Adjusted for 0-based indexing
        int maxIndex = numbers.length - 1;

        while (minIndex <= maxIndex) {
            int middleIndex = (minIndex + maxIndex) / 2;

            if (target == numbers[middleIndex]) {
                return middleIndex;
            } else if (target > numbers[middleIndex]) {
                minIndex = middleIndex + 1;
            } else {
                maxIndex = middleIndex - 1;
            }
        }

        return -1; // Target not found
    }

    // Linear Search
    public static int searchList(int[] numbers, int target) {
        int index = 0;

        while (index <= numbers.length - 1) {
            if (numbers[index] == target) {
                return index;
            }
            index++;
        }
        return -1;
    }

    // Reverse Linear Search
    public static int reverseList(int[] numbers, int target) {
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        Scanner input;
        File file = new File("C:/Users/zipit/github-classroom/BCTSCS/unit6-1d-arrays-Secant1/numbers.txt");
        input = new Scanner(file);


        int[] arr = new int[100];

        for (int i = 0; i< 100; i++) {
            arr[i] = input.nextInt();
        }

        System.out.print("What number would you like to find: ");
        int number = input.nextInt();
        System.out.println();

        System.out.println("Forward Search (50): " + searchList(arr, number));
        System.out.println("Binary Search (50): " + binaryList(arr, number));
        System.out.println("Reverse Search (50): " + reverseList(arr, number));
    }
}
