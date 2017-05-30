package com.eidiko.monitoring.alerts.model;

public class Status {

	private int code;
	private String message;

	public Status() {
	}

	public Status(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static Status getSuccesStatus(String message) {

		return new Status(200, message);
	}

	public static Status getFailureStatus(String message) {
		return new Status(999, message);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
