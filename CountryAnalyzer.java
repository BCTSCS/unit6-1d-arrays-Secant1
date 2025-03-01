import java.io.*;
import java.util.*;

public class CountryAnalyzer {
    private ArrayList<Country> countries;

    public CountryAnalyzer(String countriesFile, String populationsFile, String incomesFile, String unemploymentFile) {
        countries = new ArrayList<>();
        loadData(countriesFile, populationsFile, incomesFile, unemploymentFile);
    }

    private void loadData(String countriesFile, String populationsFile, String incomesFile, String unemploymentFile) {
        String[] countryNames = readStringArray(countriesFile);
        int[] populations = readIntArray(populationsFile);
        String[] incomes = readStringArray(incomesFile);
        double[] unemploymentRates = readDoubleArray(unemploymentFile);

        int minLength = Math.min(countryNames.length, 
                       Math.min(populations.length, 
                       Math.min(incomes.length, unemploymentRates.length)));

        for (int i = 0; i < minLength; i++) {
            countries.add(new Country(countryNames[i], incomes[i], populations[i], (float)unemploymentRates[i]));
        }
    }

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

    public void identifyHighRiskCommunities(String incomeCategory, double unemploymentThreshold) {
        if (countries.isEmpty()) {
            System.out.println("Error: Dataset is empty. Cannot analyze high-risk communities.");
            return;
        }
       
        System.out.println("High-risk communities with " + incomeCategory + " and unemployment rate above " + unemploymentThreshold + "%:");
        for (Country country : countries) {
            if (country.getIncomeLevel().equalsIgnoreCase(incomeCategory) && country.getUnemploymentRate() > unemploymentThreshold) {
                System.out.println(country.getName());
            }
        }
    }

    public double calculateAverageUnemployment() {
        if (countries.isEmpty()) {
            System.out.println("Error: No unemployment data available.");
            return 0;
        }
        double sum = 0;
        for (Country country : countries) {
            sum += country.getUnemploymentRate();
        }
        return sum / countries.size();
    }

    public String findHighestUnemploymentCountry() {
        if (countries.isEmpty()) {
            System.out.println("Error: No data available.");
            return "N/A";
        }
        
        Country maxUnemploymentCountry = countries.get(0);
        for (Country country : countries) {
            if (country.getUnemploymentRate() > maxUnemploymentCountry.getUnemploymentRate()) {
                maxUnemploymentCountry = country;
            }
        }
        return maxUnemploymentCountry.getName() + " with " + maxUnemploymentCountry.getUnemploymentRate() + "% unemployment";
    }

    public void displayInfo() {
        System.out.println("Total communities analyzed: " + countries.size());
    }

    public static void main(String[] args) {
        CountryAnalyzer data = new CountryAnalyzer("countries.txt", "populations.txt", "incomes.txt", "unemployment.txt");
        data.displayInfo();
        data.identifyHighRiskCommunities("low income", 10);
        System.out.println("Average Unemployment Rate: " + data.calculateAverageUnemployment() + "%");
        System.out.println("Country with Highest Unemployment: " + data.findHighestUnemploymentCountry());
    }
}