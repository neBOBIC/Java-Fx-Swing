import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

    private JRadioButton candidate1, candidate2, candidate3, candidate4;
    private JButton voteButton;
    private JLabel resultLabel;

    public Main() {
        setTitle("VoteDialog");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Click the \"Vote\" button once you have selected a candidate.");
        add(instructionLabel, BorderLayout.NORTH);

        JPanel candidatesPanel = new JPanel();
        candidatesPanel.setLayout(new BoxLayout(candidatesPanel, BoxLayout.Y_AXIS));
        candidatesPanel.setBorder(BorderFactory.createTitledBorder("The candidates:"));

        ButtonGroup group = new ButtonGroup();

        candidate1 = new JRadioButton("Candidate 1: Sparky the Dog");
        candidate1.setForeground(Color.RED);
        group.add(candidate1);
        candidatesPanel.add(candidate1);

        candidate2 = new JRadioButton("Candidate 2: Shady Sadie");
        candidate2.setForeground(new Color(0, 128, 0)); // dark green
        group.add(candidate2);
        candidatesPanel.add(candidate2);

        candidate3 = new JRadioButton("Candidate 3: R.I.P. McDaniels");
        candidate3.setForeground(Color.BLUE);
        group.add(candidate3);
        candidatesPanel.add(candidate3);

        candidate4 = new JRadioButton("Candidate 4: Duke the Java™ Platform Mascot");
        candidate4.setForeground(new Color(139, 0, 0)); // dark red
        group.add(candidate4);
        candidatesPanel.add(candidate4);

        add(candidatesPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        voteButton = new JButton("Vote");
        bottomPanel.add(voteButton, BorderLayout.CENTER);

        resultLabel = new JLabel("Vote now!", SwingConstants.CENTER);
        bottomPanel.add(resultLabel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showVoteResult();
            }
        });
    }

    private void showVoteResult() {
        if (candidate1.isSelected()) {
            resultLabel.setText("You voted for: Sparky the Dog");
        } else if (candidate2.isSelected()) {
            resultLabel.setText("You voted for: Shady Sadie");
        } else if (candidate3.isSelected()) {
            resultLabel.setText("You voted for: R.I.P. McDaniels");
        } else if (candidate4.isSelected()) {
            resultLabel.setText("You voted for: Duke the Java™ Platform Mascot");
        } else {
            resultLabel.setText("Please select a candidate before voting.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}