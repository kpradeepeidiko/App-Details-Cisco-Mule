package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import com.eidiko.monitoring.alerts.model.AlertConfiguration;

public interface AlertConfigurationDao extends BaseDao {
	public List<AlertConfiguration> getAlertCfgList() throws Exception;

	public AlertConfiguration getEntity(Class<AlertConfiguration> class1);

}
