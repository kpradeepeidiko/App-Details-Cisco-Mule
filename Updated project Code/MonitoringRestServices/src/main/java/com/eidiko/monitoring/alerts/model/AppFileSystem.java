package com.eidiko.monitoring.alerts.model;

public class AppFileSystem extends FileSystem {

	private static final long serialVersionUID = 1L;
	private long mapperId;
	private String fileSystemName;

	public long getMapperId() {
		return mapperId;
	}

	public void setMapperId(long mapperId) {
		this.mapperId = mapperId;
	}

	public String getFileSystemName() {
		return fileSystemName;
	}

	public void setFileSystemName(String fileSystemName) {
		this.fileSystemName = fileSystemName;
	}

	@Override
	public String toString() {
		return "AppFileSystem [mapperId=" + mapperId + ", fileSystemName="
				+ fileSystemName + "]";
	}
}
