package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JLabel;

import domain.PhantasmPackage.IPaddleListener;
import domain.PhantasmPackage.Phantasm;

public class PhantasmUI extends JLabel implements IPaddleListener {

	private int x = 0;
	private int y = 100;
	private int dx = 0;
	private final int rotationAngle = 0;
	private final int minAngle = -45;
	private final int maxAngle = 45;
	private final int thickness = 20;

	private int length = 0;
	private boolean isRight = false;
	private boolean isLeft = false;
	private static PhantasmUI instance;

	private PhantasmUI(int screenLen, int screenHeight) {

		this.length = Phantasm.getInstance().getLength();
		this.x = (screenLen / 2) - (length / 2);
		this.y = screenHeight - (screenHeight / 5);
		dx = length / 2;
		this.setOpaque(true);
		this.setBackground(Color.black);
		this.setBounds(Phantasm.getInstance().getX(), Phantasm.getInstance().getY(), Phantasm.getInstance().getLength(),
				thickness);

	}

	public static PhantasmUI getInstance() {
		if (instance == null) {
			return new PhantasmUI((int) NeadForSpearUI.width, (int) NeadForSpearUI.height);
		}
		return instance;
	}

	public void setIsRight(boolean bool) {
		this.isRight = bool;
	}

	public void setIsLeft(boolean bool) {
		this.isLeft = bool;
	}

	public boolean getIsLeft() {
		return this.isLeft;
	}

	public boolean getIsRight() {
		return this.isRight;
	}

	@Override
	public void drawPaddle() {
		this.setBounds(Phantasm.getInstance().getX(), Phantasm.getInstance().getY(), Phantasm.getInstance().getLength(),
				thickness);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Phantasm.getInstance().updateLocation();
		Phantasm.getInstance().updateAngle();
		this.setBounds(Phantasm.getInstance().getX(), Phantasm.getInstance().getY(), Phantasm.getInstance().getLength(),
				thickness);
		Shape oldshape = g2.getClip();

		g2.rotate(Math.toRadians(Phantasm.getInstance().getRotationAngle()), Phantasm.getInstance().getLength() / 2,
				this.thickness / 2);
		g2.setClip(oldshape);
		super.paintComponent(g);

	}

}
