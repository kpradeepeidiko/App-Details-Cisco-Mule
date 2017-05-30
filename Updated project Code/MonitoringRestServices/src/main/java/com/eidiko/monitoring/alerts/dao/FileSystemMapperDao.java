package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import com.eidiko.monitoring.alerts.model.FileSystemAppMapper;

public interface FileSystemMapperDao extends BaseDao {
	public List<FileSystemAppMapper> getFileSystemMapperList();
}
