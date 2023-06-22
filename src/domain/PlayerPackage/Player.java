package domain.PlayerPackage;

import java.util.ArrayList;
import java.util.List;

import domain.GameStartup;
import domain.MagicalAbilityPackage.MagicalAbility;
import domain.MagicalAbilityPackage.MagicalHex;
import domain.MagicalAbilityPackage.NoblePhantasmExpansion;
import domain.MagicalAbilityPackage.UnstoppableEnchantedSphere;
import domain.SaveLoadPackage.SaveLoadHandler;

public class Player {
	private double score = 0;
	private int chances = 3;
	private static Player instance;
	List<IPlayerListener> listeners = new ArrayList<IPlayerListener>();
	private MagicalAbility[] abilities;
	private boolean violentAbilityCounterIsVisible = false;
	private double violentAbilityCancelTime = 0;

	public boolean[] getAbilityVisible() {
		return abilityVisible;
	}

	public void setAbilityVisible(boolean[] abilityVisible) {
		this.abilityVisible = abilityVisible;
	}

	private boolean[] abilityVisible = { false, false, false };

	public boolean[] getChancesVisible() {
		return chancesVisible;
	}

	public void setChancesVisible(boolean[] chancesVisible) {
		this.chancesVisible = chancesVisible;
	}

	private boolean[] chancesVisible = { true, true, true };

	public int[] getAbilityCount() {
		return abilityCount;
	}

	public void setAbilityCount(int[] abilityCount) {
		this.abilityCount = abilityCount;
	}

	private int[] abilityCount = { 0, 0, 0 };

	public boolean isGameIsOn() {
		return gameIsOn;
	}

	public void setGameIsOn(boolean gameIsOn) {
		this.gameIsOn = gameIsOn;
	}

	private boolean gameIsOn = false;

	public static Player getInstance() {
		if (instance == null)
			instance = new Player();
		else {
			return instance;
		}
		return instance;

	}

	public int getChances() {
		return chances;
	}

	public void loseChance() {

		if (chances > 0 && chances <= 3) {
			this.chances--;
			updateChances();

		}
	}

	public void incrementChance() {
		if (chances < 3 && chances > 0) {
			this.chances++;
			updateChances();
		}
	}

	public void setChances(int chances) {
		this.chances = chances;
	}

	public void updateChances() {
		if (chances == 3) {
			chancesVisible[2] = true;
			chancesVisible[1] = true;
			chancesVisible[0] = true;
		} else if (chances == 2) {
			chancesVisible[2] = false;
			chancesVisible[1] = true;
			chancesVisible[0] = true;
		} else if (chances == 1) {
			chancesVisible[2] = false;
			chancesVisible[1] = false;
			chancesVisible[0] = true;
		} else if (chances == 0) {
			chancesVisible[2] = false;
			chancesVisible[1] = false;
			chancesVisible[0] = false;
		}
		publishChanceEvent();
		if (chances == 0) {
			publishGameOverEvent();
		}
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public void addPlayerListener(IPlayerListener lis) {
		listeners.add(lis);
	}

	public void publishChanceEvent() {
		for (IPlayerListener l : listeners)
			l.onChanceEvent(chancesVisible);
	}

	public void updateScore() {

		if (!SaveLoadHandler.loadCompleted) {
			if (GameStartup.getTimeInSeconds() == 0) {
				score = score + (300.0);
			} else {
				score = score + (300.0 / (GameStartup.getTimeInSeconds()));
			}
		}

		publishGainScoreEvent(score);
	}

	public void publishGainScoreEvent(double score) {
		for (IPlayerListener l : listeners)
			l.onGainScoreEvent(score);
	}

	public void updateViolentAbilityDuration(int timeInSeconds) {
		if (violentAbilityCounterIsVisible) {
			publishUpdateViolentAbilityDurationEvent(timeInSeconds);
		}
	}

	public void publishUpdateViolentAbilityDurationEvent(int timeInSeconds) {
		for (IPlayerListener l : listeners)
			l.onViolentAbilityEvent(timeInSeconds);
	}

	public void createViolentAbilityCounter(double time) {
		// TODO Auto-generated method stub
		violentAbilityCounterIsVisible = true;
		violentAbilityCancelTime = time;
	}

	public double getViolentAbilityCancelTime() {
		return violentAbilityCancelTime;
	}

	public void setViolentAbilityCancelTime(double violentAbilityCancelTime) {
		this.violentAbilityCancelTime = violentAbilityCancelTime;
	}

	public boolean isViolentAbilityCounterIsVisible() {
		return violentAbilityCounterIsVisible;
	}

	public void setViolentAbilityCounterIsVisible(boolean violentAbilityCounterIsVisible) {
		this.violentAbilityCounterIsVisible = violentAbilityCounterIsVisible;
	}

	public void gainMagicalAbility(MagicalAbility magicalAbility) {
		// TODO Auto-generated method stub
		if (magicalAbility instanceof MagicalHex) {
			abilityCount[0]++;
			abilityVisible[0] = true;
		}
		if (magicalAbility instanceof UnstoppableEnchantedSphere) {
			abilityCount[1]++;
			abilityVisible[1] = true;
		}
		if (magicalAbility instanceof NoblePhantasmExpansion) {
			abilityCount[2]++;
			abilityVisible[2] = true;
		}
		publishMagicalAbilityEvent();
	}

	public void loseMagicalAbility(MagicalAbility magicalAbility) {
		if (magicalAbility instanceof MagicalHex) {
			abilityCount[0]--;

			if (abilityCount[0] == 0) {
				abilityVisible[0] = false;
			}
		}
		if (magicalAbility instanceof UnstoppableEnchantedSphere) {
			abilityCount[1]--;
			if (abilityCount[1] == 0) {
				abilityVisible[1] = false;
			}
		}
		if (magicalAbility instanceof NoblePhantasmExpansion) {
			abilityCount[2]--;
			if (abilityCount[2] == 0) {
				abilityVisible[2] = false;
			}
		}
		publishMagicalAbilityEvent();
	}

	public void publishMagicalAbilityEvent() {
		for (IPlayerListener l : listeners)
			l.onMagicalAbilityButtonEvent(abilityCount, abilityVisible);
	}

	public int getMagicalHexCount() {
		return abilityCount[0];
	}

	public int getUnstoppableEnchantedSphereCount() {
		return abilityCount[1];
	}

	public int getNoblePhantasmExpansionCount() {
		return abilityCount[2];
	}

	public void publishGameOverEvent() {
		for (IPlayerListener l : listeners)
			l.onGameOverEvent(score);
	}
}
