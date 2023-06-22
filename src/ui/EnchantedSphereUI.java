package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.Timer;

import domain.SpherePackage.EnchantedSphere;
import domain.SpherePackage.ISphereListener;

public class EnchantedSphereUI extends JLabel implements ISphereListener, Serializable {

	private int delay = 10;
	private Timer timer;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	EnchantedSphere sphereDomain = EnchantedSphere.getInstance();
	private static EnchantedSphereUI instance;
	int screenLen;
	int screenHeight;
	// EnchantedSphereDomain sphere = new EnchantedSphereDomain();

	private EnchantedSphereUI() {
		this.screenLen = (int) screenSize.getWidth();
		this.screenHeight = (int) screenSize.getHeight();
		this.setSize(screenLen, screenHeight);

	}

	public static EnchantedSphereUI getInstance() {
		if (instance == null) {
			return new EnchantedSphereUI();
		}
		return instance;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Ellipse2D.Double circle = new Ellipse2D.Double(sphereDomain.getxLocation(), sphereDomain.getyLocation(),
				sphereDomain.getRadius() * 2, sphereDomain.getRadius() * 2);

		g2d.setColor(Color.BLACK);
		g2d.fill(circle);

		sphereDomain.updateLocation();
	}

	@Override
	public void moveSphereEvent(int x, int y, int dx, int dy) {
		// TODO Auto-generated method stub
//			this.x = x;
//			this.y = y;
//			
//			
//			this.dx = dx;
//			this.dy = dy;
	}

}
