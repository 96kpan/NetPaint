//1245

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class ImageObject extends PaintObject implements Serializable {

	private int newHeight;
	private int newWidth;

	public ImageObject(Color color1, Point point, Point point2) {
		super(color1, point, point2);
	}

	@Override
	public void draw(Graphics g) {
		try {



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


			int newWidth = x2-x1;
			int newHeight = y2-y1;
			

			if(newWidth != 0 && newHeight != 0){
				java.awt.Image inputImage = ImageIO.read(new File("image.jpg")).getScaledInstance(newWidth,
						newHeight, java.awt.Image.SCALE_DEFAULT);
				//g.drawImage(inputImage, x1, y1, null);
				g.drawImage(this.getImage(), x1, y1, x2-x1, y2-y1, (ImageObserver) null);
			}
			


		} catch (IOException e) {

		}

	}

	@Override
	public java.awt.Image getImage() {
		// TODO Auto-generated method stub
		try {



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


			int newWidth = x2-x1;
			int newHeight = y2-y1;

			java.awt.Image inputImage = ImageIO.read(new File("image.jpg")).getScaledInstance(newWidth,
					newHeight, java.awt.Image.SCALE_DEFAULT);

			return inputImage;


		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isImage(){
		return true;
	}

	public int getHeight(){
		return this.newHeight;
	}

	public int getWidth(){
		return this.newWidth;
	}



}
