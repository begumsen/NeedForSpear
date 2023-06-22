package ui.FeatureUIPackage;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import domain.FeaturePackage.FeatureListener;

public class PauseGameUI extends JFrame implements FeatureListener {

	public PauseGameUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(300, 300);
		this.setLocation((int) screenSize.getWidth() / 2 - this.getWidth() / 2,
				(int) screenSize.getHeight() / 2 - this.getWidth() / 2);
		JLabel label = new JLabel();
		label.setText("Game is paused.");
		label.setVisible(true);
		label.setBounds(this.getWidth() / 2 - 100, 10, 200, 200);
		add(label);
	}

	@Override
	public void onFeatureEvent() {
		this.setVisible(true);

	}

}
