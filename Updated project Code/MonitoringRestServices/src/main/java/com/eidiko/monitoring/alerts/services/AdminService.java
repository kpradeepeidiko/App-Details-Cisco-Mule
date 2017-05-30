package com.eidiko.monitoring.alerts.services;

import com.eidiko.monitoring.alerts.model.Admin;
import com.eidiko.monitoring.alerts.model.User;

public interface AdminService {
	public boolean isValidUser(Admin loginData);

	public User userAuthentication(User loginData);

	public Boolean updatePassword(User loginData);

	public Boolean addpassword(User user);
}
