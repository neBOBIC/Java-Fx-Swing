package lab_swingbasics.samples;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CelsiusConverter {
    public static void main(String[] args) {
        // Creăm fereastra principală
        JFrame frame = new JFrame("Convert Celsius to Fahrenheit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creăm panoul
        JPanel panel = new JPanel();

        // Componentele
        JTextField celsiusTextField = new JTextField(10); // câmp pentru introducere temperatură
        JLabel celsiusLabel = new JLabel("Celsius");
        JButton convertButton = new JButton("Convert");
        JLabel fahrenheitLabel = new JLabel("Fahrenheit");

        // Adăugăm componentele în panou
        panel.add(celsiusTextField);
        panel.add(celsiusLabel);
        panel.add(convertButton);
        panel.add(fahrenheitLabel);

        // Acțiune pentru buton
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int tempCelsius = Integer.parseInt(celsiusTextField.getText());
                    int tempFahrenheit = (int) (tempCelsius * 1.8 + 32);
                    fahrenheitLabel.setText(tempFahrenheit + " Fahrenheit");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Introduceți un număr valid!");
                }
            }
        });

        // Adăugăm panoul în fereastră
        frame.getContentPane().add(panel);

        // Setăm dimensiuni și facem fereastra vizibilă
        frame.setSize(300, 120);
        frame.setVisible(true);
    }
}
