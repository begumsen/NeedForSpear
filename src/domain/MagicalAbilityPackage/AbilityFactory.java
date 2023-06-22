package domain.MagicalAbilityPackage;

import domain.ViolentAbilityPackage.DoubleAccel;
import domain.ViolentAbilityPackage.HallowPurple;
import domain.ViolentAbilityPackage.InfiniteVoid;

public class AbilityFactory {
	private InfiniteVoid infiniteVoid;
	private DoubleAccel doubleAccel;
	private HallowPurple hallowPurple;
	private MagicalAbility ability;
	private static AbilityFactory abilityFactory;

	private AbilityFactory() {
	}

	public static AbilityFactory getInstance() {
		if (abilityFactory == null) {
			abilityFactory = new AbilityFactory();
		}
		return abilityFactory;
	}

	public MagicalAbility getAbility(int i) {
		if (i == 0) {
			ability = new ChanceGivingAbility();
		} else if (i == 1) {
			ability = new MagicalHex();
		} else if (i == 2) {
			ability = new NoblePhantasmExpansion();
		} else if (i == 3) {
			ability = new UnstoppableEnchantedSphere();
		}
		return ability;
	}

	public MagicalAbility activateViolentActivity(int randomValue) {
		doubleAccel = new DoubleAccel();
		hallowPurple = new HallowPurple();
		infiniteVoid = new InfiniteVoid();

		MagicalAbility ability = null;
		if (randomValue == 0)
			ability = infiniteVoid;
		if (randomValue == 1)
			ability = doubleAccel;
		if (randomValue == 2)
			ability = hallowPurple;
		return ability;
	}
}
