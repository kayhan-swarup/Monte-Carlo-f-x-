package montecarlo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Rectangle extends JPanel {
	private int x;
	private int y;
	private int width;
	private int height;
	
	Rectangle2D.Double rectangle;
	
	public Rectangle (int radius){
		this.x = this.y = 0;
		this.width = radius;
		this.height = radius;
		
	}
	@Override
	   public Dimension getPreferredSize() {
	      return new Dimension(width,height);
	   }
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLUE);
		rectangle = new Rectangle2D.Double(x,y,width,height);
		g2d.fill(rectangle);
		
	}

}
