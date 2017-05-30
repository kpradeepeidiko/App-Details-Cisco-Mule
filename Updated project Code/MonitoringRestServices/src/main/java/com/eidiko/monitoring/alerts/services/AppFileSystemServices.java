package com.eidiko.monitoring.alerts.services;

import java.util.List;

import com.eidiko.monitoring.alerts.model.AppFileName;
import com.eidiko.monitoring.alerts.model.AppFileSystem;
import com.eidiko.monitoring.alerts.model.FileSystem;

public interface AppFileSystemServices {

	public void addFileSystem(FileSystem appfilesystem) throws Exception;

	public void deleteFileSystem(long id) throws Exception;

	public void updateFileSystem(FileSystem appfilesystem) throws Exception;

	public List<FileSystem> getappFileSystemList();

	public FileSystem getFileSystemById(long id) throws Exception;

	public List<com.eidiko.monitoring.alerts.model.AppFileSystem> getFileSystemDetails(
			long id) throws Exception;

	public List<AppFileSystem> getFileSystem(long id) throws Exception;

	public List<AppFileName> getFileNameList();

	public boolean deleteFileSystemDetails(long id) throws Exception;

}
