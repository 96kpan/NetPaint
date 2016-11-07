/*
 * Katie Pan
 * Oval for Netpaint
 */

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;


public class Oval extends PaintObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Oval(Color co, Point point, Point point2) {
		super(co, point, point2);
	}
	
	public void draw(Graphics g) {
		g.setColor(super.getColor());
		
		int x1 = super.getPoint1().x;
		int y1 = super.getPoint1().y;
		int x2 = super.getPoint2().x;
		int y2 = super.getPoint2().y;
		
		//find max value
		if(x2 < x1) {
			
			x1 = super.getPoint2().x;
			x2 = super.getPoint1().x;
		}
		
		if(y2 < y1) {
			
			y1 = super.getPoint2().y;
			y2 = super.getPoint1().y;
		}
		
		g.fillOval(x1, y1, x2-x1, y2-y1);
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
