/*	Netpaint 16
 *	Authors: Katie Pan & Niven Francis
 *
 *	Section Leaders: Bree Collins & Cody Macdonald
 *	Due: 11/7/16
 *	
 *	Last Edited: 11/7 10:10
 *
 *	Line.java-------------------------------
 *	|
 *	|	Line extends PaintObject and draws
 *	|	a line depending on the points passed.
 *	|
 *
 */

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public class Line extends PaintObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Line(Color color, Point point, Point point2) {
		super(color, point, point2);
	}
	
	
	public void draw(Graphics g) {
		g.setColor(super.getColor());
		g.drawLine(super.getPoint1().x, super.getPoint1().y, super.getPoint2().x, super.getPoint2().y);
	}


	@Override
	public java.awt.Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isImage() {
		// TODO Auto-generated method stub
		return false;
	}
}
