package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import com.eidiko.monitoring.alerts.model.ApplicationMapper;

public interface ApplicationMapperDao extends BaseDao {

	public List<ApplicationMapper> getApplicationMapperList();

}
