package domain.ObstaclePackage;

import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;

public interface IObstacleListener {
	public void onHitEvent(Obstacle obs);

	public void onHitFirmEvent(Obstacle obs);

	public void onMoveEvent(int x, int y);

}
