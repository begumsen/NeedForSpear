package domain.ObstaclePackage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.SimpleObstacle;

class ObstacleCreatorTest {
	ObstacleCreator obstacleCreator;

	@BeforeEach
	void setUp() {
		obstacleCreator = new ObstacleCreator();
	}

	@Test
	void testRepOk() {
		assertTrue(obstacleCreator.repOk());
	}

	@Test
	void testAddObstacle() {
		obstacleCreator.addObstacle('s', 10, 10, 10, 10);
		assertTrue(obstacleCreator.repOk());
		assertTrue(obstacleCreator.isIn('s', 10, 10, 10, 10));
	}

	@Test
	void testRemoveObstacle() {
		Obstacle obs = new SimpleObstacle(10, 10, 10, 10, 0.0, false, false, 's');
		obstacleCreator.removeObstacle(obs, null);
		;
		assertFalse(obstacleCreator.isIn('s', 10, 10, 10, 10));
		assertTrue(obstacleCreator.repOk());
	}

	@Test
	void testAddInitialObstaclesNumber() {
		int[] array = { 1, 2, 3, 4 };
		obstacleCreator.addInitialObstacles(array, 500, 500);
		assertTrue(obstacleCreator.getObsList().size() == 10);
		assertTrue(obstacleCreator.repOk());

	}

	@Test
	void testAddInitialObstaclesType() {
		int[] array = { 1, 2, 3, 4 };
		obstacleCreator.addInitialObstacles(array, 500, 500);
		assertTrue(obstacleCreator.getObsList().get(0).getType() == 's');
		assertTrue(obstacleCreator.getObsList().get(1).getType() == 'f');
		assertTrue(obstacleCreator.getObsList().get(2).getType() == 'f');
		assertTrue(obstacleCreator.getObsList().get(3).getType() == 'e');
		assertTrue(obstacleCreator.getObsList().get(4).getType() == 'e');
		assertTrue(obstacleCreator.getObsList().get(5).getType() == 'e');
		assertTrue(obstacleCreator.getObsList().get(6).getType() == 'g');
		assertTrue(obstacleCreator.getObsList().get(7).getType() == 'g');
		assertTrue(obstacleCreator.getObsList().get(8).getType() == 'g');
		assertTrue(obstacleCreator.getObsList().get(9).getType() == 'g');
	}

	@Test
	void testDoNotAddInitialObstacles() {
		// When isActive is false, the obstacles should not be added.
		obstacleCreator.setActive(false);
		int[] array = { 1, 2, 3, 4 };
		obstacleCreator.addInitialObstacles(array, 500, 500);
		assertTrue(obstacleCreator.getObsList().size() == 0);
		assertTrue(obstacleCreator.repOk());

	}

	@Test
	void testAddInitialObstacles() {
		// When isActive is false, the obstacles should not be added.
		obstacleCreator.setActive(true);
		int[] array = { 75, 10, 5, 10 };
		obstacleCreator.addInitialObstacles(array, 1500, 1500);
		assertTrue(obstacleCreator.getObsList().size() == 100);
		assertTrue(obstacleCreator.repOk());
	}

	@Test
	void testPlayButtonClickedNotSatisfied() {
		int[] array = { 1, 2, 3, 4 };
		obstacleCreator.addInitialObstacles(array, 500, 500);
		obstacleCreator.playButtonClicked();
		assertFalse(obstacleCreator.isSatisfied());
		assertTrue(obstacleCreator.repOk());
	}

	@Test
	void testPlayButtonClickedSatisfied() {
		int[] array = { 75, 10, 5, 10 };
		obstacleCreator.addInitialObstacles(array, 1500, 1500);
		obstacleCreator.playButtonClicked();
		assertTrue(obstacleCreator.isSatisfied());
		assertTrue(obstacleCreator.repOk());

	}

}
