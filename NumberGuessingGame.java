import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame {
    private static JLabel attemptsLabel;
    private static JLabel resultLabel;
    private static JTextField userGuessField;
    private static JButton guessButton;
    private static JButton newGameButton;
    private static int randomNumber;
    private static int attempts;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel userGuessLabel = new JLabel("Enter your guess:");
        panel.add(userGuessLabel);

        userGuessField = new JTextField();
        panel.add(userGuessField);

        guessButton = new JButton("Guess");
        panel.add(guessButton);

        resultLabel = new JLabel(" ");
        panel.add(resultLabel);

        attemptsLabel = new JLabel("Attempts remaining: " + 7);
        panel.add(attemptsLabel);

        newGameButton = new JButton("New Game");
        panel.add(newGameButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        newGame();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guess();
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
    }

    private static void newGame() {
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 7;
        updateAttemptsLabel();
        updateResultLabel("");
    }

    private static void guess() {
        int guess = Integer.parseInt(userGuessField.getText());

        if (guess == randomNumber) {
            updateResultLabel("Congratulations! You guessed the correct number!");
        } else if (guess > randomNumber) {
            updateResultLabel("Your guess is too high!");
        } else {
            updateResultLabel("Your guess is too low!");
        }

        attempts--;
        updateAttemptsLabel();

        if (attempts == 0) {
            guessButton.setEnabled(false);
            userGuessField.setEditable(false);
            updateResultLabel("Game over! The correct number was " + randomNumber + ".");
        }
    }

    private static void updateAttemptsLabel() {
        attemptsLabel.setText("Attempts remaining: " + attempts);
    }

    private static void updateResultLabel(String message) {
        resultLabel.setText(message);
    }
}