package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.eidiko.monitoring.alerts.model.FileSystemAppMapper;

public class FileSystemMapperDaoImpl extends BaseDaoImpl implements
		FileSystemMapperDao {

	public static final Logger logger = Logger
			.getLogger(FileSystemMapperDaoImpl.class);

	@Override
	public List<FileSystemAppMapper> getFileSystemMapperList() {
		logger.info("entered");
		return getSessionFactory().getCurrentSession()
				.createCriteria(FileSystemAppMapper.class).list();
	}
}
