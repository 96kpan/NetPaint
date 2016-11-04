/*
 * Katie Pan
 * Rectangle for Netpaint
 */


package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle extends PaintObject {
	
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
}
