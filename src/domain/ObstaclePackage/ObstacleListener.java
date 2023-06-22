package domain.ObstaclePackage;

import java.util.ArrayList;

import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;
import ui.ObstacleUI;

public interface ObstacleListener {
	void onCreateObstacleEvent(ArrayList<Obstacle> obstacles);

	void onRemoveObstacleEvent(Obstacle obstacle, ObstacleUI obs);

	void onNumberAlertEvent();

	void onSphereAlertEvent();

}
