package domain.RewardBoxPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.GameStartup;
import domain.MagicalAbilityPackage.MagicalAbility;
import domain.SpherePackage.EnchantedSphere;

public class RewardBoxCreator implements Serializable {

	public static ArrayList<RewardBox> rewardBoxList;
	private static RewardBoxCreator instance;
	List<IRewardListener> rewardBoxListeners = new ArrayList<>();

	private RewardBoxCreator() {
		rewardBoxList = new ArrayList<RewardBox>();

	}

	public static RewardBoxCreator getInstance() {
		if (instance == null) {
			instance = new RewardBoxCreator();
		}
		return instance;
	}

	public void addRewardBox(double x, double y, double sizex, double sizey, MagicalAbility magicalAbility) {
		// System.out.println("reached here addRewardBox in RewBoxCreator");
		RewardBox rewardBox = RewardBoxFactory.getInstance().getRewardBox(x, y, sizex, sizey, magicalAbility);
		rewardBox.setPaintTime (GameStartup.getTimerCount()+1);
		rewardBoxList.add(rewardBox);

		// System.out.println("Size is"+rewardBoxList.size());

		for (RewardBox r : rewardBoxList) {
			// System.out.println(""+r.getyLocation());
		}

		publishCreateRewardBoxEvent(rewardBox);

	}

	public void addRewardBoxListener(IRewardListener lis) {
		// TODO Auto-generated method stub
		rewardBoxListeners.add(lis);

	}

	public void publishCreateRewardBoxEvent(RewardBox rewardBox) {
		// System.out.print("published event");
		for (IRewardListener l : rewardBoxListeners) {

			l.onCreateRewardBoxEvent(rewardBox);
		}
	}

	public void loadRewardBoxes() {
		for (RewardBox r : rewardBoxList) {
			if (r.getIsCaught()==0)
			publishCreateRewardBoxEvent(r);
		}
	}

}
