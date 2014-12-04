package montecarlo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class CircleComponent extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int radius;
	
	Ellipse2D.Double circle;
	
	public CircleComponent(int radius){
		this.radius = radius;
		this.x = -radius;this.y = 0;
		this.width = radius*2;
		this.height = radius*2;
//		setOpaque(false);
		
	}
	@Override
	   public Dimension getPreferredSize() {
	      return new Dimension(radius,radius);
	   }
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		setForeground(Color.RED);
		Graphics2D g2d = (Graphics2D)g;
		
		circle = new Ellipse2D.Double(x,y,width,height);
		
		g2d.setColor(Color.RED);
//		g2d.setBackground(null);
		g2d.fill(circle);
		setBackground(null);
//		setBackground(null);
		
	}

}
