package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import com.eidiko.monitoring.alerts.model.AppProcess;
import com.eidiko.monitoring.alerts.model.Process;

public interface AppProcessDao extends BaseDao {

	public List<Process> getProcessNameList();

	public List<AppProcess> getAppProcessList(long id) throws Exception;

}
