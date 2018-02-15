package model;

import java.time.LocalDateTime;

public class MonitoredData {

	private String activityLabel;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public MonitoredData(String activityLabel, LocalDateTime startTime, LocalDateTime endTime)
	{
		this.activityLabel = activityLabel;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getActivityLabel() {
		return activityLabel;
	}

	public void setActivityLabel(String activityLabel) {
		this.activityLabel = activityLabel;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

}