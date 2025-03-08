import java.io.*;
import java.util.*;

public class CountryAnalyzer {
    private ArrayList<Country> countries;
    private ArrayList<Country> filtered_countries;

    public CountryAnalyzer(String countriesFile, String populationsFile, String incomesFile, String unemploymentFile) {
        countries = new ArrayList<>();
        filtered_countries = new ArrayList<>();
        loadData(countriesFile, populationsFile, incomesFile, unemploymentFile);
    }


    // LOAD DATA

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
            filtered_countries.add(countries.get(i));
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



    // FILTER DATA
    private void _resetFilteredCountries() {
        filtered_countries = new ArrayList<Country>();
        for (Country country : countries) {
            filtered_countries.add(country);
        }
    }
    private void _filterName(ArrayList<Country> data, String name_input) {
        String[] names = name_input.split(",");
        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].toLowerCase().trim();
        }
        Boolean test;
        ArrayList<Country> remove_list = new ArrayList<>();

        for (Country country : data) {
            test = false;
            for (String name : names) {
                if (country.getName().toLowerCase().contains(name)) {
                    test = true;
                    break;
                }
            }
            if (!test) {
                remove_list.add(country);
            }
        }
        for (Country country : remove_list) {
            data.remove(country);
        }
    }
    private void _filterIncome(ArrayList<Country> data, String income_input) {
        String[] incomes = income_input.split(",");
        for (int i = 0; i < incomes.length; i++) {
            incomes[i] = incomes[i].toLowerCase().trim();
        }
        Boolean test;
        ArrayList<Country> remove_list = new ArrayList<>();

        for (Country country : data) {
            test = false;
            for (String income : incomes) {
                if (country.getIncomeLevel().toLowerCase().equals(income)) {
                    test = true;
                    break;
                }
            }
            if (!test) {
                remove_list.add(country);
            }
        }
        for (Country country : remove_list) {
            data.remove(country);
        }
    }
    private void _filterUnemployment(ArrayList<Country> data, double lower, double upper) {
        ArrayList<Country> remove_list = new ArrayList<>();

        for (Country country : data) {
            if (!(country.getUnemploymentRate() > lower && country.getUnemploymentRate() < upper)) {
                remove_list.add(country);
            }
        }
        for (Country country : remove_list) {
            data.remove(country);
        }
    }
    private void _filterPopulation(ArrayList<Country> data, int lower, int upper) {
        ArrayList<Country> remove_list = new ArrayList<>();

        for (Country country : data) {
            if (!(country.getUnemploymentRate() > lower && country.getUnemploymentRate() < upper)) {
                remove_list.add(country);
            }
        }
        for (Country country : remove_list) {
            data.remove(country);
        }
    }
    private void _filterUnemploymentRange(ArrayList<Country> data, int lower, int upper) {
        if (upper == -1) {
            upper = data.size();
        }

        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data.get(j).getUnemploymentRate() > data.get(j + 1).getUnemploymentRate()) {
                    Country temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                }
            }
        }

        ArrayList<Country> filtered = new ArrayList<>(data.subList(lower, upper));
        data.clear();
        for (Country country : filtered) {
            data.add(country);
        }
    }
    private void _filterPopulationRange(ArrayList<Country> data, int lower, int upper) {
        if (upper == -1) {
            upper = data.size();
        }

        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data.get(j).getPopulation() > data.get(j + 1).getPopulation()) {
                    Country temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                }
            }
        }

        ArrayList<Country> filtered = new ArrayList<>(data.subList(lower, upper));
        data.clear();
        for (Country country : filtered) {
            data.add(country);
        }
    }

    public void filterName(String name) {
        if (!name.equals("")) {
            _resetFilteredCountries();
            _filterName(filtered_countries, name);
        }
    }
    public void filterIncome(String income) {
        if (!income.equals("")) {
            _resetFilteredCountries();
            _filterIncome(filtered_countries, income);
        }
    }
    public void filterUnemployment(double lower, double upper) {
        _resetFilteredCountries();
        _filterUnemployment(filtered_countries, lower, upper);
    }
    public void filterPopulation(int lower, int upper) {
        _resetFilteredCountries();
        _filterPopulation(filtered_countries, lower, upper);
    }
    public void filterUnemploymentRange(int lower, int upper) {
        _resetFilteredCountries();
        _filterUnemploymentRange(filtered_countries, lower, upper);
    }
    public void filterPopulationRange(int lower, int upper) {
        _resetFilteredCountries();
        _filterPopulationRange(filtered_countries, lower, upper);
    }
    public void filterUnemployment(String lower, String upper) {
        double i1, i2;
        if (lower.equals("")) {
            i1 = 0;
        } else {
            i1 = Double.parseDouble(lower);
        }
        if (upper.equals("")) {
            i2 = 100.0;
        } else {
            i2 = Double.parseDouble(upper);
        }
        filterUnemployment(i1,i2);
    }
    public void filterPopulation(String lower, String upper) {
        int i3, i4;
        if (lower.equals("")) {
            i3 = 0;
        } else {
            i3 = Integer.parseInt(lower);
        }
        if (upper.equals("")) {
            i4 = Integer.MAX_VALUE;
        } else {
            i4 = Integer.parseInt(upper);
        }
        filterPopulation(i3, i4);
    }
    public void filterUnemploymentRange(String lower, String upper) {
        int i5, i6;
        if (lower.equals("")) {
            i5 = 0;
        } else {
            i5 = Integer.parseInt(lower);
        }
        if (upper.equals("")) {
            i6 = -1;
        } else {
            i6 = Integer.parseInt(upper);
        }
        filterUnemploymentRange(i5, i6);
    }
    public void filterPopulationRange(String lower, String upper) {
        int i5, i6;
        if (lower.equals("")) {
            i5 = 0;
        } else {
            i5 = Integer.parseInt(lower);
        }
        if (upper.equals("")) {
            i6 = -1;
        } else {
            i6 = Integer.parseInt(upper);
        }
        filterPopulationRange(i5, i6);
    }

    public void filterAll(String name, String income, double lower, double upper, int lower2, int upper2, int lower3, int upper3, int lower4, int upper4) {
        _resetFilteredCountries();
        if (!name.equals("")) {
            _filterName(filtered_countries, name);
        }
        if (!income.equals("")) {
            _filterIncome(filtered_countries, income);
        }
        _filterUnemployment(filtered_countries, lower, upper);
        _filterPopulation(filtered_countries, lower2, upper2);
        if (lower3 != -1 && upper3 != -1) {
            _filterUnemploymentRange(filtered_countries, lower3, upper3);
        }
        if (lower4 != -1 && upper4 != -1) {
            _filterPopulationRange(filtered_countries, lower4, upper4);
        }
    }
    public void filterAll(String name, String income, String lower, String upper, String lower2, String upper2, String lower3, String upper3, String lower4, String upper4) {
        double i1, i2;
        int i3, i4, i5, i6, i7, i8;

        if (lower.equals("")) {
            i1 = 0;
        } else {
            i1 = Double.parseDouble(lower);
        }
        if (upper.equals("")) {
            i2 = 100.0;
        } else {
            i2 = Double.parseDouble(upper);
        }

        if (lower2.equals("")) {
            i3 = 0;
        } else {
            i3 = Integer.parseInt(lower2);
        }
        if (upper2.equals("")) {
            i4 = Integer.MAX_VALUE;
        } else {
            i4 = Integer.parseInt(upper2);
        }

        if (lower3.equals("")) {
            i5 = -1;
        } else {
            i5 = Integer.parseInt(lower3);
        }
        if (upper3.equals("")) {
            i6 = -1;
        } else {
            i6 = Integer.parseInt(upper3);
        }

        if (lower4.equals("")) {
            i7 = -1;
        } else {
            i7 = Integer.parseInt(lower4);
        }
        if (upper4.equals("")) {
            i8 = -1;
        } else {
            i8 = Integer.parseInt(upper4);
        }

        filterAll(name, income, i1, i2, i3, i4, i5, i6, i7, i8);
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



    // OUTPUT DATA
    private String clampString(String input, int length) {
        int trail_length = length - input.length();
        for (int i = 0; i < trail_length; i++) {
            input = input + " ";
        }
        return input;
    }

    public String getFilteredCountryTable() {
        int name_length = 7;
        for (Country country : filtered_countries) {
            if (country.getName().length() > name_length) {
                name_length = country.getName().length();
            }
        }
        String output = clampString("Country", name_length) + "    Income Level            Unemployment     Population\n";
        for (Country country : filtered_countries) {
            String unem = String.valueOf(Math.round(country.getUnemploymentRate()*100));
            unem = unem.substring(0, unem.length()-2) + "." + unem.substring(unem.length()-2, unem.length()-1);
            if (unem.substring(0,1).equals(".")) {
                unem = "0" + unem;
            }
            output = output
             + clampString(country.getName(), name_length+4)
             + clampString(country.getIncomeLevel(), 24)
             + clampString(unem+"%", 17)
             + String.valueOf(country.getPopulation())
             + "\n";
        }

        return output;
    }
    
    public String getFilteredCountryStats() {
        long sum_pop = 0;
        long sum_unem = 0;
        for (Country country : filtered_countries) {
            sum_unem += country.getUnemploymentRate();
            sum_pop += country.getPopulation();
        }

        String[] income_levels = {"Low Income", "Lower Middle Income", "Upper Middle Income", "High Income"};
        int[] income_amounts = {0,0,0,0};

        for (int i = 0; i < income_levels.length; i++) {
            for (Country country : filtered_countries) {
                if (country.getIncomeLevel().equals(income_levels[i])) {
                    income_amounts[i]++;
                }
            }
        }

        String unem = String.valueOf(Math.round(sum_unem/filtered_countries.size()*100));
        unem = unem.substring(0, unem.length()-2) + "." + unem.substring(unem.length()-2, unem.length()-1);
        if (unem.substring(0,1).equals(".")) {
            unem = "0" + unem;
        }

        return "" +
           " Total:                       " + String.valueOf(filtered_countries.size()) + "\n"
         + " Average Unemployment Rate:   " + unem + "%\n"
         + " Average Population:          " + String.valueOf(sum_pop/filtered_countries.size()) + "\n"
         + "\n"
         + " Countries per Income Level:  " + "\n"
         + "   High:                      " + String.valueOf(income_amounts[3]) + "\n"
         + "   Upper Middle:              " + String.valueOf(income_amounts[2]) + "\n"
         + "   Lower Middle:              " + String.valueOf(income_amounts[1]) + "\n"
         + "   Low:                       " + String.valueOf(income_amounts[0]) + "\n";
         
        
         
    }
}