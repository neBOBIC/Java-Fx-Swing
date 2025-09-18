import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Password password = new Password();
            password.setLocationRelativeTo(null);
            password.setVisible(true);
        });
    }
}