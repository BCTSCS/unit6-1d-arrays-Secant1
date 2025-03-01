public class Country {
    
    private String name;
    private String income;
    private int population;
    private double unemployment;

    /**
     * Default constructor that initializes a Country object with default values.
     * Sets name and income to "None", population to 0, and unemployment to 0.0.
     */
    public Country() {
        this.name = "None";
        this.income = "None";
        this.population = 0;
        this.unemployment = 0.0;
    }

    /**
     * Parameterized constructor that initializes a Country object with specified values.
     * 
     * @param name The name of the country
     * @param income The income level classification (e.g., "High", "Middle", "Low")
     * @param population The total population of the country
     * @param unemployment The unemployment rate as a percentage
     */
    public Country(String name, String income, int population, float unemployment) {
        this.name = name;
        this.income = income;
        this.population = population;
        this.unemployment = unemployment;
    }

    /**
     * Gets the name of the country.
     * 
     * @return The country name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the country.
     * 
     * @param name The new country name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the income level classification of the country.
     * 
     * @return The income level (e.g., "High", "Middle", "Low")
     */
    public String getIncomeLevel() {
        return income;
    }

    /**
     * Sets the income level classification of the country.
     * 
     * @param income The new income level
     */
    public void setIncomeLevel(String income) {
        this.income = income;
    }

    /**
     * Gets the population of the country.
     * 
     * @return The total population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Sets the population of the country.
     * 
     * @param population The new population value
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Gets the unemployment rate of the country.
     * 
     * @return The unemployment rate as a percentage
     */
    public double getUnemploymentRate() {
        return unemployment;
    }

    /**
     * Sets the unemployment rate of the country.
     * 
     * @param unemployment The new unemployment rate
     */
    public void setUnemploymentRate(double unemployment) {
        this.unemployment = unemployment;
    }

    /**
     * Returns a string representation of the Country object.
     * Includes name, income level, population, and unemployment rate.
     * 
     * @return A formatted string with country information
     */
    public String toString() {
        return "The country of " + this.name + ". It has a " + income + " level, a population of " + population + " people, and an unemployment rate of " + unemployment + "%.";
    }
}