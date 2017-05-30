package com.eidiko.monitoring.alerts.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.eidiko.monitoring.alerts.dao.AppObjectsDao;
import com.eidiko.monitoring.alerts.model.AppObjects;
import com.eidiko.monitoring.alerts.model.AppQueueMangers;
import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.QueueManager;
import com.eidiko.monitoring.alerts.services.AppObjectsServices;

public class AppObjectsServicesImpl implements AppObjectsServices {
	public static final Logger logger = Logger
			.getLogger(AppObjectsServicesImpl.class);

	@Autowired
	AppObjectsDao appObjectsDao;

	@Override
	public void deleteAppObject(long id) throws Exception {
		logger.info("entered");
		appObjectsDao.delete(AppObjects.class, id);
		logger.info("exiting");
	}

	@Override
	public void addAppObject(AppObjects appObjects) throws Exception {
		logger.info("entered");
		appObjectsDao.saveOrUpdate(appObjects);
		logger.info("exiting");
	}

	@Override
	public List<AppObjects> getAppObjectsList() {
		logger.info("entered");
		return appObjectsDao.getAppObjectsList();

	}

	@Override
	public void updateAppObjects(AppObjects appObjects) throws Exception {
		logger.info("entered");
		appObjectsDao.saveOrUpdate(appObjects);
		logger.info("exiting");
	}

	@Override
	public List<AppObjects> getAppObjectsById(long id) throws Exception {
		logger.info("entered");
		return appObjectsDao.getAppObjectsById(id);
	}

	@Override
	public AppObjects getQueueById(long id) throws Exception {
		logger.info("entered");
		return (AppObjects) appObjectsDao.getEntityById(AppObjects.class, id);
	}

	@Override
	public QueueManager getQueueManger(AppQueueMangers appqmgr) {
		logger.info("entered");
		return appObjectsDao.getQueueManger(appqmgr);

	}

}
