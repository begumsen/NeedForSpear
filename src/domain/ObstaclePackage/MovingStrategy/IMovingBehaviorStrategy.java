package domain.ObstaclePackage.MovingStrategy;

import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;

public interface IMovingBehaviorStrategy {
	public int[] move(double x, double y, int direction, Obstacle obs);
}
