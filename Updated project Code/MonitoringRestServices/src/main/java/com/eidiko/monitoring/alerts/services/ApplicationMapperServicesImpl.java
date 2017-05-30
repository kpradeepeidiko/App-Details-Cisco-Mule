package com.eidiko.monitoring.alerts.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.eidiko.monitoring.alerts.dao.ApplicationMapperDao;
import com.eidiko.monitoring.alerts.model.ApplicationMapper;

public class ApplicationMapperServicesImpl implements ApplicationMapperServices {
	public static final Logger logger = Logger
			.getLogger(ApplicationMapperServicesImpl.class);
	@Autowired
	ApplicationMapperDao applicationMapperDao;

	@Override
	public void deleteApplicationMapper(long id) throws Exception {
		logger.info("entered");
		applicationMapperDao.delete(ApplicationMapper.class, id);
		logger.info("exiting");
	}

	@Override
	public void addApplicationMapper(ApplicationMapper applicationMapper)
			throws Exception {
		logger.info("entered");
		applicationMapperDao.saveOrUpdate(applicationMapper);
		logger.info("exiting");
	}

	@Override
	public List<ApplicationMapper> getApplicationMapperList() {
		logger.info("entered");
		return applicationMapperDao.getApplicationMapperList();
	}

	@Override
	public void updateApplicationMapper(ApplicationMapper applicationMapper)
			throws Exception {
		logger.info("entered");
		applicationMapperDao.saveOrUpdate(applicationMapper);
		logger.info("exiting");
	}

	@Override
	public ApplicationMapper getApplicationMapperById(long id) throws Exception {
		logger.info("entered");
		return (ApplicationMapper) applicationMapperDao.getEntityById(
				ApplicationMapper.class, id);
	}

}
