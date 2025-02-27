import java.io.*;
import java.util.*;
 
public class DataAnalyzer {
    private String[] countries;
    private int[] populations;
    private String[] incomes;
    private double[] unemploymentRates;
 
    // Constructor to initialize data
    public DataAnalyzer(String countriesFile, String populationsFile, String incomesFile, String unemploymentFile) {
        countries = readStringArray(countriesFile);
        populations = readIntArray(populationsFile);
        incomes = readIncomeCategoryArray(incomesFile);
        unemploymentRates = readDoubleArray(unemploymentFile);
    }
 
    // Reads file and returns String array
    private String[] readStringArray(String filename) {
        ArrayList<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
        return list.isEmpty() ? new String[0] : list.toArray(new String[0]);
    }
 
    // Reads file and returns int array
    private int[] readIntArray(String filename) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + filename);
        }
        return list.isEmpty() ? new int[0] : list.stream().mapToInt(i -> i).toArray();
    }
 
    // Reads file and returns double array
    private double[] readDoubleArray(String filename) {
        ArrayList<Double> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Double.parseDouble(line));
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + filename);
        }
        return list.isEmpty() ? new double[0] : list.stream().mapToDouble(d -> d).toArray();
    }
 
    // Reads file and returns String array for income categories
    private String[] readIncomeCategoryArray(String filename) {
        return readStringArray(filename);
    }
 
    // Identify high-risk communities
    public void identifyHighRiskCommunities(String incomeCategory, double unemploymentThreshold) {
        if (countries.length == 0 || incomes.length == 0 || unemploymentRates.length == 0) {
            System.out.println("Error: One or more datasets are empty. Cannot analyze high-risk communities.");
            return;
        }
       
        System.out.println("High-risk communities with " + incomeCategory + " and unemployment rate above " + unemploymentThreshold + "%:");
        for (int i = 0; i < Math.min(countries.length, Math.min(incomes.length, unemploymentRates.length)); i++) {
            if (incomes[i].equalsIgnoreCase(incomeCategory) && unemploymentRates[i] > unemploymentThreshold) {
                System.out.println(countries[i]);
            }
        }
    }
 
    // Statistical method: Calculate average unemployment rate
    public double calculateAverageUnemployment() {
        if (unemploymentRates.length == 0) {
            System.out.println("Error: No unemployment data available.");
            return 0;
        }
        double sum = 0;
        for (double rate : unemploymentRates) {
            sum += rate;
        }
        return sum / unemploymentRates.length;
    }
 
    // Analytical method: Find country with highest unemployment rate
    public String findHighestUnemploymentCountry() {
        if (countries.length == 0 || unemploymentRates.length == 0) {
            System.out.println("Error: No data available.");
            return "N/A";
        }
        int maxIndex = 0;
        for (int i = 1; i < unemploymentRates.length; i++) {
            if (unemploymentRates[i] > unemploymentRates[maxIndex]) {
                maxIndex = i;
            }
        }
        return countries[maxIndex] + " with " + unemploymentRates[maxIndex] + "% unemployment";
    }
 
    // Display basic info
    public void displayInfo() {
        System.out.println("Total communities analyzed: " + Math.min(countries.length, Math.min(incomes.length, unemploymentRates.length)));
    }
 
    // Main method for testing
    public static void main(String[] args) {
        DataAnalyzer data = new DataAnalyzer("countries.txt", "populations.txt", "incomes.txt", "unemployment.txt");
        data.displayInfo();
        data.identifyHighRiskCommunities("low income", 10);
        System.out.println("Average Unemployment Rate: " + data.calculateAverageUnemployment() + "%");
        System.out.println("Country with Highest Unemployment: " + data.findHighestUnemploymentCountry());
    }
}
 