package domain.MagicalAbilityPackage;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import domain.ObstaclePackage.ObstacleCreator;
import domain.PhantasmPackage.Phantasm;
import domain.PlayerPackage.Player;
import domain.SpherePackage.EnchantedSphere;
import domain.MagicalAbilityPackage.MagicalHexCreator;

public class MagicalAbilityHandler implements Serializable {
	private static MagicalAbilityHandler instance;
	
	MagicalHexCreator magicalHexCreator;

	private MagicalAbilityHandler() {
		magicalHexCreator = new MagicalHexCreator();

	}

	public static MagicalAbilityHandler getInstance() {
		if (instance == null) {
			instance = new MagicalAbilityHandler();
		}
		return instance;
	}

	public void addMagicalHexListener(IMagicalAbilityListener lis) {
		magicalHexCreator.addMagicalHexListener(lis);
		
	}
	
	public void hexButtonClicked() {
		if(!magicalHexCreator.isMagicalHexActive && Player.getInstance().getMagicalHexCount() != 0) {
			magicalHexCreator.hexButtonClicked();

		}
	}
	public void expandPhantasm() {
		if (!Phantasm.getInstance().isExpanded() && Player.getInstance().getNoblePhantasmExpansionCount() != 0) {
			Phantasm.getInstance().expand();
		}
	}

	public void makeSphereUnstoppable() {
		if (!EnchantedSphere.getInstance().isUnstoppable()
				&& Player.getInstance().getUnstoppableEnchantedSphereCount() != 0) {
			EnchantedSphere.getInstance().makeUnstoppable();
		}
	}

	public void checkAbilityCancel(int currentTime) {
		if (currentTime == Phantasm.getInstance().getExpansionCancelTime()) {
			Phantasm.getInstance().deExpand();
		}
		if (currentTime == EnchantedSphere.getInstance().getUnstoppableSphereCancelTime()) {
			EnchantedSphere.getInstance().makeStoppable();
		}
		if(currentTime == Phantasm.getInstance().getMagicalHexCancelTime()) {
			magicalHexCreator.stopMagicalHex();
		}
		if (currentTime == Phantasm.getInstance().getNextHexReleaseTime() && magicalHexCreator.isMagicalHexActive) {
			Phantasm.getInstance().setNextHexReleaseTime(currentTime+3);
			magicalHexCreator.releaseTwoHexes();
		}		
		if (currentTime == EnchantedSphere.getInstance().getDoubleAccelCancelTime()) {
			EnchantedSphere.getInstance().removeDoubleAccelslowDown();
		}
		if (currentTime == ObstacleCreator.getInstance().getInfiniteVoidCancelTime()) {
			ObstacleCreator.getInstance().deactivateInfiniteVoid();
		}
	}

	public void interpretMagicalAbilityChoice(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_T) {
			if (!Phantasm.getInstance().isExpanded() && Player.getInstance().getNoblePhantasmExpansionCount() != 0) {
				Phantasm.getInstance().expand();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_U) {
			if (!EnchantedSphere.getInstance().isUnstoppable()
					&& Player.getInstance().getUnstoppableEnchantedSphereCount() != 0) {
				EnchantedSphere.getInstance().makeUnstoppable();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_H) {
			if (!magicalHexCreator.isMagicalHexActive /*&& Player.getInstance().getMagicalHexCount()>0*/) {
				magicalHexCreator.hexButtonClicked();
			}
		}
	}
}
