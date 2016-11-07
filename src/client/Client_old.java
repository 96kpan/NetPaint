package client;
//package netpaint;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Point;
//import java.util.Vector;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
///**
//  * A JPanel GUI for Netpaint that has all paint objects drawn on it.
//  * Currently, a list of paint objects is hardcoded.  A JPanel exists
//  * in this JFrame that will draw this list of paint objects.
//  * 
//  * @author Rick Mercer
// */
//
//public class Client extends JFrame {
//
//  public static void main(String[] args) {
//    Client client = new Client();
//    
//    // All of these PaintObject objects must be constructed with two Point objects.
//    // The first point could be to the upper left or to the lower right 
//
//    // Create six line object, where any line must be drawn between the
//    // two Point objects in the provided color. 
//    PaintObject a = new Line(Color.RED, new Point(10, 100), new Point(500, 100));
//    PaintObject b = new Line(Color.CYAN, new Point(250, 150), new Point(250, 5));
//    PaintObject c = new Line(Color.GREEN, new Point(255, 10), new Point(255, 145));
//    PaintObject d = new Line(Color.BLACK, new Point(245, 145), new Point(245, 10));
//    PaintObject oneMore = new Line(Color.BLACK, new Point(0, 0), new Point(245, 145));
//    PaintObject anOther = new Line(Color.GRAY, new Point(500, 0), new Point(255, 145));
//    allPaintObjects = new Vector<>();
//    allPaintObjects.add(a);
//    allPaintObjects.add(b);
//    allPaintObjects.add(c);
//    allPaintObjects.add(d);
//    allPaintObjects.add(oneMore);
//    allPaintObjects.add(anOther);
//
//    // Draw five rectangles
//    // First Point(200, 200) is above and the left of the second point
//    PaintObject e = new Rectangle(Color.PINK, new Point(200, 200), new Point(350, 500));
//    // First Point (150, 300) is below and to the right of the second point
//    PaintObject f = new Rectangle(Color.CYAN, new Point(150, 300), new Point(100, 100));
//    PaintObject g = new Rectangle(Color.GREEN, new Point(400, 200), new Point(420, 250));
//    PaintObject h = new Rectangle(Color.BLUE, new Point(500, 380), new Point(360, 420));
//    PaintObject i = new Rectangle(Color.RED, new Point(400, 520), new Point(540, 480));
//    allPaintObjects.add(e);
//    allPaintObjects.add(f);
//    allPaintObjects.add(g);
//    allPaintObjects.add(h);
//    allPaintObjects.add(i);
//
//    // Draw five ovals 
//    // First Point(500, 20) is above and the left of the second point
//    PaintObject j = new Oval(Color.BLACK, new Point(500, 20), new Point(600, 220));
//    // Make an oval that is wider than higher. First Point is still upper left
//    PaintObject k = new Oval(Color.GREEN, new Point(500, 220), new Point(680, 230));
//    // First Point(800, 250) is below and to the right of the second point for a circle
//    PaintObject l = new Oval(Color.RED, new Point(760, 320), new Point(660, 220));
//    // Another circle with upper left to the other side
//    PaintObject m = new Oval(Color.BLUE, new Point(600, 350), new Point(700, 400));
//    // One more oval that should be missing the bottom
//    PaintObject n = new Oval(Color.YELLOW, new Point(600, 400), new Point(700, 633));
//    allPaintObjects.add(j);
//    allPaintObjects.add(k);
//    allPaintObjects.add(l);
//    allPaintObjects.add(m);
//    allPaintObjects.add(n);
//
//    client.setVisible(true);
//  }
//
//  private DrawingPanel drawingPanel;
//  private static Vector<PaintObject> allPaintObjects;
//
//  public Client() {
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    setResizable(false);
//
//    setLocation(20, 20);
//    setSize(800, 600);
//
//    drawingPanel = new DrawingPanel();
//
//    add(drawingPanel, BorderLayout.CENTER);
//    setVisible(true);
//  }
//
//  /**
//   * This is where all the drawing goes.
//   * @author mercer
//   */
//  class DrawingPanel extends JPanel {
//
//     public void paintComponent(Graphics g) {
//      super.paintComponent(g);
//      g.setColor(Color.white);
//      g.fillRect(0, 0, this.getWidth(), this.getHeight());
//
//      // draw all of the paint objects
//      for (PaintObject ob : allPaintObjects)
//        ob.draw(g);
//    }
//  }
//}
