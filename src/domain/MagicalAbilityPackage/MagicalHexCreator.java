package domain.MagicalAbilityPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.GameStartup;
import domain.PhantasmPackage.Phantasm;
import domain.SaveLoadPackage.SaveLoadHandler;

public class MagicalHexCreator implements Serializable {
	
	public static ArrayList<MagicalHex> magicalHexList;



	public static List<IMagicalAbilityListener> magicalHexListeners = new ArrayList<>();
	public static boolean isMagicalHexActive=false;
	


	
	
	public MagicalHexCreator() {
		magicalHexList = new ArrayList<MagicalHex>();
		
	}
	

	public void hexButtonClicked() {		
		//activate Hex
		this.isMagicalHexActive=true;
		Phantasm.getInstance().setMagicalHexCancelTime(GameStartup.getTimeInSeconds()+30);
		Phantasm.getInstance().setNextHexReleaseTime(GameStartup.getTimeInSeconds()+1);
		
	}
	
	public void addMagicalHex(String side) {
		MagicalHex hex = MagicalHexFactory.getInstance().getHex(side);
		hex.setPaintTime(GameStartup.getTimerCount()+1);
		magicalHexList.add(hex);
	}
	
	public void addMagicalHexListener(IMagicalAbilityListener lis) {
		magicalHexListeners.add(lis);
		for(MagicalHex hex : magicalHexList) {
			
			hex.addMagicalAbilityListener(lis);
		}
	}
	
	public void releaseTwoHexes() {
		//create two hexes at both sides
		this.addMagicalHex("left side");
		this.addMagicalHex("right side");
		this.publishHexEvent();
	}
	
	public void publishHexEvent() {
		for (IMagicalAbilityListener l : magicalHexListeners) {
			ArrayList<MagicalHex> tempList = new ArrayList<MagicalHex>();
			for (MagicalHex hexDomain : magicalHexList) {
				if(!hexDomain.isReleased() || SaveLoadHandler.loadCompleted) {
					hexDomain.setReleased(true);
					tempList.add(hexDomain);
				}
			}
			l.onHexEvent(tempList);
		}
	}

	public void stopMagicalHex() {
		this.isMagicalHexActive=false;
	}


	
	

}
