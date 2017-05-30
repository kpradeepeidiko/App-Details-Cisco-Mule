package com.eidiko.monitoring.alerts.services;

import java.util.List;

import com.eidiko.monitoring.alerts.model.ProcessAppMapper;

public interface ProcessMapperServices {
	public void deleteProcessMapper(long id) throws Exception;

	public void addProcessAppMapper(ProcessAppMapper processMapper)
			throws Exception;

	public List<ProcessAppMapper> getAppProcessMapperList();

	public void addAppProcessMapper(ProcessAppMapper processMapper)
			throws Exception;

	public void updateAppProcessMapper(ProcessAppMapper processMapper)
			throws Exception;

	public ProcessAppMapper getAppProcessMapperById(long id1) throws Exception;

}
