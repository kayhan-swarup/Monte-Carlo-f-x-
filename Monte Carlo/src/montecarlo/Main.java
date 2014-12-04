package montecarlo;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	
	public static int radius = 400;
	
	public Main() throws AWTException{
		super();
		setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
		mainPanel = this;
		createAndShowGui();
		System.out.println(""+getArea());
		
	}
	
	
	public int getArea() {
		
		int [] areas = new int [100];
		for(int i=0;i<100;i++){
			int counter = 0;
			for(int j=0;j<1000;j++){
				int randomX = new Random().nextInt(radius);
				int randomY = new Random().nextInt(radius);
				if(Math.pow((randomX*randomX+randomY*randomY), .5)<=radius){
					counter++;
					System.out.println("IN");
				}else
					System.out.println("OUT");
//				if(getComponentAt(randomX,randomY)==circle)
//					System.out.println("CONTAINS!");
//				else
//					System.out.println("NOT!");
				Graphics g = getGraphics();
				g.setColor(Color.WHITE);
				g.drawOval(randomX, randomY, 1, 1);
				
			}
			areas[i] = counter*radius*radius/1000;
		}
		int totalArea = 0;
		for(int i=0;i<100;i++)
			totalArea += areas[i];
		
		return totalArea/100;
		
	}


	static JFrame frame;
	static CircleComponent circle;
	static Main mainPanel;
	private  static void createAndShowGui() {
		
		
//		Scanner scanner = new Scanner(System.in);
		System.out.println("Input Radius:");
//		radius = scanner.nextInt();
	      mainPanel.setSize(radius, radius);
	      circle = new CircleComponent(radius);
	      
//	      mainPanel.setBounds(0, 0, radius, radius);
//	      circle.setSize(radius,radius);
	      
//	      circle.setBounds(0,0,radius,radius);
	      mainPanel.setBackground(Color.BLACK);
	      mainPanel.add(circle);
	      
	      frame = new JFrame();
	      frame.setBackground(Color.BLACK);
	      frame.setSize(radius,radius);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      frame.getContentPane().add(mainPanel);
	      frame.pack();
	      frame.setLocationByPlatform(true);
	      frame.setVisible(true);
	      
	      
	      
	      
	   }
	
	
	
	

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Equation equ = new Equation("2x^2+4x");
//	      System.out.println(""+equ.getValue(1));
//			try {
//				new Main();
//			} catch (AWTException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			
//	}

}
