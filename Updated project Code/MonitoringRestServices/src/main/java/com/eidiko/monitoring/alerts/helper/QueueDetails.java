package com.eidiko.monitoring.alerts.helper;

public class QueueDetails {

	private String objectName;
	private String objectType;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	@Override
	public String toString() {
		return "QueueDetails [objectName=" + objectName + ", objectType="
				+ objectType + "]";
	}

}
