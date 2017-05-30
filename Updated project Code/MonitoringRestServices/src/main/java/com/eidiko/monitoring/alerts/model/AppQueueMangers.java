package com.eidiko.monitoring.alerts.model;

public class AppQueueMangers extends QueueManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long mapperId;
	private String queueName;

	public long getMapperId() {
		return mapperId;
	}

	public void setMapperId(long mapperId) {
		this.mapperId = mapperId;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	@Override
	public String toString() {
		return "AppQueueMangers [mapperId=" + mapperId + "]";
	}

}
