package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import model.Polinom;

public class GraphicDesign extends Frame{

	private static final long serialVersionUID = 1L;
	//I use three text fields, two for the input of the polynomials and one to display the output
	 JTextField output = new JTextField ("Result");
	 JTextField input1 = new JTextField("Type coefficients of first polynomial here");
	 JTextField input2 = new JTextField("Type coefficients of second polynomial here");
	public GraphicDesign() {//constructor, everything is done here
		
		JFrame window = new JFrame ("Polynomials");
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		
		
		panel.add(input1);
		panel.add(input2);
		panel.add(output);
		
		JButton addButton = new JButton ("Addition");
		JButton subButton = new JButton ("Subtraction");
		JButton derButton = new JButton ("Derivation");
		JButton intButton = new JButton ("Integration");
		JButton mulButton = new JButton ("Multiplication");
		
		addButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Polinom p1 = new Polinom();
				Polinom p2 = new Polinom();
				
				if (input1.getText().equals("Type coefficients of first polynomial here") || input2.getText().equals("Type coefficients of first polynomial here")) {
					output.setText("You must provide 2 polynomials for this operation");
				} else {
					p1.readPolinom(input1.getText());
					p2.readPolinom(input2.getText());
					output.setText(p1.add(p2).printPolinom());
				}
			}
		});
		
		subButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				output.setText("");
				Polinom p1 = new Polinom();
				Polinom p2 = new Polinom();
				
				if (input1.getText().equals("Type coefficients of first polynomial here") || input2.getText().equals("Type coefficients of first polynomial here")) {
					output.setText("You must provide 2 polynomials for this operation");
				} else {
					p1.readPolinom(input1.getText());
					p2.readPolinom(input2.getText());
					output.setText(p1.subtract(p2).printPolinom());
				}
			}
		});
		
		mulButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				output.setText("");
				Polinom p1 = new Polinom();
				Polinom p2 = new Polinom();
				
				if (input1.getText().equals("Type coefficients of first polynomial here") || input2.getText().equals("Type coefficients of first polynomial here")) {
					output.setText("You must provide 2 polynomials for this operation");
				} else {
					p1.readPolinom(input1.getText());
					p2.readPolinom(input2.getText());
					output.setText(p1.multiply(p2).printPolinom());
					
				}
			}
		});
		
		derButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				output.setText("");
				Polinom p1 = new Polinom();
				
				if (input1.getText().isEmpty() || input2.getText().isEmpty()) {
					output.setText("You must provide 2 polynomials for this operation");
				} else {
					p1.readPolinom(input1.getText());
					output.setText(p1.derive().printPolinom());
				}
			}
		});
		
		intButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				output.setText("");
				Polinom p1 = new Polinom();
				
				if (input1.getText().isEmpty() || input2.getText().isEmpty()) {
					output.setText("You must provide 2 polynomials for this operation");
				} else {
					p1.readPolinom(input1.getText());
					output.setText(p1.integrate().printPolinom());
				}
			}
		});

		
		panel.add(addButton);
		panel.add(subButton);
		panel.add(derButton);
		panel.add(intButton);
		panel.add(mulButton);
		window.add(panel);
		
		
		window.setVisible(true);
	}
	
}
