/*
 * Katie Pan
 * Line for Netpaint
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Line extends PaintObject {
	public Line(Color color, Point point, Point point2) {
		super(color, point, point2);
	}
	
	
	public void draw(Graphics g) {
		g.setColor(super.getColor());
		g.drawLine(super.getPoint1().x, super.getPoint1().y, super.getPoint2().x, super.getPoint2().y);
	}
}
