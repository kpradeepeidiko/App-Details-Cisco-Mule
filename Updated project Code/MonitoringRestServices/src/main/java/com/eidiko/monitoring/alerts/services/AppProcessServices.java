package com.eidiko.monitoring.alerts.services;

import java.util.List;

import com.eidiko.monitoring.alerts.model.AppProcess;
import com.eidiko.monitoring.alerts.model.AppProcessName;
import com.eidiko.monitoring.alerts.model.Process;

public interface AppProcessServices {

	public List<Process> getProcessNameList();

	public void updateProcess(Process process) throws Exception;

	public void addProcess(Process process) throws Exception;

	public void deleteProcess(long id) throws Exception;

	public List<AppProcess> getappProcessList(long id) throws Exception;

}
