package com.eidiko.monitoring.alerts.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.eidiko.monitoring.alerts.dao.QueueConfigurationDao;
import com.eidiko.monitoring.alerts.model.AlertConfiguration;
import com.eidiko.monitoring.alerts.model.QueueConfiguration;

public class QueueConfigurationServiceImpl implements QueueConfigurationService {
	public static final Logger logger = Logger
			.getLogger(QueueConfigurationServiceImpl.class);

	@Autowired
	QueueConfigurationDao queueCfgDao;

	@Override
	public void addQueueCfg(QueueConfiguration queueCfg) throws Exception {
		logger.info("Add Queue Configuration entered");
		queueCfgDao.saveOrUpdate(queueCfg);
		logger.info("exiting");

	}

	@Override
	public void deleteQueueCfg(long id) throws Exception {
		logger.debug("Delete Queue Configuration Entered");
		logger.debug("Deleted Queue Configuration By Id :" + id);
		queueCfgDao.delete(QueueConfiguration.class, id);
		logger.info("exiting");
	}

	@Override
	public void updateQueueCfg(QueueConfiguration queueCfg) throws Exception {
		logger.info("Update Queue Configuration Entered");
		queueCfgDao.saveOrUpdate(queueCfg);
		logger.info(queueCfg);
	}

	@Override
	public QueueConfiguration getQueueCfgById(long id) throws Exception {
		logger.info("Get Queue Configuration By Id entered");
		logger.debug("Get Queue Configuration By Id :" + id);
		return (QueueConfiguration) queueCfgDao.getEntityById(
				QueueConfiguration.class, id);
	}

	@Override
	public QueueConfiguration getQueueCfg() {
		logger.info("Get queue Configuration ");
		return (QueueConfiguration) queueCfgDao
				.getEntity(QueueConfiguration.class);
	}

	@Override
	public List<QueueConfiguration> getQueueCfgList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
