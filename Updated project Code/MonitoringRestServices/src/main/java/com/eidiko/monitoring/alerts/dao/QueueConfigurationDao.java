package com.eidiko.monitoring.alerts.dao;

import com.eidiko.monitoring.alerts.model.QueueConfiguration;

public interface QueueConfigurationDao extends BaseDao {

	QueueConfiguration getEntity(Class<QueueConfiguration> class1);

}
