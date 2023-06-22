package ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

//import com.sun.xml.internal.ws.developer.Serialization;
import domain.GameStartup;
import domain.FeaturePackage.FeatureHandler;
import domain.MagicalAbilityPackage.MagicalAbilityHandler;
import domain.MagicalAbilityPackage.MagicalHex;
import domain.MagicalAbilityPackage.IMagicalAbilityListener;
import domain.ObstaclePackage.BreakObstacleHandler;
import domain.ObstaclePackage.BuildGameHandler;
import domain.ObstaclePackage.ObstacleCreator;
import domain.ObstaclePackage.ObstacleListener;
import domain.ObstaclePackage.ObstacleTypesPackage.FirmObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;
import domain.PhantasmPackage.PaddleHandler;
import domain.PhantasmPackage.Phantasm;
import domain.PlayerPackage.IPlayerListener;
import domain.PlayerPackage.Player;
import domain.RewardBoxPackage.IRewardListener;
import domain.RewardBoxPackage.RewardBox;
import domain.SaveLoadPackage.SaveLoadHandler;
import domain.SpherePackage.EnchantedSphere;
import domain.SpherePackage.SphereHandler;
import ui.FeatureUIPackage.GameOverUI;
import ui.FeatureUIPackage.HelpScreenUI;
import ui.FeatureUIPackage.LoadScreenUI;
import ui.FeatureUIPackage.PauseGameUI;
import ui.FeatureUIPackage.SaveScreenUI;

public class NeadForSpearUI extends JFrame
		implements KeyListener, ObstacleListener, MouseListener, IPlayerListener, Serializable, IRewardListener, IMagicalAbilityListener {
	PhantasmUI paddleUI;
	private int time;

	private PaddleHandler paddleHandler;
	public static LoginScreen loginScreen;

	private FeatureHandler featureHandler;
	public BuildGameHandler buildGameHandler;
	private SphereHandler sphereHandler;
	public static BreakObstacleHandler breakObstacleHandler;
	HelpScreenUI helpscreen;
	PauseGameUI pausegame;
	public static double width;
	public static double height;
	private MagicalAbilityHandler magicalAbilityHandler;
	private boolean gameIsOn = false;
	private int[] noOfObstacles;
	public static SimulationFrame simulationFrame;
	SaveScreenUI saveScreenUI;
	LoadScreenUI loadScreenUI;
	GameOverUI gameOverUI;
	JLabel firstChance, secondChance, thirdChance, scoreLabel;
	JTextField violentAbilityCounterDisplay;
	private boolean chancesNotAddedToScreen = true;
	RewardBoxUI rewBox;
	JButton phantasmExpansionButton = new JButton("Expand Phantasm");
	JButton unstoppableSphereButton = new JButton("make Unstoppable");
	JButton magicalHexButton = new JButton("make MagicalHex");
	// check this assignment later
	int radius = 5;
	double xLocation = (Phantasm.getInstance().getX() + Phantasm.getInstance().getLen() / 2) - radius;
	double yLocation = Phantasm.getInstance().getY() - radius * 2 - 1;

	boolean sphereAddedOnce = true;
	// EnchantedSphereDomain sphereDomain = new
	// EnchantedSphereDomain((int)xLocation, (int)yLocation, radius, 4, 2);

	public NeadForSpearUI(FeatureHandler featureHandler, PaddleHandler paddleHandler, BuildGameHandler buildGameHandler,
			SimulationFrame simulationFrame, SphereHandler sphereHandler, BreakObstacleHandler breakObstacleHandler,
			MagicalAbilityHandler magicalAbilityHandler, LoginScreen loginScreen) {

		width = GameStartup.getWidth();

		height = GameStartup.getHeight();

		// Screen widht=1440.0
		// Phantasm length=L=144
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(null);
		this.magicalAbilityHandler = magicalAbilityHandler;
		this.featureHandler = featureHandler;
		this.sphereHandler = sphereHandler;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton saveButton = new JButton("Save");
		saveButton.setFocusable(false);
		saveButton.setSize(75, 75);
		JButton loadButton = new JButton("Load");
		loadButton.setFocusable(false);
		loadButton.setSize(75, 75);
		loadButton.setLocation(75, 0);
		JButton pauseButton = new JButton("Pause");
		pauseButton.setFocusable(false);
		pauseButton.setSize(75, 75);
		pauseButton.setLocation(150, 0);
		pauseButton.addActionListener(e -> {
			featureHandler.openPauseScreen();
		});
		JButton resumeButton = new JButton("Resume");
		resumeButton.setSize(75, 75);
		resumeButton.setLocation(225, 0);
		resumeButton.addActionListener(e -> {
			featureHandler.resumeTimer();
		});
		resumeButton.setFocusable(false);
		JButton quitButton = new JButton("Quit");
		quitButton.setFocusable(false);
		quitButton.setSize(75, 75);
		quitButton.setLocation(300, 0);
		add(saveButton);
		add(loadButton);
		add(pauseButton);
		add(quitButton);
		quitButton.addActionListener(e -> {
			featureHandler.quitGame();
		});
		add(resumeButton);
		saveButton.addActionListener(e -> {
			featureHandler.openSaveScreen();
		});
		loadButton.addActionListener(e -> {
			featureHandler.openLoadScreen();
		});
		saveScreenUI = new SaveScreenUI();
		loadScreenUI = new LoadScreenUI();
		gameOverUI = new GameOverUI();

		JButton helpButton = new JButton("Help");
		helpButton.setFocusable(false);
		helpButton.setSize(75, 75);
		helpButton.setLocation(375, 0);
		helpButton.addActionListener(e -> {
			featureHandler.openHelpScreen();
		});
		add(helpButton);
		JButton enterTable = new JButton("Build");
		enterTable.setFocusable(false);
		enterTable.setSize(75, 75);
		enterTable.setLocation(450, 0);
		add(enterTable);
		JButton playButton = new JButton("Play");

		playButton.setSize(75, 75);
		playButton.setLocation(525, 0);
		playButton.setFocusable(false);
		add(playButton);

		phantasmExpansionButton.setSize(75, 75);
		phantasmExpansionButton.setLocation(650, 0);
		phantasmExpansionButton.addActionListener(e -> {
			magicalAbilityHandler.expandPhantasm();
		});
		phantasmExpansionButton.setFocusable(false);
		add(phantasmExpansionButton);
		phantasmExpansionButton.setVisible(false);

		unstoppableSphereButton.setSize(75, 75);
		unstoppableSphereButton.setLocation(725, 0);
		unstoppableSphereButton.addActionListener(e -> {
			magicalAbilityHandler.makeSphereUnstoppable();
		});
		unstoppableSphereButton.setFocusable(false);
		add(unstoppableSphereButton);
		unstoppableSphereButton.setVisible(false);

		magicalHexButton.setSize(75, 75);
		magicalHexButton.setLocation(800, 0);
		magicalHexButton.addActionListener(e -> {
			magicalAbilityHandler.hexButtonClicked();
		});
		magicalHexButton.setFocusable(false);
		add(magicalHexButton);
		magicalHexButton.setVisible(false);
		magicalAbilityHandler.addMagicalHexListener(this);

		scoreLabel = new JLabel();
		scoreLabel.setText("Score = " + Player.getInstance().getScore());
		scoreLabel.setSize(100, 100);
		scoreLabel.setLocation(1225, 10);
		scoreLabel.setSize(100, 100);
		scoreLabel.setLocation(1225, 10);

		firstChance = new JLabel();
		secondChance = new JLabel();
		thirdChance = new JLabel();
		ImageIcon chanceIcon = new ImageIcon(
				new ImageIcon("images/chance.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		firstChance.setIcon(chanceIcon);
		firstChance.setSize(50, 50);
		firstChance.setLocation(1000, 10);
		secondChance.setIcon(chanceIcon);
		secondChance.setLocation(1075, 10);
		secondChance.setSize(50, 50);
		thirdChance.setIcon(chanceIcon);
		thirdChance.setLocation(1150, 10);
		thirdChance.setSize(50, 50);
		// Count down from 15

		violentAbilityCounterDisplay = new JTextField("15");
		violentAbilityCounterDisplay.setBounds(1225, 0, 50, 50);
		// GameStartup.getTimeInSeconds();
		add(violentAbilityCounterDisplay);
		violentAbilityCounterDisplay.setVisible(false);

		// Count down from 15 ends here
		playButton.addActionListener(e -> {

			buildGameHandler.playButtonClicked();
			if ((chancesNotAddedToScreen && (buildGameHandler.getIsActive() == false))) {

				this.add(scoreLabel);

				this.add(firstChance);
				this.add(secondChance);
				this.add(thirdChance);
				chancesNotAddedToScreen = false;
			}
			if (!gameIsOn)
				gameIsOn = true;
			SaveLoadHandler.isLoad = false;

		});

		this.paddleHandler = paddleHandler;
		paddleUI = PhantasmUI.getInstance();
		this.setFocusable(true);
		add(paddleUI);
		this.addKeyListener(this);
		this.addMouseListener(this);

		Player.getInstance().addPlayerListener(this);

		helpscreen = new HelpScreenUI();
		pausegame = new PauseGameUI();
		paddleHandler.addListener(paddleUI);
		featureHandler.addHelpScreenListener(helpscreen);
		featureHandler.addSaveScreenListener(saveScreenUI);
		featureHandler.addLoadScreenListener(loadScreenUI);

		featureHandler.addListener(pausegame);
		featureHandler.addGameOverListener(gameOverUI);
		buildGameHandler.addListener(this);
		this.simulationFrame = simulationFrame;
		simulationFrame.setFocusable(false);
		this.buildGameHandler = buildGameHandler;
		enterTable.addActionListener(e -> {
			this.add(scoreLabel);

			this.add(firstChance);
			this.add(secondChance);
			this.add(thirdChance);
			chancesNotAddedToScreen = false;
			buildGameHandler.addInitialObstacles(simulationFrame.getNumberOfObstacle(), width, height);
			buildGameHandler.putPhantasm(Phantasm.getInstance().getX());
			buildGameHandler.putSphere(EnchantedSphere.getInstance().getxLocation(),
					EnchantedSphere.getInstance().getyLocation(), EnchantedSphere.getInstance().getDx(),
					EnchantedSphere.getInstance().getDy());

		});

		this.breakObstacleHandler = breakObstacleHandler;

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		paddleHandler.interpretPaddleMovement(e);
		magicalAbilityHandler.interpretMagicalAbilityChoice(e);
		if (e.getKeyCode() == KeyEvent.VK_SPACE && gameIsOn) {
			if (sphereAddedOnce || !EnchantedSphere.getInstance().getisNotdropped()) {
				EnchantedSphere.getInstance().setPaintTime(GameStartup.getTimerCount()+1);
				ObstacleCreator.getInstance().publishSphere();
				sphereAddedOnce = false;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		paddleHandler.interpretKeyReleasePaddleMovement(e);
	}

	@Override
	public void onCreateObstacleEvent(ArrayList<Obstacle> obstacles) {
		// TODO Auto-generated method stub
		// place obstacles
		for (Obstacle obs : obstacles) {

			boolean isVisible = obs.isVisible();
			char type = obs.getType();

			if (!isVisible) {
				ObstacleUI obstacle;

				if (type == 'e') {

					obstacle = new ExplosiveObstacleUI(obs, buildGameHandler);
				} else {
					obstacle = new ObstacleUI(obs, buildGameHandler);
				}

				// Border border = BorderFactory.createLineBorder(Color.BLACK);
				// obstacle.setBorder(border);
				breakObstacleHandler.addListener(obs, obstacle);

				if (type == 'f') {
					obstacle.setText(Integer.toString(((FirmObstacle) obs).getHitNum()));
					obstacle.setHorizontalAlignment(JLabel.CENTER);
					obstacle.setVerticalAlignment(JLabel.CENTER);
				}

				this.add(obstacle);
				obs.setVisible(true);
			}
		}

	}

	public void setNumberOfObstacle(int[] no) {
		this.noOfObstacles = no;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() instanceof NeadForSpearUI) {

			// Add an obstacle

			int[] array = new int[4];
			int index = (int) (Math.random() * 4);
			array[index] = 1;
			buildGameHandler.addObstacleAfterBuild(array, width, height, (double) e.getX(), (double) e.getY() - 20);

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRemoveObstacleEvent(Obstacle object, ObstacleUI obs) {
		// TODO Auto-generated method stub
		// obs.setVisible(false);
		this.remove(obs);
		EnchantedSphere.getInstance().setObsList(buildGameHandler.getObsList());
	}

	@Override
	public void onNumberAlertEvent() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null,
				"Minimum number criteria is not satisfied. There should be at least 75 simple obstacles, 10 firm obstacles, 5 explosive obstacles, 10 gift obstacles. ");
	}

	@Override
	public void onSphereAlertEvent() {
		// TODO Auto-generated method stub
		// sphereDomain= new EnchantedSphereDomain(100, 150, 5, 4, 2);
		// if (EnchantedSphere.getInstance().getyLocation() > height
		// - EnchantedSphere.getInstance().getRadius()) {
		if (!SaveLoadHandler.loadCompleted) {
			EnchantedSphere.getInstance().setxLocation(Phantasm.getInstance().getX()
					+ Phantasm.getInstance().getLen() / 2 - EnchantedSphere.getInstance().getRadius());
			EnchantedSphere.getInstance()
					.setyLocation((Phantasm.getInstance().getY() - 2 * EnchantedSphere.getInstance().getRadius()) - 1);
		}
		if (!EnchantedSphere.getInstance().isDoubleAccelActive()&& !SaveLoadHandler.loadCompleted) {
			EnchantedSphere.getInstance().setDx(0);
			EnchantedSphere.getInstance().setDy(-2);
		} else if (!SaveLoadHandler.loadCompleted) {
			EnchantedSphere.getInstance().setDx(0);
			EnchantedSphere.getInstance().setDy(-1);
		}
		// }

		EnchantedSphere.getInstance().setisNotdropped(true);

		EnchantedSphere.getInstance().setObsList(buildGameHandler.getObsList());
		if ((!gameIsOn || sphereAddedOnce)) {
			if ((SaveLoadHandler.gameNeverLoaded)) {
				add(EnchantedSphereUI.getInstance());
				SaveLoadHandler.gameNeverLoaded = false;
			}
		}
		sphereHandler.addListener(EnchantedSphereUI.getInstance());
	}

	@Override
	public void onCreateRewardBoxEvent(RewardBox rewardBox) {
		// TODO Auto-generated method stub

		rewardBox.addRewardBoxListener(this);

		RewardBoxUI rewBox = new RewardBoxUI(rewardBox);
		rewardBox.addRewardBoxListener(rewBox);

		Border border = BorderFactory.createLineBorder(Color.PINK);
		rewBox.setBorder(border);
		rewBox.setBounds((int) rewardBox.getxLocation(), (int) rewardBox.getyLocation(), 200, 200);

		this.add(rewBox);
		// rewBox.setVisible(true);

	}

	@Override
	public void onMoveRewardBoxEvent(RewardBox rewardBox) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRewardAlertEvent(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCaughtRewardBoxEvent(RewardBox rewardBox) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRemoveRewardBoxEvent(RewardBox rewardBox, RewardBoxUI rewardBoxUI) {
		// TODO Auto-generated method stub

		this.remove(rewardBoxUI);
	}

	@Override
	public void onGainScoreEvent(double score) {
		// TODO Auto-generated method stub
		String c = "Score = ";
		String s = c.concat(Double.toString((int) score));
		scoreLabel.setText(s);
		this.add(scoreLabel);

	}

	@Override
	public void onViolentAbilityEvent(int time) {
		// this.remove(violentAbilityCounterDisplay);
		if (time == Player.getInstance().getViolentAbilityCancelTime()) {
			Player.getInstance().setViolentAbilityCounterIsVisible(false);
			violentAbilityCounterDisplay.setVisible(false);
		} else {
			// violentAbilityCounterDisplay = new
			// JTextField(""+(Player.getInstance().getViolentAbilityCancelTime()-time));
			violentAbilityCounterDisplay.setText("" + (Player.getInstance().getViolentAbilityCancelTime() - time));
			violentAbilityCounterDisplay.setBounds(1225, 0, 50, 50);
			// GameStartup.getTimeInSeconds();
			// add(violentAbilityCounterDisplay);
			violentAbilityCounterDisplay.setVisible(true);
		}

	}

	@Override
	public void onMagicalAbilityButtonEvent(int[] count, boolean[] visible) {
		// TODO Auto-generated method stub
		magicalHexButton.setVisible(visible[0]);
		this.add(magicalHexButton);
		this.add(unstoppableSphereButton);
		this.add(phantasmExpansionButton);

		magicalHexButton.setText("MH: " + count[0]);
		unstoppableSphereButton.setVisible(visible[1]);
		unstoppableSphereButton.setText("US: " + count[1]);
		phantasmExpansionButton.setVisible(visible[2]);
		phantasmExpansionButton.setText("PE: " + count[2]);

	}

	@Override
	public void onChanceEvent(boolean[] chancesVisible) {
		// TODO Auto-generated method stub
		thirdChance.setVisible(chancesVisible[2]);
		secondChance.setVisible(chancesVisible[1]);
		firstChance.setVisible(chancesVisible[0]);
		this.add(firstChance);
		this.add(secondChance);
		this.add(thirdChance);
	}

	@Override
	public void onGameOverEvent(double score) {
		// TODO Auto-generated method stub
		featureHandler.openGameOverScreen();

	}

	@Override
	public void onHexEvent(ArrayList<MagicalHex> hexList) {
		
		for (MagicalHex hexDomain : hexList) {	
			//here add if hex is on top of phantasm
			
			MagicalHexUI hexUI = new MagicalHexUI(hexDomain);
			this.add(hexUI);
			hexUI.setVisible(true);
		}
		
	}

}
