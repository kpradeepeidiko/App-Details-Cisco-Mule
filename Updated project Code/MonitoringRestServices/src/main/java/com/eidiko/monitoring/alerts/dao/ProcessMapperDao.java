package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import com.eidiko.monitoring.alerts.model.ProcessAppMapper;

public interface ProcessMapperDao extends BaseDao {
	public List<ProcessAppMapper> getProcessMapperList();

}
