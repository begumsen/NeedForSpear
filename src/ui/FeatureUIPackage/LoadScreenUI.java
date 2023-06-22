package ui.FeatureUIPackage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import domain.FeaturePackage.FeatureListener;
import domain.SaveLoadPackage.SaveLoadHandler;

public class LoadScreenUI extends JFrame implements FeatureListener {
	SaveLoadHandler saveLoadHandler;

	public LoadScreenUI() {
		saveLoadHandler = new SaveLoadHandler();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(300, 300);
		this.setLocation((int) screenSize.getWidth() / 2 - this.getWidth() / 2,
				(int) screenSize.getHeight() / 2 - this.getWidth() / 2);
		JLabel label = new JLabel();
		label.setText("Enter your name");
		label.setVisible(true);
		label.setBounds(this.getWidth() / 2 - 50, 10, 100, 100);
		add(label);
		JTextField nameTextField = new JTextField();
		nameTextField.setVisible(true);
		nameTextField.setBounds(this.getWidth() / 2 - 50, 60, 100, 100);
		add(nameTextField);
		JButton loadButton = new JButton();
		loadButton.setText("Load");
		loadButton.setVisible(true);
		loadButton.setBounds(this.getWidth() / 2 - 50, 130, 100, 100);
		add(loadButton);
		loadButton.addActionListener(e -> {

			try {
				saveLoadHandler.loadButtonClicked(nameTextField.getText());
			} catch (IOException | ClassNotFoundException ioException) {
				ioException.printStackTrace();
			}

		});

	}

	@Override
	public void onFeatureEvent() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}
}
