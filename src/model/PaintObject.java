/*	Netpaint 16
 *	Authors: Katie Pan & Niven Francis
 *
 *	Section Leaders: Bree Collins & Cody Macdonald
 *	Due: 11/7/16
 *	
 *	Last Edited: 11/7 10:10
 *
 *	PaintObject.java-------------------------------
 *	|
 *	|	Super and abstract class used for multiple
 *	|	paint objects.
 *	|
 *
 */


package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public abstract class PaintObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public abstract java.awt.Image getImage();
	
	public abstract boolean isImage();
}