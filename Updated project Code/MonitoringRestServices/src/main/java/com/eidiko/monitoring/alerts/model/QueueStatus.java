package com.eidiko.monitoring.alerts.model;

public class QueueStatus {

	private String queueName;
	private int queueCurrentDepth;
	private int openInputCount;
	private int openOutputCount;
	private String applicationTag;
	private String applicationType;
	private String channelName;
	private String connectionName;
	private String processId;

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public int getQueueCurrentDepth() {
		return queueCurrentDepth;
	}

	public void setQueueCurrentDepth(int queueCurrentDepth) {
		this.queueCurrentDepth = queueCurrentDepth;
	}

	public int getOpenInputCount() {
		return openInputCount;
	}

	public void setOpenInputCount(int openInputCount) {
		this.openInputCount = openInputCount;
	}

	public int getOpenOutputCount() {
		return openOutputCount;
	}

	public void setOpenOutputCount(int openOutputCount) {
		this.openOutputCount = openOutputCount;
	}

	public String getApplicationTag() {
		return applicationTag;
	}

	public void setApplicationTag(String applicationTag) {
		this.applicationTag = applicationTag;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getConnectionName() {
		return connectionName;
	}

	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

}
