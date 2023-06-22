package domain.ObstaclePackage.ObstacleTypesPackage;

import java.awt.Color;

import domain.ObstaclePackage.MovingStrategy.CircularMovement;
import domain.ObstaclePackage.MovingStrategy.StiffMovement;

public class ExplosiveObstacle extends Obstacle {

	private boolean isExploded;

	public ExplosiveObstacle(double xLocation, double yLocation, double sizeX, double sizeY, double speedX,
			boolean isMoving, boolean isVisible, char type) {
		super(xLocation, yLocation, sizeX, sizeY, speedX, isMoving, isVisible, type);
		this.isExploded = false;

		this.movingBehavior = new StiffMovement();
		this.setDirection(0);
		// TODO Auto-generated constructor stub
	}

	public void explode() {
		this.isExploded = true;
	}

	public boolean isExploded() {
		return isExploded;
	}

	public void setExploded(boolean isExploded) {
		this.isExploded = isExploded;
	}

	@Override
	public Color getColor() {
		if (this.isFrozen())
			return Color.lightGray;
		return Color.RED;
	}

	@Override

	public void setMovingBehavior() {
		if (this.isMoving) {
			this.movingBehavior = new CircularMovement(this.getxLocation(), this.getyLocation());
		}

	}

}
