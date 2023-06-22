package domain.ObstaclePackage.ObstacleTypesPackage;

import java.awt.Color;

import domain.MagicalAbilityPackage.MagicalAbility;
import domain.ObstaclePackage.MovingStrategy.StiffMovement;

public class GiftObstacle extends Obstacle {
	private MagicalAbility ability;

	public GiftObstacle(double xLocation, double yLocation, double sizeX, double sizeY, double speedX, boolean isMoving,
			boolean isVisible, char type) {

		super(xLocation, yLocation, sizeX, sizeY, speedX, isMoving, isVisible, type);
		this.movingBehavior = new StiffMovement();
		this.setDirection(0);

		// TODO Auto-generated constructor stub

	}

	@Override
	public Color getColor() {
		if (this.isFrozen())
			return Color.lightGray;
		return Color.YELLOW;
	}

	public MagicalAbility getAbility() {
		return ability;
	}

	public void setAbility(MagicalAbility ability) {
		this.ability = ability;
	}

}
