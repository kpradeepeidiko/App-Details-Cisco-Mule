package com.eidiko.monitoring.alerts.model;

public class NightlyBatchEvents {
	private String errorMessage;
	private String parameters;
	private String description;
	private String insertedDate;
	private String host;
	private long errorLevel;

	
	public long getErrorLevel() {
		return errorLevel;
	}

	public void setErrorLevel(long errorLevel) {
		this.errorLevel = errorLevel;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(String insertedDate) {
		this.insertedDate = insertedDate;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	

}
