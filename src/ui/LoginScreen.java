package ui;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import domain.SaveLoadPackage.SaveLoadHandler;

public class LoginScreen extends JFrame {

	SaveLoadHandler saveLoadHandler = new SaveLoadHandler();

	public LoginScreen(SimulationFrame simulationFrame) {
		this.setTitle("LOGIN");
		this.setFocusable(false);

		JLabel a = new JLabel("Enter your username to load an existing game. Exit the frame to start a new game");

		JTextField name = new JTextField();

		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(e -> {
			// TODO Auto-generated method stub
			try {
				saveLoadHandler.loadButtonClicked(name.getText());
			} catch (IOException ioException) {
				ioException.printStackTrace();
			} catch (ClassNotFoundException classNotFoundException) {
				classNotFoundException.printStackTrace();
			}

		});

		this.add(a);
		this.add(name);

		this.add(loadButton);

		this.setLayout(new GridLayout(5, 3));
		this.setSize(550, 400);

	}

}
