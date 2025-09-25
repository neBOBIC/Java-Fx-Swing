import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inversare extends JFrame {
    private JTextField textField;
    private JButton button;
    private JLabel label;

    public Inversare() {
        setTitle("Procesare text sau număr");
        setSize(500, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centru ecran
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Introduceți un text sau un număr", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(30, 60, 120));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        centerPanel.setBackground(new Color(240, 245, 255));

        textField = new JTextField(20);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        button = new JButton("Procesează");
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(140, 40));

        label = new JLabel("Rezultatul va apărea aici", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(new Color(40, 40, 40));

        centerPanel.add(textField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(button);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(label);

        add(centerPanel, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText().trim();

                if (input.isEmpty()) {
                    label.setText("Introduceți ceva!");
                    label.setForeground(Color.RED);
                    return;
                }

                if (input.matches("-?\\d+")) {
                    int numar = Integer.parseInt(input);
                    if (numar % 2 == 0) {
                        label.setText("Număr par: " + numar);
                        label.setForeground(new Color(0, 128, 0));
                    } else {
                        label.setText("Număr impar: " + numar);
                        label.setForeground(new Color(0, 100, 180));
                    }
                } else {
                    String invers = new StringBuilder(input).reverse().toString();
                    label.setText("Invers: " + invers);
                    label.setForeground(new Color(80, 0, 120));
                }
            }
        });
    }}
