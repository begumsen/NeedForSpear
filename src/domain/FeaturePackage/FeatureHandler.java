package domain.FeaturePackage;

import java.io.Serializable;

import javax.swing.Timer;

import domain.HelpPackage.Help;
import domain.PauseResumePackage.GameOver;
import domain.PauseResumePackage.Pause;
import domain.PauseResumePackage.Resume;
import domain.SaveLoadPackage.Load;
import domain.SaveLoadPackage.Save;

public class FeatureHandler implements Serializable {
	Help help;
	Pause pause;
	Resume resume;
	Timer timer;
	Save save;
	Load load;
	GameOver gameOver;

	public FeatureHandler() {
		help = new Help();
		pause = new Pause();
		resume = new Resume();
		save = new Save();
		load = new Load();
		gameOver = new GameOver();

	}

	public void openHelpScreen() {
		help.setFeature();
	}

	public void openSaveScreen() {
		save.setFeature();
	}

	public void openLoadScreen() {
		load.setFeature();
	}

	public void quitGame() {
		System.exit(0);
	}

	public void openPauseScreen() {
		pause.setFeature(timer);

	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void resumeTimer() {
		resume.startTimer(timer);
	}

	public void addHelpScreenListener(FeatureListener lis) {
		help.addFeatureListener(lis);
	}

	public void addSaveScreenListener(FeatureListener lis) {
		save.addFeatureListener(lis);
	}

	public void addLoadScreenListener(FeatureListener lis) {
		load.addFeatureListener(lis);
	}

	public void addListener(FeatureListener lis) {
		pause.addFeatureListener(lis);
	}

	public void addGameOverListener(FeatureListener lis) {
		gameOver.addFeatureListener(lis);
	}

	public void openGameOverScreen() {
		// TODO Auto-generated method stub
		gameOver.setFeature(timer);
	}
}