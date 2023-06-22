package ui;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SimulationFrame extends JFrame {

	int noSimple, noFirm, noExplosive, noGift;

	public SimulationFrame() {

		this.setTitle("Number of Obstacles");
		this.setFocusable(false);

		JLabel simpleImg = new JLabel();
		ImageIcon simpleImageIcon = new ImageIcon(
				new ImageIcon("images/simple.png").getImage().getScaledInstance(100, 75, Image.SCALE_DEFAULT));
		simpleImg.setIcon(simpleImageIcon);

		JLabel firmImg = new JLabel();
		ImageIcon firmImageIcon = new ImageIcon(
				new ImageIcon("images/firm.png").getImage().getScaledInstance(100, 75, Image.SCALE_DEFAULT));
		firmImg.setIcon(firmImageIcon);

		JLabel explosiveImg = new JLabel();
		ImageIcon explosiveImageIcon = new ImageIcon(
				new ImageIcon("images/explosive.png").getImage().getScaledInstance(100, 75, Image.SCALE_DEFAULT));
		explosiveImg.setIcon(explosiveImageIcon);

		JLabel giftImg = new JLabel();
		ImageIcon giftImageIcon = new ImageIcon(
				new ImageIcon("images/gift.png").getImage().getScaledInstance(100, 75, Image.SCALE_DEFAULT));
		giftImg.setIcon(giftImageIcon);

		JLabel a = new JLabel("  Number of Simple Obstacle");
		JLabel b = new JLabel("  Number of Firm Obstacle");
		JLabel c = new JLabel("  Number of Explosive Obstacle");
		JLabel d = new JLabel("  Number of Gift Obstacle");

		JTextField nSimple = new JTextField();
		JTextField nFirm = new JTextField();
		JTextField nExplosive = new JTextField();
		JTextField nGift = new JTextField();

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(e -> {
			// TODO Auto-generated method stub

			try {
				noSimple = Integer.parseInt(nSimple.getText());
			} catch (Exception ee) {
				noSimple = 75;
			}
			try {
				noFirm = Integer.parseInt(nFirm.getText());
			} catch (Exception ee) {
				noFirm = 10;
			}
			try {
				noExplosive = Integer.parseInt(nExplosive.getText());
			} catch (Exception ee) {
				noExplosive = 5;
			}

			try {
				noGift = Integer.parseInt(nGift.getText());
			} catch (Exception ee) {
				noGift = 10;
			}

		});

		this.add(simpleImg);
		this.add(a);
		this.add(nSimple);

		this.add(firmImg);
		this.add(b);
		this.add(nFirm);

		this.add(explosiveImg);
		this.add(c);
		this.add(nExplosive);

		this.add(giftImg);
		this.add(d);
		this.add(nGift);

		this.add(saveButton);

		this.setLayout(new GridLayout(5, 3));
		this.setSize(550, 400);

	}

	public int[] getNumberOfObstacle() {

		int[] noOfObstacle = new int[4];
		noOfObstacle[0] = noSimple;
		noOfObstacle[1] = noFirm;
		noOfObstacle[2] = noExplosive;
		noOfObstacle[3] = noGift;

		return noOfObstacle;
	}
}
