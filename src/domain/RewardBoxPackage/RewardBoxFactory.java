package domain.RewardBoxPackage;

import domain.MagicalAbilityPackage.MagicalAbility;

public class RewardBoxFactory {
	private RewardBox rewardBox;
	private static RewardBoxFactory rewardBoxFactory;

	public RewardBoxFactory() {
	}

	public static RewardBoxFactory getInstance() {
		if (rewardBoxFactory == null) {
			rewardBoxFactory = new RewardBoxFactory();
		}
		return rewardBoxFactory;
	}

	public RewardBox getRewardBox(double x, double y, double sizex, double sizey, MagicalAbility magicalAbility) {

		rewardBox = new RewardBox(x, y, sizex, sizey, 2, magicalAbility);

		return rewardBox;
	}
}