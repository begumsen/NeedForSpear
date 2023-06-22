package domain.SaveLoadPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.FeaturePackage.FeatureListener;

public class Load implements Serializable {
	List<FeatureListener> listeners = new ArrayList<>();

	public void setFeature() {
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
