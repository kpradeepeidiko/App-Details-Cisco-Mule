package com.eidiko.monitoring.alerts.services;

import java.util.List;

import com.eidiko.monitoring.alerts.model.AppUsers;
import com.eidiko.monitoring.alerts.model.NBUsers;
import com.eidiko.monitoring.alerts.model.NightlyBatchEvents;
import com.eidiko.monitoring.alerts.model.User;

public interface UserService {

	public User getUserById(long id) throws Exception;

	public void deleteUser(long id) throws Exception;

	public void addUser(User user) throws Exception;

	public List<User> getUserList();

	public void updatePassword(User user) throws Exception;

	public void updateUser(User user) throws Exception;

	public List<AppUsers> getUserDetails(long id) throws Exception;

	public List<NBUsers> getNBUserDetails() throws Exception;

	public boolean deleteUsersDetails(long id) throws Exception;

	public List<NightlyBatchEvents> getNightlyBatchEvents(long id,
			String startDateFormat, String endDateFormat) throws Exception;

}
