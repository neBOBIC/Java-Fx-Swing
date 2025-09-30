import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

class Camera {
    String tip;
    double pretNoapte;
    int id;

    public Camera(int id, String tip, double pretNoapte) {
        this.id = id;
        this.tip = tip;
        this.pretNoapte = pretNoapte;
    }

    @Override
    public String toString() {
        return "Camera " + id + " - " + tip + " (" + pretNoapte + " €/noapte)";
    }
}

class Rezervare {
    String client;
    Camera camera;
    LocalDate start, end;
    double costTotal;

    public Rezervare(String client, Camera camera, LocalDate start, LocalDate end) {
        this.client = client;
        this.camera = camera;
        this.start = start;
        this.end = end;
        long zile = ChronoUnit.DAYS.between(start, end);
        this.costTotal = zile * camera.pretNoapte;
    }

    @Override
    public String toString() {
        return client + " - " + camera + " (" + start + " -> " + end + ") = " + costTotal + " €";
    }
}

public class Main extends JFrame {
    private ArrayList<Camera> camere = new ArrayList<>();
    private ArrayList<Rezervare> rezervari = new ArrayList<>();
    private JTextArea output = new JTextArea();

    public Main() {
        setTitle("Hotel Management");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu camereMenu = new JMenu("Camere");
        JMenu rezervariMenu = new JMenu("Rezervari");
        JMenu rapoarteMenu = new JMenu("Rapoarte");

        JMenuItem adaugaCamera = new JMenuItem("Adauga camera");
        JMenuItem afiseazaCamere = new JMenuItem("Afiseaza camere");

        JMenuItem adaugaRezervare = new JMenuItem("Adauga rezervare");
        JMenuItem afiseazaRezervari = new JMenuItem("Afiseaza rezervari");

        JMenuItem raportIncasari = new JMenuItem("Raport incasari");

        camereMenu.add(adaugaCamera);
        camereMenu.add(afiseazaCamere);

        rezervariMenu.add(adaugaRezervare);
        rezervariMenu.add(afiseazaRezervari);

        rapoarteMenu.add(raportIncasari);

        menuBar.add(camereMenu);
        menuBar.add(rezervariMenu);
        menuBar.add(rapoarteMenu);

        setJMenuBar(menuBar);
        add(new JScrollPane(output));

        // Actiuni
        adaugaCamera.addActionListener(e -> adaugaCameraDialog());
        afiseazaCamere.addActionListener(e -> afiseazaCamere());
        adaugaRezervare.addActionListener(e -> adaugaRezervareDialog());
        afiseazaRezervari.addActionListener(e -> afiseazaRezervari());
        raportIncasari.addActionListener(e -> raportIncasari());
    }

    private void adaugaCameraDialog() {
        JTextField idField = new JTextField();
        JTextField tipField = new JTextField();
        JTextField pretField = new JTextField();

        Object[] fields = {
                "ID:", idField,
                "Tip (standard, deluxe, suite):", tipField,
                "Pret/noapte:", pretField
        };

        int opt = JOptionPane.showConfirmDialog(this, fields, "Adauga camera", JOptionPane.OK_CANCEL_OPTION);
        if (opt == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String tip = tipField.getText();
            double pret = Double.parseDouble(pretField.getText());
            camere.add(new Camera(id, tip, pret));
            output.setText("Camera adaugata!\n");
        }
    }

    private void afiseazaCamere() {
        output.setText("Lista camere:\n");
        for (Camera c : camere) {
            output.append(c + "\n");
        }
    }

    private void adaugaRezervareDialog() {
        if (camere.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nu exista camere!");
            return;
        }

        JTextField clientField = new JTextField();
        JComboBox<Camera> cameraBox = new JComboBox<>(camere.toArray(new Camera[0]));
        JTextField startField = new JTextField("2025-10-01");
        JTextField endField = new JTextField("2025-10-05");

        Object[] fields = {
                "Nume client:", clientField,
                "Camera:", cameraBox,
                "Data start (yyyy-mm-dd):", startField,
                "Data final (yyyy-mm-dd):", endField
        };

        int opt = JOptionPane.showConfirmDialog(this, fields, "Adauga rezervare", JOptionPane.OK_CANCEL_OPTION);
        if (opt == JOptionPane.OK_OPTION) {
            try {
                String client = clientField.getText();
                Camera camera = (Camera) cameraBox.getSelectedItem();
                LocalDate start = LocalDate.parse(startField.getText());
                LocalDate end = LocalDate.parse(endField.getText());
                Rezervare rez = new Rezervare(client, camera, start, end);
                rezervari.add(rez);
                output.setText("Rezervare adaugata!\n" + rez);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Date invalide!");
            }
        }
    }

    private void afiseazaRezervari() {
        output.setText("Lista rezervari:\n");
        for (Rezervare r : rezervari) {
            output.append(r + "\n");
        }
    }

    private void raportIncasari() {
        double total = 0;
        output.setText("Raport incasari:\n");
        for (Rezervare r : rezervari) {
            output.append(r + "\n");
            total += r.costTotal;
        }
        output.append("TOTAL: " + total + " €\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}