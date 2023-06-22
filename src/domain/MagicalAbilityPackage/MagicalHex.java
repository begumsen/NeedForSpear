package domain.MagicalAbilityPackage;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import domain.GameStartup;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;
import domain.ObstaclePackage.*;
import domain.SaveLoadPackage.SaveLoadHandler;

public class MagicalHex extends MagicalAbility {
	
	private int xLocation;
	private int yLocation;
	private double dx;
	private double dy;
	private int radius;
	private String side;
	private int paintTime = 0;
	
	private boolean isReleased=false;
	
	ArrayList<Obstacle> obslist;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private Rectangle rectOfHex;
	
	List<IMagicalAbilityListener> listeners = new ArrayList<>();
	
	public MagicalHex() {
		
	}
	public MagicalHex(String side, int phantasmRotationAngle) {
		this.radius=3;
	}
	
	public MagicalHex(int xLoc, int yLoc, double dx, double dy, int radius) {
		this.xLocation=xLoc;
		this.yLocation=yLoc;
		this.dx=dx;
		this.dy=dy;
		this.radius=radius;
		
	}
	//GETTERS AND SETTERS-------------
	public int getxLocation() {
		return xLocation;
	}

	public void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}

	public int getyLocation() {
		return yLocation;
	}

	public void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean isReleased() {
		return isReleased;
	}
	public void setReleased(boolean isReleased) {
		this.isReleased = isReleased;
	}
	public void setObsList(ArrayList<Obstacle> obs) {
		this.obslist = obs;
	}
	//--------------------
	
	public void updateLocation() {

		
		//Wall reflection
		if (!GameStartup.pause && !SaveLoadHandler.loadCompleted) {
			if (xLocation < radius)
				dx = Math.abs(dx);
			if (xLocation > screenSize.getWidth() - radius)
				dx = -Math.abs(dx);

			if (yLocation + (2 * radius) > 0) {
				yLocation += dy;
				xLocation += dx;
			}
		}
			
	}
	
	public void checkHitAndUpdateLocation() {
		
		//MODIFIES: rectOfHex, (when there is no hit on obstacle) xLocation and yLocation
		//EFFECTS: Checks whether the hex hit any obstacle or not in that timer period. 
		// If the hex doesn't hit, then updates location. If it hits any, then removes breaks that obstacle.
		if(this.getPaintTime() == GameStartup.getTimerCount()) {
			
			this.setPaintTime(GameStartup.getTimerCount()+1);
		this.obslist = ObstacleCreator.getInstance().getObsList();
		
		
		this.rectOfHex = new Rectangle(xLocation, yLocation, 2 * radius, 2 * radius);
		
		
		//As there are so much obstacles to hit, and it is sometimes hard to continue playing.
		//We wanted magical hex to hit more than one obstacle to help player approach to winning.
		for (int i = 0; i < this.obslist.size(); i++) {
			checkHitOnObstacle(i);		
		}
		updateLocation();
		}
		
	}
	
	public void checkHitOnObstacle(int i) {
		
		// Rectangle version of obstacle
		Rectangle rectOfObs = this.obslist.get(i).getRect();
		
		if (collisionBtw(this.rectOfHex, rectOfObs)) {			
			
			//Touch the obstacle
			if(!this.obslist.get(i).isFrozen()) this.obslist.get(i).obstacleIsTouched();
		
		}
		
	}
	
	public void addMagicalAbilityListener(IMagicalAbilityListener lis) {
		listeners.add(lis);
	}


	public boolean collisionBtw(Rectangle rectA, Rectangle rectB) {
		return rectA.intersects(rectB);
	}
	public int getPaintTime() {
		return paintTime;
	}
	public void setPaintTime(int paintTime) {
		this.paintTime = paintTime;
	}
}
