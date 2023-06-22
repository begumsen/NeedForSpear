package domain.SaveLoadPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.GameStartup;
import domain.MagicalAbilityPackage.IMagicalAbilityListener;
import domain.MagicalAbilityPackage.MagicalHex;
import domain.MagicalAbilityPackage.MagicalHexCreator;
import domain.ObstaclePackage.ObstacleCreator;
import domain.PhantasmPackage.Phantasm;
import domain.PlayerPackage.Player;
import domain.RewardBoxPackage.RewardBox;
import domain.RewardBoxPackage.RewardBoxCreator;
import domain.SpherePackage.EnchantedSphere;

public class SaveLoadHandler implements Serializable {
	public static boolean isLoad = false;
	public static boolean loadCompleted = false;
	public static boolean gameNeverLoaded = true;
	MagicalHexCreator magicalHexCreator = new MagicalHexCreator();

	public void saveButtonClicked(String playerName) throws IOException, ClassNotFoundException {
		ArrayList<SavedGame> savedGamesList;
		SavedGame savedGame = new SavedGame(Player.getInstance().getScore(),
				Phantasm.getInstance().getExpansionCancelTime(), Phantasm.getInstance().getX(),
				Player.getInstance().getChances(), Phantasm.getInstance().getLength(),
				Phantasm.getInstance().getExpanded(), EnchantedSphere.getInstance().getUnstoppableSphereCancelTime(),
				EnchantedSphere.getInstance().isUnstoppable(), EnchantedSphere.getInstance().getxLocation(),
				EnchantedSphere.getInstance().getyLocation(), EnchantedSphere.getInstance().getPaddleMoveDirection(),
				ObstacleCreator.getInstance().obstacles, RewardBoxCreator.rewardBoxList,
				Phantasm.getInstance().getRotationAngle(), Player.getInstance().getAbilityCount(),
				Player.getInstance().getChancesVisible(), Player.getInstance().getAbilityVisible(),
				EnchantedSphere.getInstance().getDoubleAccelCancelTime(),
				ObstacleCreator.getInstance().getInfiniteVoidCancelTime(),
				Player.getInstance().getViolentAbilityCancelTime(),
				ObstacleCreator.getInstance().getIndexArray(),
				EnchantedSphere.getInstance().getPauseDx(),
				EnchantedSphere.getInstance().getPauseDy(), Phantasm.getInstance().getMagicalHexCancelTime(),
				Phantasm.getInstance().getNextHexReleaseTime(),
				MagicalHexCreator.magicalHexList
		);
		savedGame.setUserName(playerName);
		File file = new File("loadedGames.txt");
		if (file.length() == 0) {
			savedGamesList = new ArrayList<SavedGame>();
		} else {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("loadedGames.txt"));
			savedGamesList = (ArrayList<SavedGame>) inputStream.readObject();
			savedGamesList.add(savedGame);
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();

		}
		savedGamesList.add(savedGame);
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("loadedGames.txt"));
		outputStream.writeObject(savedGamesList);
		JOptionPane.showMessageDialog(null, "Your game is saved");

	}

	public void loadButtonClicked(String playerName) throws IOException, ClassNotFoundException {

		ObstacleCreator obstacleCreator = ObstacleCreator.getInstance();

		ArrayList<SavedGame> savedGamesList;
		SavedGame gameToLoad = null;
		File file = new File("loadedGames.txt");
		if (file.length() == 0) {
			JOptionPane.showMessageDialog(null, "There are no games saved ");
			return;
		} else {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("loadedGames.txt"));
			Object obj = inputStream.readObject();

			savedGamesList = (ArrayList<SavedGame>) obj;
			for (SavedGame sg : savedGamesList) {
				if (sg.getUserName().equals(playerName)) {
					gameToLoad = sg;
				}
			}
		}
		if (gameToLoad == null) {
			JOptionPane.showMessageDialog(null, "There are no games saved for this user ");
			return;
		}
		isLoad = true;
		loadCompleted = true;
		Player.getInstance().setChancesVisible(gameToLoad.getChancesVisible());
		Phantasm.getInstance().setX(gameToLoad.getPaddleXcoordinate());
		Phantasm.getInstance().setLength(gameToLoad.getPaddleLength());
		Phantasm.getInstance().setExpansionCancelTime(gameToLoad.getPaddleExpansionCanceltime());
		Phantasm.getInstance().setExpanded(gameToLoad.isPaddleExpanded());

		// p move
		EnchantedSphere.getInstance().setUnstoppableSphereCancelTime(gameToLoad.getSphereUnstoppableCancelTime());
		EnchantedSphere.getInstance().setUnstoppable(gameToLoad.isSphereIsUnstoppable());
		EnchantedSphere.getInstance().setObsList(gameToLoad.getCurrentObstacleList());
		EnchantedSphere.getInstance().setxLocation(gameToLoad.getSphereXcoordinate());
		EnchantedSphere.getInstance().setyLocation(gameToLoad.getSphereYcoordinate());
		EnchantedSphere.getInstance().setDx(gameToLoad.getSphereDx());
		EnchantedSphere.getInstance().setDy(gameToLoad.getSphereDy());

		EnchantedSphere.getInstance().setPaddleMoveDirection(gameToLoad.getPaddleMoveDirection());
		Player.getInstance().setScore(gameToLoad.getPlayerScore());
		Player.getInstance().setChances(gameToLoad.getPlayerChanceCount());
		obstacleCreator.addLoadedObstacles(gameToLoad.getCurrentObstacleList(), GameStartup.getWidth(),
				GameStartup.getHeight());
		obstacleCreator.publishSphere();
		GameStartup.setTimeInSeconds(gameToLoad.getGameTime());
		Player.getInstance().updateChances();
		Phantasm.setRotationAngle(gameToLoad.getPhantasmRotationAngle());
		Player.getInstance().updateScore();

		RewardBoxCreator.rewardBoxList = gameToLoad.getRewardBoxList();
		RewardBoxCreator.getInstance().loadRewardBoxes();
		Player.getInstance().setAbilityCount(gameToLoad.getPlayerAbilityCount());
		Player.getInstance().setAbilityVisible(gameToLoad.getAbilityVisible());
		Player.getInstance().publishMagicalAbilityEvent();
		ObstacleCreator.getInstance().setInfiniteVoidCancelTime(gameToLoad.getInfVoidCancelTime());
		EnchantedSphere.getInstance().setDoubleAccelCancelTime(gameToLoad.getDoubleAccelCancelTime());
		ObstacleCreator.getInstance().setActive(false);
		Phantasm.getInstance().setMagicalHexCancelTime(gameToLoad.getMagicalHexCancelTime());
		Phantasm.getInstance().setNextHexReleaseTime(gameToLoad.getHexReleaseTime());
		MagicalHexCreator.magicalHexList = gameToLoad.getMagicalHexList();
		magicalHexCreator.publishHexEvent();
		EnchantedSphere.getInstance().setPaintTime(GameStartup.getTimerCount()+1);
		Phantasm.getInstance().setPaintTime(GameStartup.getTimerCount()+1);
		for (MagicalHex hex: MagicalHexCreator.magicalHexList) {
			hex.setPaintTime(GameStartup.getTimerCount()+1);
		}
		for (RewardBox reward: RewardBoxCreator.rewardBoxList) {
			reward.setPaintTime(GameStartup.getTimerCount()+1);
		}
		if (Phantasm.getInstance().getMagicalHexCancelTime() > GameStartup.getTimeInSeconds()){
			MagicalHexCreator.isMagicalHexActive = true;
		}
		if (ObstacleCreator.getInstance().getInfiniteVoidCancelTime()>=GameStartup.getTimeInSeconds()) {
			ObstacleCreator.getInstance().loadInfiniteVoid(gameToLoad.getInfiniteVoidIndexArray());
		}
		Player.getInstance().setViolentAbilityCancelTime(gameToLoad.getViolentAbilityCancelTime());
		if (Player.getInstance().getViolentAbilityCancelTime()>=GameStartup.getTimeInSeconds()){
			Player.getInstance().setViolentAbilityCounterIsVisible(true);
		}

		JOptionPane.showMessageDialog(null, "Your saved data is loaded, press play.");
	}
}
