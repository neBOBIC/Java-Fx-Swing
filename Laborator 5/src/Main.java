import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Bilet extends JFrame {
    private JRadioButton radio1;
    private JRadioButton radio2;
    private JRadioButton radio3;
    private JTextField textField;
    private JButton button;

    public Bilet() {
        setTitle("Exemplu Bilet");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        radio1 = new JRadioButton("Opțiunea 1");
        radio2 = new JRadioButton("Opțiunea 2");
        radio3 = new JRadioButton("Opțiunea 3");
        button = new JButton("Arată Detalii");

        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);

        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(radio1);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(radio2);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(radio3);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(button);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 10));

        add(leftPanel, BorderLayout.WEST);
        add(textField, BorderLayout.EAST);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radio1.isSelected()){
                    textField.setText("Standard: acces limitat, preț 100 Lei/lună");
                } else if (radio2.isSelected()) {
                    textField.setText("Premium: acces complet, preț 200 Lei/lună");
                } else if (radio3.isSelected()) {
                    textField.setText("VIP: acces complet + beneficii extra, 350 Lei/lună");
                } else {
                    textField.setText("Alegeti un abonament mai inainte!");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bilet mainFrame = new Bilet();
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
        });
    }
}
