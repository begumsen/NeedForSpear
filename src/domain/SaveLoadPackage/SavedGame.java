package domain.SaveLoadPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.GameStartup;
import domain.MagicalAbilityPackage.IMagicalAbilityListener;
import domain.MagicalAbilityPackage.MagicalHex;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;
import domain.RewardBoxPackage.RewardBox;
import domain.SpherePackage.EnchantedSphere;

public class SavedGame implements Serializable {
	private int paddleXcoordinate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String userName;
	private int paddleExpansionCanceltime;
	private double playerScore;
	private int playerChanceCount;

	public int[] getInfiniteVoidIndexArray() {
		return infiniteVoidIndexArray;
	}

	public void setInfiniteVoidIndexArray(int[] infiniteVoidIndexArray) {
		this.infiniteVoidIndexArray = infiniteVoidIndexArray;
	}

	private int[] infiniteVoidIndexArray ;

	public String getPaddleMoveDirection() {
		return paddleMoveDirection;
	}

	private int paddleLength;

	public int getDoubleAccelCancelTime() {
		return doubleAccelCancelTime;
	}

	public void setDoubleAccelCancelTime(int doubleAccelCancelTime) {
		this.doubleAccelCancelTime = doubleAccelCancelTime;
	}

	private int doubleAccelCancelTime;

	public ArrayList<Obstacle> getCurrentObstacleList() {
		return currentObstacleList;
	}

	private boolean isPaddleExpanded;
	private int sphereUnstoppableCancelTime;
	private boolean sphereIsUnstoppable;

	public boolean isSphereIsUnstoppable() {
		return sphereIsUnstoppable;
	}

	public int getGameTime() {
		return gameTime;
	}

	public ArrayList<RewardBox> getRewardBoxList() {
		return rewardBoxList;
	}

	public void setRewardBoxList(ArrayList<RewardBox> rewardBoxList) {
		this.rewardBoxList = rewardBoxList;
	}

	private ArrayList<RewardBox> rewardBoxList;

	public int getInfVoidCancelTime() {
		return infVoidCancelTime;
	}

	public void setInfVoidCancelTime(int infVoidCancelTime) {
		this.infVoidCancelTime = infVoidCancelTime;
	}

	private int infVoidCancelTime;

	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}

	private int gameTime = GameStartup.getTimeInSeconds();

	public int[] getPlayerAbilityCount() {
		return playerAbilityCount;
	}

	private int[] playerAbilityCount;

	public int getSphereUnstoppableCancelTime() {
		return sphereUnstoppableCancelTime;
	}

	public int getSphereYcoordinate() {
		return sphereYcoordinate;
	}

	public int getSphereXcoordinate() {
		return sphereXcoordinate;
	}

	public boolean isPaddleExpanded() {
		return isPaddleExpanded;
	}

	public int getPaddleLength() {
		return paddleLength;
	}

	public int getPlayerChanceCount() {
		return playerChanceCount;
	}

	public boolean[] getChancesVisible() {
		return chancesVisible;
	}

	public void setChancesVisible(boolean[] chancesVisible) {
		this.chancesVisible = chancesVisible;
	}

	private boolean[] chancesVisible;

	public int getPaddleExpansionCanceltime() {
		return paddleExpansionCanceltime;
	}

	public double getPlayerScore() {
		return playerScore;
	}

	private int sphereXcoordinate;

	public int getPaddleXcoordinate() {
		return paddleXcoordinate;
	}

	private int sphereYcoordinate;
	private String paddleMoveDirection;

	public double getSphereDx() {
		return sphereDx;
	}

	public void setSphereDx(double sphereDx) {
		this.sphereDx = sphereDx;
	}

	private double sphereDx;

	public double getSphereDy() {
		return sphereDy;
	}

	public void setSphereDy(double sphereDy) {
		this.sphereDy = sphereDy;
	}

	private double sphereDy;

	public int getPhantasmRotationAngle() {
		return phantasmRotationAngle;
	}

	public void setPhantasmRotationAngle(int phantasmRotationAngle) {
		this.phantasmRotationAngle = phantasmRotationAngle;
	}

	public boolean[] getAbilityVisible() {
		return abilityVisible;
	}

	public void setAbilityVisible(boolean[] abilityVisible) {
		this.abilityVisible = abilityVisible;
	}

	private boolean[] abilityVisible;

	public double getViolentAbilityCancelTime() {
		return violentAbilityCancelTime;
	}

	public void setViolentAbilityCancelTime(int violentAbilityCancelTime) {
		this.violentAbilityCancelTime = violentAbilityCancelTime;
	}

	private double violentAbilityCancelTime;

	public int getMagicalHexCancelTime() {
		return magicalHexCancelTime;
	}

	public void setMagicalHexCancelTime(int magicalHexCancelTime) {
		this.magicalHexCancelTime = magicalHexCancelTime;
	}

	private int magicalHexCancelTime;

	public int getHexReleaseTime() {
		return hexReleaseTime;
	}

	public void setHexReleaseTime(int hexReleaseTime) {
		this.hexReleaseTime = hexReleaseTime;
	}

	private int hexReleaseTime;

	public ArrayList<MagicalHex> getMagicalHexList() {
		return magicalHexList;
	}


	ArrayList<MagicalHex> magicalHexList;




	List<IMagicalAbilityListener> magicalHexListeners;

	private int phantasmRotationAngle;

	public SavedGame(double playerScore, int paddleExpansionCanceltime, int paddleXcoordinate, int playerChanceCount,
                     int paddleLength, boolean isPaddleExpanded, int sphereUnstoppableCancelTime, boolean sphereIsUnstoppable,
                     int sphereXcoordinate, int sphereYcoordinate, String paddleMoveDirection,
                     ArrayList<Obstacle> currentObstacleList, ArrayList<RewardBox> rewardBoxList, int phantasmRotationAngle,
                     int[] playerAbilityCount, boolean[] chancesVisible, boolean[] abilityvisible, int doubleAccelCancelTime, int infVoidCancelTime,
                     double violentAbilityCancelTime,
                     int[] infiniteVoidIndexArray,
                     double sphereDx, double sphereDy, int magicalHexCancelTime, int hexReleaseTime, ArrayList<MagicalHex> magicalHexList) {
		this.playerScore = playerScore;
		this.paddleExpansionCanceltime = paddleExpansionCanceltime;
		this.paddleXcoordinate = paddleXcoordinate;
		this.playerChanceCount = playerChanceCount;
		this.paddleLength = paddleLength;
		this.isPaddleExpanded = isPaddleExpanded;
		this.sphereUnstoppableCancelTime = sphereUnstoppableCancelTime;
		this.sphereIsUnstoppable = sphereIsUnstoppable;
		this.sphereXcoordinate = sphereXcoordinate;
		this.sphereYcoordinate = sphereYcoordinate;
		this.paddleMoveDirection = paddleMoveDirection;
		this.rewardBoxList = rewardBoxList;
		this.currentObstacleList = currentObstacleList;
		this.phantasmRotationAngle = phantasmRotationAngle;
		this.playerAbilityCount = playerAbilityCount;
		this.chancesVisible = chancesVisible;
		this.abilityVisible = abilityvisible;
		this.doubleAccelCancelTime = doubleAccelCancelTime;
		this.infVoidCancelTime = infVoidCancelTime;
		this.violentAbilityCancelTime = violentAbilityCancelTime;
		this.infiniteVoidIndexArray = infiniteVoidIndexArray;
		this.sphereDx=sphereDx;
		this.sphereDy=sphereDy;
		this.magicalHexCancelTime= magicalHexCancelTime;
		this.hexReleaseTime = hexReleaseTime;
		this.magicalHexList =magicalHexList;
		this.magicalHexListeners = magicalHexListeners;
		this.magicalHexListeners =magicalHexListeners;
	}

	ArrayList<Obstacle> currentObstacleList;

}
