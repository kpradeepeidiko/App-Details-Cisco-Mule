package com.eidiko.monitoring.alerts.services;

import java.util.List;

import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.User;

public interface ApplicationServices {

	public void deleteApplication(long id) throws Exception;

	public void addApplication(Application application) throws Exception;

	public List<Application> getApplicationList(User user);

	public void updateApplication(Application application) throws Exception;

	public Application getApplicationById(long id) throws Exception;

	public boolean deleteApplicationDetails(long id) throws Exception;
}
