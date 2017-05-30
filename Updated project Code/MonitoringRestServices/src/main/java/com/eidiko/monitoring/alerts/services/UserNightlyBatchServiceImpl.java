package com.eidiko.monitoring.alerts.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.eidiko.monitoring.alerts.dao.UserNightlyBatchMapperDao;
import com.eidiko.monitoring.alerts.model.UserApplicationMapper;
import com.eidiko.monitoring.alerts.model.UserNBMapper;

public class UserNightlyBatchServiceImpl implements UserNightlyBatchService {

	public static final Logger logger = Logger
			.getLogger(UserNightlyBatchServiceImpl.class);

	@Autowired
	UserNightlyBatchMapperDao userNBDao;

	@Override
	public void addUserNightlyBatchMapperId(UserNBMapper mapper)
			throws Exception {
		logger.info("entered");
		userNBDao.saveOrUpdate(mapper);
		logger.info("exiting");
	}

	@Override
	public void deleteUserNightlyBatchMapperId(long id) throws Exception {
		logger.info("entered");
		userNBDao.delete(UserNBMapper.class, id);
		logger.info("exiting");

	}
}
