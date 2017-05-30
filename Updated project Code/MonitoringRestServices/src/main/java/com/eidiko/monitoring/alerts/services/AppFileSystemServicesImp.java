package com.eidiko.monitoring.alerts.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.eidiko.monitoring.alerts.dao.AppFileSystemDao;
import com.eidiko.monitoring.alerts.model.AppFileName;
import com.eidiko.monitoring.alerts.model.AppFileSystem;
import com.eidiko.monitoring.alerts.model.FileSystem;

public class AppFileSystemServicesImp implements AppFileSystemServices {
	@Autowired
	AppFileSystemDao appFileSystemDao;
	public static final Logger logger = Logger
			.getLogger(AppFileSystemServicesImp.class);

	@Override
	public void addFileSystem(FileSystem appfilesystem) throws Exception {
		// TODO Auto-generated method stub
		logger.info("entered");
		appFileSystemDao.saveOrUpdate(appfilesystem);
		logger.info("exiting");
	}

	@Override
	public void deleteFileSystem(long id) throws Exception {
		// TODO Auto-generated method stub
		logger.info("entered");

		appFileSystemDao.delete(FileSystem.class, id);

		logger.info("exiting");
	}

	@Override
	public void updateFileSystem(FileSystem appfilesystem) throws Exception {
		// TODO Auto-generated method stub
		logger.info("entered");
		appFileSystemDao.saveOrUpdate(appfilesystem);
		logger.info("exiting");
	}

	@Override
	public List<FileSystem> getappFileSystemList() {
		// TODO Auto-generated method stub
		logger.info("entered");
		return appFileSystemDao.getFileSystemList();
	}

	@Override
	public FileSystem getFileSystemById(long id) throws Exception {
		// TODO Auto-generated method stub
		logger.info("entered");
		return (FileSystem) appFileSystemDao
				.getEntityById(FileSystem.class, id);

	}

	@Override
	public List<AppFileSystem> getFileSystemDetails(long id) throws Exception {
		logger.info("entered");
		return appFileSystemDao.getFileSystemDetails(id);
	}

	@Override
	public List<AppFileSystem> getFileSystem(long id) throws Exception {
		logger.info("entered");
		return appFileSystemDao.getFileSystem(id);
	}

	@Override
	public List<AppFileName> getFileNameList() {
		// TODO Auto-generated method stub
		logger.info("entered");
		return appFileSystemDao.getFileNameList();
	}

	@Override
	public boolean deleteFileSystemDetails(long id) throws Exception {
		logger.info("entered");
		return appFileSystemDao.deleteFileSystemDetails(id);
	}
}
