package domain.ObstaclePackage;

import java.io.Serializable;
import java.util.ArrayList;

import domain.MagicalAbilityPackage.AbilityCreator;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;
import domain.PhantasmPackage.PaddleHandler;
import domain.RewardBoxPackage.RewardBoxCreator;
import domain.SpherePackage.EnchantedSphere;
import domain.SpherePackage.SphereHandler;
import ui.NeadForSpearUI;
import ui.ObstacleUI;

public class BuildGameHandler implements Serializable {
	AbilityCreator abilityCreator;
	private boolean gameIsOn = false;
	RewardBoxCreator rewardBoxCreator;

	public BuildGameHandler() {
		rewardBoxCreator = RewardBoxCreator.getInstance();
		ObstacleCreator.getInstance().setRewardBoxCreator(rewardBoxCreator);
		abilityCreator = new AbilityCreator();
	}

	public void addInitialObstacles(int[] array, double width, double height) {
		ObstacleCreator.getInstance().addInitialObstacles(array, width, height);

	}

	public void addRewardBox(double x, double y, double sizex, double sizey, char type) {

		rewardBoxCreator.addRewardBox(x, y, sizex, sizey, null);

	}

	public void putPhantasm(int phantasmXCoordinate) {
		PaddleHandler.paddle.loadPhantasm(phantasmXCoordinate);
	}

	public void putSphere(int x, int y, double dx, double dy) {
		SphereHandler.sphere.loadSphere(x, y, dx, dy);
	}

	public void addObstacleAfterBuild(int[] array, double width, double height, double x, double y) {
		if (y > 100 && y < height - (height / 5) - 100) {
			ObstacleCreator.getInstance().addObstacleAfterBuild(array, width, height, x, y);
		}
	}

	public void addListener(NeadForSpearUI lis) {
		ObstacleCreator.getInstance().addCreateObstacleListener(lis);
		rewardBoxCreator.addRewardBoxListener(lis);
	}

	public void removeObstacle(Obstacle obstacle, ObstacleUI obs) {
		ObstacleCreator.getInstance().removeObstacle(obstacle, obs);
	}

	public boolean isGameIsOn() {
		return gameIsOn;
	}

	public void playButtonClicked() {
		ObstacleCreator.getInstance().playButtonClicked();
		ArrayList<Obstacle> obsList = abilityCreator.createMagicalAbility(getObsList());
		ObstacleCreator.getInstance().setObstacles(obsList);
		EnchantedSphere.getInstance().setObsList(obsList);
		gameIsOn = true;

		for (Obstacle obstacle : obsList) {
			if (obstacle.getType() == 'g') {
			}
		}
		// Check

	}

	public ArrayList<Obstacle> getObsList() {
		return ObstacleCreator.getInstance().getObsList();
	}

	public boolean getIsActive() {
		return ObstacleCreator.getInstance().getIsActive();
	}

	public void setIsHit(boolean b) {
		ObstacleCreator.getInstance().setIsHit(b);

	}

}
