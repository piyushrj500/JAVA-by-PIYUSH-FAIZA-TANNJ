import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;
import java.time.LocalDate;
import java.time.Period;

public class AstrologyApp extends JFrame {
    private JTextField nameField;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JTextField yearField;
    private JTextArea resultArea;
    
    public AstrologyApp() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Astrology Information System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel headerLabel = new JLabel("🌟 Astrology Information System 🌟", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(0, 100, 0));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(headerLabel, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Result Area
        resultArea = new JTextArea(15, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setBackground(new Color(255, 255, 240));
        resultArea.setBorder(BorderFactory.createTitledBorder("Astrology Results"));
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Enter Your Details"));

        panel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Birth Day:"));
        dayComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) dayComboBox.addItem(String.valueOf(i));
        panel.add(dayComboBox);

        panel.add(new JLabel("Birth Month:"));
        monthComboBox = new JComboBox<>(new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"});
        panel.add(monthComboBox);

        panel.add(new JLabel("Birth Year:"));
        yearField = new JTextField("2000");
        panel.add(yearField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        
        JButton calculateButton = new JButton("Get Astrology Info");
        calculateButton.addActionListener(e -> calculateAstrology());
        
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields());
        
        panel.add(calculateButton);
        panel.add(clearButton);
        
        return panel;
    }

    private void calculateAstrology() {
        try {
            String name = nameField.getText().trim();
            int day = dayComboBox.getSelectedIndex() + 1;
            int month = monthComboBox.getSelectedIndex() + 1;
            int year = Integer.parseInt(yearField.getText().trim());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name");
                return;
            }

            String zodiacSign = calculateZodiac(day, month);
            String chineseZodiac = calculateChineseZodiac(year);
            String birthstone = getBirthstone(calculateZodiacIndex(day, month));
            
            String result = "✨ ASTROLOGY REPORT ✨\n" +
                           "====================\n\n" +
                           "Name: " + name + "\n" +
                           "Birth Date: " + day + "/" + month + "/" + year + "\n" +
                           "Age: " + calculateAge(day, month, year) + " years\n\n" +
                           "Sun Sign: " + zodiacSign + "\n" +
                           "Chinese Zodiac: " + chineseZodiac + "\n" +
                           "Birthstone: " + birthstone + "\n" +
                           "Lucky Number: " + ((day + month) % 9) + "\n";
            
            resultArea.setText(result);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid data");
        }
    }

    private String calculateZodiac(int day, int month) {
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) return "Aries ♈";
        else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) return "Taurus ♉";
        else if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) return "Gemini ♊";
        else if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) return "Cancer ♋";
        else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) return "Leo ♌";
        else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) return "Virgo ♍";
        else if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) return "Libra ♎";
        else if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) return "Scorpio ♏";
        else if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) return "Sagittarius ♐";
        else if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) return "Capricorn ♑";
        else if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) return "Aquarius ♒";
        else return "Pisces ♓";
    }

    private int calculateZodiacIndex(int day, int month) {
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) return 0;
        else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) return 1;
        else if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) return 2;
        else if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) return 3;
        else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) return 4;
        else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) return 5;
        else if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) return 6;
        else if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) return 7;
        else if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) return 8;
        else if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) return 9;
        else if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) return 10;
        else return 11;
    }

    private String getBirthstone(int zodiacIndex) {
        String[] stones = {"Diamond", "Emerald", "Pearl", "Ruby", "Peridot", "Sapphire",
                          "Opal", "Topaz", "Turquoise", "Garnet", "Amethyst", "Aquamarine"};
        return stones[zodiacIndex];
    }

    private String calculateChineseZodiac(int year) {
        String[] animals = {"Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake",
                           "Horse", "Goat", "Monkey", "Rooster", "Dog", "Pig"};
        return animals[(year - 1900) % 12];
    }

    private int calculateAge(int day, int month, int year) {
        LocalDate birthDate = LocalDate.of(year, month, day);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private void clearFields() {
        nameField.setText("");
        dayComboBox.setSelectedIndex(0);
        monthComboBox.setSelectedIndex(0);
        yearField.setText("2000");
        resultArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AstrologyApp().setVisible(true);
        });
    }
}