package domain.PauseResumePackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import domain.GameStartup;
import domain.FeaturePackage.FeatureListener;
import domain.SpherePackage.EnchantedSphere;

public class Pause implements Serializable {
	List<FeatureListener> listeners = new ArrayList<>();

	public void setFeature(Timer timer) {
		timer.stop();
		GameStartup.pause = true;
		EnchantedSphere.getInstance().pause();
		GameStartup.setResume(false);
		publishFeatureEvent();

	}

	public void addFeatureListener(FeatureListener lis) {
		listeners.add(lis);
	}

	public void publishFeatureEvent() {
		for (FeatureListener l : listeners)
			l.onFeatureEvent();
	}

}