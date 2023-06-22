package domain.PhantasmPackage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.GameStartup;
import domain.SaveLoadPackage.SaveLoadHandler;

public class Phantasm implements Serializable {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	private static Phantasm instance;
	private int paintTime = 0;
	
	private boolean isExpanded = false;

	public int getMagicalHexCancelTime() {
		return magicalHexCancelTime;
	}

	public void setMagicalHexCancelTime(int magicalHexCancelTime) {
		this.magicalHexCancelTime = magicalHexCancelTime;
	}

	private int magicalHexCancelTime;

	public int getNextHexReleaseTime() {
		return nextHexReleaseTime;
	}

	public void setNextHexReleaseTime(int nextHexReleaseTime) {
		this.nextHexReleaseTime = nextHexReleaseTime;
	}

	private int nextHexReleaseTime;

	public static void setInstance(Phantasm instance) {
		Phantasm.instance = instance;
	}

	public static void setRotationAngle(int phantasmRotationAngle) {
		rotationAngle = phantasmRotationAngle;
	}

	public void setTurningCounterClockwise(boolean turningCounterClockwise) {
		this.turningCounterClockwise = turningCounterClockwise;
	}

	private int x = 0;
	private int y = 100;
	private int slowdx = 0;
	private int expansionCancelTime = -100;
	private int singleMoveCounter = 0;

	public void setTurningClockwise(boolean turningClockwise) {
		this.turningClockwise = turningClockwise;
	}

	private boolean turningClockwise = false;
	private boolean turningCounterClockwise = false;

	public void setExpansionCancelTime(int expansionCancelTime) {
		this.expansionCancelTime = expansionCancelTime;
	}

	public boolean getIsMovingRight() {
		return this.isMovingRight;
	}

	public boolean getIsMovingLeft() {
		return this.isMovingLeft;
	}

	public boolean getExpanded() {
		return isExpanded;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpansionCancelTime() {
		return expansionCancelTime;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean expanded) {
		isExpanded = expanded;
	}

	private static int rotationAngle = 0;
	private int angleDx = 1;
	private final int minAngle = -45;
	private final int maxAngle = 45;
	private final int thickness = 20;
	private boolean isMovingRight = false;
	public int getThickness() {
		return thickness;
	}
	public int getLength() {
		return length;
	}

	private boolean isMovingLeft = false;
	private int length = 0;
	private boolean isRight = false;
	private boolean isLeft = false;

	private int rotation_pivot_x;
	private int rotation_pivot_y;
	private int direction = -1;

	private Phantasm() {

		this.length = ((int) width) / 10;
		this.x = ((int) width / 2) - (length / 2);
		this.y = (int) (height - (height / 5));
		slowdx = (length / 100)+1;
		rotation_pivot_x = x + (length / 2);
		rotation_pivot_y = y + thickness / 2;
		
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public int getLen() {
		return this.length;
	}

	public static int getRotationAngle() {
		return rotationAngle;
	}

	public void loadPhantasm(int phantasmXCoordinate) {
		x = phantasmXCoordinate;
		publishDrawPaddleEvent();
	}

	public static Phantasm getInstance() {
		if (instance == null)
			instance = new Phantasm();
		return instance;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void expand() {
		length = length * 2;
		publishDrawPaddleEvent();
		setExpanded(true);
		setExpansionCancelTime(GameStartup.getTimeInSeconds() + 30);

	}

	public void deExpand() {
		length = length / 2;
		publishDrawPaddleEvent();
		setExpanded(false);

	}

	List<IPaddleListener> listeners = new ArrayList<IPaddleListener>();

	public void rightMovement() {
		/*
		 * this.isMovingLeft = false; if (this.isMovingRight) { if (x + 2 * dx + length
		 * <= width) {
		 * 
		 * x = x + 2 * dx; } } if (x + dx + length <= width) { x = x + dx; }
		 * this.isMovingRight = true; publishDrawPaddleEvent();
		 */
		singleMoveCounter = 0;
		direction = 1;
		this.isMovingRight = true;

	}

	public void updateLocation() {
		if(this.getPaintTime() == GameStartup.getTimerCount()) {
		
		if ((direction == 1) && (isMovingRight) && (x + slowdx + length <= width)) {
			x = x + 2 * slowdx;
			return;
		}
		if ((direction == 0) && (isMovingLeft) && (x - 2 * slowdx >= 0)) {
			x = x - 2 * slowdx;
			return;
		}
		if ((singleMoveCounter <= 25) && (x + slowdx + length <= width) && (direction == 1)) {
			x = x + slowdx;
			singleMoveCounter++;
			return;
		}
		if ((singleMoveCounter <= 25) && (x - slowdx >= 0) && (direction == 0)) {
			x = x - slowdx;
			singleMoveCounter++;
			return;
		}
		}

	}

	public void updateAngle() {
		
		if(this.getPaintTime() == GameStartup.getTimerCount()) {
			this.setPaintTime(GameStartup.getTimerCount()+1);
		if (!SaveLoadHandler.isLoad) {
			if ((!turningClockwise) & (!turningCounterClockwise)) {
				if (rotationAngle < 0)
					rotationAngle++;
				else if (rotationAngle > 0)
					rotationAngle--;
			}

			if ((rotationAngle + angleDx <= maxAngle) && (turningClockwise)) {
				rotationAngle = rotationAngle + angleDx;
			}
			if ((rotationAngle - angleDx >= minAngle) && (turningCounterClockwise)) {
				rotationAngle = rotationAngle - angleDx;
			}
		}
		}
	}

	public int getRotation_pivot_x() {
		return rotation_pivot_x;
	}

	public void setMovingLeft(boolean movingLeft) {
		isMovingLeft = movingLeft;
	}

	public void setMovingRight(boolean movingRight) {
		isMovingRight = movingRight;
	}

	public void leftMovement() {
		/*
		 * this.isMovingRight = false; if (this.isMovingLeft) { if (x - 2 * slowdx >= 0)
		 * { x = x - 2 * slowdx; } } if (x - slowdx >= 0) { x = x - slowdx; }
		 * this.isMovingLeft = true; publishDrawPaddleEvent();
		 */
		singleMoveCounter = 0;
		direction = 0;
		this.isMovingLeft = true;
	}

	public void clockwiseRotation() {
		this.turningClockwise = true;
	}

	public int getRotation_pivot_y() {
		return rotation_pivot_y;
	}

	public void counterClockwiseRotation() {
		this.turningCounterClockwise = true;

	}

	public void addPaddleListener(IPaddleListener lis) {
		listeners.add(lis);
	}

	public void publishDrawPaddleEvent() {
		for (IPaddleListener l : listeners)
			l.drawPaddle();
	}
	public int getPaintTime() {
		return paintTime;
	}

	public void setPaintTime(int paintTime) {
		this.paintTime = paintTime;
	}

}
