import FileOperator.FileOperator;

public class DataAnalyzer {

    String[] countries;
    int[] populations;
    double[] unemployment;
    String[] incomes;

    DataAnalyzer(String path) {
        countries = FileOperator.toStringArray(path + "countries.txt", 215);
        populations = FileOperator.toIntArray(path + "populations.txt", 215);
        unemployment = FileOperator.toDoubleArray(path + "unemployment.txt", 215);
        incomes = FileOperator.toStringArray(path + "incomes.txt", 215);
    }

    String getCountryByPopulation(int pop) {
        return countries[FileOperator.getIntIndex(populations, pop)];
    }
    String getCountryByUnemployment(double unemp) {
        return countries[FileOperator.getDoubleIndex(unemployment, unemp)];
    }
    String[] getCountriesByIncome(String income) {
        String[] tlist = new String[215];
        int j = 0;
        for (int i = 0; i < 215; i++) {
            if (incomes[i] == income) {
                tlist[j] = countries[i];
                j++;
            }
        }

        String[] rlist = new String[j];
        for (int i = 0; i < j; i++) {
            rlist[i] = tlist[i];
        }
        return rlist;
    }


    double getIncomeLevelPercent(String income_level) {
        int i = 0;
        for (String income : incomes) {
            if (income.equals(income_level)) {
                i++;
            }
        }
        return (double)i/255;
    }

    String[] getUnemploymentInterval(double low, double high) {
        String[] tlist = new String[215];
        int j = 0;
        for (int i = 0; i < 215; i++) {
            if (unemployment[i] >= low && unemployment[i] <= high) {
                tlist[j] = countries[i];
                j++;
            }
        }

        String[] rlist = new String[j];
        for (int i = 0; i < j; i++) {
            rlist[i] = tlist[i];
        }
        return rlist;
    }

    String[] getPopulationInterval(int low, int high) {
        String[] tlist = new String[215];
        int j = 0;
        for (int i = 0; i < 215; i++) {
            if (populations[i] >= low && populations[i] <= high) {
                tlist[j] = countries[i];
                j++;
            }
        }

        String[] rlist = new String[j];
        for (int i = 0; i < j; i++) {
            rlist[i] = tlist[i];
        }
        return rlist;
    }

    public static void main(String[] args) {
        DataAnalyzer analyzer = new DataAnalyzer("C:/Users/zipit/github-classroom/BCTSCS/unit6-1d-arrays-Secant1/data/");
    
        // Showcase method functionalities
        System.out.println("Country with population 331002651: " + analyzer.getCountryByPopulation(331002651));
        System.out.println("Country with 5.5% unemployment: " + analyzer.getCountryByUnemployment(5.5));
    
        System.out.println("\nCountries with 'High income':");
        for (String country : analyzer.getCountriesByIncome("High income")) {
            System.out.println(country);
        }
    
        System.out.println("\nPercentage of 'Low income' countries: " + analyzer.getIncomeLevelPercent("Low income") * 100 + "%");
    
        System.out.println("\nCountries with unemployment rate between 3.0% and 7.0%:");
        for (String country : analyzer.getUnemploymentInterval(3.0, 7.0)) {
            System.out.println(country);
        }
    
        System.out.println("\nCountries with populations between 10M and 100M:");
        for (String country : analyzer.getPopulationInterval(10000000, 100000000)) {
            System.out.println(country);
        }
    }
    
}
