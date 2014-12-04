package montecarlo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JPanel;

public class Equation extends JPanel{
	
	String str;double power = 1;
		double constant = 1;
	ArrayList<Segment> equationList = new ArrayList<Equation.Segment>();
	public Equation(String str){
		Segment segment;
		this.str =  str;
		Scanner sc;
		int t=0;
		int start_point=0;
		for(int i=1; i<str.length();i++){

				if(str.charAt(i)=='+'||str.charAt(i)=='-'){
					segment = new Segment(str.substring(start_point,i));
					if(str.charAt(i)=='-')
						start_point  = i;
					else start_point = i+1;
					equationList.add(segment);
					}
				else if(i+1==str.length()){
					equationList.add(new Segment(str.substring(start_point,str.length())));
				}
			
		}
		
		
	}
	public double getValue(double x){
		double sum = 0;
		for(int i=0;i<equationList.size();i++){
			sum += equationList.get(i).getValue(x);
		}
		return sum;
				
	}
	
	
	
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}

	class Segment {
		double constant=1;
		double power=0;
		Segment(double con,double pow){
			this.constant = con;
			this.power = pow;
		}
		
		Segment(String str){
			System.out.println(str);
			if(str.charAt(0)=='x')
				constant = 1;
			else if(str.contains("x"))
				constant *= Double.parseDouble(str.substring(0, str.indexOf('x')));
			else constant *= Double.parseDouble(str);
			
			if(str.contains("^"))
				power = Double.parseDouble(str.substring(str.indexOf('^')+1, str.length()));
			if(str.contains("x")&&!str.contains("^"))
				power = 1;
			System.out.println("Constant:"+constant+" power: "+power);
			
		}

		double getValue(double x){
			return constant * Math.pow(x, power);
		}
		
	}
	public final double [] origin = {200,200};
	public double getWindowX(double x){
		return origin[0] + x*10;
	}
	public double getWindowY(double y){
		return origin[1] - y*10;
	}
	

	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(400,400);
		
	}
	

	
	
	public double area = 0;
	boolean areaChecked = false;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		setBackground(null);
		Graphics2D g2 = (Graphics2D)g;
		g2.draw(new Line2D.Double(new Point2D.Double(getWindowX(0),getWindowY(-20)),new Point2D.Double(getWindowX(0),getWindowY(20))));
		g2.draw(new Line2D.Double(new Point2D.Double(getWindowX(-20),getWindowY(0)),new Point2D.Double(getWindowX(20),getWindowY(0))));
		g2.draw(new Line2D.Double(new Point2D.Double(getWindowX(MonteCarlo.x1),getWindowY(0)),new Point2D.Double(getWindowX(MonteCarlo.x1),getWindowY(20))));
		g2.draw(new Line2D.Double(new Point2D.Double(getWindowX(MonteCarlo.x2),getWindowY(0)),new Point2D.Double(getWindowX(MonteCarlo.x2),getWindowY(20))));
		g2.setColor(Color.BLACK);
		for(double i = -20;i<20;i+=.3){
			double y1 = getValue(i);double y2 = getValue((i+.3));
			g2.draw(new Line2D.Double(new Point2D.Double(getWindowX(i),getWindowY(y1)),new Point2D.Double(getWindowX(i+.3),getWindowY(y2))));
			if(i%40==0){
//				System.out.println("[X,Y] => ["+getWindowX(i)+", "+getWindowY(y1)+"]");
			}
		}
		
			
		
	}
	public void getSimulatedArea(){
		double count = 0;
		int i=0;
		repaint();
		
		Graphics2D g2 = (Graphics2D)getGraphics();
		super.paint(g2);
			areaChecked=true;
			
			g2.setColor(Color.RED);
		for(i=0;i<100000;i++){
			double randX = new Random().nextDouble()*(MonteCarlo.x2-MonteCarlo.x1) + MonteCarlo.x1;
			double randY = new Random().nextDouble()*20;
//			System.out.println(randX+" "+randY);
			g2.fill(new Ellipse2D.Double(getWindowX(randX),getWindowY(randY),.5,.5));
			if(getValue(randX)>=randY)
				count++;
			

		}
		
		area = (double)20*(MonteCarlo.x2-MonteCarlo.x1)*count/100000;
		repaint();
		System.out.println("Area: "+(double)20*(MonteCarlo.x2-MonteCarlo.x1)*count/100000);
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		if(MonteCarlo.x1!=0||MonteCarlo.x2!=0){
			g.drawString(""+MonteCarlo.x1,(int)getWindowX(MonteCarlo.x1), 210);
			g.drawString(""+MonteCarlo.x2,(int)getWindowX(MonteCarlo.x2), 210);
			g.setColor(Color.BLACK);
			g.drawString("Area "+area,50,220);
			
		}
		
		
		
		
		
		
	}
	
	
	

}
