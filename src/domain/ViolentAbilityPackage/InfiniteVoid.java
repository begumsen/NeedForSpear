package domain.ViolentAbilityPackage;

import domain.MagicalAbilityPackage.MagicalAbility;
import domain.ObstaclePackage.ObstacleCreator;

public class InfiniteVoid extends MagicalAbility {
	public void showEffect() {
		ObstacleCreator.getInstance().activateInfiniteVoid();

	}
}
