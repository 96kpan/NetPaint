/*	Netpaint 16
 *	Authors: Katie Pan & Niven Francis
 *
 *	Section Leaders: Bree Collins & Cody Macdonald
 *	Due: 11/7/16
 *	
 *	Last Edited: 11/7 10:10
 *
 *	Rectangle.java-------------------------------
 *	|
 *	|	Rectangle extends PaintObject and draws
 *	|	a Rectangle depending on the points passed.
 *	|
 *
 */


package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public class Rectangle extends PaintObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Rectangle(Color color1, Point point, Point point2) {
		super(color1, point, point2);
	}
	
	public void draw(Graphics g) {
		g.setColor(super.getColor());
		
		int x1 = super.getPoint1().x;
		int y1 = super.getPoint1().y;
		int x2 = super.getPoint2().x;
		int y2 = super.getPoint2().y;
		
		//find the largest values
		
		if(y2 < y1) {
			y1 = super.getPoint2().y;
			y2 = super.getPoint1().y;
			
		}
		
		if(x2 < x1) {
			x1 = super.getPoint2().x;
			x2 = super.getPoint1().x;
			
		}
		
		
		
		g.fillRect(x1, y1, x2-x1, y2-y1);
	}

	@Override
	public java.awt.Image getImage() {
		return null;
	}

	@Override
	public boolean isImage() {
		return false;
	}
}
