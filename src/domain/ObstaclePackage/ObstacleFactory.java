package domain.ObstaclePackage;

import domain.ObstaclePackage.ObstacleTypesPackage.ExplosiveObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.FirmObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.GiftObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.PurpleObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.SimpleObstacle;

public class ObstacleFactory {
	private Obstacle obstacle;
	private static ObstacleFactory obstacleFactory;

	public ObstacleFactory() {
	}

	public static ObstacleFactory getInstance() {
		if (obstacleFactory == null) {
			obstacleFactory = new ObstacleFactory();
		}
		return obstacleFactory;
	}

	public Obstacle getObstacle(char letter, double x, double y, double sizex, double sizey, int hitNo) {
		if (letter == 's') {
			obstacle = new SimpleObstacle(x, y, sizex, sizey, 0.0, getRandom(), false, 's');
		} else if (letter == 'f') {
			obstacle = new FirmObstacle(x, y, sizex, sizey, 0.0, getRandom(), false, 'f', hitNo);
		} else if (letter == 'e') {
			obstacle = new ExplosiveObstacle(x, y, sizex, sizey, 0.0, getRandom(), false, 'e');
		} else if (letter == 'g') {
			obstacle = new GiftObstacle(x, y, sizex, sizey, 0.0, false, false, 'g');
		} else if (letter == 'p') {
			obstacle = new PurpleObstacle(x, y, sizex, sizey, 0.0, getRandom(), false, 's');
			obstacle.setMovingBehavior();
		}
		return obstacle;
	}

	public Obstacle getLoadedObstacle(char letter, double x, double y, double sizex, double sizey, int hitNo, boolean isMoving) {
		if (letter == 's') {
			obstacle = new SimpleObstacle(x, y, sizex, sizey, 0.0, isMoving, false, 's');
		} else if (letter == 'f') {
			obstacle = new FirmObstacle(x, y, sizex, sizey, 0.0, isMoving, false, 'f', hitNo);
		} else if (letter == 'e') {
			obstacle = new ExplosiveObstacle(x, y, sizex, sizey, 0.0, isMoving, false, 'e');
		} else if (letter == 'g') {
			obstacle = new GiftObstacle(x, y, sizex, sizey, 0.0, false, false, 'g');
		} else if (letter == 'p') {
			obstacle = new PurpleObstacle(x, y, sizex, sizey, 0.0, isMoving, false, 's');
			obstacle.setMovingBehavior();
		}
		return obstacle;
	}


	public boolean getRandom() {
		int randomNo = (int) (Math.random() * 10 + 1);
		if (randomNo > 2) {
			return false;
		}
		return true;
	}
}