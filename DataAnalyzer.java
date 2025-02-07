import java.io.File;
import java.io.IOException;
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
        Scanner input = null;
        try {
            File file = new File("C:\\Users\\zipit\\github-classroom\\BCTSCS\\unit6-1d-arrays-Secant1\\numbers.txt");
            input = new Scanner(file);
        } catch (IOException error) {
            System.out.println("Error opening file: " + error.getMessage());
            System.exit(1);
        }

        // Ensure input is not null before proceeding
        if (input == null) {
            System.out.println("Failed to open file.");
            return;
        }

        // Read integers dynamically instead of assuming exactly 100 numbers
        int[] arr = new int[100];
        int count = 0;
        while (input.hasNextInt() && count < arr.length) {
            arr[count++] = input.nextInt();
        }

        if (count == 0) {
            System.out.println("File is empty or contains no valid integers.");
            System.exit(1);
        }

        System.out.print("What number would you like to find: ");
        if (!input.hasNextInt()) {
            System.out.println("No valid input received.");
            System.exit(1);
        }

        int number = input.nextInt();
        System.out.println();

        // Assuming searchList, binaryList, and reverseList are defined
        System.out.println("Forward Search: " + searchList(arr, number));
        System.out.println("Binary Search: " + binaryList(arr, number));
        System.out.println("Reverse Search: " + reverseList(arr, number));

        input.close(); // Close scanner to prevent resource leaks
    }
}
