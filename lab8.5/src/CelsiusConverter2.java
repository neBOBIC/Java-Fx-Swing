import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CelsiusConverter2 extends JFrame {
    private JTextField celsiusTextField;
    private JLabel fahrenheitLabel;
    private JButton convertButton;

    private static class GradientButton extends JButton {
        private Color startColor;
        private Color endColor;

        public GradientButton(String text, Color start, Color end) {
            super(text);
            this.startColor = start;
            this.endColor = end;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(Color.BLACK);
            setFont(new Font("Segoe UI", Font.BOLD, 18));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint gp = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);

            Shape clip = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 25, 25);

            g2.setPaint(gp);
            g2.fill(clip);

            g2.setClip(clip);
            super.paintComponent(g2);

            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground().darker());
            g2.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 25, 25));
            g2.dispose();
        }
    }

    public CelsiusConverter2() {
        setTitle("Celsius to Fahrenheit Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(135, 206, 250, 143);
                Color color2 = new Color(70, 112, 25, 171);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel celsiusLabel = new JLabel("Celsius:");
        celsiusLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        celsiusLabel.setForeground(Color.BLACK);

        celsiusTextField = new JTextField(10);
        celsiusTextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        celsiusTextField.setHorizontalAlignment(JTextField.LEFT);

        convertButton = new GradientButton("Convertește", new Color(255, 202, 109), new Color(255, 165, 0));

        fahrenheitLabel = new JLabel("Fahrenheit: ");
        fahrenheitLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        fahrenheitLabel.setForeground(Color.BLACK);

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(celsiusLabel, gbc);

        gbc.gridx = 1;
        panel.add(celsiusTextField, gbc);

        gbc.gridy = 1;

        gbc.gridx = 0;
        panel.add(convertButton, gbc);

        gbc.gridx = 1;
        panel.add(fahrenheitLabel, gbc);

        convertButton.addActionListener(e -> {
            try {
                double celsius = Double.parseDouble(celsiusTextField.getText());
                double fahrenheit = celsius * 9 / 5 + 32;
                fahrenheitLabel.setText(String.format("Fahrenheit: %.2f", fahrenheit));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CelsiusConverter2().setVisible(true);
        });
    }
}
