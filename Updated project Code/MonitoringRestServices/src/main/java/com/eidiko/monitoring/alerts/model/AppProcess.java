package com.eidiko.monitoring.alerts.model;

public class AppProcess extends Process {

	private static final long serialVersionUID = 1L;

	private long mapperId;
	private String processName;

	public long getMapperId() {
		return mapperId;
	}

	public void setMapperId(long mapperId) {
		this.mapperId = mapperId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	@Override
	public String toString() {
		return "AppProcess [mapperId=" + mapperId + ", processName="
				+ processName + "]";
	}

}
