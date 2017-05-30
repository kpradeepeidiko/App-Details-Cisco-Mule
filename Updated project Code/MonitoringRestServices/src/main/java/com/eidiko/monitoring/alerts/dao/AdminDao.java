package com.eidiko.monitoring.alerts.dao;

import com.eidiko.monitoring.alerts.model.Admin;
import com.eidiko.monitoring.alerts.model.User;

public interface AdminDao extends BaseDao {

	public boolean isValidUser(Admin loginData);

	public User userAuthentication(User loginData);

	boolean updatePassword(User user);
}
