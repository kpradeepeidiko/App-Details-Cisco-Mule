package com.eidiko.monitoring.alerts.services;

import java.util.List;

import com.eidiko.monitoring.alerts.model.AlertConfiguration;
import com.eidiko.monitoring.alerts.model.QueueConfiguration;

public interface QueueConfigurationService {
	public void addQueueCfg(QueueConfiguration queueCfg) throws Exception;

	public void deleteQueueCfg(long id) throws Exception;

	public void updateQueueCfg(QueueConfiguration queueCfg) throws Exception;

	public QueueConfiguration getQueueCfgById(long id) throws Exception;

	public List<QueueConfiguration> getQueueCfgList() throws Exception;

	public QueueConfiguration getQueueCfg();
}
