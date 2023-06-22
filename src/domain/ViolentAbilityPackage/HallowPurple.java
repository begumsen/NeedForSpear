package domain.ViolentAbilityPackage;

import domain.MagicalAbilityPackage.MagicalAbility;
import domain.ObstaclePackage.ObstacleCreator;

public class HallowPurple extends MagicalAbility {
	public HallowPurple() {
	}

	public void showEffect() {
		ObstacleCreator.getInstance().addPurpleObstacles(8, 1440, 900);
	}
}
