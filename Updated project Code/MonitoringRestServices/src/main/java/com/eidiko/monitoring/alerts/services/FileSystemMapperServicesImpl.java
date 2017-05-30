package com.eidiko.monitoring.alerts.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.eidiko.monitoring.alerts.dao.FileSystemMapperDao;
import com.eidiko.monitoring.alerts.model.FileSystemAppMapper;

public class FileSystemMapperServicesImpl implements FileSystemMapperServices {
	public static final Logger logger = Logger
			.getLogger(FileSystemMapperServicesImpl.class);
	@Autowired
	FileSystemMapperDao fileSystemMapperDao;

	@Override
	public void deleteFileSystemMapper(long id) throws Exception {
		logger.info("entered");
		fileSystemMapperDao.delete(FileSystemAppMapper.class, id);
		logger.info("exiting");
	}

	@Override
	public void addFileSystemAppMapper(FileSystemAppMapper fileSystemMapper)
			throws Exception {
		logger.info("entered");
		fileSystemMapperDao.saveOrUpdate(fileSystemMapper);
		logger.info("exiting");
	}

	@Override
	public List<FileSystemAppMapper> getAppFileSystemMapperList() {
		logger.info("entered");
		return fileSystemMapperDao.getFileSystemMapperList();

	}

	@Override
	public void addAppFileSystemMapper(FileSystemAppMapper fileSystemMapper)
			throws Exception {
		logger.info("entered");
		fileSystemMapperDao.saveOrUpdate(fileSystemMapper);
		logger.info("exiting");
	}

	@Override
	public void updateAppFileSystemMapper(FileSystemAppMapper fileSystemMapper)
			throws Exception {
		logger.info("entered");
		fileSystemMapperDao.saveOrUpdate(fileSystemMapper);
		logger.info("exiting");
	}

	@Override
	public FileSystemAppMapper getAppFileSystemMapperById(long id)
			throws Exception {
		logger.info("entered");
		return (FileSystemAppMapper) fileSystemMapperDao.getEntityById(
				FileSystemAppMapper.class, id);
	}
}
