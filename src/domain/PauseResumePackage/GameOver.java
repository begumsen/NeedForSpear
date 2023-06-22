package domain.PauseResumePackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import domain.GameStartup;
import domain.FeaturePackage.FeatureListener;

public class GameOver implements Serializable {
	List<FeatureListener> listeners = new ArrayList<>();

	public void setFeature(Timer timer) {
		publishFeatureEvent();
		timer.stop();
		GameStartup.setResume(false);
		GameStartup.pause = true;

	}

	public void publishFeatureEvent() {
		for (FeatureListener l : listeners)
			l.onFeatureEvent();
	}

	public void addFeatureListener(FeatureListener lis) {
		// TODO Auto-generated method stub
		listeners.add(lis);

	}
}
