package domain.ObstaclePackage.ObstacleTypesPackage;

import java.awt.Color;

import domain.ObstaclePackage.MovingStrategy.HorizontalMovement;
import domain.ObstaclePackage.MovingStrategy.StiffMovement;

public class SimpleObstacle extends Obstacle {

	public SimpleObstacle(double xLocation, double yLocation, double sizeX, double sizeY, double speedX,
			boolean isMoving, boolean isVisible, char type) {

		super(xLocation, yLocation, sizeX, sizeY, speedX, isMoving, isVisible, type);
		this.movingBehavior = new StiffMovement();
		this.setDirection(0);
		// TODO Auto-generated constructor stub

	}

	@Override
	public Color getColor() {
		if (this.isFrozen())
			return Color.lightGray;
		return Color.BLUE;
	}

	@Override

	public void setMovingBehavior() {
		if (this.isMoving) {
			this.movingBehavior = new HorizontalMovement();
			this.setDirection(1);
		}

	}
}
