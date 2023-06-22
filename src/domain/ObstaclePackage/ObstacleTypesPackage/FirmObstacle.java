package domain.ObstaclePackage.ObstacleTypesPackage;

import java.awt.Color;

import domain.ObstaclePackage.IObstacleListener;
import domain.ObstaclePackage.MovingStrategy.HorizontalMovement;
import domain.ObstaclePackage.MovingStrategy.StiffMovement;
import domain.SpherePackage.EnchantedSphere;

public class FirmObstacle extends Obstacle {

	private int hitNum;

	public FirmObstacle(double xLocation, double yLocation, double sizeX, double sizeY, double speedX, boolean isMoving,
			boolean isVisible, char type, int hitNum) {

		super(xLocation, yLocation, sizeX, sizeY, speedX, isMoving, isVisible, type);
		this.hitNum = hitNum;
		this.movingBehavior = new StiffMovement();
		this.setDirection(0);
		// TODO Auto-generated constructor stub
	}

	public int getHitNum() {
		return hitNum;
	}

	public void decreaseHitNum() {
		this.hitNum = this.hitNum - 1;
	}

	@Override
	public Color getColor() {
		if (this.isFrozen())
			return Color.lightGray;
		return Color.ORANGE;
	}

	@Override
	public void obstacleIsTouched() {
		if ((hitNum == 1) || (EnchantedSphere.getInstance().isUnstoppable())) {
			updateIsVisible();
			publishBreakEvent(this);
		} else {
			hitNum--;
			publishFirmNumber(this);
		}

	}

	private void publishFirmNumber(FirmObstacle firmObstacle) {
		// TODO Auto-generated method stub
		for (IObstacleListener l : listeners) {
			l.onHitFirmEvent(firmObstacle);
		}
	}

	@Override
	public void setMovingBehavior() {
		if (this.isMoving) {
			this.movingBehavior = new HorizontalMovement();
			this.setDirection(1);
		}

	}

}
