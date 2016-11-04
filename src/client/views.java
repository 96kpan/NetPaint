package client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.Line;
import model.ImageObject;
import model.Oval;
import model.PaintObject;
import model.Rectangle;

/**
 * A JPanel GUI for Netpaint that has all paint objects drawn on it.
 * Currently, a list of paint objects is hardcoded.  A JPanel exists
 * in this JFrame that will draw this list of paint objects.
 * 
 * @author Rick Mercer
 */

public class views extends JFrame {

	private int xInitPosition;
	private int yInitPosition;
	private int xEndPosition;
	private int yEndPosition;
	private int count = 0;
	private DrawingPanel drawingPanel;
	private static Vector<PaintObject> allPaintObjects;
	private static Vector<PaintObject> tempObjects;
	private JRadioButton lineButton;
	private JRadioButton rectangleButton;
	private JRadioButton ovalButton;
	private JRadioButton imageButton;
	private ButtonGroup group = new ButtonGroup();
	private Color color;
	private JScrollPane scroll;
	private String shape;

	public static void main(String[] args) {
		views client = new views();

		allPaintObjects = new Vector<>();

		client.setVisible(true);
	}

	public views() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);

		//fill any screen with your app
		setLocation(20, 20);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);

		//do this to make the drawing area bigger with jscrollpane
		//this.setPreferredSize(new Dimension(2048, 1024));

		drawingPanel = new DrawingPanel();
		drawingPanel.setPreferredSize(new Dimension(2*screenSize.width, 2*screenSize.height-150));

		scroll = new JScrollPane(drawingPanel);
		scroll.setPreferredSize(new Dimension(screenSize.width, screenSize.height-150));
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		color = Color.RED; //default selection
		JButton colorChooser = new JButton();
		colorChooser.setText("Color Chooser");
		colorChooser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				JColorChooser jcc = new JColorChooser();
				Color selectedColor = jcc.showDialog(null, "Please select a color", Color.RED);
				color = selectedColor;
			}
		});

		//radio buttons for shape choice
		lineButton = new JRadioButton();
		lineButton.setSelected(true); //default selection
		lineButton.setText("Line");
		rectangleButton = new JRadioButton();
		rectangleButton.setText("Rectangle");
		ovalButton = new JRadioButton();
		ovalButton.setText("Oval");
		imageButton = new JRadioButton();
		imageButton.setText("Image");

		group.add(lineButton);
		group.add(rectangleButton);
		group.add(ovalButton);
		group.add(imageButton);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(lineButton);
		buttonPanel.add(rectangleButton);
		buttonPanel.add(ovalButton);
		buttonPanel.add(imageButton);

		this.setLayout(new FlowLayout());

		add(scroll, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.CENTER);
		add(colorChooser, BorderLayout.CENTER);

		setVisible(true);
	}

	/**
	 * This is where all the drawing goes.
	 * @author mercer
	 */
	class DrawingPanel extends JPanel {

		boolean dragging = false;

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			this.addMouseListener(new MouseAdapter() {



				@Override
				public void mousePressed(MouseEvent e) {

					if(!dragging){
						xInitPosition = e.getX();
						yInitPosition = e.getY();
						System.out.println("xInitPosition  " + xInitPosition + " yInitPosition " + yInitPosition);
						dragging = true;
						return;
					}

					else{
						PaintObject draw = null;

						xEndPosition = e.getX();
						yEndPosition = e.getY();
						dragging = false;

						if(lineButton.isSelected()){
							draw = new Line(color, new Point(xInitPosition, yInitPosition), new Point(xEndPosition, yEndPosition));
							shape = "Line";
						}
						else if(rectangleButton.isSelected()){
							draw = new Rectangle(color, new Point(xInitPosition, yInitPosition), new Point(xEndPosition, yEndPosition));
							shape = "Rectangle";
						}
						else if(ovalButton.isSelected()){
							draw = new Oval(color, new Point(xInitPosition, yInitPosition), new Point(xEndPosition, yEndPosition));
							shape = "Oval";
						}
						else if(imageButton.isSelected()){
							draw = new ImageObject(color, new Point(xInitPosition, yInitPosition), new Point(xEndPosition, yEndPosition));
							shape = "Image";
						}

						//allPaintObjects.add(draw);
						repaint();
						draw.draw(g);
					}


				}

			});

			this.addMouseMotionListener(new MouseMotionAdapter()
			{
				//mouse drag listener
				public void mouseDragged(MouseEvent e)
				{
					PaintObject temp = null;
					Point tempPt = e.getPoint();
					//System.out.println("xEndPosition " + tempPt.getX() + " yEndPosition " + tempPt.getY());

					if(lineButton.isSelected()){

						temp = new Line(color, new Point(xInitPosition, yInitPosition), tempPt);
						System.out.println("xEndPosition " + tempPt.getX() + " yEndPosition " + tempPt.getY());
						shape = "Line";
					}
					else if(rectangleButton.isSelected()){
						temp = new Rectangle(color, new Point(xInitPosition, yInitPosition), tempPt);
						shape = "Rectangle";
					}
					else if(ovalButton.isSelected()){
						temp = new Oval(color, new Point(xInitPosition, yInitPosition), tempPt);
						shape = "Oval";
					}
					else if(imageButton.isSelected()){
						temp = new ImageObject(color, new Point(xInitPosition, yInitPosition), new Point(xEndPosition, yEndPosition));
						shape = "Image";
					}
					repaint();
					temp.draw(g);



					//System.out.println("x " + m.getX() + " y " + m.getY());

				}

				@Override
				public void mouseMoved(MouseEvent e){

					System.out.println(" x "  + e.getX() + "  y " + e.getY());
					

					PaintObject temp = null;
					
					
					//allPaintObjects.remove(temp);
					if(lineButton.isSelected()){
						temp = new Line(color, new Point(xInitPosition, yInitPosition), e.getPoint());
					}
					else if(rectangleButton.isSelected()){
						temp = new Rectangle(color, new Point(xInitPosition, yInitPosition), new Point(xEndPosition, yEndPosition));
					}
					else if(ovalButton.isSelected()){
						temp = new Oval(color, new Point(xInitPosition, yInitPosition), new Point(xEndPosition, yEndPosition));
					}
					else if(imageButton.isSelected()){
						temp = new ImageObject(color, new Point(xInitPosition, yInitPosition), new Point(xEndPosition, yEndPosition));
					}

					repaint();
					temp.draw(g);



				}
			});

			// draw all of the paint objects
			for (PaintObject ob : allPaintObjects)
				ob.draw(g);
				
		}
	}

	public Point getInitPoint(){
		return new Point(this.xInitPosition, this.yInitPosition);
	}

	public Point getEndPoint(){
		return new Point(this.xEndPosition, this.yEndPosition);
	}

	public Color getColor(){
		return color;
	}

	public String getPaintObject(){
		return shape;
	}


}
