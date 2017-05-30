package com.eidiko.monitoring.alerts.services;

import java.util.List;

import com.eidiko.monitoring.alerts.model.AppObjects;
import com.eidiko.monitoring.alerts.model.AppQueueMangers;
import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.QueueManager;

public interface AppObjectsServices {

	public void deleteAppObject(long id) throws Exception;

	public void addAppObject(AppObjects appObjects) throws Exception;

	public List<AppObjects> getAppObjectsList();

	public void updateAppObjects(AppObjects appObjects) throws Exception;

	public List<AppObjects> getAppObjectsById(long id) throws Exception;

	public AppObjects getQueueById(long id) throws Exception;

	public QueueManager getQueueManger(AppQueueMangers appqmgr);
}
