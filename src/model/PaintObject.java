/*
 * Katie Pan
 * PaintObject for Netpaint -> Abstract, super class
 */


package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class PaintObject {
	private Color color;
	private Point pointone;
	private Point pointtwo;
	
	public PaintObject(Color thiscolor, Point point, Point point2) {
		color = thiscolor;
		pointone = point;
		pointtwo = point2;
	}
	
	public Point getPoint1() {
		return pointone;
	}
	
	
	public Color getColor() {
		return color;
	}
	
	
	
	public Point getPoint2() {
		return pointtwo;
	}

	public abstract void draw(Graphics g);
}