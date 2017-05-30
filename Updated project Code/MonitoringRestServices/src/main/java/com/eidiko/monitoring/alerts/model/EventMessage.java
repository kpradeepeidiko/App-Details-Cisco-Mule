package com.eidiko.monitoring.alerts.model;

public class EventMessage {

	private String objectName;
	private String qmgrName;
	private String eventType = null;
	private String eventDescription = null;
	private String eventGeneratedDate;
	private String hostName;
	private Long eventLevel;
	private String objectType;
	private String fsPath;

	public String getFsPath() {
		return fsPath;
	}

	public void setFsPath(String fsPath) {
		this.fsPath = fsPath;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getQmgrName() {
		return qmgrName;
	}

	public void setQmgrName(String qmgrName) {
		this.qmgrName = qmgrName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventGeneratedDate() {
		return eventGeneratedDate;
	}

	public void setEventGeneratedDate(String eventGeneratedDate) {
		this.eventGeneratedDate = eventGeneratedDate;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Long getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(Long eventLevel) {
		this.eventLevel = eventLevel;
	}

}
