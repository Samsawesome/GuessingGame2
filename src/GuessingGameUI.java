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

public class GuessingGameUI extends JFrame {
	
	private JPanel contentPane;
	private JTextField textGuess;
	
	private final int MAX_LIVES = 5;
	private final int MAX_NUMBER = 100;
	private Random rand = new Random();
	private int randomInt = rand.nextInt(MAX_NUMBER) + 1;
	private int lives = MAX_LIVES;
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textGuess = new JTextField();
		textGuess.setBounds(120, 167, 200, 22);
		contentPane.add(textGuess);
		
		JLabel lblText = new JLabel("Guess a number between 1-100. Lives remaining: " + lives);
		lblText.setBounds(70, 13, 300, 16);
		contentPane.add(lblText);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(145, 215, 150, 25);
		btnEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				guessStr = textGuess.getText();
				
				int guess = Integer.parseInt(guessStr);
				if (guess == randomInt) {
					lblText.setText("You won! Good job! Lives remaining " + lives);
					btnEnter.setEnabled(false);
				} else {
					lives--;
					if (lives == 0) {
						lblText.setText("Sorry! You lost! Lives remaining: " + lives);
						btnEnter.setEnabled(false);
					} else if (randomInt - guess > 0 && randomInt - guess <= 5) {
						lblText.setText("Your guess is just a little too low. Lives remaining " + lives);
					} else if (randomInt - guess < 0 && randomInt - guess >= -5) {
						lblText.setText("Your guess is just a little too high. Lives remaining " + lives);
					} else if (randomInt - guess > 0) {
						lblText.setText("Your guess is too low. Lives remaining " + lives);
					} else if (randomInt - guess < 0) {
						lblText.setText("Your guess is too high. Lives remaining " + lives);
					}
				}
			}
		});
		contentPane.add(btnEnter);
	}
}
