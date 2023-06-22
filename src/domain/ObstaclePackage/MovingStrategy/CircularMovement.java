package domain.ObstaclePackage.MovingStrategy;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import domain.GameStartup;
import domain.ObstaclePackage.ObstacleCreator;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;

public class CircularMovement implements IMovingBehaviorStrategy {
	public static ArrayList<Obstacle> obstacles = ObstacleCreator.getInstance().obstacles;
	double angle = 0;
	double xLoc = 0;
	double yLoc = 0;

	public CircularMovement(double x, double y) {
		xLoc = x + 15;
		yLoc = y - 15;

	}

	@Override
	public int[] move(double x, double y, int direction, Obstacle obs) {
		int[] array = { (int) x, (int) y, direction };
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		boolean notIntersect = true;
		for (Obstacle otherObs : obstacles) {
			if ((x + 1 > width - 15) || (intersect(otherObs, (xLoc + 15 * Math.cos(angle + 0.01)),
					(yLoc + 15 * Math.sin(angle + 0.01)), 15, 15) && (otherObs != obs))) {
				notIntersect = false;
			}
		}
		if (!GameStartup.pause) {
			if (notIntersect) {
				angle += 0.01;
				array[0] = (int) (15 * Math.cos(angle) + xLoc);
				array[1] = (int) (15 * Math.sin(angle) + yLoc);
				array[2] = 1;
			} else {
				// obs.setMovingBehavior(new StiffMovement());
				array[0] = (int) x;
				array[1] = (int) y;
			}
		}
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
