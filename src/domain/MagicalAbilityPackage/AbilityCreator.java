package domain.MagicalAbilityPackage;

import java.io.Serializable;
import java.util.ArrayList;

import domain.ObstaclePackage.ObstacleTypesPackage.GiftObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;
import domain.ViolentAbilityPackage.Ymir;

public class AbilityCreator implements Serializable {
	MagicalAbility ability;

	public ArrayList<Obstacle> createMagicalAbility(ArrayList<Obstacle> obsList) {
		// REQUIRES: An arrayList of obstacles is given as an input
		// MODIFIES: obsList
		// EFFECTS: If obsList is null, throws NullPointerException,
		// else if the obsList contains less than or equal to 4 gift obstacles,
		// it assigns magical abilities to the gift obstacles so that each gift obstacle
		// has a different magical ability and returns that updated obsList
		// else if the obsList contains more than 4 gift obstacles,
		// it assigns magical abilities to the gift obstacles so that each magical
		// ability appears at least once and returns that updated obsList
		// else it returns the same obsList as input
		int i = 0;
		for (Obstacle obstacle : obsList) {
			if (obstacle.getType() == 'g') {

				if (i < 4) {
					ability = AbilityFactory.getInstance().getAbility(i);

				} else {
					int randomNo = (int) (Math.random() * 4);
					ability = AbilityFactory.getInstance().getAbility(randomNo);
				}
				((GiftObstacle) obstacle).setAbility(ability);
				i++;
			}
		}
		return obsList;
	}

	public void activateViolentAbility(int randomValue, Ymir ymir) {
		ability = AbilityFactory.getInstance().activateViolentActivity(randomValue);
		ymir.setViolentAbility(ability);
	}
}
