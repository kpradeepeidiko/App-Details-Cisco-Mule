package com.eidiko.monitoring.alerts.model;

public class EventWithProperties {
	private long id;
	private String mqObjectType;
	private String mqObjectName;
	private String mqQmgrName;
	private String mqObjectStatus;
	private String mqObjectDate;
	private int reasonCode;
	private String mqEventHostName;
	private String mqEventInsertedDate;
	private long eventLevel;
	private String eventDescription;
	private String fsPath;
	private String fsMount;

	public String getFsMount() {
		return fsMount;
	}

	public void setFsMount(String fsMount) {
		this.fsMount = fsMount;
	}

	public long getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(long eventlLevel) {
		this.eventLevel = eventlLevel;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMqObjectType() {
		return mqObjectType;
	}

	public void setMqObjectType(String mqObjectType) {
		this.mqObjectType = mqObjectType;
	}

	public String getMqObjectName() {
		return mqObjectName;
	}

	public void setMqObjectName(String mqObjectName) {
		this.mqObjectName = mqObjectName;
	}

	public String getMqQmgrName() {
		return mqQmgrName;
	}

	public void setMqQmgrName(String mqQmgrName) {
		this.mqQmgrName = mqQmgrName;
	}

	public String getMqObjectStatus() {
		return mqObjectStatus;
	}

	public void setMqObjectStatus(String mqObjectStatus) {
		this.mqObjectStatus = mqObjectStatus;
	}

	public String getMqObjectDate() {
		return mqObjectDate;
	}

	public void setMqObjectDate(String mqObjectDate) {
		this.mqObjectDate = mqObjectDate;
	}

	public int getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getMqEventHostName() {
		return mqEventHostName;
	}

	public void setMqEventHostName(String mqEventHostName) {
		this.mqEventHostName = mqEventHostName;
	}

	public String getMqEventInsertedDate() {
		return mqEventInsertedDate;
	}

	public void setMqEventInsertedDate(String mqEventInsertedDate) {
		this.mqEventInsertedDate = mqEventInsertedDate;
	}

	public String getFsPath() {
		return fsPath;
	}

	public void setFsPath(String fsPath) {
		this.fsPath = fsPath;
	}

}
