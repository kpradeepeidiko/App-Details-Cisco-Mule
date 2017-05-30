package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import com.eidiko.monitoring.alerts.model.AlertConfiguration;
import com.eidiko.monitoring.alerts.model.QueueConfiguration;

public class QueueConfigurationDaoImpl extends BaseDaoImpl implements
		QueueConfigurationDao {

	public QueueConfiguration getEntity(Class<QueueConfiguration> class1) {
		QueueConfiguration queueConfig = new QueueConfiguration();
		List<QueueConfiguration> queueConfigList = getSessionFactory()
				.getCurrentSession().createCriteria(QueueConfiguration.class)
				.list();
		if (queueConfigList != null && queueConfigList.size() > 0) {
			queueConfig = queueConfigList.get(0);
		}
		return queueConfig;
	}

}
