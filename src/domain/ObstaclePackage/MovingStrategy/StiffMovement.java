package domain.ObstaclePackage.MovingStrategy;

import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;

public class StiffMovement implements IMovingBehaviorStrategy {
	public StiffMovement() {

	}

	@Override
	public int[] move(double x, double y, int direction, Obstacle obs) {
		// TODO Auto-generated method stub
		int[] array = { 1, 2, direction };

		array[0] = (int) x;
		array[1] = (int) y;

		obs.setDirection(0);

		return array;
	}
}
