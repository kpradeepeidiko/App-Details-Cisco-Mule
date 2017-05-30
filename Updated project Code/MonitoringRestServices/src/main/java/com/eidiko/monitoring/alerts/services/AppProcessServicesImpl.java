package com.eidiko.monitoring.alerts.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.eidiko.monitoring.alerts.dao.AppProcessDao;
import com.eidiko.monitoring.alerts.model.AppProcess;
import com.eidiko.monitoring.alerts.model.AppProcessName;
import com.eidiko.monitoring.alerts.model.Process;

public class AppProcessServicesImpl implements AppProcessServices {
	@Autowired
	AppProcessDao appProcessDao;
	public static final Logger logger = Logger
			.getLogger(AppProcessServicesImpl.class);

	@Override
	public List<Process> getProcessNameList() {
		// TODO Auto-generated method stub
		logger.info("entered");
		return appProcessDao.getProcessNameList();
	}

	@Override
	public void updateProcess(Process process) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		logger.info("entered");
		appProcessDao.saveOrUpdate(process);
		logger.info("exiting");
	}

	@Override
	public void addProcess(Process process) throws Exception {
		// TODO Auto-generated method stub
		logger.info("entered");
		appProcessDao.saveOrUpdate(process);
		logger.info("exiting");
	}

	@Override
	public void deleteProcess(long id) throws Exception {
		// TODO Auto-generated method stub
		logger.info("entered");
		appProcessDao.delete(Process.class, id);
		logger.info("exiting");
	}

	@Override
	public List<AppProcess> getappProcessList(long id) throws Exception {
		logger.info("entered");
		return appProcessDao.getAppProcessList(id);
	}

}
