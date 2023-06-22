package domain.SpherePackage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.ObstaclePackage.ObstacleFactory;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;

class EnchantedSphereReflectionOnObstacleTest {

	EnchantedSphere sphere = EnchantedSphere.getInstance();

	@BeforeEach
	void setUp() {
		sphere.setxLocation(100);
		sphere.setyLocation(100);
		sphere.setRadius(5);

		Rectangle rect = new Rectangle(sphere.getxLocation(), sphere.getyLocation(), sphere.getRadius() * 2,
				sphere.getRadius() * 2);
		sphere.setRectOfSphere(rect);

		sphere.obslist = new ArrayList<Obstacle>();

		// Creating examples of obstacles that would interact with the sphere with
		// different side and corners
		Obstacle obs1 = ObstacleFactory.getInstance().getObstacle('s', 85, 105, 40, 30, 0);
		Obstacle obs2 = ObstacleFactory.getInstance().getObstacle('s', 85, 75, 40, 30, 0);
		Obstacle obs3 = ObstacleFactory.getInstance().getObstacle('s', 109, 85, 40, 30, 0);
		Obstacle obs4 = ObstacleFactory.getInstance().getObstacle('s', 64, 85, 40, 30, 0);
		Obstacle obs5 = ObstacleFactory.getInstance().getObstacle('s', 107, 107, 40, 30, 0);
		Obstacle obs6 = ObstacleFactory.getInstance().getObstacle('s', 63, 107, 40, 30, 0);
		Obstacle obs7 = ObstacleFactory.getInstance().getObstacle('s', 109, 71, 40, 30, 0);
		Obstacle obs8 = ObstacleFactory.getInstance().getObstacle('s', 62, 71, 40, 30, 0);
		Obstacle obs9 = ObstacleFactory.getInstance().getObstacle('s', 1, 1, 40, 30, 0);

		sphere.obslist.add(obs1);
		sphere.obslist.add(obs2);
		sphere.obslist.add(obs3);
		sphere.obslist.add(obs4);
		sphere.obslist.add(obs5);
		sphere.obslist.add(obs6);
		sphere.obslist.add(obs7);
		sphere.obslist.add(obs8);
		sphere.obslist.add(obs9);

		sphere.setUnstoppable(false);

	}

	@Test
	void testOnTopSide() {
		sphere.setDx(4.0);
		sphere.setDy(5.0);
		sphere.reflectOnObstacle(0);
		assertTrue((sphere.getDx() == 4.0) && (sphere.getDy() == -5.0));

	}

	@Test
	void testOnBottomSide() {
		sphere.setDx(4.0);
		sphere.setDy(-5.0);
		sphere.reflectOnObstacle(1);
		assertTrue((sphere.getDx() == 4.0) && (sphere.getDy() == 5.0));
	}

	@Test
	void testOnLeftSide() {
		sphere.setDx(4.0);
		sphere.setDy(-5.0);
		sphere.reflectOnObstacle(2);
		assertTrue((sphere.getDx() == -4.0) && (sphere.getDy() == -5.0));
	}

	@Test
	void testOnRightSide() {
		sphere.setDx(-4.0);
		sphere.setDy(-5.0);
		sphere.reflectOnObstacle(3);
		assertTrue((sphere.getDx() == 4.0) && (sphere.getDy() == -5.0));
	}

	@Test
	void testOnTopLeftCorner() {
		sphere.setDx(4.0);
		sphere.setDy(5.0);
		sphere.reflectOnObstacle(4);
		assertTrue((sphere.getDx() == -5.0) && (sphere.getDy() == -4.0));
	}

	@Test
	void testOnTopRightCorner() {
		sphere.setDx(4.0);
		sphere.setDy(5.0);
		sphere.reflectOnObstacle(5);
		assertTrue((sphere.getDx() == 5.0) && (sphere.getDy() == 4.0));
	}

	@Test
	void testOnBottomLeftCorner() {
		sphere.setDx(4.0);
		sphere.setDy(-5.0);
		sphere.reflectOnObstacle(6);
		assertTrue((sphere.getDx() == -5.0) && (sphere.getDy() == 4.0));
	}

	@Test
	void testOnBottomRightCorner() {
		sphere.setDx(-4.0);
		sphere.setDy(5.0);
		sphere.reflectOnObstacle(7);
		assertTrue((sphere.getDx() == -5.0) && (sphere.getDy() == 4.0));
	}

	@Test
	void testOnUnstoppableSphere() {
		sphere.setDx(4.0);
		sphere.setDy(5.0);
		sphere.setUnstoppable(true);
		sphere.reflectOnObstacle(0);
		assertTrue((sphere.getDx() == 4.0) && (sphere.getDy() == 5.0));
	}

	@Test
	void testOnNoCollision() {
		sphere.setDx(4.0);
		sphere.setDy(5.0);
		sphere.reflectOnObstacle(8);
		assertTrue((sphere.getDx() == 4.0) && (sphere.getDy() == 5.0));
	}

}
