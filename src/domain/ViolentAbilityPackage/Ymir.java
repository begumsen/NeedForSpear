package domain.ViolentAbilityPackage;

import java.util.Random;

import domain.MagicalAbilityPackage.AbilityCreator;
import domain.MagicalAbilityPackage.MagicalAbility;

public class Ymir {
	MagicalAbility ability;
	AbilityCreator abilityCreator = new AbilityCreator();
	Random rand = new Random();
	int lastFlipSecond = 0;

	public void setViolentAbility(MagicalAbility ability) {
		this.ability = ability;
		ability.showEffect();
	}

	public boolean flipCoin(int gameTime) {
		if (rand.nextInt(2) == 0) {
			if (!(lastFlipSecond == gameTime)) {
				lastFlipSecond = gameTime;
				chooseAbility();
				return true;
			}
		}
		return false;

	}

	public void chooseAbility() {
		abilityCreator.activateViolentAbility(rand.nextInt(3), this);
	}

}
