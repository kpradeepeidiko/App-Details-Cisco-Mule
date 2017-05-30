package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import com.eidiko.monitoring.alerts.model.AppUsers;
import com.eidiko.monitoring.alerts.model.NBUsers;
import com.eidiko.monitoring.alerts.model.NightlyBatchEvents;
import com.eidiko.monitoring.alerts.model.User;

public interface UserDao extends BaseDao {

	public List<User> getUserList();

	public List<AppUsers> getUserDetails(long id) throws Exception;

	public List<NBUsers> getNBUserDetails() throws Exception;

	public boolean deleteUsersDetails(long id) throws Exception;

	public List<NightlyBatchEvents> getNightlyBatchEvents(long id,
			String startDate, String endDate) throws Exception;
}
