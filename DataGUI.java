import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

 
public class DataGUI extends JFrame {
    private JTextField nameFilterInput, incomeFilterInput, unemploymentFilterLower, unemploymentFilterUpper, unemploymentFilterRangeLower, unemploymentFilterRangeUpper, popFilterLower, popFilterUpper, popFilterRangeLower, popFilterRangeUpper;
    private JButton nameFilterButton, incomeFilterButton, unemploymentFilterButton, unemploymentFilterRangeButton, popFilterButton, popFilterRangeButton;
    private JPanel nameFilterPanel, incomeFilterPanel, unemploymentFilterPanel, unemploymentFilterRangePanel, popFilterPanel, popFilterRangePanel, allFilterPanel, sidePanel, screenshotPanel;

    private JButton filterAll, screenshotListButton, screenshotStatsButton;

    private static int screenshot_counter = 0;

    private JTextArea resultsArea, statsArea;
    private CountryAnalyzer analyzer;
 
    public DataGUI() {
        super("Data Analysis Tool");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
 
        // Initialize data analyzer with data files
        analyzer = new CountryAnalyzer("countries.txt", "populations.txt",
                                  "incomes.txt", "unemployment.txt");
 
        // Create components        
        nameFilterInput              = new JTextField(20);
        nameFilterButton             = new JButton("    Filter by Name    ");
        nameFilterPanel              = new JPanel(new GridLayout(1,2));
        nameFilterPanel.add(nameFilterInput);
        nameFilterPanel.add(nameFilterButton);
        
        incomeFilterInput            = new JTextField(20);
        incomeFilterButton           = new JButton("Filter by Income Group");
        incomeFilterPanel            = new JPanel(new GridLayout(1,2));
        incomeFilterPanel.add(incomeFilterInput);
        incomeFilterPanel.add(incomeFilterButton);

        unemploymentFilterLower      = new JTextField(10);
        unemploymentFilterUpper      = new JTextField(10);
        unemploymentFilterButton     = new JButton("     Filter by Unemployment     ");
        unemploymentFilterPanel      = new JPanel(new GridBagLayout());
        unemploymentFilterPanel.add(unemploymentFilterLower);
        unemploymentFilterPanel.add(unemploymentFilterUpper);
        unemploymentFilterPanel.add(unemploymentFilterButton);

        popFilterLower               = new JTextField(10);
        popFilterUpper               = new JTextField(10);
        popFilterButton              = new JButton("      Filter by Population      ");
        popFilterPanel               = new JPanel(new GridBagLayout());
        popFilterPanel.add(popFilterLower);
        popFilterPanel.add(popFilterUpper);
        popFilterPanel.add(popFilterButton);

        unemploymentFilterRangeLower      = new JTextField(10);
        unemploymentFilterRangeUpper      = new JTextField(10);
        unemploymentFilterRangeButton     = new JButton("  Filter by Unemployment Range  ");
        unemploymentFilterRangePanel      = new JPanel(new GridBagLayout());
        unemploymentFilterRangePanel.add(unemploymentFilterRangeLower);
        unemploymentFilterRangePanel.add(unemploymentFilterRangeUpper);
        unemploymentFilterRangePanel.add(unemploymentFilterRangeButton);

        popFilterRangeLower               = new JTextField(10);
        popFilterRangeUpper               = new JTextField(10);
        popFilterRangeButton              = new JButton("   Filter by Population Range   ");
        popFilterRangePanel               = new JPanel(new GridBagLayout());
        popFilterRangePanel.add(popFilterRangeLower);
        popFilterRangePanel.add(popFilterRangeUpper);
        popFilterRangePanel.add(popFilterRangeButton);

        filterAll = new JButton(" Filter by All");
        filterAll.setPreferredSize(new Dimension(900, 30));
        
        allFilterPanel = new JPanel();
        allFilterPanel.add(filterAll);


        resultsArea = new JTextArea(40, 100);
        resultsArea.setFont(new Font("Monospaced", Font.PLAIN, 10));
        resultsArea.setEditable(false);

        statsArea = new JTextArea(8, 40);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        statsArea.setEditable(false);

        screenshotListButton  = new JButton("                  Screenshot Country List                  ");
        screenshotStatsButton = new JButton("                   Screenshot Statistics                   ");

        screenshotPanel = new JPanel(new GridLayout(2,1));
        screenshotPanel.add(screenshotListButton);
        screenshotPanel.add(screenshotStatsButton);

        sidePanel = new JPanel(new GridLayout(2,1));
        sidePanel.add(screenshotPanel);
        sidePanel.add(statsArea);


        // Add components to window
        add(nameFilterPanel);
        add(incomeFilterPanel);
        add(unemploymentFilterPanel);
        add(popFilterPanel);
        add(unemploymentFilterRangePanel);
        add(popFilterRangePanel);
        add(allFilterPanel);

        add(new JScrollPane(resultsArea));
        add(sidePanel);
        
        // Setup button actions
        filterAll.addActionListener(e -> filterAll());
        nameFilterButton.addActionListener(e -> {
            analyzer.filterName(nameFilterInput.getText());
            refresh();
        });
        incomeFilterButton.addActionListener(e -> {
            analyzer.filterIncome(incomeFilterInput.getText());
            refresh();
        });
        unemploymentFilterButton.addActionListener(e -> {
            analyzer.filterUnemployment(unemploymentFilterLower.getText(), unemploymentFilterUpper.getText());
            refresh();
        });
        popFilterButton.addActionListener(e -> {
            analyzer.filterPopulation(popFilterLower.getText(), popFilterUpper.getText());
            refresh();
        });
        unemploymentFilterRangeButton.addActionListener(e -> {
            analyzer.filterUnemploymentRange(unemploymentFilterRangeLower.getText(), unemploymentFilterRangeUpper.getText());
            refresh();
        });
        popFilterRangeButton.addActionListener(e -> {
            analyzer.filterPopulationRange(popFilterRangeLower.getText(), popFilterRangeUpper.getText());
            refresh();
        });
        screenshotListButton.addActionListener(e -> {
            saveScreen(resultsArea);
        });
        screenshotStatsButton.addActionListener(e -> {
            saveScreen(statsArea);
        });
 
        setVisible(true);
    }

    public void refresh() {
        resultsArea.setText(analyzer.getFilteredCountryTable());
        statsArea.setText(analyzer.getFilteredCountryStats());
    }

    public void filterAll() {
        analyzer.filterAll(nameFilterInput.getText(), incomeFilterInput.getText(), unemploymentFilterLower.getText(), unemploymentFilterUpper.getText(), popFilterLower.getText(), popFilterUpper.getText(), unemploymentFilterRangeLower.getText(), unemploymentFilterRangeUpper.getText(), popFilterRangeLower.getText(), popFilterRangeUpper.getText());
        refresh();
    }
    
    public void saveScreen(JTextArea area) {
        screenshot_counter++;
        try {
            // Create a BufferedImage to store content
            int w = area.getWidth();
            int h = area.getHeight();
            int type = BufferedImage.TYPE_INT_ARGB;
            BufferedImage sshot = new BufferedImage(w, h, type);

            // Grab the canvas of the image
            Graphics2D g2d = sshot.createGraphics();
            // Like a virtual brush, paint the canvas with resultArea
            area.paint(g2d);

            // Make a file
            File out = new File("Search" + screenshot_counter + ".png");
            // Save the content of the image to this file
            ImageIO.write(sshot, "png", out);

            // Remove the canvas
            g2d.dispose();

            System.out.println("Screenshot saved: " + out.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DataGUI y = new DataGUI();
        y.filterAll();
    }
}