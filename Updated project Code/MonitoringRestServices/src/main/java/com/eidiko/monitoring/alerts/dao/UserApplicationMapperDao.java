package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.QueueManager;
import com.eidiko.monitoring.alerts.model.UserApplicationMapper;

public interface UserApplicationMapperDao extends BaseDao {

	public List<UserApplicationMapper> getUserApplicationMapperList();

	public List<UserApplicationMapper> getApplicationsByUserId(long id);

	public List<Application> getQuemanagerId(long id);

	public List<QueueManager> getQueueManagerDetails(List list);
}
