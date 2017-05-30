package com.eidiko.monitoring.alerts.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.eidiko.monitoring.alerts.dao.ApplicationDao;
import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.User;

public class ApplicationServicesImpl implements ApplicationServices {
	public static final Logger logger = Logger
			.getLogger(ApplicationServicesImpl.class);

	@Autowired
	ApplicationDao applicationDao;

	@Override
	public void deleteApplication(long id) throws Exception {
		logger.info("entered");
		applicationDao.delete(Application.class, id);
		logger.info("exiting");
	}

	@Override
	public void addApplication(Application application) throws Exception {
		logger.info("entered");
		applicationDao.saveOrUpdate(application);
		logger.info("exiting");
	}

	@Override
	public List<Application> getApplicationList(User user) {
		logger.info("entered");
		return applicationDao.getApplicationList(user);
	}

	@Override
	public void updateApplication(Application application) throws Exception {
		logger.info("entered");
		applicationDao.saveOrUpdate(application);
		logger.info("exiting");
	}

	@Override
	public Application getApplicationById(long id) throws Exception {
		logger.info("entered");
		return (Application) applicationDao
				.getEntityById(Application.class, id);
	}

	@Override
	public boolean deleteApplicationDetails(long id) throws Exception {
		logger.info("entered");

		return applicationDao.deleteApplicationDetails(id);
	}

}
