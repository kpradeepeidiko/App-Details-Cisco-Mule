package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import com.eidiko.monitoring.alerts.model.FileSystemAppMapper;
import com.eidiko.monitoring.alerts.model.ProcessAppMapper;

public class ProcessMapperDaoImpl extends BaseDaoImpl implements
		ProcessMapperDao {

	@Override
	public List<ProcessAppMapper> getProcessMapperList() {
		logger.info("entered");
		return getSessionFactory().getCurrentSession()
				.createCriteria(ProcessAppMapper.class).list();
	}

}
