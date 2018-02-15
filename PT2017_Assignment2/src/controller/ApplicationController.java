package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.Queue;
import model.Client;


public class ApplicationController extends Thread {

	private int currentTime; // simulation time: how many seconds have passed
								// relative to
	// the start of the simulation
	private int minArrivingTime;
	private int maxArrivingTime;
	private int minServiceTime;
	private int maxServiceTime;
	private int queueNumber;
	private int simulationInterval;
	private int clientNumber;

	private ArrayList<Client> allClients = new ArrayList<>();
	private Vector<Queue> queues;
	Random randomGen = new Random();
	private int maximumNumberOfClients;
	private int peakHour;
	private int averageWaitingTime;
	private int lastArrivalTime;
	private JTextArea textTime = new JTextArea();
	private JTextArea queueRepresentation = new JTextArea();
	private JLabel lblPeakHour = new JLabel();
	private JLabel lblAverageWaitTime = new JLabel();
	private JTextArea txtLog = new JTextArea();
	private int queueIndex;

	public ApplicationController(int minArrivingTime, int maxArrivingTime, int minServiceTime, int maxServiceTime,
			int queueNumber, int simulationInterval, int clientNumber, JTextArea queueRepresentation,
			JTextArea textTime, JLabel lblPeakHour, JLabel lblAverageWaitTime, JTextArea txtLog) {
		this.textTime = textTime;
		this.queueRepresentation = queueRepresentation;
		this.minArrivingTime = minArrivingTime;
		this.maxArrivingTime = maxArrivingTime;
		this.minServiceTime = minServiceTime;
		this.maxServiceTime = maxServiceTime;
		this.queueNumber = queueNumber;
		this.simulationInterval = simulationInterval;
		this.clientNumber = clientNumber;
		this.lblPeakHour = lblPeakHour;
		this.lblAverageWaitTime = lblAverageWaitTime;
		this.txtLog = txtLog;

		queues = new Vector<Queue>(clientNumber);
	}

	public void createClients() {
		lastArrivalTime = 0;
		int arrivalTime = 1 + randomGen.nextInt(maxArrivingTime - minArrivingTime + 1);
		int serviceTime = minServiceTime + randomGen.nextInt(maxServiceTime - minServiceTime);
		String ID = UUID.randomUUID().toString().substring(0, 5);
		Client c = new Client(arrivalTime, serviceTime, ID);
		allClients.add(c);
		lastArrivalTime = arrivalTime;
		for (int i = 1; i < this.clientNumber; i++) {
			arrivalTime = lastArrivalTime + 1 + randomGen.nextInt(maxArrivingTime - minArrivingTime + 1);
			serviceTime = minServiceTime + randomGen.nextInt(maxServiceTime - minServiceTime);
			ID = UUID.randomUUID().toString().substring(0, 5);
			c = new Client(arrivalTime, serviceTime, ID);
			allClients.add(c);
			lastArrivalTime = arrivalTime;
		}
	}

	public synchronized void addClientToQueue(Client c) {
		int min = maximumNumberOfClients * maxServiceTime;
		int index = 0;
		for (int j = 0; j < queues.size(); j++) {
			if (queues.elementAt(j).getTotalWaitingTime() < min) {
				min = queues.elementAt(j).getTotalWaitingTime();
				index = j;
			}
		}
		queues.elementAt(index).enqueue(c);
		queueIndex = index + 1;
	}

	public void setPeakHour() {
		int sumOfClients = 0;
		for (int i = 0; i < queues.size(); i++) {
			sumOfClients += queues.elementAt(i).queueSize();
		}
		if (sumOfClients > maximumNumberOfClients) {
			this.maximumNumberOfClients = sumOfClients;
			this.peakHour = currentTime;
		}
	}

	public void setAverageWaitingTime() {
		int sum = 0;
		int count = 0;
		for (int i = 0; i < queues.size(); i++) {
			sum = sum + queues.elementAt(i).getTotalWaitingTime();
			count += queues.elementAt(i).queueSize();
		}
		if (count > 0) {
			this.averageWaitingTime = sum / count;
		} else {
			this.averageWaitingTime = 0;
		}
	}
	
	public int getPeakHour() {
		return this.peakHour;
	}

	public int getMaximumNumberOfClients() {
		return this.maximumNumberOfClients;
	}

	public int getQueueNumber() {
		return this.queueNumber;
	}

	public String listQueueStates() {
		String string = new String("");
		for (int i = 0; i < this.queueNumber; i++) {
			string = string + "Queue" + (i + 1) + " ";
			Queue q = queues.elementAt(i);
			for (int j = 0; j < q.queueSize(); j++) {
				string = string + q.getClients().get(j).getServiceTime() + " ";
			}
			string = string + "\n" + "\n";
		}
		return string;
	}

	public void run() {
		
		String logText = new String("");

		createClients();
		for (int i = 0; i < this.queueNumber; i++)
			queues.add(new Queue(i + 1));
		for (currentTime = 1; currentTime <= simulationInterval; currentTime++) {
			textTime.setText(Integer.toString(currentTime));
			ArrayList<Thread> threads = new ArrayList<Thread>();
			for (int i = 0; i < this.queueNumber; i++) {
				final int ii;
				ii = i;
				Thread t = new Thread( new Runnable() {
					public void run() {
						queues.elementAt(ii).go();
					}
				});
				t.start();
				threads.add(t);
			}
			
			for (Client c : allClients) {
				if (currentTime == c.getArrivalTime()) {
					addClientToQueue(c);
					logText = logText + c.toString() + " at the queue: " + queueIndex + "\n";
					txtLog.setText(logText);
				}
			}

			for (Thread t : threads) {
				try {
					t.join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			setAverageWaitingTime();
			setPeakHour();
			queueRepresentation.setText(listQueueStates());
			lblPeakHour.setText("Peak hour: " + Integer.toString(peakHour));
			lblAverageWaitTime.setText("Average waiting time at the queues is: " + Integer.toString(averageWaitingTime));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logText = logText + "FINISH";
		txtLog.setText(logText);
	}
}
