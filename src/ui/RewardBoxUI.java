package ui;

// FACTORY PATTERN
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

import domain.RewardBoxPackage.IRewardListener;
import domain.RewardBoxPackage.RewardBox;

public class RewardBoxUI extends JLabel implements IRewardListener {

	RewardBox rewardBox;
	double x;
	double y;
	double sizex;
	double sizey;
	Color color;

	public RewardBoxUI(RewardBox rewardBox) {
		this.color = rewardBox.getColor();

		this.rewardBox = rewardBox;
		this.x = rewardBox.getxLocation();
		this.y = rewardBox.getyLocation();
		this.sizex = rewardBox.getSizeX();
		this.sizey = rewardBox.getSizeY();

		// color = rewardBox.getColor();

		this.setOpaque(true);
		this.setBackground(color);// color);
		// this.setBounds((int)x,(int)y, (int) sizex, (int)sizey);
		this.setLocation((int) rewardBox.getxLocation(), (int) rewardBox.getyLocation());
		this.setBorder(null);
		this.setSize((int) rewardBox.getSizeX(), (int) rewardBox.getSizeY());

	}

	/*
	 * public void paint(Graphics g) { //raphics2D g2d = (Graphics2D) g;
	 * //Rectangle2D.Double reward = new Rectangle2D.Double(0, 0, // 40, 50);
	 * 
	 * g.setColor(Color.PINK);
	 * g.fillRect((int)rewardBox.getxLocation(),(int)rewardBox.getyLocation(),50,50)
	 * ; //g2d.setColor(Color.PINK); //g2d.fill(reward);
	 * 
	 * System.out.println(rewardBox.getyLocation()); System.out.println(this.y);
	 * 
	 * 
	 * rewardBox.checkCatchAndUpdateLocation(); }
	 */
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, 10, 10);

		g2d.setColor(color);
		g2d.fill(rect);
		// rewardBox.checkCatchAndUpdateLocation(rewardBox);
		rewardBox.checkCatchAndUpdateLocation();
		// System.out.println(this);
		// this.setBounds((int)rewardBox.getxLocation(), (int)rewardBox.getyLocation(),
		// 10, 10);

	}

	public RewardBox getRewardBox() {
		return this.rewardBox;
	}

	@Override
	public void onMoveRewardBoxEvent(RewardBox rewardBoxa) {// RewardBox rewardBox) {
		// TODO Auto-generated method stub

		// boolean isVisible = rb.isVisible();
		this.setBounds((int) rewardBox.getxLocation(), (int) rewardBox.getyLocation(), 10, 10);

		// if (!isVisible) {

		// }
	}

	@Override
	public void onCreateRewardBoxEvent(RewardBox rewardBox) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRewardAlertEvent(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCaughtRewardBoxEvent(RewardBox rewardBox) {
		// TODO Auto-generated method stub

		rewardBox.removeRewardBox(rewardBox, this);
	}

	@Override
	public void onRemoveRewardBoxEvent(RewardBox rewardBox, RewardBoxUI rewardBoxUI) {
		// TODO Auto-generated method stub

	}

}
