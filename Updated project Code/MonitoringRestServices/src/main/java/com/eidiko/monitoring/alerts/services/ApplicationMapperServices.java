package com.eidiko.monitoring.alerts.services;

import java.util.List;

import com.eidiko.monitoring.alerts.model.ApplicationMapper;

public interface ApplicationMapperServices {

	public void deleteApplicationMapper(long id) throws Exception;

	public void addApplicationMapper(ApplicationMapper applicationMapper)
			throws Exception;

	public List<ApplicationMapper> getApplicationMapperList();

	public void updateApplicationMapper(ApplicationMapper applicationMapper)
			throws Exception;

	public ApplicationMapper getApplicationMapperById(long id) throws Exception;
}
