import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.util.Random;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingConstants;

public class GuessingGameUI extends JFrame {
	
	private JPanel contentPane;
	
	private final int MAX_LIVES = 5;
	private final int MAX_NUMBER = 100;
	private Random rand = new Random();
	private int randomInt;
	private int lives;
	private String guessStr = " ";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuessingGameUI frame = new GuessingGameUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuessingGameUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 204);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblText = new JLabel("Press \"New Game\" to start.");
		JTextField textGuess;
		JButton btnEnter = new JButton("Enter");
		JButton btnNewGame = new JButton("New Game");
		
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setBounds(10, 22, 367, 16);
		contentPane.add(lblText);
		
		textGuess = new JTextField();
		textGuess.setBounds(93, 49, 200, 22);
		contentPane.add(textGuess);
		
		btnEnter.setBounds(123, 82, 150, 25);
		btnEnter.setEnabled(false);
		btnEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try {
					guessStr = textGuess.getText();
					
					int guess = Integer.parseInt(guessStr);
					if (guess > 100 || guess < 1) {
						lblText.setText("Error. Please guess a number between 1-100. Lives remaining: " + lives);
					} else if (guess == randomInt) {
						lblText.setText("You won! Good job! Lives remaining: " + lives);
						btnEnter.setEnabled(false);
						btnNewGame.setEnabled(true);
					} else {
						lives--;
						if (lives == 0) {
							lblText.setText("Sorry! You lost! Lives remaining: " + lives);
							btnEnter.setEnabled(false);
							btnNewGame.setEnabled(true);
						} else if (randomInt - guess > 0 && randomInt - guess <= 5) {
							lblText.setText("Your guess is just a little too low. Lives remaining: " + lives);
						} else if (randomInt - guess < 0 && randomInt - guess >= -5) {
							lblText.setText("Your guess is just a little too high. Lives remaining: " + lives);
						} else if (randomInt - guess > 0) {
							lblText.setText("Your guess is too low. Lives remaining: " + lives);
						} else if (randomInt - guess < 0) {
							lblText.setText("Your guess is too high. Lives remaining: " + lives);
						}
					}
				} catch (NumberFormatException e) {
					lblText.setText("Error. Please guess a number between 1-100. Lives remaining: " + lives);
				}
			}
		});
		contentPane.add(btnEnter);
		
		btnNewGame.setBounds(123, 118, 150, 25);
		btnNewGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				textGuess.setText("");
				btnEnter.setEnabled(true);
				btnNewGame.setEnabled(false);
				randomInt = rand.nextInt(MAX_NUMBER) + 1;
				lives = MAX_LIVES;
				lblText.setText("Guess a number between 1-100. Lives remaining: " + lives);
			}
		});
		contentPane.add(btnNewGame);
	}
}
