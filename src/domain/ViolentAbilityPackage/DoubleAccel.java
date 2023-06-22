package domain.ViolentAbilityPackage;

import domain.MagicalAbilityPackage.MagicalAbility;
import domain.SpherePackage.EnchantedSphere;

public class DoubleAccel extends MagicalAbility {

	public void showEffect() {
		EnchantedSphere.getInstance().doubleAccelslowDown();
	}

}
