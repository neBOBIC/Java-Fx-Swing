import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

interface ProcesRetail {
    void initiereVanzare(String produs, int cantitate);
    int urmarireStoc(String produs);
    void finalizareTranzactie();
}

class ProcesRetailImpl implements ProcesRetail {
    private java.util.Map<String, Integer> stocuri;
    private int totalProduseVandute;

    public ProcesRetailImpl() {
        stocuri = new java.util.HashMap<>();
        stocuri.put("Laptop", 10);
        stocuri.put("Telefon", 15);
        stocuri.put("Casti", 20);
        totalProduseVandute = 0;
    }

    @Override
    public void initiereVanzare(String produs, int cantitate) {
        if (stocuri.containsKey(produs)) {
            int stocCurent = stocuri.get(produs);
            if (stocCurent >= cantitate) {
                stocuri.put(produs, stocCurent - cantitate);
                totalProduseVandute += cantitate;
                JOptionPane.showMessageDialog(null,
                        "Vânzare reușită: " + cantitate + " x " + produs);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Stoc insuficient pentru " + produs);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Produs inexistent!");
        }
    }

    @Override
    public int urmarireStoc(String produs) {
        return stocuri.getOrDefault(produs, -1);
    }

    @Override
    public void finalizareTranzactie() {
        JOptionPane.showMessageDialog(null,
                "Tranzacție finalizată! Produse vândute total: " + totalProduseVandute);
    }
}

public class Main extends JFrame {
    private ProcesRetail procesRetail;
    private JComboBox<String> comboProduse;
    private JTextField txtCantitate;

    public Main() {
        procesRetail = new ProcesRetailImpl();

        setTitle("Automatizare Retail");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        comboProduse = new JComboBox<>(new String[]{"Laptop", "Telefon", "Casti"});
        txtCantitate = new JTextField(5);

        JButton btnVanzare = new JButton("Inițiază Vânzare");
        JButton btnStoc = new JButton("Urmărește Stoc");
        JButton btnFinalizare = new JButton("Finalizare Tranzacție");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.add(new JLabel("Selectează produs:"));
        panel.add(comboProduse);
        panel.add(new JLabel("Cantitate:"));
        panel.add(txtCantitate);
        panel.add(btnVanzare);
        panel.add(btnStoc);
        panel.add(btnFinalizare);

        add(panel);

        btnVanzare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String produs = (String) comboProduse.getSelectedItem();
                try {
                    int cantitate = Integer.parseInt(txtCantitate.getText());
                    procesRetail.initiereVanzare(produs, cantitate);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduceți o cantitate validă!");
                }
            }
        });

        btnStoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String produs = (String) comboProduse.getSelectedItem();
                int stoc = procesRetail.urmarireStoc(produs);
                JOptionPane.showMessageDialog(null, "Stoc disponibil pentru " + produs + ": " + stoc);
            }
        });

        btnFinalizare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesRetail.finalizareTranzactie();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
