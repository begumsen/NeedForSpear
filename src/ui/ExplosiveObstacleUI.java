package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import domain.ObstaclePackage.BuildGameHandler;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;

public class ExplosiveObstacleUI extends ObstacleUI implements MouseListener {

	BuildGameHandler buildGameHandler;

	public ExplosiveObstacleUI(Obstacle obs, BuildGameHandler buildGameHandler) {
		super(obs, buildGameHandler);

		this.setSize(15, 15);
		this.setLocation((int) obs.getxLocation(), (int) obs.getyLocation());

		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, 15, 15);

		g2d.setColor(Color.red);
		g2d.fill(circle);
		obstacle.updateLocation();
	}

}
