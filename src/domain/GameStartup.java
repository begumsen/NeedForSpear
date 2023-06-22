package domain;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.Timer;

import domain.FeaturePackage.FeatureHandler;
import domain.MagicalAbilityPackage.MagicalAbilityHandler;
import domain.MagicalAbilityPackage.MagicalHex;
import domain.MagicalAbilityPackage.MagicalHexCreator;
import domain.ObstaclePackage.BreakObstacleHandler;
import domain.ObstaclePackage.BuildGameHandler;
import domain.ObstaclePackage.ObstacleCreator;
import domain.PhantasmPackage.PaddleHandler;
import domain.PlayerPackage.Player;
import domain.SpherePackage.EnchantedSphere;
import domain.SpherePackage.SphereHandler;
import domain.ViolentAbilityPackage.Ymir;
import ui.LoginScreen;
import ui.NeadForSpearUI;
import ui.SimulationFrame;

public class GameStartup implements Serializable {
	private static NeadForSpearUI neadForSpearUI;
	// Added for resume timer
	private static Timer timer;
	private static FeatureHandler featureHandler;
	public static SimulationFrame simulationFrame;

	private static int timeInSeconds = 0;
	private static int timerCount = 0;
	private static boolean resume = true;
	public static boolean pause = false;
	private static double width;
	private static double height;

	public static void setTimerCount(int timerCount) {
		GameStartup.timerCount = timerCount;
	}

	public static int getTimerCount() {
		return timerCount;
	}

	public static double getHeight() {
		return height;
	}

	public static void setTimeInSeconds(int time) {
		timeInSeconds = time;
	}

	public static int getTimeInSeconds() {
		return timeInSeconds;
	}

	public static double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();

		height = screenSize.getHeight();
		CreateAndShowUI();
		Ymir ymir = new Ymir();
		Timer timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) // will run when the timer fires
			{
				if (ObstacleCreator.getInstance().isGameIsOn())
					timerCount++;

				if (((timerCount % 100) == 0) && (timerCount != 0)) {
					timeInSeconds++;
					Player.getInstance().updateViolentAbilityDuration(timeInSeconds);
					MagicalAbilityHandler.getInstance().checkAbilityCancel(timeInSeconds);
				}

				if (((timeInSeconds % 30) == 0) && (timeInSeconds != 0)) {
					boolean successFlip = ymir.flipCoin(timeInSeconds);
					if (successFlip && timerCount % 3000 == 0) {
						Player.getInstance().createViolentAbilityCounter(timeInSeconds + 15);
					}
				}
				if (resume) {
					neadForSpearUI.revalidate();
					neadForSpearUI.repaint();
				}
				
				for (MagicalHex hexDomain : MagicalHexCreator.magicalHexList) {
					System.out.println("hex " + MagicalHexCreator.magicalHexList.indexOf(hexDomain) + " dx:"+ hexDomain.getDx());
					System.out.println("hex " + MagicalHexCreator.magicalHexList.indexOf(hexDomain) + " dy:"+ hexDomain.getDy());
					System.out.println("hex " + MagicalHexCreator.magicalHexList.indexOf(hexDomain) + " xloc:"+ hexDomain.getxLocation());
					System.out.println("hex " + MagicalHexCreator.magicalHexList.indexOf(hexDomain) + " yloc:"+ hexDomain.getyLocation());
				}				
			}
		});

		timer.start(); // start the timer
		featureHandler.setTimer(timer);
	}

	private static void CreateAndShowUI() {
		PaddleHandler paddleHandler = new PaddleHandler(EnchantedSphere.getInstance());
		featureHandler = new FeatureHandler();
		BuildGameHandler buildGameHandler = new BuildGameHandler();
		MagicalAbilityHandler magicalAbilityHandler = MagicalAbilityHandler.getInstance();

		simulationFrame = new SimulationFrame();
		LoginScreen loginScreen = new LoginScreen(simulationFrame);

		SphereHandler sphereHandler = new SphereHandler(EnchantedSphere.getInstance());
		BreakObstacleHandler breakObstacleHandler = new BreakObstacleHandler();
		neadForSpearUI = new NeadForSpearUI(featureHandler, paddleHandler, buildGameHandler, simulationFrame,
				sphereHandler, breakObstacleHandler, magicalAbilityHandler, loginScreen);
		neadForSpearUI.setVisible(true);
		simulationFrame.setVisible(true);
		loginScreen.setVisible(true);

	}

	public static boolean isResume() {
		return resume;
	}

	public static void setResume(boolean resume) {
		GameStartup.resume = resume;
	}
}
