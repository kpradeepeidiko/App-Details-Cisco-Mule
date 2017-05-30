package com.eidiko.monitoring.alerts.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.eidiko.monitoring.alerts.dao.ProcessMapperDao;
import com.eidiko.monitoring.alerts.model.ProcessAppMapper;

public class ProcessMapperServicesImpl implements ProcessMapperServices {
	public static final Logger logger = Logger
			.getLogger(FileSystemMapperServicesImpl.class);
	@Autowired
	ProcessMapperDao processMapperDao;

	@Override
	public void deleteProcessMapper(long id) throws Exception {
		// TODO Auto-generated method stub
		logger.info("entered");
		processMapperDao.delete(ProcessAppMapper.class, id);
		logger.info("exiting");
	}

	@Override
	public void addProcessAppMapper(ProcessAppMapper processMapper)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("entered");
		processMapperDao.saveOrUpdate(processMapper);
		logger.info("exiting");
	}

	@Override
	public List<ProcessAppMapper> getAppProcessMapperList() {
		logger.info("entered");
		return processMapperDao.getProcessMapperList();
	}

	@Override
	public ProcessAppMapper getAppProcessMapperById(long id1) throws Exception {
		logger.info("entered");
		return (ProcessAppMapper) processMapperDao.getEntityById(
				ProcessAppMapper.class, id1);
	}

	@Override
	public void addAppProcessMapper(ProcessAppMapper processMapper)
			throws Exception {
		logger.info("entered");
		processMapperDao.saveOrUpdate(processMapper);
		logger.info("exiting");
	}

	@Override
	public void updateAppProcessMapper(ProcessAppMapper processMapper)
			throws Exception {
		logger.info("entered");
		processMapperDao.saveOrUpdate(processMapper);
		logger.info("exiting");

	}

}
