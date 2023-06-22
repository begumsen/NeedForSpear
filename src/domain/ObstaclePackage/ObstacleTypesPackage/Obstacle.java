package domain.ObstaclePackage.ObstacleTypesPackage;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.GameStartup;
import domain.ObstaclePackage.IObstacleListener;
import domain.ObstaclePackage.ObstacleCreator;
import domain.ObstaclePackage.MovingStrategy.IMovingBehaviorStrategy;

public abstract class Obstacle implements Serializable {

	// location
	private double xLocation;
	private double yLocation;

	// size
	private double sizeX;
	private double sizeY;

	// speed
	private double speedX;
	private boolean pause;

	protected boolean isMoving;
	private boolean isVisible;
	private char type;
	protected transient IMovingBehaviorStrategy movingBehavior;
	int direction = 1;

	private boolean isFrozen = false;
	private int paintTime=0;

	List<IObstacleListener> listeners = new ArrayList<>();
	ObstacleCreator obstacleCreator;

	public Obstacle(double xLocation, double yLocation, double sizeX, double sizeY, double speedX, boolean isMoving,
			boolean isVisible, char type) {

		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.speedX = speedX;
		this.isMoving = isMoving;
		this.isVisible = isVisible;
		this.type = type;

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

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public boolean isFrozen() {
		return isFrozen;
	}

	public void setFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}

	// ---------------------------------------------------------------------------------

	public void updateIsVisible() {
		this.isVisible = false;
	}

	public void obstacleIsTouched() {
		updateIsVisible();
		publishBreakEvent(this);
		ObstacleCreator.getInstance().checkWin();
	}

	public Color getColor() {
		return Color.BLACK;
	}

	// ------------------ reflection related methods
	public Rectangle getRect() {
		return new Rectangle((int) xLocation, (int) yLocation, (int) sizeX, (int) sizeY);
	}

	public void addBreakListener(IObstacleListener lis) {
		listeners.add(lis);
	}

	public void publishBreakEvent(Obstacle obstacle) {
		for (IObstacleListener l : listeners) {
			l.onHitEvent(obstacle);
		}
	}

	public IMovingBehaviorStrategy getMovingBehavior() {
		return movingBehavior;
	}

	public void setMovingBehavior(IMovingBehaviorStrategy movingBehavior) {
		this.movingBehavior = movingBehavior;
	}

	public void updateLocation() {
		if (this.getPaintTime() == GameStartup.getTimerCount()) {
		int[] location = this.getMovingBehavior().move(this.getxLocation(), this.getyLocation(), direction, this);
		this.setxLocation(location[0]);
		this.setyLocation(location[1]);
		this.setDirection(location[2]);
		publishMoveEvent(location[0], location[1]);
		this.setPaintTime(GameStartup.getTimerCount()+1);
		}
	}

	public void publishMoveEvent(int x, int y) {
		for (IObstacleListener l : listeners) {
			l.onMoveEvent(x, y);
		}
	}

	public void setMovingBehavior() {

	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getPaintTime() {
		return paintTime;
	}

	public void setPaintTime(int paintTime) {
		this.paintTime = paintTime;
	}

}
