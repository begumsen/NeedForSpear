package domain.MagicalAbilityPackage;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.ObstaclePackage.ObstacleCreator;
import domain.ObstaclePackage.ObstacleTypesPackage.GiftObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;

class AbilityCreatorTest {
	AbilityCreator abilityCreator;
	ObstacleCreator obstacleCreator;

	@BeforeEach
	void setUp() {
		obstacleCreator = new ObstacleCreator();
		abilityCreator = new AbilityCreator();
	}

	@Test
	public void expectedNullPointerException() {
		// BB throws exception
		// Checks this condition: If obsList is null, throws NullPointerException
		assertThrows(NullPointerException.class, () -> {
			abilityCreator.createMagicalAbility(null);
		});
	}

	@Test
	public void lessFourGiftObstacles() {
		// BB corner case
		//// Checks this condition: if the obsList contains less than 4 gift obstacles,
		// it assigns magical abilities to the gift obstacles so that each gift obstacle
		// has a different magical ability and returns that updated obsList
		boolean different = true;
		int[] array = { 10, 10, 10, 3 };
		obstacleCreator.addInitialObstacles(array, 1000, 1000);
		// to assign different magical abilities to gift obstacles
		ArrayList<Obstacle> newObstacles = abilityCreator.createMagicalAbility(obstacleCreator.obstacles);
		for (Obstacle obstacle : newObstacles) {
			if (obstacle.getType() == 'g') {
				MagicalAbility ability = ((GiftObstacle) obstacle).getAbility();
				for (Obstacle obs : newObstacles) {
					if (obs.getType() == 'g') {
						if (ability.getClass() == ((GiftObstacle) obs).getAbility().getClass() && obstacle != obs) {
							different = false;
						}
					}
				}
			}
		}
		assertTrue(different);
	}

	@Test
	public void moreFourGiftObstacles() {
		// BB corner case
		// Tests this condition: if the obsList contains more than 4 gift obstacles,
		// it assigns magical abilities to the gift obstacles so that each magical
		// ability appears at least once and returns that updated obsList
		// First 4 gift obstacles should contain 4 different magical abilities
		boolean different = true;
		int[] array = { 2, 2, 2, 4 };
		obstacleCreator.addInitialObstacles(array, 100, 100);
		ArrayList<Obstacle> obstacles = obstacleCreator.obstacles;
		ArrayList<Obstacle> newObstacles = abilityCreator.createMagicalAbility(obstacles);
		ArrayList<GiftObstacle> giftObstacles = new ArrayList<GiftObstacle>();
		int i = 0;
		for (Obstacle obstacle : newObstacles) {
			if (obstacle.getType() == 'g') {
				if (i < 4) {
					MagicalAbility ability = ((GiftObstacle) obstacle).getAbility();
					for (GiftObstacle gifobstacle : giftObstacles) {
						if (ability.getClass() == gifobstacle.getAbility().getClass()) {
							different = false;
						}
					}
					giftObstacles.add((GiftObstacle) obstacle);
					i++;
				}
			}
		}
		assertTrue(different);
	}

	@Test
	public void zeroGiftObstacles() {
		// BB corner case
		// Tests this condition: else it returns the same obsList as input
		// When there are 0 gift obstacles, method returns the same arrayList as input
		int[] array = { 2, 2, 2, 0 };
		obstacleCreator.addInitialObstacles(array, 100, 100);
		ArrayList<Obstacle> obstacles = obstacleCreator.obstacles;
		assertTrue(abilityCreator.createMagicalAbility(obstacles) == obstacles);
	}

	@Test
	public void firstIteration() {
		// GB testing
		// input arrayList only has one obstacle in it
		// It is a gift obstacle. According to the implementation, the first gift
		// obstacle should have ChanceGivingAbility
		int[] array = { 0, 0, 0, 1 };
		obstacleCreator.addInitialObstacles(array, 100, 100);
		ArrayList<Obstacle> obstacles = obstacleCreator.obstacles;
		assertTrue(((GiftObstacle) abilityCreator.createMagicalAbility(obstacles).get(0))
				.getAbility() instanceof ChanceGivingAbility);
	}

	@Test
	public void secondIteration() {
		// input arrayList only has two obstacle in it
		// In this test, one obstacle is a gift obstacle, other is simple.According to
		// the implementation,
		// the first obstacle should not be gift and second should be gift obstacle with
		// ChanceGivingAbility
		int[] array = { 1, 0, 0, 1 };
		obstacleCreator.addInitialObstacles(array, 100, 100);
		ArrayList<Obstacle> obstacles = obstacleCreator.obstacles;
		ArrayList<Obstacle> returned = abilityCreator.createMagicalAbility(obstacles);

		assertTrue((returned.get(0).getType() != 'g')
				&& (((GiftObstacle) returned.get(1)).getAbility() instanceof ChanceGivingAbility));
	}

	@Test
	public void lessFourGiftObstaclesOrdered() {
		// GB testing
		// Each gift obstacle should contain a different magical ability in order
		int[] array = { 2, 2, 2, 3 };
		obstacleCreator.addInitialObstacles(array, 1000, 1000);
		ArrayList<Obstacle> obstacles = obstacleCreator.obstacles;
		ArrayList<Obstacle> newObstacles = abilityCreator.createMagicalAbility(obstacles);
		ArrayList<GiftObstacle> giftObstacles = new ArrayList<GiftObstacle>();
		for (Obstacle obstacle : newObstacles) {
			if (obstacle.getType() == 'g') {
				giftObstacles.add((GiftObstacle) obstacle);
			}
		}
		assertTrue(giftObstacles.get(0).getAbility() instanceof ChanceGivingAbility);
		assertTrue(giftObstacles.get(1).getAbility() instanceof MagicalHex);
		assertTrue(giftObstacles.get(2).getAbility() instanceof NoblePhantasmExpansion);
	}

	@Test
	public void fourGiftObstaclesOrdered() {
		// GB testing
		// The implementation includes that the first 4 magical abilities are assigned
		// to
		// a type in order
		int[] array = { 2, 2, 2, 4 };
		obstacleCreator.addInitialObstacles(array, 1000, 1000);
		ArrayList<Obstacle> obstacles = obstacleCreator.obstacles;
		ArrayList<Obstacle> newObstacles = abilityCreator.createMagicalAbility(obstacles);
		ArrayList<GiftObstacle> giftObstacles = new ArrayList<GiftObstacle>();
		for (Obstacle obstacle : newObstacles) {
			if (obstacle.getType() == 'g') {
				giftObstacles.add((GiftObstacle) obstacle);
			}
		}
		assertTrue(giftObstacles.get(0).getAbility() instanceof ChanceGivingAbility);
		assertTrue(giftObstacles.get(1).getAbility() instanceof MagicalHex);
		assertTrue(giftObstacles.get(2).getAbility() instanceof NoblePhantasmExpansion);
		assertTrue(giftObstacles.get(3).getAbility() instanceof UnstoppableEnchantedSphere);
	}

	@Test
	public void moreFourGiftObstaclesOrdered() {
		// GB testing
		// First 4 gift obstacles should contain 4 different magical abilities in order
		// and rest should be random
		int[] array = { 2, 2, 2, 6 };
		obstacleCreator.addInitialObstacles(array, 100, 100);
		ArrayList<Obstacle> obstacles = obstacleCreator.obstacles;
		ArrayList<Obstacle> newObstacles = abilityCreator.createMagicalAbility(obstacles);
		ArrayList<GiftObstacle> giftObstacles = new ArrayList<GiftObstacle>();
		for (Obstacle obstacle : newObstacles) {
			if (obstacle.getType() == 'g') {
				giftObstacles.add((GiftObstacle) obstacle);
			}
		}
		assertTrue(giftObstacles.get(0).getAbility() instanceof ChanceGivingAbility);
		assertTrue(giftObstacles.get(1).getAbility() instanceof MagicalHex);
		assertTrue(giftObstacles.get(2).getAbility() instanceof NoblePhantasmExpansion);
		assertTrue(giftObstacles.get(3).getAbility() instanceof UnstoppableEnchantedSphere);
		assertTrue(giftObstacles.get(4).getAbility() instanceof MagicalAbility);
		assertTrue(giftObstacles.get(5).getAbility() instanceof MagicalAbility);
	}

}
