package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import domain.ObstaclePackage.BuildGameHandler;
import domain.ObstaclePackage.IObstacleListener;
import domain.ObstaclePackage.ObstacleTypesPackage.FirmObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;

public class ObstacleUI extends JLabel implements MouseListener, IObstacleListener, Serializable {

	Obstacle obstacle;

	BuildGameHandler buildGameHandler;

	public ObstacleUI(Obstacle obstacle, BuildGameHandler buildGameHandler) {
		Color color = obstacle.getColor();

		this.obstacle = obstacle;

		if (!(color == Color.RED)) {

			int x = (int) obstacle.getxLocation();
			int y = (int) obstacle.getyLocation();
			int sizex = (int) obstacle.getSizeX();
			int sizey = (int) obstacle.getSizeY();

			this.setOpaque(true);
			this.setBackground(color);
			Border border = BorderFactory.createLineBorder(Color.BLACK);
			this.setBorder(border);
			this.setBounds((int) x, (int) y, (int) sizex, (int) sizey);
			this.setSize(sizex, sizey);
		} else {
			this.setBorder(null);
		}

		this.buildGameHandler = buildGameHandler;
		this.addMouseListener(this);
		obstacle.addBreakListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() instanceof ObstacleUI) {
			ObstacleUI obs = (ObstacleUI) e.getSource();
			buildGameHandler.removeObstacle(obs.getObstacle(), obs);

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public Obstacle getObstacle() {
		return this.obstacle;
	}

	@Override
	public void onHitEvent(Obstacle obstacle) {
		// TODO Auto-generated method stub
		this.setVisible(false);
		buildGameHandler.setIsHit(true);
		buildGameHandler.removeObstacle(obstacle, this);

	}

	public void onHitFirmEvent(Obstacle obstacle) {
		this.setText(Integer.toString((((FirmObstacle) obstacle).getHitNum())));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
	}

	@Override
	public void onMoveEvent(int x, int y) {
		// int x=(int)obstacle.getxLocation()+200;
		// this.setBounds(x,400,100,100);

		// int[]
		// location=obstacle.getMovingBehavior().move(obstacle.getxLocation(),obstacle.getyLocation());
		this.setBounds(x, y, (int) obstacle.getSizeX(), 30);

	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(obstacle.getColor());
		g2d.fillRect(0, 0, (int) obstacle.getSizeX(), (int) obstacle.getSizeY());

		if (obstacle.getType() == 'f') {
			g2d.setColor(Color.BLACK);
			g2d.drawString(Integer.toString(((FirmObstacle) obstacle).getHitNum()), (int) obstacle.getSizeX() / 2 - 5,
					(int) obstacle.getSizeY() / 2 + 5);
		}

		obstacle.updateLocation();

	}

}
