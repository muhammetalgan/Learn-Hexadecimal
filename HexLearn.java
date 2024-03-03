import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HexLearn extends JFrame {
    private JLabel cardLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JButton newCardButton;
    private Map<String, Integer> hexMap;

    private String currentBinaryValue;
    private int currentDecimalValue;

    public HexLearn() {
        setTitle("ALGAN - HexLearn");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        hexMap = new HashMap<>();
        hexMap.put("0000", 0);
        hexMap.put("0001", 1);
        hexMap.put("0010", 2);
        hexMap.put("0011", 3);
        hexMap.put("0100", 4);
        hexMap.put("0101", 5);
        hexMap.put("0110", 6);
        hexMap.put("0111", 7);
        hexMap.put("1000", 8);
        hexMap.put("1001", 9);
        hexMap.put("1010", 10);
        hexMap.put("1011", 11);
        hexMap.put("1100", 12);
        hexMap.put("1101", 13);
        hexMap.put("1110", 14);
        hexMap.put("1111", 15);

        cardLabel = new JLabel("", SwingConstants.CENTER);
        cardLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        add(cardLabel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        newCardButton = new JButton("New Card");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        newCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCard();
            }
        });

        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        inputPanel.add(newCardButton);
        add(inputPanel, BorderLayout.SOUTH);

        newCard();
    }

    private void newCard() {
        currentBinaryValue = selectBinaryDigit();
        currentDecimalValue = hexMap.get(currentBinaryValue);
        cardLabel.setText(currentBinaryValue);
    }

    private String selectBinaryDigit() {
        Random rand = new Random();
        int randNum = rand.nextInt(16);
        return String.format("%04d", Integer.parseInt(Integer.toBinaryString(randNum)));
    }

    private void checkGuess() {
        String guess = guessField.getText().toUpperCase();
        if (guess.equals(Integer.toHexString(currentDecimalValue).toUpperCase())) {
            JOptionPane.showMessageDialog(this, "Congratulations! You guessed it right.");
        } else {
            JOptionPane.showMessageDialog(this, "Sorry, wrong guess. Correct answer: " + Integer.toHexString(currentDecimalValue).toUpperCase());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HexLearn game = new HexLearn();
                game.setVisible(true);
                game.setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştir
                game.setResizable(false); // Pencerenin boyutunun değiştirilememesini sağla
            }
        });
    }
}
