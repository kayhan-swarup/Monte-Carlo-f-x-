package montecarlo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JPanel;

public class Equation extends JPanel{
	
	String str;double power = 1;
		double constant = 1;
	ArrayList<Segment> equationList = new ArrayList<Equation.Segment>();
	public Equation(String str){
		
		this.str =  str;
		Scanner sc;
		int t=0;
		int start_point=0;
		for(int i=0; i<str.length();i++){
			if(i+1 == str.length() || str.charAt(i)=='+' || str.charAt(i)=='-'){
				if(str.charAt(i)=='+'||str.charAt(i)=='-'){
					equationList.add(new Segment(str.substring(start_point,i),start_point>0&&str.charAt(start_point-1)=='-'));
					}
				else if(i+1==str.length()){
					equationList.add(new Segment(str.substring(start_point,str.length()),start_point>0&&str.charAt(start_point-1)=='-'));
				}
				if(i+1<str.length())
					start_point = i+1;
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
		Segment(String str,boolean isNegative){
			
			int start_time = 0;
//			System.out.println(str);
			Scanner sc= new Scanner(str);
			String con="";
			String pow="";
			if(isNegative)
				constant = -1;
			if(str.length()>0&&str.charAt(str.length()-1)=='x'){
				if(str.charAt(0)=='x')
					constant *= 1;
				else
					constant *= Double.parseDouble(str.substring(0,str.length()-1));
				power = 1;
			}else if(!str.contains("x")){
				constant *= Double.parseDouble(str);
				power = 0;
			}else if(str.charAt(0)=='x'){
				constant=1*constant;
				if(str.length()>2&&str.charAt(1)=='^'){
					power = Double.parseDouble(str.substring(2,str.length()));
					
				}else if(!str.contains("^"))
					power = 1;
					
			}else if(str.charAt(start_time)>=48&&str.charAt(start_time)<=57&&str.contains("^")){
				int t=0;
				while(str.length()>t&&str.charAt(t)!='x'){
					t++;
					
				}
				constant *= Double.parseDouble(str.substring(start_time, t));
				if((str.length()>t+2&&str.charAt(t+1)=='^'&&str.charAt(t+2)>=48&&str.charAt(t+2)<=57)){
					t = t +2;

					
						pow += str.charAt(t++);
						
					
					power = Double.parseDouble(pow);
						
					
				}
					
					
				
					
				
			
			}
			
			
			System.out.print("con:"+constant+"  \n pow:"+power+"\n");
				
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
	
	public double getArea(){
		double area = 0;
		double count = 0;
		Graphics g = getGraphics();
				
		g.setColor(Color.RED);
		
		for(int i=0;i<1000;i++){
			double randX = new Random().nextDouble()*(MonteCarlo.x2-MonteCarlo.x1) + MonteCarlo.x1;
			double randY = new Random().nextDouble()*20;
			System.out.println(randX+" "+randY);
			g.drawOval((int)randX, (int)randY, 2, 2);
			if(getValue(randX)>=randY)
				count++;

		}
		
		area = (double) 20*(MonteCarlo.x2-MonteCarlo.x1) * count/1000;
		return area;
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
				System.out.println("[X,Y] => ["+getWindowX(i)+", "+getWindowY(y1)+"]");
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
			System.out.println(randX+" "+randY);
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
