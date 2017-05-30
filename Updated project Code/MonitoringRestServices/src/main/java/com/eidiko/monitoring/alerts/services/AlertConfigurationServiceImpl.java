package com.eidiko.monitoring.alerts.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.eidiko.monitoring.alerts.dao.AlertConfigurationDao;
import com.eidiko.monitoring.alerts.model.AlertConfiguration;

public class AlertConfigurationServiceImpl implements AlertConfigurationService {
	public static final Logger logger = Logger
			.getLogger(AlertConfigurationServiceImpl.class);

	@Autowired
	AlertConfigurationDao alertCfgDao;

	@Override
	public void addAlertCfg(AlertConfiguration alertCfg) throws Exception {
		logger.info("Add Alert Configuration Entered");
		alertCfgDao.saveOrUpdate(alertCfg);
		logger.info("exiting");
	}

	@Override
	public void deleteAlertCfg(long id) throws Exception {
		logger.debug("Delete Alert Configuration Entered");
		logger.debug("Deleted Aert Configuration By Id :" + id);
		alertCfgDao.delete(AlertConfiguration.class, id);
		logger.info("exiting");

	}

	@Override
	public void updateAlertCfg(AlertConfiguration alertCfg) throws Exception {
		logger.info("Update Alert Configuration Entered");
		alertCfgDao.saveOrUpdate(alertCfg);
		logger.info(alertCfg);
	}

	@Override
	public AlertConfiguration getAlertCfgById(long id) throws Exception {
		logger.info("Get Alert Configuration By Id entered");
		logger.debug("Get Alert Configuration By Id :" + id);
		return (AlertConfiguration) alertCfgDao.getEntityById(
				AlertConfiguration.class, id);
	}

	public AlertConfiguration getAlertCfg() {
		logger.info("Get Alert Configuration");
		return (AlertConfiguration) alertCfgDao
				.getEntity(AlertConfiguration.class);
	}

	@Override
	public List<AlertConfiguration> getAlertCfgList() throws Exception {
		logger.info("Get Alert Configuration List entered");
		return alertCfgDao.getAlertCfgList();
	}

}
