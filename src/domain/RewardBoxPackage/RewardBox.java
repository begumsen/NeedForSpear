package domain.RewardBoxPackage;

// FACTORY PATTERN
import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.GameStartup;
import domain.MagicalAbilityPackage.ChanceGivingAbility;
import domain.MagicalAbilityPackage.ExplosiveRemains;
import domain.MagicalAbilityPackage.MagicalAbility;
import domain.PhantasmPackage.Phantasm;
import domain.PlayerPackage.Player;
import domain.SaveLoadPackage.SaveLoadHandler;
import ui.RewardBoxUI;

public class RewardBox implements Serializable {

	// location
	public double xLocation;
	public double yLocation;

	// size
	public double sizeX;
	public double sizeY;

	// speed
	public double speedY;
	public boolean isAdded;

	public MagicalAbility magicalAbility;
	
	private int paintTime = 0;

	List<IRewardListener> rewardBoxListeners = new ArrayList<>();

	public RewardBox(double xLocation, double yLocation, double sizeX, double sizeY, double speedY,
			MagicalAbility magicalAbility) {

		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.speedY = speedY;
		this.magicalAbility = magicalAbility;
		this.isAdded = false;

	}

	// ---------------------------GETTERS AND SETTERS---------------------------

	public double getxLocation() {
		return xLocation;
	}

	public void setxLocation(double xLocation) {
		this.xLocation = xLocation;
	}

	public double getyLocation() {
		return yLocation;
	}

	public void setyLocation(double yLocation) {
		this.yLocation = yLocation;
	}

	public double getSizeX() {
		return sizeX;
	}

	public void setSizeX(double sizeX) {
		this.sizeX = sizeX;
	}

	public double getSizeY() {
		return sizeY;
	}

	public void setSizeY(double sizeY) {
		this.sizeY = sizeY;
	}

	public double getSpeedY() {
		return speedY;
	}

	public MagicalAbility getAbility() {
		return magicalAbility;
	}

	public void setAbility(MagicalAbility magicalAbility) {
		this.magicalAbility = magicalAbility;
	}

	// ---------------------------------------------------------------------------------

	public Color getColor() {
		if (magicalAbility instanceof ExplosiveRemains) {
			return Color.DARK_GRAY;
		} else {
			return Color.PINK;
		}
	}

	public void addRewardBoxListener(IRewardListener lis) { // this used to be addRBL2
		// TODO Auto-generated method stub
		rewardBoxListeners.add(lis);

	}

	public Rectangle getRect() {
		return new Rectangle((int) xLocation, (int) yLocation, (int) sizeX, (int) sizeY);
	}

	public int getIsCaught() {
		return isCaught;
	}

	public void setIsCaught(int isCaught) {
		this.isCaught = isCaught;
	}

	private int isCaught = 0;

	/*
	 * public void checkCatchAndUpdateLocation(RewardBox rewardBox) {
	 * 
	 * if((rewardBox.getyLocation()> Phantasm.getInstance().getY()) &&
	 * (rewardBox.getxLocation()> Phantasm.getInstance().getX())&&
	 * (rewardBox.getxLocation()<
	 * Phantasm.getInstance().getX()+Phantasm.getInstance().getWidth())) { //caught
	 * the rewardBox isCaught++; if(isCaught == 1) {
	 * System.out.println(magicalAbility); } }else {
	 * rewardBox.setyLocation(rewardBox.getyLocation()+1); }
	 * publishMoveRewardBoxEvent(rewardBox);
	 * 
	 * 
	 * }
	 */
	public void checkCatchAndUpdateLocation() {
		
		if(this.getPaintTime() == GameStartup.getTimerCount()) {
			this.setPaintTime(GameStartup.getTimerCount()+1);
		
		if ((yLocation < Phantasm.getInstance().getY() + Phantasm.getInstance().getThickness())&&(yLocation > Phantasm.getInstance().getY()) && (xLocation > Phantasm.getInstance().getX())
				&& (xLocation < Phantasm.getInstance().getX() + Phantasm.getInstance().getLength()) &&(Phantasm.getInstance().getRotationAngle() == 0)) {
			// caught the rewardBox
			isCaught++;
			if (isCaught == 1) {

				if (magicalAbility instanceof ChanceGivingAbility) {

					Player.getInstance().incrementChance();

				} else if (magicalAbility instanceof ExplosiveRemains) {

					Player.getInstance().loseChance();

				} else {
					Player.getInstance().gainMagicalAbility(magicalAbility);
				}
				publishCaughtRewardBoxEvent(this);
			}
		}
		else if ( (Phantasm.getInstance().getRotationAngle() != 0) &&(((((Phantasm.getInstance().getX() + (Phantasm.getInstance().getLen() / 2))
				- ((Phantasm.getInstance().getLen() / 2)
						* Math.cos(Math.toRadians(Phantasm.getRotationAngle() * (-1))))) <= xLocation)
				&& ((Phantasm.getInstance().getX() + (Phantasm.getInstance().getLen() / 2)) > xLocation)
				&& ((((Phantasm.getInstance().getX() + (Phantasm.getInstance().getLen() / 2)) - xLocation)
						* Math.tan(Math.toRadians(Phantasm.getRotationAngle() * (-1)))
						+ (Phantasm.getInstance().getY())) <= yLocation + sizeY))
				||

				// touches right half of phantasm when rotated
				((((Phantasm.getInstance().getX() + (Phantasm.getInstance().getLen() / 2))
						+ ((Phantasm.getInstance().getLen() / 2)
								* Math.cos(Math.toRadians(Phantasm.getRotationAngle() * (-1))))) >= xLocation)
						&& ((Phantasm.getInstance().getX() + (Phantasm.getInstance().getLen() / 2)) <= xLocation)
						&& (((Phantasm.getInstance().getY()) - ((xLocation
								- (Phantasm.getInstance().getX() + (Phantasm.getInstance().getLen() / 2)))
								* Math.tan(Math.toRadians(Phantasm.getRotationAngle() * (-1))))) <= yLocation
										+ sizeY))))

		{
			isCaught++;
			if (isCaught == 1) {

				if (magicalAbility instanceof ChanceGivingAbility) {

					Player.getInstance().incrementChance();

				} else if (magicalAbility instanceof ExplosiveRemains) {

					Player.getInstance().loseChance();

				} else {
					Player.getInstance().gainMagicalAbility(magicalAbility);
				}
				publishCaughtRewardBoxEvent(this);
			}
		}
		
		
		else {
			if (!GameStartup.pause && !SaveLoadHandler.isLoad)
				yLocation++;
			publishMoveRewardBoxEvent(this);
		}
		
		
		}

	}

	public void publishMoveRewardBoxEvent(RewardBox rewardBox) {// RewardBox rewardBox) {

		for (IRewardListener l : rewardBoxListeners)

			l.onMoveRewardBoxEvent(rewardBox);// rewardBox);

	}

	public void publishCaughtRewardBoxEvent(RewardBox rewardBox) {

		for (IRewardListener l : rewardBoxListeners)

			l.onCaughtRewardBoxEvent(rewardBox);

	}

	public void removeRewardBox(RewardBox rewardBox, RewardBoxUI rewardBoxUI) {
		// TODO Auto-generated method stub

		publishRemoveRewardBox(rewardBox, rewardBoxUI);
	}

	public void publishRemoveRewardBox(RewardBox rewardBox, RewardBoxUI rewardBoxUI) {

		for (IRewardListener l : rewardBoxListeners)

			l.onRemoveRewardBoxEvent(rewardBox, rewardBoxUI);

	}

	public int getPaintTime() {
		return paintTime;
	}

	public void setPaintTime(int paintTime) {
		this.paintTime = paintTime;
	}

}
