package ui;

import java.util.ArrayList;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import domain.MagicalAbilityPackage.MagicalHex;


public class MagicalHexUI extends JLabel {
	
	public static ArrayList<MagicalHex> magicalHexDomainList;
	public MagicalHex hexDomain;
	
	public MagicalHexUI(MagicalHex hexDomain) {
		this.hexDomain= hexDomain;
		
		
		this.setOpaque(true);
		this.setBackground(Color.gray);
		this.setBounds(hexDomain.getxLocation(),hexDomain.getyLocation(),hexDomain.getRadius()*2,hexDomain.getRadius()*2);
		
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		this.setBounds(hexDomain.getxLocation(),hexDomain.getyLocation(),
				hexDomain.getRadius() * 2, hexDomain.getRadius() * 2);
		hexDomain.checkHitAndUpdateLocation();		
		
	}
	


}
