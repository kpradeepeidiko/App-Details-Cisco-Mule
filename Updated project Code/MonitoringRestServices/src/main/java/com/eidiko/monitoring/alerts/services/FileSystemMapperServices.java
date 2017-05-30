package com.eidiko.monitoring.alerts.services;

import java.util.List;

import com.eidiko.monitoring.alerts.model.FileSystemAppMapper;

public interface FileSystemMapperServices {

	public void deleteFileSystemMapper(long id) throws Exception;

	public void addFileSystemAppMapper(FileSystemAppMapper fileSystemMapper)
			throws Exception;

	public List<FileSystemAppMapper> getAppFileSystemMapperList();

	public void addAppFileSystemMapper(FileSystemAppMapper fileSystemMapper)
			throws Exception;

	public void updateAppFileSystemMapper(FileSystemAppMapper fileSystemMapper)
			throws Exception;

	public FileSystemAppMapper getAppFileSystemMapperById(long id1)
			throws Exception;

}
