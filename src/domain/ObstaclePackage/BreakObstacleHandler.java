package domain.ObstaclePackage;

import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;
import ui.ObstacleUI;

public class BreakObstacleHandler {

	public void addListener(Obstacle obstacle, ObstacleUI obstacleUI) {
		obstacle.addBreakListener(obstacleUI);
	}

}
