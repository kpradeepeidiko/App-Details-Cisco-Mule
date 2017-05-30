package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.eidiko.monitoring.alerts.model.AlertConfiguration;
import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.EntityObject;

public class AlertConfigurationDaoImpl extends BaseDaoImpl implements
		AlertConfigurationDao {

	public static final Logger logger = Logger
			.getLogger(AlertConfigurationDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<AlertConfiguration> getAlertCfgList() throws Exception {
		logger.info("Get Alert Configuration List entered");

		return getSessionFactory().getCurrentSession()
				.createCriteria(AlertConfiguration.class).list();
	}

	@Override
	public AlertConfiguration getEntity(Class<AlertConfiguration> class1) {
		AlertConfiguration alertConfig = new AlertConfiguration();
		@SuppressWarnings("unchecked")
		List<AlertConfiguration> alertConfigList = getSessionFactory()
				.getCurrentSession().createCriteria(AlertConfiguration.class)
				.list();
		if (alertConfigList != null && alertConfigList.size() > 0) {
			alertConfig = alertConfigList.get(0);
		}
		return alertConfig;

	}

}
