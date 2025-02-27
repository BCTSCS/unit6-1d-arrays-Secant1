import java.awt.*;
import javax.swing.*;
 
public class DataGUI extends JFrame {
    private JTextField inputField;
    private JButton statusButton, percentageButton;
    private JTextArea resultsArea;
    private DataAnalyzer analyzer;
 
    public DataGUI() {
        super("Data Analysis Tool");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
 
        // Initialize data analyzer with data files
        analyzer = new DataAnalyzer("countries.txt", "populations.txt",
                                  "incomes.txt", "unemployment.txt");
 
        // Create components
        inputField = new JTextField(20);
        statusButton = new JButton("Analyze by Status");
        percentageButton = new JButton("Status Percentage");
        resultsArea = new JTextArea(10, 40);
        resultsArea.setEditable(false);
 
        // Add components to window
        add(inputField);
        add(statusButton);
        add(percentageButton);
        add(new JScrollPane(resultsArea));
 
        // Setup button actions
        statusButton.addActionListener(e -> analyzeStatus());
        percentageButton.addActionListener(e -> showPercentage());
 
        setVisible(true);
    }
 
    private void analyzeStatus() {
        String status = inputField.getText();
        resultsArea.setText("Analyzing status: " + status);
        analyzer.identifyHighRiskCommunities(status, 10);
    }
 
    private void showPercentage() {
        double average = analyzer.calculateAverageUnemployment();
        resultsArea.setText("Average unemployment: " + average + "%");
    }
 
    public static void main(String[] args) {
        new DataGUI();
    }
}