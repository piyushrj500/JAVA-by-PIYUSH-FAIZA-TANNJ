import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.Period;

public class AstrologyApp extends JFrame {
    private JTextField nameField;
    private JComboBox<String> dayComboBox, monthComboBox, yearComboBox;
    private JTextArea resultArea;
    private JButton calculateButton, clearButton, exitButton;
    private JLabel titleLabel;
    // private JLabel titleLabel, iconLabel;

    // Colors
    private final Color PRIMARY_COLOR = new Color(106, 27, 154);  // Purple
    private final Color SECONDARY_COLOR = new Color(255, 111, 0); // Orange
    private final Color BACKGROUND_COLOR = new Color(250, 250, 255);
    private final Color CARD_COLOR = new Color(255, 255, 255);
    
    // Zodiac data
    private final String[] ZODIAC_INFO = {
        "Aries ♈", "Taurus ♉", "Gemini ♊", "Cancer ♋", "Leo ♌", "Virgo ♍",
        "Libra ♎", "Scorpio ♏", "Sagittarius ♐", "Capricorn ♑", "Aquarius ♒", "Pisces ♓"
    };
    
    private final String[] BIRTHSTONES = {
        "💎 Diamond", "💚 Emerald", "⚪ Pearl", "❤️ Ruby", "💛 Peridot", "💙 Sapphire",
        "🌈 Opal", "🟡 Topaz", "🔷 Turquoise", "🔴 Garnet", "🟣 Amethyst", "🔵 Aquamarine"
    };

    public AstrologyApp() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("✨ Astrology Pro - Personal Horoscope");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        createHeader();
        createMainPanel();
        createFooter();

        // Set application icon
        setIcon();
    }

    private void createHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title with icon
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(PRIMARY_COLOR);
        
        titleLabel = new JLabel("🌟 ASTROLOGY PRO");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel subtitleLabel = new JLabel("Discover Your Cosmic Blueprint");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));

        titlePanel.add(titleLabel);
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);
    }

    private void createMainPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(BACKGROUND_COLOR);

        mainPanel.add(createInputPanel());
        mainPanel.add(createResultPanel());

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(CARD_COLOR);
        panel.setBorder(createModernBorder("Personal Information"));

        // Name Field
        panel.add(createLabel("Full Name"));
        nameField = createStyledTextField();
        panel.add(nameField);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Birth Date Section
        panel.add(createLabel("Date of Birth"));
        JPanel datePanel = new JPanel(new GridLayout(1, 3, 10, 0));
        datePanel.setBackground(CARD_COLOR);

        // Day
        dayComboBox = createStyledComboBox();
        for (int i = 1; i <= 31; i++) dayComboBox.addItem(i < 10 ? "0" + i : String.valueOf(i));
        datePanel.add(createComboBoxPanel("Day", dayComboBox));

        // Month
        monthComboBox = createStyledComboBox();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (String month : months) monthComboBox.addItem(month);
        datePanel.add(createComboBoxPanel("Month", monthComboBox));

        // Year
        yearComboBox = createStyledComboBox();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear; i >= 1900; i--) yearComboBox.addItem(String.valueOf(i));
        datePanel.add(createComboBoxPanel("Year", yearComboBox));

        panel.add(datePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Buttons
        panel.add(createButtonPanel());

        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CARD_COLOR);
        panel.setBorder(createModernBorder("Astrology Report"));

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setBackground(new Color(248, 248, 252));
        resultArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        resultArea.setText("✨ Your astrology report will appear here...\n\n" +
                          "• Enter your details\n" +
                          "• Click 'Generate Report'\n" +
                          "• Discover your cosmic insights!");

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(248, 248, 252));

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void createFooter() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(245, 245, 245));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel footerLabel = new JLabel("© 2024 Astrology Pro - Connect with the Cosmos");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerLabel.setForeground(new Color(100, 100, 100));

        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 0));
        panel.setBackground(CARD_COLOR);

        calculateButton = createStyledButton("Generate Report", SECONDARY_COLOR);
        calculateButton.addActionListener(e -> calculateAstrology());

        clearButton = createStyledButton("Clear All", new Color(100, 100, 100));
        clearButton.addActionListener(e -> clearFields());

        exitButton = createStyledButton("Exit", new Color(200, 50, 50));
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(calculateButton);
        panel.add(clearButton);
        panel.add(exitButton);

        return panel;
    }

    // Helper methods for styled components
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(60, 60, 60));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        return field;
    }

    private JComboBox<String> createStyledComboBox() {
        JComboBox<String> combo = new JComboBox<>();
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        combo.setFocusable(false);
        return combo;
    }

    private JPanel createComboBoxPanel(String label, JComboBox<String> combo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CARD_COLOR);
        
        JLabel title = new JLabel(label, JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        title.setForeground(new Color(100, 100, 100));
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(combo, BorderLayout.CENTER);
        
        return panel;
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw gradient background
                GradientPaint gp = new GradientPaint(0, 0, color, 0, getHeight(), color.darker());
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Draw text
                g2.setColor(Color.WHITE);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(getText(), x, y);
                
                g2.dispose();
            }
        };
        
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.repaint();
            }
            public void mouseExited(MouseEvent e) {
                button.repaint();
            }
        });
        
        return button;
    }

    private Border createModernBorder(String title) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder(
                    BorderFactory.createEmptyBorder(),
                    title,
                    TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION,
                    new Font("Segoe UI", Font.BOLD, 16),
                    PRIMARY_COLOR
                )
            )
        );
    }

    private void setIcon() {
        try {
            // You can add an actual icon file here
            setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
        } catch (Exception e) {
            // Continue without icon if file not found
        }
    }

    private void calculateAstrology() {
        try {
            String name = nameField.getText().trim();
            int day = dayComboBox.getSelectedIndex() + 1;
            int month = monthComboBox.getSelectedIndex() + 1;
            int year = Integer.parseInt(yearComboBox.getSelectedItem().toString());

            if (name.isEmpty()) {
                showError("Please enter your name");
                return;
            }

            String result = generateAstrologyReport(name, day, month, year);
            resultArea.setText(result);

        } catch (Exception ex) {
            showError("Please enter valid data");
        }
    }

    private String generateAstrologyReport(String name, int day, int month, int year) {
        int zodiacIndex = calculateZodiacIndex(day, month);
        String zodiacSign = ZODIAC_INFO[zodiacIndex];
        String birthstone = BIRTHSTONES[zodiacIndex];
        String chineseZodiac = calculateChineseZodiac(year);
        int age = calculateAge(day, month, year);
        int luckyNumber = calculateLuckyNumber(day, month);

        return "✨ COSMIC BLUEPRINT REPORT ✨\n" +
               "══════════════════════════════\n\n" +
               "👤 PERSONAL INFORMATION\n" +
               "────────────────────────\n" +
               "• Name: " + name + "\n" +
               "• Birth Date: " + String.format("%02d/%02d/%d", day, month, year) + "\n" +
               "• Age: " + age + " years\n\n" +
               
               "🌞 WESTERN ASTROLOGY\n" +
               "────────────────────\n" +
               "• Sun Sign: " + zodiacSign + "\n" +
               "• Birthstone: " + birthstone + "\n" +
               "• Element: " + getElement(zodiacIndex) + "\n" +
               "• Ruling Planet: " + getRulingPlanet(zodiacIndex) + "\n" +
               "• Lucky Color: " + getLuckyColor(zodiacIndex) + "\n" +
               "• Lucky Number: " + luckyNumber + "\n\n" +
               
               "⭐ PERSONALITY TRAITS\n" +
               "─────────────────────\n" +
               getPersonalityTraits(zodiacIndex) + "\n\n" +
               
               "💼 COMPATIBLE CAREERS\n" +
               "─────────────────────\n" +
               getCompatibleProfessions(zodiacIndex) + "\n\n" +
               
               "🐉 CHINESE ZODIAC\n" +
               "─────────────────\n" +
               "• Animal Sign: " + chineseZodiac + "\n" +
               "• Characteristics: " + getChineseZodiacTraits(chineseZodiac) + "\n\n" +
               
               "══════════════════════════════\n" +
               "May the stars guide your path! 🌟\n";
    }

    // Existing calculation methods (same as before)
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

    private String calculateChineseZodiac(int year) {
        String[] animals = {"🐀 Rat", "🐂 Ox", "🐅 Tiger", "🐇 Rabbit", "🐉 Dragon", "🐍 Snake",
                           "🐎 Horse", "🐐 Goat", "🐒 Monkey", "🐓 Rooster", "🐕 Dog", "🐖 Pig"};
        return animals[(year - 1900) % 12];
    }

    private int calculateAge(int day, int month, int year) {
        LocalDate birthDate = LocalDate.of(year, month, day);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private int calculateLuckyNumber(int day, int month) {
        int num = (day + month) % 9;
        return num == 0 ? 9 : num;
    }

    private String getElement(int zodiacIndex) {
        String[] elements = {"🔥 Fire", "🌍 Earth", "💨 Air", "💧 Water", "🔥 Fire", "🌍 Earth",
                           "💨 Air", "💧 Water", "🔥 Fire", "🌍 Earth", "💨 Air", "💧 Water"};
        return elements[zodiacIndex];
    }

    private String getRulingPlanet(int zodiacIndex) {
        String[] planets = {"♂ Mars", "♀ Venus", "☿ Mercury", "☽ Moon", "☉ Sun", "☿ Mercury",
                          "♀ Venus", "♇ Pluto", "♃ Jupiter", "♄ Saturn", "♅ Uranus", "♆ Neptune"};
        return planets[zodiacIndex];
    }

    private String getLuckyColor(int zodiacIndex) {
        String[] colors = {"🔴 Red", "🟢 Green", "🟡 Yellow", "⚪ White", "🟠 Gold", "🔵 Navy Blue",
                          "💗 Pink", "🔴 Red", "🟣 Purple", "⚫ Black", "🔵 Blue", "🟢 Sea Green"};
        return colors[zodiacIndex];
    }

    private String getPersonalityTraits(int zodiacIndex) {
        String[] traits = {
            "• Courageous, confident, energetic\n• Optimistic, honest, passionate",
            "• Reliable, patient, practical\n• Devoted, responsible, stable",
            "• Gentle, affectionate, curious\n• Adaptable, quick learner",
            "• Tenacious, imaginative, emotional\n• Sympathetic, persuasive",
            "• Creative, passionate, generous\n• Warm-hearted, cheerful, humorous",
            "• Loyal, analytical, kind\n• Hardworking, practical",
            "• Cooperative, diplomatic, gracious\n• Fair-minded, social",
            "• Resourceful, brave, passionate\n• Stubborn, true friend",
            "• Generous, idealistic\n• Great sense of humor",
            "• Responsible, disciplined\n• Self-controlled, good manager",
            "• Progressive, original\n• Independent, humanitarian",
            "• Compassionate, artistic\n• Intuitive, gentle, wise"
        };
        return traits[zodiacIndex];
    }

    private String getCompatibleProfessions(int zodiacIndex) {
        String[] professions = {
            "Entrepreneur, Athlete, Soldier, Pilot, Firefighter",
            "Banker, Artist, Chef, Architect, Farmer",
            "Journalist, Teacher, Writer, Salesperson, Translator",
            "Chef, Historian, Nurse, Psychologist, Real Estate Agent",
            "Actor, Manager, CEO, Designer, Teacher",
            "Doctor, Researcher, Accountant, Editor, Analyst",
            "Lawyer, Artist, Counselor, Designer, Diplomat",
            "Scientist, Detective, Psychologist, Researcher, Surgeon",
            "Travel Guide, Professor, Writer, Philosopher, Explorer",
            "Manager, Engineer, Scientist, Administrator, Banker",
            "Scientist, Inventor, Social Worker, Programmer, Astronaut",
            "Artist, Musician, Writer, Therapist, Marine Biologist"
        };
        return professions[zodiacIndex];
    }

    private String getChineseZodiacTraits(String zodiac) {
        if (zodiac.contains("Rat")) return "Quick-witted, resourceful, versatile, kind";
        else if (zodiac.contains("Ox")) return "Diligent, dependable, strong, determined";
        else if (zodiac.contains("Tiger")) return "Brave, confident, competitive";
        else if (zodiac.contains("Rabbit")) return "Quiet, elegant, kind, responsible";
        else if (zodiac.contains("Dragon")) return "Confident, intelligent, enthusiastic";
        else if (zodiac.contains("Snake")) return "Enigmatic, intelligent, wise";
        else if (zodiac.contains("Horse")) return "Animated, active, energetic";
        else if (zodiac.contains("Goat")) return "Calm, gentle, sympathetic";
        else if (zodiac.contains("Monkey")) return "Sharp, smart, curious";
        else if (zodiac.contains("Rooster")) return "Observant, hardworking, courageous";
        else if (zodiac.contains("Dog")) return "Lovely, honest, prudent";
        else return "Compassionate, generous, diligent";
    }

    private void clearFields() {
        nameField.setText("");
        dayComboBox.setSelectedIndex(0);
        monthComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);
        resultArea.setText("✨ Your astrology report will appear here...\n\n" +
                          "• Enter your details\n" +
                          "• Click 'Generate Report'\n" +
                          "• Discover your cosmic insights!");
        nameField.requestFocus();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
    // Remove the problematic line completely
    SwingUtilities.invokeLater(() -> {
        new AstrologyApp().setVisible(true);
    });
}
}