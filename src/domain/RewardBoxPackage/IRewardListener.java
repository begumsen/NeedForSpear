package domain.RewardBoxPackage;

import ui.RewardBoxUI;

public interface IRewardListener {
	// public void moveRewardEvent(int x,int y,int dx,int dy);
	void onCreateRewardBoxEvent(RewardBox rewardBox);

	void onMoveRewardBoxEvent(RewardBox rewardBox);// RewardBox rewardBox);

	void onRewardAlertEvent(int x, int y);

	void onCaughtRewardBoxEvent(RewardBox rewardBox);

	void onRemoveRewardBoxEvent(RewardBox rewardBox, RewardBoxUI rewardBoxUI);

}
