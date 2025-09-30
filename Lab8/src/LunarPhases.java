import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LunarPhases extends JFrame {
    private JComboBox<String> phaseComboBox;
    private JLabel imageLabel;

    public LunarPhases() {
        setTitle("Lunar Phases");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());


        JPanel selectPanel = new JPanel();
        selectPanel.setBorder(BorderFactory.createTitledBorder("Select Phase"));

        String[] phases = {
                "New Moon",
                "Waxing Crescent",
                "Full Moon",
                "Waning Gibbous",
                "Last Quarter",
                "Waning Crescent"
        };

        phaseComboBox = new JComboBox<>(phases);
        selectPanel.add(phaseComboBox);


        JPanel displayPanel = new JPanel();
        displayPanel.setBorder(BorderFactory.createTitledBorder("Display Phase"));
        imageLabel = new JLabel();
        displayPanel.add(imageLabel);

        add(selectPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);


        phaseComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPhase = (String) phaseComboBox.getSelectedItem();
                updateImage(selectedPhase);
            }
        });


        updateImage("Waxing Gibbous");
    }

    private void updateImage(String phase) {

        String imagePath = "images/" + phase.replace(" ", "_") + ".jpg";

        ImageIcon icon = new ImageIcon(imagePath);

        if (icon.getIconWidth() > 0) {
            Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
            imageLabel.setText(null);
        } else {
            imageLabel.setIcon(null);
            imageLabel.setText("Image not found!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LunarPhases().setVisible(true));
    }
}
