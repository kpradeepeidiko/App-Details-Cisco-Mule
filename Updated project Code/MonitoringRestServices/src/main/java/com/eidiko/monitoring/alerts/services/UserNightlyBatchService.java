package com.eidiko.monitoring.alerts.services;

import com.eidiko.monitoring.alerts.model.UserNBMapper;

public interface UserNightlyBatchService {

	public void addUserNightlyBatchMapperId(UserNBMapper mapper)
			throws Exception;

	public void deleteUserNightlyBatchMapperId(long id) throws Exception;

}
