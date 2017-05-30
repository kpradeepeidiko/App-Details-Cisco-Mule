package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.User;

public interface ApplicationDao extends BaseDao {
	public List<Application> getApplicationList(User user);

	public boolean deleteApplicationDetails(long id) throws Exception;

}
