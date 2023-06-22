package domain.ObstaclePackage.MovingStrategy;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import domain.GameStartup;
import domain.ObstaclePackage.ObstacleCreator;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;
import domain.SaveLoadPackage.SaveLoadHandler;

public class HorizontalMovement implements IMovingBehaviorStrategy {
	public static ArrayList<Obstacle> obstacles = ObstacleCreator.getInstance().obstacles;

	public HorizontalMovement() {
		// this.obstacleCreator=obstacleCreator;
	}

	@Override
	public int[] move(double x, double y, int direction, Obstacle obs) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();

		// TODO Auto-generated method stub
		// 1 is right
		// -1 is left
		int[] array = { (int) x, (int) y, direction };
		boolean notIntersect = true;
		// Right
		if (!GameStartup.pause && !SaveLoadHandler.loadCompleted) {
			if (direction == 1) {
				for (Obstacle otherObs : obstacles) {
					if ((x + 1 > width - 57.6) || (intersect(otherObs, x + 1, y, 57.6, 30) && (otherObs != obs))) {
						notIntersect = false;
					}
				}
				if (notIntersect) {
					array[0] = (int) x + 1;
				} else {
					array[2] = -1;
				}
			}
			if (direction == -1) {
				notIntersect = true;
				for (Obstacle otherObs : obstacles) {
					if ((x - 1 < 0) || (intersect(otherObs, x - 1, y, 57.6, 30) && (otherObs != obs))) {
						notIntersect = false;
					}
				}
				if (notIntersect) {
					array[0] = (int) x - 1;
				} else {
					array[2] = 1;
				}
			}
		}
		// Left
		return array;
	}

	public boolean intersect(Obstacle obstacle, double randomx, double randomy, double sizex, double sizey) {
		double x = obstacle.getxLocation();
		double y = obstacle.getyLocation();
		double sizeX = obstacle.getSizeX();
		double sizeY = obstacle.getSizeY();
		return randomx < x + sizeX + 1 && randomx + sizex + 1 > x && randomy < y + sizeY + 1 && randomy + 1 + sizey > y;
	}
}
