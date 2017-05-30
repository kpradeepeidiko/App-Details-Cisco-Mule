package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import com.eidiko.monitoring.alerts.model.AppFileName;
import com.eidiko.monitoring.alerts.model.AppFileSystem;
import com.eidiko.monitoring.alerts.model.FileSystem;

public interface AppFileSystemDao extends BaseDao {

	public List<FileSystem> getFileSystemList();

	public List<AppFileSystem> getFileSystemDetails(long id) throws Exception;

	public List<AppFileSystem> getFileSystem(long id);

	public List<AppFileName> getFileNameList();

	public boolean deleteFileSystemDetails(long id) throws Exception;;

}
