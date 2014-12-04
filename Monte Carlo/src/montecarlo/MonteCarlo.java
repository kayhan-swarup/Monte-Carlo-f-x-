package montecarlo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MonteCarlo {
	public static JButton button;
	public static	JLabel label,xLabel;
	public static	JTextField text,x1Text,x2Text;
	
	public static JPanel inputPanel;
//	public static JFrame input;
	public static JFrame frame;
	public static double x1=0;
	public static double x2=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		input = new JFrame();
		frame = new JFrame("MonteCarlo");
		frame.setLayout(null);
		inputPanel = new JPanel();
		inputPanel.setLayout(null);
		
//		frame.setBounds(100,100,400,600);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 600);
		inputPanel.setBounds(0,0,400,200);
//		input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		input.setLayout(null);
		
		text = new JTextField();
		x1Text = new JTextField();
		x2Text = new JTextField();
		
		JLabel example = new JLabel("e.g: f(x) = 2x^2-4x+3");
		example.setBounds(20,50,150,30);
		
		button = new JButton("Find Area");
		button.setBounds(200,50,120,25);
		label = new JLabel("F(x)= ");
		xLabel = new JLabel("< x <");
		
		label.setBounds(5,15,40,40);
		text.setBounds(50,15,100,40);
		x1Text.setBounds(160,15,30,30);xLabel.setBounds(210,15,40,30);x2Text.setBounds(260,15,30,30);
		button.addActionListener(new MyListener());
		
		inputPanel.add(label);inputPanel.add(text);inputPanel.add(x1Text);
		inputPanel.add(xLabel);inputPanel.add(x2Text);inputPanel.add(button);
		inputPanel.add(example);
		inputPanel.setVisible(true);
		frame.add(inputPanel);
		frame.setVisible(true);
		
	      
	      
	}
	
	static class MyListener implements ActionListener{
		Equation equation;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			frame.setBackground(Color.BLACK);
			if(text.getText().length()<=0||x1Text.getText().length()<=0||x2Text.getText().length()<=0)
				return;
			x1 = Double.parseDouble(x1Text.getText());x2=Double.parseDouble(x2Text.getText());
			String equ = text.getText();
//			
//			input.setVisible(false);
		      
		      equation = new Equation(equ);
		      
		      equation.setBounds(0,150,400,400);
		      equation.setVisible(true);
		      frame.setVisible(true);
		      
		      frame.add(equation);
//		      frame.pack();
		      equation.getSimulatedArea();
		      
//		      System.out.println(equation.getArea(Double.parseDouble(x1Text.getText()),Double.parseDouble(x2Text.getText())));
		}
		
	}

}
