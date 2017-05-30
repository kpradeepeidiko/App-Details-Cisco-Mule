package com.eidiko.monitoring.alerts.model;

public class NBUsers extends User {

	private long usersMapperId;

	public long getUsersMapperId() {
		return usersMapperId;
	}

	public void setUsersMapperId(long usersMapperId) {
		this.usersMapperId = usersMapperId;
	}

	@Override
	public String toString() {
		return "NBUsers [usersMapperId=" + usersMapperId + "]";
	}

}
