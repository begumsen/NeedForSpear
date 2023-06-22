package domain.PhantasmPackage;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import domain.SpherePackage.EnchantedSphere;

public class PaddleHandler implements Serializable {
	public static Phantasm paddle;
	EnchantedSphere sphere;

	public PaddleHandler(EnchantedSphere sphereDomain) {
		paddle = Phantasm.getInstance();
		sphere = sphereDomain;
	}

	public void interpretPaddleMovement(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			paddle.rightMovement();
			sphere.isPaddleMoving(true, "right");
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			paddle.leftMovement();
			sphere.isPaddleMoving(true, "left");
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			paddle.clockwiseRotation();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			paddle.counterClockwiseRotation();
		}

	}

	public void interpretKeyReleasePaddleMovement(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_A) || (e.getKeyCode() == KeyEvent.VK_D)) {
			paddle.setTurningClockwise(false);
			paddle.setTurningCounterClockwise(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			paddle.setMovingLeft(false);

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			paddle.setMovingRight(false);

		}
	}

	public void addListener(IPaddleListener lis) {
		paddle.addPaddleListener(lis);
	}

	/*
	 * public void addListener(PaddleListener lis){ paddle.addPaddleListener(lis); }
	 */
}
