package com.eidiko.monitoring.alerts.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.eidiko.monitoring.alerts.dao.UserApplicationMapperDao;
import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.QueueManager;
import com.eidiko.monitoring.alerts.model.UserApplicationMapper;

public class UserApplicationMapperServiceImpl implements
		UserApplicationMapperService {
	public static final Logger logger = Logger
			.getLogger(UserApplicationMapperServiceImpl.class);

	@Autowired
	UserApplicationMapperDao userApplicationDao;

	@Override
	public UserApplicationMapper getUserApplicationMapperId(long id)
			throws Exception {
		logger.info("entered");
		return (UserApplicationMapper) userApplicationDao.getEntityById(
				UserApplicationMapper.class, id);
	}

	@Override
	public void deleteUserApplicationMapperId(long id) throws Exception {
		logger.info("entered");
		userApplicationDao.delete(UserApplicationMapper.class, id);
		logger.info("exiting");

	}

	@Override
	public void addUserApplicationMapperId(UserApplicationMapper mapper)
			throws Exception {
		logger.info("entered");
		userApplicationDao.saveOrUpdate(mapper);
		logger.info("exiting");

	}

	@Override
	public List<UserApplicationMapper> getUserApplicationMapperList() {
		logger.info("entered");
		return userApplicationDao.getUserApplicationMapperList();
	}

	@Override
	public void updateUserApplicationMapper(UserApplicationMapper mapper)
			throws Exception {
		logger.info("entered");
		userApplicationDao.saveOrUpdate(mapper);
		logger.info("exiting");

	}

	@Override
	public List<UserApplicationMapper> getApplicationsByUserId(long id) {
		return userApplicationDao.getApplicationsByUserId(id);
	}

	@Override
	public List<Application> getQuemanagerId(long id) {
		return userApplicationDao.getQuemanagerId(id);
	}

	@Override
	public List<QueueManager> getQueueManagerDetails(List list) {
		return userApplicationDao.getQueueManagerDetails(list);
	}

}
