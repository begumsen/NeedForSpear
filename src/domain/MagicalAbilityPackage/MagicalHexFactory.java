package domain.MagicalAbilityPackage;

import domain.PhantasmPackage.Phantasm;

public class MagicalHexFactory {
	private MagicalHex hex;
	private static MagicalHexFactory magicalHexFactory;
	
	public MagicalHexFactory() {	
	}
	
	public static MagicalHexFactory getInstance() {
		if (magicalHexFactory == null) {
			magicalHexFactory = new MagicalHexFactory();
		}
		return magicalHexFactory;
	}
	
	public MagicalHex getHex(String side) {
		double dx, dy;
		int xLoc, yLoc;
		int radius=3;
		
		//final draft:
		double alpha = Math.toRadians(Math.abs(Phantasm.getInstance().getRotationAngle()));
		int phantasmCenterX = Phantasm.getInstance().getX() + Phantasm.getInstance().getLen()/2;
		int phantasmCenterY = Phantasm.getInstance().getY();
		
		if(Phantasm.getInstance().getRotationAngle()==0) {
			
			//NON-ROTATED PHANTASM
			dx=0;
			dy=-1; //or -0.02?
			if(side.equals("left side")) {
				xLoc = Phantasm.getInstance().getX() + 1;
				yLoc = Phantasm.getInstance().getY() - (2 * radius);
				hex = new MagicalHex(xLoc, yLoc, dx, dy, radius);
			}else {
				xLoc = Phantasm.getInstance().getX() + Phantasm.getInstance().getLen() - (2*radius) - 1;
				yLoc = Phantasm.getInstance().getY() - (2 * radius);
				hex = new MagicalHex(xLoc, yLoc, dx, dy, radius);
			}
			
		}else if(Phantasm.getInstance().getRotationAngle()<0) {
			//COUNTER-CLOCKWISE ROTATED PHANTASM			
			if(side.equals("left side")) {
				dx=-1;
				dy=-1;
				xLoc = (int) (phantasmCenterX - ((Phantasm.getInstance().getLen()/2) * Math.cos(alpha)));
				yLoc = (int) (phantasmCenterY + ((Phantasm.getInstance().getLen()/2) * Math.sin(alpha)));
				hex = new MagicalHex(xLoc, yLoc, dx, dy, radius);
			}else { //right side
				dx=-1;
				dy=-1;
				xLoc = (int) (phantasmCenterX + ((Phantasm.getInstance().getLen()/2) * Math.cos(alpha)));
				yLoc = (int) (phantasmCenterY - ((Phantasm.getInstance().getLen()/2) * Math.sin(alpha)));
				hex = new MagicalHex(xLoc, yLoc, dx, dy, radius);			
			}
			
		}else {
			//CLOCKWISE ROTATED PHANTASM
			if(side.equals("left side")) {
				dx=1;
				dy=-1;
				xLoc = (int) (phantasmCenterX - ((Phantasm.getInstance().getLen()/2) * Math.cos(alpha)));
				yLoc = (int) (phantasmCenterY - ((Phantasm.getInstance().getLen()/2) * Math.sin(alpha)));
				hex = new MagicalHex(xLoc, yLoc, dx, dy, radius);
			}else {
				dx= 1;
				dy=-1;
				xLoc = (int) (phantasmCenterX + ((Phantasm.getInstance().getLen()/2) * Math.cos(alpha)));
				yLoc = (int) (phantasmCenterY + ((Phantasm.getInstance().getLen()/2) * Math.sin(alpha)));
				hex = new MagicalHex(xLoc, yLoc, dx, dy, radius);
			}
			
		}
		
		return hex;
	}

}
