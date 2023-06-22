package domain.SpherePackage;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.GameStartup;
import domain.MagicalAbilityPackage.UnstoppableEnchantedSphere;
import domain.ObstaclePackage.ObstacleTypesPackage.GiftObstacle;
import domain.ObstaclePackage.ObstacleTypesPackage.Obstacle;
import domain.PhantasmPackage.Phantasm;
import domain.PlayerPackage.Player;
import domain.SaveLoadPackage.SaveLoadHandler;

public class EnchantedSphere implements Serializable {
	private static EnchantedSphere instance;
	private boolean isUnstoppable = false;
	private int paintTime=0;

	public void setUnstoppable(boolean unstoppable) {
		isUnstoppable = unstoppable;
	}

	boolean collusionWithMovingPaddle = false;

	public double getPauseDx() {
		return pauseDx;
	}

	public void setPauseDx(double pauseDx) {
		this.pauseDx = pauseDx;
	}

	private double pauseDx;

	public double getPauseDy() {
		return pauseDy;
	}

	public void setPauseDy(double pauseDy) {
		this.pauseDy = pauseDy;
	}

	private double pauseDy;

	public static void setInstance(EnchantedSphere instance) {
		EnchantedSphere.instance = instance;
	}

	public boolean isUnstoppable() {
		return isUnstoppable;
	}

	String PaddleMoveDirection = "";
	private boolean doubleAccelActive = false;

	public boolean isDoubleAccelActive() {
		return doubleAccelActive;
	}

	private int xLocation;
	private int unstoppableSphereCancelTime = -1;
	private int yLocation;
	private double dx = 0;
	private double dy = -1;
	private boolean isNotdropped = true;
	private int dropNumberLeft = 3;

	public void setPaddleMoveDirection(String paddleMoveDirection) {
		PaddleMoveDirection = paddleMoveDirection;
	}

	private double epsilon = 2;
	private int radius;

	public String getPaddleMoveDirection() {
		return PaddleMoveDirection;
	}

	public void loadSphere(int x, int y, double dx, double dy) {
		xLocation = x;
		yLocation = y;
		this.dx = dx;
		this.dy = dy;

	}

	private Rectangle rectOfSphere;
	private int doubleAccelCancelTime = -1;

	ArrayList<Obstacle> obslist;

	public int getDoubleAccelCancelTime() {
		return doubleAccelCancelTime;
	}

	public void setDoubleAccelCancelTime(int doubleAccelCancelTime) {
		this.doubleAccelCancelTime = doubleAccelCancelTime;
	}

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	List<ISphereListener> listeners = new ArrayList<>();

	private EnchantedSphere() {
		this.radius = 5;
		this.xLocation = (Phantasm.getInstance().getX() + Phantasm.getInstance().getLen() / 2) - radius;
		this.yLocation = Phantasm.getInstance().getY() - radius * 2 - 1;
		this.dx = 0;
		this.dy = -2;
	}

	public void doubleAccelslowDown() {
		dx = dx / 2;
		dy = dy / 2;
		setDoubleAccelCancelTime(GameStartup.getTimeInSeconds() + 15);
		doubleAccelActive = true;
	}

	public void removeDoubleAccelslowDown() {
		dx = dx * 2;
		dy = dy * 2;
		doubleAccelActive = false;
	}

	public void setUnstoppableSphereCancelTime(int unstoppableSphereCancelTime) {
		this.unstoppableSphereCancelTime = unstoppableSphereCancelTime;
	}

	public int getUnstoppableSphereCancelTime() {
		return unstoppableSphereCancelTime;
	}

	public static EnchantedSphere getInstance() {
		if (instance == null)
			instance = new EnchantedSphere();
		return instance;
	}

	public void isPaddleMoving(boolean ismoving, String direction) {
		collusionWithMovingPaddle = ismoving; // change to false when you are done
		PaddleMoveDirection = direction;
	}

	public void makeUnstoppable() {
		Player.getInstance().loseMagicalAbility(new UnstoppableEnchantedSphere());
		isUnstoppable = true;
		setUnstoppableSphereCancelTime(GameStartup.getTimeInSeconds() + 30);
	}

	public void makeStoppable() {
		isUnstoppable = false;
	}

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

	public void setObsList(ArrayList<Obstacle> obs) {
		this.obslist = obs;
	}

	public Rectangle getRectOfSphere() {
		return this.rectOfSphere;
	}

	public void setRectOfSphere(Rectangle rectOfSphere) {
		this.rectOfSphere = rectOfSphere;
	}

	// DEGISTIR
	public boolean getisNotdropped() {
		return isNotdropped;
	}

	public void setisNotdropped(boolean isNotdropped) {
		this.isNotdropped = isNotdropped;
	}

	public int dropNumberLeft() {
		return dropNumberLeft;
	}

	public void setdropNumber(int dropNumberLeft) {
		this.dropNumberLeft = dropNumberLeft;
	}

	public void pause() {
		this.pauseDx = dx;
		this.pauseDy = dy;
		this.dx = 0;
		this.dy = 0;
	}

	public void updateLocation() {
		// Before updating, we get the rectangle version of the sphere for obstacle
		// reflection part.
		if (!SaveLoadHandler.isLoad && this.getPaintTime() == GameStartup.getTimerCount()) {
			this.rectOfSphere = new Rectangle(xLocation, yLocation, 2 * radius, 2 * radius);

			// Wall Reflection
			if (xLocation < radius)
				dx = Math.abs(dx);
			if (xLocation > screenSize.getWidth() - radius)
				dx = -Math.abs(dx);
			if (yLocation < radius)
				dy = Math.abs(dy);
			if (yLocation > screenSize.getHeight() - radius) {
				dy = 0;

				dx = 0;

				if (isNotdropped) {
					dropNumberLeft--;
					isNotdropped = false;

					Player.getInstance().loseChance();

				}

				// xLocation=(PhantasmDomain.getInstance().getX()+PhantasmDomain.getInstance().getLen()/2)-radius;
				// yLocation=PhantasmDomain.getInstance().getY()-radius*2-1;
			}
			// Phantasm Reflection
			if ((Phantasm.getInstance().getRotationAngle() == 0&&(dy>=0))
					&& ((Phantasm.getInstance().getY() < yLocation + radius * 2)
							&& ((yLocation + radius * 2) < (Phantasm.getInstance().getY() + 2 * Math.abs(dy))))) // if
																													// the
			// bottom of
			// the
			// sphere
			// touches
			// the
			// phantasm
			{
				if (touchesCorner(xLocation)) {
					if (touchesLeftCorner(xLocation)) {
						// touches left corner
						doReflectionOnCorner(0);

					} else {
						doReflectionOnCorner(1);
						// touches right corner
					}
				} else if (touchesTopBottomSide(xLocation) && Phantasm.getInstance().getIsMovingRight()) {
					// Phantasm moving right
					doReflectionOnSide(1);
					reflectFromTopBottomOnMovingObject(1);
					// touches top side
				} else if (touchesTopBottomSide(xLocation) && Phantasm.getInstance().getIsMovingLeft()) {
					// Phantasm moving left
					doReflectionOnSide(1);
					reflectFromTopBottomOnMovingObject(-1);
					// touches top side
				} else if (touchesTopBottomSide(xLocation)) {
					doReflectionOnSide(1);
					// touches top side
				}

				/*
				 * if (PhantasmDomain.getInstance().getX()-2*radius<xLocation && //
				 * PhantasmDomain.getInstance().getX()+PhantasmDomain.getInstance().getLen() >
				 * xLocation ) { doReflectionOnCorner(0); //double temp = dy; dy = -dx; dx =
				 * -temp;// }
				 */
			} else if ((dy>=0)&&( (Phantasm.getInstance().getRotationAngle() != 0) &&(((((Phantasm.getInstance().getX() + (Phantasm.getInstance().getLen() / 2))
					- ((Phantasm.getInstance().getLen() / 2)
							* Math.cos(Math.toRadians(Phantasm.getRotationAngle() * (-1))))) <= xLocation)
					&& ((Phantasm.getInstance().getX() + (Phantasm.getInstance().getLen() / 2)) > xLocation)
					&& ((((Phantasm.getInstance().getX() + (Phantasm.getInstance().getLen() / 2)) - xLocation)
							* Math.tan(Math.toRadians(Phantasm.getRotationAngle() * (-1)))
							+ (Phantasm.getInstance().getY())) <= yLocation + 2 * radius))
					||

					// touches right half of phantasm when rotated
					((((Phantasm.getInstance().getX() + (Phantasm.getInstance().getLen() / 2))
							+ ((Phantasm.getInstance().getLen() / 2)
									* Math.cos(Math.toRadians(Phantasm.getRotationAngle() * (-1))))) >= xLocation)
							&& ((Phantasm.getInstance().getX() + (Phantasm.getInstance().getLen() / 2)) <= xLocation)
							&& (((Phantasm.getInstance().getY()) - ((xLocation
									- (Phantasm.getInstance().getX() + (Phantasm.getInstance().getLen() / 2)))
									* Math.tan(Math.toRadians(Phantasm.getRotationAngle() * (-1))))) <= yLocation
											+ 2 * radius)))))

			{
				doReflectionOnRotatedPhantasm(Phantasm.getRotationAngle() * (-1));
			}
			// Obstacle Reflection

			for (int i = 0; i < this.obslist.size(); i++) {
				reflectOnObstacle(i);
			}
			this.setPaintTime(GameStartup.getTimerCount()+1);
			xLocation += dx;
			yLocation += dy;
			
			// publishMoveSphereEvent(xLocation,yLocation,dx,dy);
		}
	}

	public boolean touchesTopBottomSide(double x) {
		if ((x + radius < Phantasm.getInstance().getX() + Phantasm.getInstance().getLen() - epsilon)
				&& (x + radius > Phantasm.getInstance().getX() + epsilon)) {
			return true;
		}
		return false;

	}

	public boolean touchesCorner(double x) {
		if (((x + radius < Phantasm.getInstance().getX() + epsilon)
				&& (x + radius > Phantasm.getInstance().getX() - epsilon))
				|| ((x + radius < Phantasm.getInstance().getX() + epsilon + Phantasm.getInstance().getLen())
						&& (x + radius > Phantasm.getInstance().getX() - epsilon + Phantasm.getInstance().getLen()))) {
			return true;
		}
		return false;
	}

	public boolean touchesLeftCorner(double x) {
		if ((x + radius < Phantasm.getInstance().getX() + epsilon)
				&& (x + radius > Phantasm.getInstance().getX() - epsilon)) {
			return true;
		}
		return false;
	}

	public void doReflectionOnCorner(int corner) { // 0 = left_top and right_bottom, 1 = right_top and left_bottom
		if (corner == 0) {
			double temp = dy;
			this.dy = -dx;
			this.dx = -temp;
		} else {
			double temp = dy;
			this.dy = dx;
			this.dx = temp;
		}

	}

	public void doReflectionOnSide(int side) { // 0 = left and right sides, 1 = top and bottom sides
		if (side == 0) {
			this.dx = -dx;
		} else {
			this.dy = -dy;
		}
	}

	public void reflectOnObstacle(int i) {
		// REQUIRES: i >= 0
		// MODIFIES: In some conditions: this.dX or this.dY or both, in some conditions:
		// this.obslist.get(i)
		// EFFECTS: If the enchanted sphere collides with the obstacle (that has the
		// index i in the obslist arraylist of Obstacle),
		// the enchanted sphere is reflected (according to the "assumed" physics law of
		// motion and the position it hits the obstacle),
		// and that obstacle is touched.
		// Also, if the enchanted sphere is unstoppable, i.e. the player has used the
		// Unstoppable Enchanted Sphere Magical Ability,
		// the sphere is not reflected (continues its movement) and that obstacle is
		// touched.
		// If there is no collision, nothing changes.
		// -Arda Aliz

		// this.obslist.get(i).getDirection()==1 right
		// this.obslist.get(i).getDirection()==-1 left
		double xLocObs = this.obslist.get(i).getxLocation();
		double yLocObs = this.obslist.get(i).getyLocation();
		double sizeXObs = this.obslist.get(i).getSizeX();
		double sizeYObs = this.obslist.get(i).getSizeY();

		// Rectangle version of obstacle
		Rectangle rectOfObs = this.obslist.get(i).getRect();

		// We need absolute value of dy and dx while using them as parameters for sizes.
		double dy_abs = Math.abs(dy);
		double dx_abs = Math.abs(dx);

		// In order to detect where the sphere intersects with the obstacle,
		// we divided the obstacle's side and corner parts' and created rectangles in
		// the edge of them.
		Rectangle rectTopSide = new Rectangle((int) (xLocObs + dx_abs), (int) yLocObs, (int) (sizeXObs - 2 * dx_abs),
				(int) dy_abs);
		Rectangle rectBottomSide = new Rectangle((int) (xLocObs + dx_abs), (int) (yLocObs + sizeYObs - dy_abs),
				(int) (sizeXObs - 2 * dx_abs), (int) dy_abs);
		Rectangle rectLeftSide = new Rectangle((int) xLocObs, (int) (yLocObs + dy_abs), (int) dx_abs,
				(int) (sizeYObs - 2 * dy_abs));
		Rectangle rectRightSide = new Rectangle((int) (xLocObs + sizeXObs - dx_abs), (int) (yLocObs + dy_abs),
				(int) dx_abs, (int) (sizeYObs - 2 * dy_abs));

		Rectangle rectTopLeftCorner = new Rectangle((int) xLocObs, (int) yLocObs, (int) dx_abs, (int) dy_abs);
		Rectangle rectTopRightCorner = new Rectangle((int) (xLocObs + sizeXObs - dx_abs), (int) yLocObs, (int) dx_abs,
				(int) dy_abs);
		Rectangle rectBottomLeftCorner = new Rectangle((int) xLocObs, (int) (yLocObs + sizeYObs - dy_abs), (int) dx_abs,
				(int) dy_abs);
		Rectangle rectBottomRightCorner = new Rectangle((int) (xLocObs + sizeXObs - dx_abs),
				(int) (yLocObs + sizeYObs - dy_abs), (int) dx_abs, (int) dy_abs);

		// If the sphere intersects with the obstacle
		if (collisionBtw(this.rectOfSphere, rectOfObs)) {
			Rectangle rectOfIntersect = this.rectOfSphere.intersection(rectOfObs);
			Point intersectionPoint = rectOfIntersect.getLocation();

			// We checked from where the sphere has intersected with the obstacle by looking
			// at the intersection point.
			// 0 = left and right sides, 1 = top and bottom sides
			// There are two conditions for reflecting:
			// If the sphere is normal (Unstoppable Enchanted Sphere not active) OR
			// if Unstoppable Enchanted Sphere is active but also the obstacle is frozen
			// (InfVoid).
			if (!isUnstoppable || (isUnstoppable && this.obslist.get(i).isFrozen())) {
				if (rectTopSide.contains(intersectionPoint)) {
					if (dy > 0) {
						doReflectionOnSide(1);
						reflectFromTopBottomOnMovingObject(this.obslist.get(i).getDirection());
					}
				} else if (rectBottomSide.contains(intersectionPoint)) {
					if (dy < 0) {
						doReflectionOnSide(1);
						reflectFromTopBottomOnMovingObject(this.obslist.get(i).getDirection());
					}
				} else if (rectLeftSide.contains(intersectionPoint)) {
					if (dx > 0) {
						doReflectionOnSide(0);
					}
				} else if (rectRightSide.contains(intersectionPoint)) {
					if (dx < 0) {
						doReflectionOnSide(0);
					}
				} else if (rectTopLeftCorner.contains(intersectionPoint)) {
					doReflectionOnCorner(0);
				} else if (rectTopRightCorner.contains(intersectionPoint)) {
					doReflectionOnCorner(1);
				} else if (rectBottomLeftCorner.contains(intersectionPoint)) {
					doReflectionOnCorner(1);
				} else if (rectBottomRightCorner.contains(intersectionPoint)) {
					doReflectionOnCorner(0);
				}
			}
			// Break obstacle or decrease number in firm

			// TO ACCESS MAGICAL ABILITY WHEN A GIFT OBSTACLE IS HIT
			if (this.obslist.get(i).getType() == 'g') {
				((GiftObstacle) this.obslist.get(i)).getAbility();
			}
			// If the obstacle is not frozen because of InfVoid, then it is
			if (!this.obslist.get(i).isFrozen())
				this.obslist.get(i).obstacleIsTouched();
		}
	}

	public boolean collisionBtw(Rectangle rectA, Rectangle rectB) {
		return rectA.intersects(rectB);
	}

	public void addSphereListener(ISphereListener lis) {
		listeners.add(lis);
	}

	public void publishMoveSphereEvent(int x, int y, int dx, int dy) {
		for (ISphereListener l : listeners) {
			l.moveSphereEvent(x, y, dx, dy);

		}
	}

	public void reflectFromTopBottomOnMovingObject(int right) {
		if (right == 1) {
			if (this.dx > 0) {
				this.dx += 0.05;
			} else if (this.dx < 0) {
				this.dx = -this.dx;
			} else {
				this.dx = Math.abs(this.dy);
			}
		} else if (right == -1) {
			if (this.dx < 0) {
				this.dx -= 0.05;
			} else if (this.dx > 0) {
				this.dx = -this.dx;
			} else {
				this.dx = -Math.abs(this.dy);
			}
		}

	}

	public void resumeSpeed() {
		this.dx = pauseDx;
		this.dy = pauseDy;
	}

	private void doReflectionOnRotatedPhantasm(int alpha) {
		double velocity = Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
		if ((alpha > 0) ){
			//this.dx = velocity * Math.sin(Math.toRadians(alpha));
			//this.dy = -velocity * Math.cos(Math.toRadians(alpha));
			if (alpha < 45) {
			if(dx==0) {
				dy = -dy/2;
				dx = dy*Math.sqrt(3);
			}else if(dy == 0) {
				dx = dx/2;
				dy = -dx*Math.sqrt(3);
			}else {
				doReflectionOnCorner(0);
			}
			}else {
			doReflectionOnCorner(0);
			}

		} else {
			//this.dx = velocity * Math.sin(Math.toRadians(alpha));
			//this.dy = -velocity * Math.cos(Math.toRadians(alpha));
			if (alpha > -45) {
				if(dx==0) {
					dy = -dy/2;
					dx = -dy*Math.sqrt(3);
				}else if(dy == 0) {
					dx = dx/2;
					dy = dx*Math.sqrt(3);
				}else {
					doReflectionOnCorner(1);
				}
			}else {
			
			doReflectionOnCorner(1);
			}
		}

		// this.dx *= Math.sin(Math.toRadians(alpha));
		// this.dy *= Math.cos(Math.toRadians(alpha));

	}

	public int getPaintTime() {
		return paintTime;
	}

	public void setPaintTime(int paintTime) {
		this.paintTime = paintTime;
	}
}
