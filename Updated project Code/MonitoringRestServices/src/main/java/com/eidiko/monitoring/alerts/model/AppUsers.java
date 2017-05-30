package com.eidiko.monitoring.alerts.model;

public class AppUsers extends User {
	private long userMapperId;

	public long getUserMapperId() {
		return userMapperId;
	}

	public void setUserMapperId(long userMapperId) {
		this.userMapperId = userMapperId;
	}

	@Override
	public String toString() {
		return "AppUsers [userMapperId=" + userMapperId + "]";
	}

}
