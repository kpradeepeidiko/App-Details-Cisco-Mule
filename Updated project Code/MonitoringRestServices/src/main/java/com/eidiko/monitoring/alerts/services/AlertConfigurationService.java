package com.eidiko.monitoring.alerts.services;

import java.util.List;

import com.eidiko.monitoring.alerts.model.AlertConfiguration;

public interface AlertConfigurationService {
	public void addAlertCfg(AlertConfiguration alertCfg) throws Exception;

	public void deleteAlertCfg(long id) throws Exception;

	public void updateAlertCfg(AlertConfiguration alertCfg) throws Exception;

	public AlertConfiguration getAlertCfgById(long id) throws Exception;

	public List<AlertConfiguration> getAlertCfgList() throws Exception;

	public AlertConfiguration getAlertCfg();

}
