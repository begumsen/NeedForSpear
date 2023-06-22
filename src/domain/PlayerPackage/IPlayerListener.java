package domain.PlayerPackage;

public interface IPlayerListener {

	public void onGainScoreEvent(double score);

	public void onViolentAbilityEvent(int time);

	public void onMagicalAbilityButtonEvent(int[] count, boolean[] visible);

	public void onChanceEvent(boolean[] chancesVisible);

	public void onGameOverEvent(double score);

}