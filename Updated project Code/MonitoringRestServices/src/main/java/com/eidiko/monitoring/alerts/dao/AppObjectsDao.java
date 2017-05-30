package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import com.eidiko.monitoring.alerts.model.AppObjects;
import com.eidiko.monitoring.alerts.model.AppQueueMangers;
import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.QueueManager;

public interface AppObjectsDao extends BaseDao {

	public List<AppObjects> getAppObjectsList();

	public List<AppObjects> getAppObjectsById(long id) throws Exception;

	public QueueManager getQueueManger(AppQueueMangers appqmgr);

}
