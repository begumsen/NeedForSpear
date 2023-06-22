package domain.ObstaclePackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;

class AddInitialObstacleTest {

	ObstacleCreator obstacleCreator;

	@BeforeEach
	void setUp() {
		obstacleCreator = new ObstacleCreator();
	}

	@Test
	public void expectedNullPointerException() {
		// BB throws exception
		assertThrows(NullPointerException.class, () -> {
			obstacleCreator.addInitialObstacles(null, 1000, 1000);
		});
	}

	@Test
	public void addInitialObstacle() {
		// Check if the initial obstacles are added in the correct number

		int[] array = { 1, 1, 1, 1 };
		obstacleCreator.addInitialObstacles(array, 1000, 1000);
		assertEquals(4, obstacleCreator.getObsList().size());
	}

	@Test
	public void ObstacleNotAdded() {
		// Check if after we clicked play button the obstacles are added or not
		// After we clicked play button we shouldn't add any obstacles

		obstacleCreator.setActive(false);
		int[] array = { 1, 1, 1, 1 };
		obstacleCreator.addInitialObstacles(array, 1000, 1000);
		assertEquals(0, obstacleCreator.getObsList().size());
	}

	@Test
	public void ObstacleAdded() {
		// Before we click the play button we should add obstacles

		obstacleCreator.setActive(true);
		int[] array = { 1, 1, 1, 1 };
		obstacleCreator.addInitialObstacles(array, 1000, 1000);
		assertEquals(4, obstacleCreator.getObsList().size());
	}

	@Test
	public void ObstacleAddedCorrectType() {
		// check if the obstacles are added in the correct order and type

		int[] array = { 1, 1, 1, 1 };
		obstacleCreator.addInitialObstacles(array, 1000, 1000);
		assertEquals('s', obstacleCreator.getObsList().get(0).getType());
		assertEquals('f', obstacleCreator.getObsList().get(1).getType());
		assertEquals('e', obstacleCreator.getObsList().get(2).getType());
		assertEquals('g', obstacleCreator.getObsList().get(3).getType());
	}

	@Test
	public void ObstacleAddedCorrectNumber() {
		// check if the obstacles are added in the correct number given by the array

		int[] array = { 1, 2, 3, 4 };
		obstacleCreator.addInitialObstacles(array, 1000, 1000);
		assertEquals('s', obstacleCreator.getObsList().get(0).getType());
		assertEquals('f', obstacleCreator.getObsList().get(1).getType());
		assertEquals('f', obstacleCreator.getObsList().get(2).getType());
		assertEquals('e', obstacleCreator.getObsList().get(3).getType());
		assertEquals('e', obstacleCreator.getObsList().get(4).getType());
		assertEquals('e', obstacleCreator.getObsList().get(5).getType());
		assertEquals('g', obstacleCreator.getObsList().get(6).getType());
		assertEquals('g', obstacleCreator.getObsList().get(7).getType());
		assertEquals('g', obstacleCreator.getObsList().get(8).getType());
		assertEquals('g', obstacleCreator.getObsList().get(9).getType());
	}

	@Test
	public void ObstaclesNotIntersect() {
		// check if the obstacles do not collide/intersect with each other

		boolean bool = false;
		int[] array = { 75, 10, 5, 10 };
		obstacleCreator.addInitialObstacles(array, 1500, 1500);
		Obstacle obs = obstacleCreator.getObsList().get(0);
		for (Obstacle obstacle : obstacleCreator.getObsList()) {
			if (obstacleCreator.intersect(obstacle, obs.getxLocation(), obs.getyLocation(), obs.getSizeX(),
					obs.getSizeY())) {
				if (obstacle != obs) {
					bool = true;
				}
			}

		}
		assertFalse(bool);
	}

	@Test
	public void SimpleObstacleForLoopFirstIteration() {
		// GB for the for loop of the simpleObstacle
		int[] array = { 1, 0, 0, 0 };
		obstacleCreator.addInitialObstacles(array, 1000, 1000);
		assertEquals('s', obstacleCreator.getObsList().get(0).getType());

	}

	@Test
	public void SimpleObstacleForLoopSecondIteration() {
		// GB for the for loop of the simpleObstacle
		int[] array = { 2, 0, 0, 0 };
		obstacleCreator.addInitialObstacles(array, 1000, 1000);
		assertEquals('s', obstacleCreator.getObsList().get(0).getType());
		assertEquals('s', obstacleCreator.getObsList().get(1).getType());

	}

	@Test
	public void FirmObstacleForLoopFirstIteration() {
		// GB for the for loop of the simpleObstacle
		int[] array = { 0, 1, 0, 0 };
		obstacleCreator.addInitialObstacles(array, 1000, 1000);
		assertEquals('f', obstacleCreator.getObsList().get(0).getType());

	}

	@Test
	public void FirmObstacleForLoopSecondIteration() {
		// GB for the for loop of the simpleObstacle
		int[] array = { 0, 2, 0, 0 };
		obstacleCreator.addInitialObstacles(array, 1000, 1000);
		assertEquals('f', obstacleCreator.getObsList().get(0).getType());
		assertEquals('f', obstacleCreator.getObsList().get(1).getType());
	}

}
