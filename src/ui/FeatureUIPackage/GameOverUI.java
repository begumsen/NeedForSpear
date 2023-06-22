package ui.FeatureUIPackage;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import domain.FeaturePackage.FeatureListener;
import domain.PlayerPackage.Player;

public class GameOverUI extends JFrame implements FeatureListener {
	JLabel label = new JLabel();

	public GameOverUI() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(300, 300);
		this.setLocation((int) screenSize.getWidth() / 2 - this.getWidth() / 2,
				(int) screenSize.getHeight() / 2 - this.getWidth() / 2);

		label.setText("GAME OVER \n" + "Score: " + (int) Player.getInstance().getScore());
		label.setVisible(true);
		label.setBounds(this.getWidth() / 2 - 100, 10, 200, 200);
		add(label);
	}

	@Override
	public void onFeatureEvent() {
		label.setText("GAME OVER \n" + "Score: " + (int) Player.getInstance().getScore());
		this.setVisible(true);

	}

}
