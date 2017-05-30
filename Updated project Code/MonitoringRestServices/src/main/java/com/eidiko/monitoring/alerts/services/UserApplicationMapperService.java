package com.eidiko.monitoring.alerts.services;

import java.util.List;

import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.QueueManager;
import com.eidiko.monitoring.alerts.model.UserApplicationMapper;

public interface UserApplicationMapperService {

	public UserApplicationMapper getUserApplicationMapperId(long id)
			throws Exception;

	public void deleteUserApplicationMapperId(long id) throws Exception;

	public void addUserApplicationMapperId(UserApplicationMapper mapper)
			throws Exception;

	public List<UserApplicationMapper> getUserApplicationMapperList();

	public void updateUserApplicationMapper(UserApplicationMapper mapper)
			throws Exception;

	public List<UserApplicationMapper> getApplicationsByUserId(long id);

	public List<Application> getQuemanagerId(long id);

	public List<QueueManager> getQueueManagerDetails(List list);

}
