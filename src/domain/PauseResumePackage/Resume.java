package domain.PauseResumePackage;

import java.io.Serializable;

import javax.swing.Timer;

import domain.GameStartup;
import domain.SpherePackage.EnchantedSphere;

public class Resume implements Serializable {

	public void startTimer(Timer timer) {

		timer.start();
		GameStartup.pause = false;
		EnchantedSphere.getInstance().resumeSpeed();
		GameStartup.setResume(true);
	}
}
