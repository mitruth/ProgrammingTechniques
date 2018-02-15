package model;

import java.util.ArrayList;

public class Queue {

	private ArrayList<Client> clientQueue;
	private int totalWaitingTime;
	private int queueNr;
	private int averageServiceTime;

	public Queue(int number) {
		this.clientQueue = new ArrayList<Client>();
		this.totalWaitingTime = 0;
		this.queueNr = number;
		this.averageServiceTime = 0;
	}

	public void go() 
	{
			if (this.queueSize() != 0) {
				int firstServiceTime = this.clientQueue.get(0).getServiceTime();
				this.clientQueue.get(0).setServiceTime(firstServiceTime - 1);
				setAverageServiceTime();
				this.totalWaitingTime--;
				if (this.clientQueue.get(0).getServiceTime() == 0) 
					this.dequeue();
			}
	}

	public int getQueueNr() {
		return this.queueNr;
	}

	public ArrayList<Client> getClients() {
		return this.clientQueue;
	}

	public void enqueue(Client c) {
		this.clientQueue.add(c);
		this.setTotalWaitingTime(this.getTotalWaitingTime() + c.getServiceTime());
	}

	public void dequeue() {
		if (!(this.clientQueue.isEmpty())) {
			Client c = this.clientQueue.get(0);
			this.clientQueue.remove(c);
		}
	}

	public int getTotalWaitingTime() {
		return this.totalWaitingTime;
	}

	public int queueSize() {
		return this.clientQueue.size();
	}
	
	public void setTotalWaitingTime(int twt) {
		this.totalWaitingTime=twt;
	}

	public void setAverageServiceTime() {
		int count = 0;
		int sumServiceTime = 0;
		for (Client c : this.clientQueue) {
			count++;
			sumServiceTime += c.getServiceTime();
		}
		if (count > 0) {
			this.averageServiceTime = sumServiceTime / count;
		} else {
			this.averageServiceTime = 0;
		}
	}
	
	public int getAverageServiceTime() {
		return this.averageServiceTime;
	}
}
