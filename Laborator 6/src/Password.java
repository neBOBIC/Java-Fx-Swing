import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Password extends JFrame {
    JTextField textField;
    JLabel label;
    JButton button;

    public Password() {
        setTitle("Verificare parola");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        inputPanel.add(new JLabel("Introdu parola:"));
        textField = new JTextField();
        inputPanel.add(textField);

        button = new JButton("Verifica Parola");

        label = new JLabel(" ", SwingConstants.CENTER);

        add(inputPanel, BorderLayout.NORTH);
        add(button, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();

                boolean majuscul = text.matches(".*[A-Z].*");
                boolean special = text.matches(".*[^a-zA-Z0-9].*");
                int lungime = text.length();

                if (lungime >= 8 && majuscul && special) {
                    label.setText("Parola este puternica");
                } else if(lungime < 8){
                    label.setText("Parola trebuie sa fie mai lunga");
                } else if (!majuscul) {
                    label.setText("Parola trebuie sa contina un majuscul");
                } else if (!special) {
                    label.setText("Parola trebuie sa contina un caracter special");
                }
            }
        });
        setVisible(true);
    }
}