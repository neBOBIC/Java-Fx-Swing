import javax.swing.*;
import java.awt.*;

public class Sarcina3 extends JFrame{
    private final JTextField textField1;

    public Sarcina3(){
        setTitle("TextField și Butoane Multiple");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        textField1 = new JTextField();
        textField1.setMaximumSize(new Dimension(600, 30));
        JButton jButton1 = new JButton("Text A");
        JButton jButton2 = new JButton("Text B");
        JLabel jLabel1 = new JLabel("Aici va aparea textul");

        textField1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(textField1);
        add(jButton2);
        add(jButton1);

        jButton1.addActionListener(_ -> textField1.setText("Text A"));

        jButton2.addActionListener(_ -> textField1.setText("Text B"));
    }
}
