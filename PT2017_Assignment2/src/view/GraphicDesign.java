package view;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import controller.ApplicationController;

	public class GraphicDesign extends Frame{

		private static final long serialVersionUID = 1L;

		 private JTextField input1 = new JTextField();
		 private JTextField input2 = new JTextField();
		 private JTextField input3 = new JTextField();
		 private JTextField input4 = new JTextField();
		 private JTextField input5 = new JTextField();
		 private JTextField input6 = new JTextField();
		 private JTextField input7 = new JTextField();
		 private JTextArea textTime = new JTextArea();
		 
		 private JTextArea queueRepresentation = new JTextArea();
		 
		public GraphicDesign() {//constructor, everything is done here
			
			JFrame window = new JFrame ("Queues");
			window.setBounds(100, 100, 900, 800);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			
			JLabel lblMinArrivingTime = new JLabel("Minimum arriving time");
			lblMinArrivingTime.setBounds(15, 10, 135, 20);
			window.getContentPane().add(lblMinArrivingTime);

			JLabel lblMaxArrivingTime = new JLabel("Maximum arriving time");
			lblMaxArrivingTime.setBounds(15, 50, 135, 20);
			window.getContentPane().add(lblMaxArrivingTime);

			JLabel lblMinServiceTime = new JLabel("Minimum service time");
			lblMinServiceTime.setBounds(15, 90, 135, 20);
			window.getContentPane().add(lblMinServiceTime);

			JLabel lblMaxServiceTime = new JLabel("Maximum service time");
			lblMaxServiceTime.setBounds(15, 130, 135, 20);
			window.getContentPane().add(lblMaxServiceTime);

			JLabel lblNrQueues = new JLabel("Number of queues");
			lblNrQueues.setBounds(15, 170, 135, 20);
			window.getContentPane().add(lblNrQueues);

			JLabel lblSimTime = new JLabel("Simulation time");
			lblSimTime.setBounds(15, 210, 135, 20);
			window.getContentPane().add(lblSimTime);
			
			JLabel lblClientNr = new JLabel("Number of clients");
			lblClientNr.setBounds(15, 240, 135, 20);
			window.getContentPane().add(lblClientNr);
			
			JLabel lblPeakHour = new JLabel("Peak hour: ");
			lblPeakHour.setBounds(500, 250, 300, 20);
			window.getContentPane().add(lblPeakHour);
			
			JLabel lblAverageWaitTime = new JLabel("Average waiting time at the queues is: ");
			lblAverageWaitTime.setBounds(500, 300, 300, 20);
			window.getContentPane().add(lblAverageWaitTime);
			
			JLabel lblLog = new JLabel ("Log of events");
			lblLog.setBounds(500, 10, 135, 20);
			window.getContentPane().add(lblLog);
			
			JTextArea txtLog = new JTextArea ();
			txtLog.setEditable(false);
			txtLog.setBounds(500, 40, 350, 150);
			txtLog.setLineWrap(true);
			txtLog.setWrapStyleWord(true);
			JScrollPane scrollLog = new JScrollPane(txtLog);
			scrollLog.setBounds(500, 40, 350, 150);
			scrollLog.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
			window.getContentPane().add(scrollLog);

			input1.setBounds(235, 13, 135, 20);
			window.getContentPane().add(input1);

			input2.setBounds(235, 52, 135, 20);
			window.getContentPane().add(input2);

			input3.setBounds(235, 90, 135, 20);
			window.getContentPane().add(input3);

			input4.setBounds(235, 130, 135, 20);
			window.getContentPane().add(input4);

			input5.setBounds(235, 170, 135, 20);
			window.getContentPane().add(input5);

			input6.setBounds(235, 210, 135, 20);
			window.getContentPane().add(input6);
			
			input7.setBounds(235, 250, 135, 20);
			window.getContentPane().add(input7);
			
			JLabel timeLabel = new JLabel("Time");
			timeLabel.setBounds(500, 200, 55, 10);
			window.getContentPane().add(timeLabel);

			textTime.setEditable(false);
			textTime.setBounds(550, 200, 135, 20);
			window.getContentPane().add(textTime);
			
			queueRepresentation.setBounds(10, 400, 800, 300);
			window.getContentPane().add(queueRepresentation);
			
			JButton startSimulation = new JButton ("START");
			startSimulation.setBounds(15, 270, 200, 100);
			startSimulation.setForeground(Color.RED);
			
			startSimulation.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					if (input1.getText().length() != 0 && input2.getText().length() != 0 && input3.getText().length() != 0 && 
							input4.getText().length() != 0 && input5.getText().length() != 0 && input6.getText().length() != 0 && 
							input7.getText().length() != 0) {
						try{
							int minimumArrivingTime = (int)Integer.parseInt(input1.getText());
							int maximumArrivingTime = (int)Integer.parseInt(input2.getText());
							int minimumServiceTime = (int)Integer.parseInt(input3.getText());
							int maximumServiceTime = (int)Integer.parseInt(input4.getText());
							int queueNumber  = (int)Integer.parseInt(input5.getText());
							int simulationTime = (int)Integer.parseInt(input6.getText());
							int clientNr = (int)Integer.parseInt(input7.getText());
							ApplicationController app = new ApplicationController(minimumArrivingTime, maximumArrivingTime, minimumServiceTime, maximumServiceTime
									, queueNumber, simulationTime, clientNr, queueRepresentation, textTime, lblPeakHour, lblAverageWaitTime, txtLog);
							app.start();
						} catch (NumberFormatException s){
							JOptionPane.showMessageDialog(null,"WRONG INPUT DATA"); 
						}
					} else {
						JOptionPane.showMessageDialog(null,"WRONG INPUT DATA");
					}
				}
			});
			
			panel.add(startSimulation);
			window.add(panel);
			
			window.setVisible(true);
		} 
	}