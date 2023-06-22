package domain.ObstaclePackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.GameStartup;
import domain.MagicalAbilityPackage.ExplosiveRemains;
import domain.ObstaclePackage.ObstacleTypesPackage.ExplosiveObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.FirmObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.GiftObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.PurpleObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.SimpleObstacle;
import domain.PlayerPackage.IPlayerListener;
import domain.PlayerPackage.Player;
import domain.RewardBoxPackage.RewardBoxCreator;
import domain.SaveLoadPackage.SaveLoadHandler;
import domain.SpherePackage.EnchantedSphere;
import ui.NeadForSpearUI;
import ui.ObstacleUI;
import domain.PhantasmPackage.Phantasm;

public class ObstacleCreator implements Serializable {
	// OVERVIEW: ObstacleCreator is a mutable class where obstacles are created and
	// stored
	// the rep

	public ArrayList<Obstacle> obstacles;
	int playButtonClickedNumber = 0;
	List<ObstacleListener> obstacleListeners = new ArrayList<>();
	boolean isActive;
	boolean isHit = false;
	boolean satisfied = false;
	RewardBoxCreator rewardBoxCreator;
	private static ObstacleCreator obstacleCreator;
	private int infiniteVoidCancelTime = -1;

	// The abstraction function is
	// AF(c) = c.getObsList().get(i) | 0 <= i < c.getObsList().size()

	// The rep invariant is
	// c.obstacles not null&&
	// c.obstacleListeners not null

	// constructor
	public ObstacleCreator() {
		obstacles = new ArrayList<Obstacle>();
		obstacleListeners = new ArrayList<>();
		isActive = true;
	}

	// methods
	public static ObstacleCreator getInstance() {
		if (obstacleCreator == null) {
			obstacleCreator = new ObstacleCreator();
		}
		return obstacleCreator;
	}

	public void setRewardBoxCreator(RewardBoxCreator rewardBoxCreator) {
		this.rewardBoxCreator = rewardBoxCreator;
	}

	public void addObstacle(char type, double x, double y, double sizex, double sizey) {
		// MODIFIES: obstacles
		// EFFECTS: Initializes an obstacle and adds it to the obstacles
		if (isActive) {
			Obstacle obs = ObstacleFactory.getInstance().getObstacle(type, x, y, sizex, sizey, getRandom(5));
			obstacles.add(obs);
		}
	}

	public void addObstacle(char type, double x, double y, double sizex, double sizey, int hitNum) {
		// MODIFIES: obstacles
		// EFFECTS: Initializes an obstacle and adds it to the obstacles
		if (isActive) {
			Obstacle obs = ObstacleFactory.getInstance().getObstacle(type, x, y, sizex, sizey, hitNum);
			obstacles.add(obs);
		}
	}

	public void addInitialObstacles(int[] array, double width, double height) {

		/*
		 * REQUIRES: the width and the height of the screen ,an array with number of
		 * obstacle in each index array[0]= simple array[1}= firm array[2]= explosive
		 * array[3]= gift
		 */
		// MODIFIES: obstacles (ArrayList<Obstacle>)
		/*
		 * EFFECTS: If isActive is true, it initializes obstacles according to the
		 * quantities given in the array and adds them to the obstacles arraylist
		 */

		if (isActive && !SaveLoadHandler.loadCompleted) {
			int simpleNo = array[0];
			int firmNo = array[1];
			int explosiveNo = array[2];
			int giftNo = array[3];

			// determine random locations
			// create and add obstacles according to their types
			for (int i = 0; i < simpleNo; i++) {
				double[] locations = setRandomLocationX(width, height, 57.6, 30);
				addObstacle('s', locations[0], locations[1], 57.6, 30);
				// sizex=57.6
			}
			for (int i = 0; i < firmNo; i++) {
				double[] locations = setRandomLocationX(width, height, 57.6, 30);
				addObstacle('f', locations[0], locations[1], 57.6, 30);
			}
			for (int i = 0; i < explosiveNo; i++) {
				double[] locations = setRandomLocationX(width, height, 15, 15);
				addObstacle('e', locations[0], locations[1], 15, 15);
			}
			for (int i = 0; i < giftNo; i++) {
				double[] locations = setRandomLocationX(width, height, 57.6, 30);
				addObstacle('g', locations[0], locations[1], 57.6, 30);
			}
			publishCreateObstacleEvent();
		}

	}

	public void addObstacle(char type, double x, double y, double sizex, double sizey, boolean isMoving) {
		// MODIFIES: obstacles
		// EFFECTS: Initializes an obstacle and adds it to the obstacles
		if (isActive) {
			Obstacle obs = ObstacleFactory.getInstance().getLoadedObstacle(type, x, y, sizex, sizey, getRandom(5), isMoving);
			obstacles.add(obs);
		}
	}

	public void addObstacle(char type, double x, double y, double sizex, double sizey, int hitNum, boolean isMoving) {
		// MODIFIES: obstacles
		// EFFECTS: Initializes an obstacle and adds it to the obstacles
		if (isActive) {
			Obstacle obs = ObstacleFactory.getInstance().getLoadedObstacle(type, x, y, sizex, sizey, hitNum, isMoving);
			obstacles.add(obs);
		}
	}


	public void addLoadedObstacles(ArrayList<Obstacle> list, double width, double height) {
		// 's' 'f' 'e' 'g'
		if (isActive) {
			if (!SaveLoadHandler.loadCompleted){
				for (Obstacle obstacle : list) {
					if (obstacle instanceof SimpleObstacle) {
						addObstacle('s', obstacle.getxLocation(), obstacle.getyLocation(), 57.6, 30);
					}
					if (obstacle instanceof FirmObstacle) {
						addObstacle('f', obstacle.getxLocation(), obstacle.getyLocation(), 57.6, 30,
								((FirmObstacle) obstacle).getHitNum());
					}
					if (obstacle instanceof ExplosiveObstacle) {
						addObstacle('e', obstacle.getxLocation(), obstacle.getyLocation(), 15, 15);
					}
					if (obstacle instanceof GiftObstacle) {
						addObstacle('g', obstacle.getxLocation(), obstacle.getyLocation(), 57.6, 30);
					}
					if (obstacle instanceof PurpleObstacle) {
						addObstacle('p', obstacle.getxLocation(), obstacle.getyLocation(), 57.6, 30);
					}
				}
				publishCreateObstacleEvent();
			} else {
				for (Obstacle obstacle : list) {
					if (obstacle instanceof SimpleObstacle) {
						addObstacle('s', obstacle.getxLocation(), obstacle.getyLocation(), 57.6, 30, obstacle.isMoving());
					}
					if (obstacle instanceof FirmObstacle) {
						addObstacle('f', obstacle.getxLocation(), obstacle.getyLocation(), 57.6, 30,
								((FirmObstacle) obstacle).getHitNum() , obstacle.isMoving());
					}
					if (obstacle instanceof ExplosiveObstacle) {
						addObstacle('e', obstacle.getxLocation(), obstacle.getyLocation(), 15, 15 , obstacle.isMoving());
					}
					if (obstacle instanceof GiftObstacle) {
						addObstacle('g', obstacle.getxLocation(), obstacle.getyLocation(), 57.6, 30 , obstacle.isMoving());
					}
					if (obstacle instanceof PurpleObstacle) {
						addObstacle('p', obstacle.getxLocation(), obstacle.getyLocation(), 57.6, 30 , obstacle.isMoving());
					}
				}
				publishCreateObstacleEvent();
			}
		}

	}

	public int[] getIndexArray() {
		return indexArray;
	}

	public void setIndexArray(int[] indexArray) {
		this.indexArray = indexArray;
	}

	int[] indexArray = new int[7];


	public void addObstacleAfterBuild(int[] array, double width, double height, double x, double y) {
		// 's' 'f' 'e' 'g'
		if (isActive) {
			int simpleNo = array[0];
			int firmNo = array[1];
			int explosiveNo = array[2];
			int giftNo = array[3];

			// determine random locations
			// create and add obstacles according to their types

			for (int i = 0; i < simpleNo; i++) {
				if (!findEmptyRandomSpace(width, height, x, y, 57.6, 30)) {
					addObstacle('s', x, y, 57.6, 30);
					publishCreateObstacleEvent();
				}
			}
			for (int i = 0; i < firmNo; i++) {
				if (!findEmptyRandomSpace(width, height, x, y, 57.6, 30)) {
					addObstacle('f', x, y, 57.6, 30);
					publishCreateObstacleEvent();
				}
			}
			for (int i = 0; i < explosiveNo; i++) {
				if (!findEmptyRandomSpace(width, height, x, y, 15, 15)) {
					addObstacle('e', x, y, 15, 15);
					publishCreateObstacleEvent();
				}
			}
			for (int i = 0; i < giftNo; i++) {
				if (!findEmptyRandomSpace(width, height, x, y, 57.6, 30)) {
					addObstacle('g', x, y, 57.6, 30);
					publishCreateObstacleEvent();
				}
			}
		}

	}

	public void addPurpleObstacles(int num, double width, double height) {
		// MODIFIES: obstacles
		// EFFECTS: Initializes obstacles according to the quantities and adds them to
		// the obstacles arraylist
		// 's' 'f' 'e' 'g'
		// determine random locations
		// create and add obstacles according to their types
		for (int i = 0; i < num; i++) {
			double[] locations = setRandomLocationX(width, height, 57.6, 30);
			Obstacle obs = ObstacleFactory.getInstance().getObstacle('p', locations[0], locations[1], 57.6, 30,
					getRandom(5));
			obstacles.add(obs);
			// sizex=57.6
		}
		publishCreateObstacleEvent();
	}

	// Find an empty random location
	public double[] setRandomLocationX(double width, double height, double sizex, double sizey) {
		double[] array = new double[2];
		boolean again = true;
		double randomx = 0;
		double randomy = 0;
		while (again) {
			randomx = getRandom((int) (width - (57.6)));
			randomy = 100 + getRandom((int) (height - (height / 5) - 300));
			again = findEmptyRandomSpace(width, height, randomx, randomy, sizex, sizey);
		}
		array[0] = randomx;
		array[1] = randomy;
		return array;
	}

	// Helper methods for random location
	public int getRandom(int max) {
		int randomNo = (int) (Math.random() * max + 1);
		return randomNo;

	}

	public boolean findEmptyRandomSpace(double width, double height, double randomx, double randomy, double sizex,
			double sizey) {
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle obstacle = obstacles.get(i);
			// intersect
			if (intersect(obstacle, randomx, randomy, sizex, sizey)) {
				return true;
			}
		}
		return false;
	}

	public void addCreateObstacleListener(NeadForSpearUI lis) {
		// TODO Auto-generated method stub
		obstacleListeners.add(lis);

	}

	public ArrayList<Obstacle> getObsList() {
		return obstacles;
	}

	public void publishCreateObstacleEvent() {
		for (ObstacleListener l : obstacleListeners)
			l.onCreateObstacleEvent(obstacles);

	}

	public boolean intersect(Obstacle obstacle, double randomx, double randomy, double sizex, double sizey) {
		double x = obstacle.getxLocation();
		double y = obstacle.getyLocation();
		double sizeX = obstacle.getSizeX();
		double sizeY = obstacle.getSizeY();
		return randomx < x + sizeX + 3 && randomx + sizex + 3 > x && randomy < y + sizeY + 3 && randomy + 3 + sizey > y;
	}

//randomy < y + sizeY + 3
	// 100<90+30 diğer obstacle
	// randomy + 3 + sizeY > y
	// 100+>90 o obstacle
	public void removeObstacle(Obstacle obstacle, ObstacleUI obs) {
		// MODIFIES: obstacles
		// EFFECTS: Removes an obstacle from the obstacles
		if (isActive || isHit) {
			for (Obstacle obsobject : obstacles) {
				if (contains(obstacle.getxLocation(), obstacle.getyLocation(), obsobject.getxLocation(),
						obsobject.getyLocation(), obsobject.getSizeX(), obsobject.getSizeY())) {

					if (isHit && obsobject.getType() == 'g') {

						rewardBoxCreator.addRewardBox(obsobject.getxLocation(), obsobject.getyLocation(), 50, 50,
								((GiftObstacle) obsobject).getAbility());

					}
					if (isHit && obsobject.getType() == 'e') {

						rewardBoxCreator.addRewardBox(obsobject.getxLocation(), obsobject.getyLocation(), 50, 50,
								new ExplosiveRemains());

					}
					if (isHit && !(obsobject instanceof PurpleObstacle)) {
						Player.getInstance().updateScore();
					}

					obstacles.remove(obsobject);
					EnchantedSphere.getInstance().setObsList(obstacles);

					publishRemoveObstacleEvent(obstacle, obs);

					isHit = false;
					break;

				}
			}
		}
	}

	public void publishRemoveObstacleEvent(Obstacle obstacle, ObstacleUI obs) {
		for (ObstacleListener l : obstacleListeners)
			l.onRemoveObstacleEvent(obstacle, obs);

	}

	public boolean contains(double d, double e, double f, double g, double h, double i) {
		if (f <= d && d <= f + h && g <= e && e <= g + i)
			return true;

		return false;
	}

	public void setSatisfied(boolean satisfied) {
		this.satisfied = satisfied;
	}

	public boolean isSatisfied() {
		return satisfied;
	}

	public boolean isGameIsOn() {
		return gameIsOn;
	}

	private boolean gameIsOn = false;

	public void playButtonClicked() {
		// MODIFIES: isActive, satisfied
		// EFFECTS: check if numbers of obstacles match the prerequisites and publish
		// accordingly
		gameIsOn = true;
		int simpleNo = 0;
		int firmNo = 0;
		int explosiveNo = 0;
		int giftNo = 0;
		for (Obstacle obs : obstacles) {
			if (obs.getType() == 's') {
				simpleNo++;
			} else if (obs.getType() == 'f') {
				firmNo++;
			} else if (obs.getType() == 'e') {
				explosiveNo++;
			} else {
				giftNo++;
			}
			if (simpleNo >= 75 && firmNo >= 10 && explosiveNo >= 5 && giftNo >= 10) {

				satisfied = true;
			}
		}
		if ((satisfied == false) && (!SaveLoadHandler.loadCompleted) && playButtonClickedNumber == 0) {
			publishNumberAlert();
		} else {
			isActive = false;
			Phantasm.getInstance().setPaintTime(GameStartup.getTimerCount()+1);
			for (Obstacle obs : obstacles) {
				obs.setMovingBehavior();
				obs.setPaintTime(GameStartup.getTimerCount()+1);
			}
			if (!SaveLoadHandler.loadCompleted)
				isActive = isActive;
			// publishSphere();
			if (SaveLoadHandler.loadCompleted) {
				SaveLoadHandler.loadCompleted = false;
				playButtonClickedNumber++;
			}
		}
	}

	public int getInfiniteVoidCancelTime() {
		return infiniteVoidCancelTime;
	}

	public void setInfiniteVoidCancelTime(int infiniteVoidCancelTime) {
		this.infiniteVoidCancelTime = infiniteVoidCancelTime;
	}

	public void activateInfiniteVoid() {
		// MODIFIES: obstacles' obstacles
		// EFFECTS: 8 obstacles are randomly chosen. (If there exists less, all of them
		// are chosen.)
		// These chosen obstacles are turned into frozen.

		if (!this.isActive) {
			int randomIndex = 0;
			int i = 0;
			this.isActive=true;
			if (this.obstacles.size() > 8) {
				// The while loop will continue until it has 8 different index values
				while (i != 7) {
					randomIndex = (int) (Math.random() * (this.obstacles.size() - 1));
					// We have to check if the previous random indexes and the current random index
					// are different or not.
					// If not different, then we have to do another random to get a different
					boolean isDifferentIndex = true;
					for (int prevIndex : indexArray) {
						if (randomIndex == prevIndex) {
							isDifferentIndex = false;
							// The current index has been chosen before so we have to change it.
							break;
						}
					}

					if (isDifferentIndex) {
						indexArray[i] = randomIndex;
						this.obstacles.get(randomIndex).setFrozen(true);
						i++;
					}
				}
			} else {
				// When there are less than 8 obstacles exist
				for (Obstacle obs : this.obstacles) {
					obs.setFrozen(true);
				}
			}
			if (!SaveLoadHandler.loadCompleted) {
				this.setInfiniteVoidCancelTime(GameStartup.getTimeInSeconds() + 15);
			}
		}
	}

	public void loadInfiniteVoid(int[] indexArray) {
		for (Integer integer : indexArray){
			this.obstacles.get(integer).setFrozen(true);
		}


	}


	public void deactivateInfiniteVoid() {
		// MODIFIES: obstacles' obstacles
		// EFFECTS: makes all existing obstacles non-frozen
		for (Obstacle obs : this.obstacles) {
			obs.setFrozen(false);
		}
	}

	public void publishSphere() {
		for (ObstacleListener l : obstacleListeners)
			l.onSphereAlertEvent();
	}

	public void publishNumberAlert() {
		for (ObstacleListener l : obstacleListeners)
			l.onNumberAlertEvent();
	}

	public void setObstacles(ArrayList<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsHit(boolean isHit) {
		this.isHit = isHit;
	}

	public boolean isIn(char type, double x, double y, double sizex, double sizey) {
		for (Obstacle obs : obstacles) {
			if (obs.getxLocation() == x && obs.getyLocation() == y && obs.getSizeX() == sizex && obs.getSizeY() == sizey
					&& obs.getType() == type)
				return true;
		}
		return false;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean repOk() {
		if (obstacles == null)
			return false;
		if (obstacleListeners == null)
			return false;

		return true;
	}

	public void checkWin() {
		// TODO Auto-generated method stub
		//No obstacles left
		if(obstacles.size()==0&&!this.isActive) {
			Player.getInstance().publishGameOverEvent();
		}
		
	}


}