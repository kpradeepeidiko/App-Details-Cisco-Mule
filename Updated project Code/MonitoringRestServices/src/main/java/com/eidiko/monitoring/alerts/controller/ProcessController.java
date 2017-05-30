package com.eidiko.monitoring.alerts.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.eidiko.monitoring.alerts.model.AppProcessName;
import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.Process;
import com.eidiko.monitoring.alerts.model.AppProcess;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.services.AppProcessServices;

@Controller
public class ProcessController {
	public static final Logger logger = Logger
			.getLogger(ProcessController.class);
	@Autowired
	AppProcessServices appprocessService;

	public Process getProcessFromJson(JSONObject jsonObject) {
		logger.info("entered");
		Process appprocess = new Process();
		String psName = jsonObject.getString("psName");
		String psHost = jsonObject.getString("pshost");
		String psFilter = jsonObject.getString("psFilter");
		String psMin = jsonObject.getString("psMin");
		String psMax = jsonObject.getString("psMax");
		appprocess.setPsName(psName);
		appprocess.setPsHost(psHost);
		appprocess.setPsFilter(psFilter);
		appprocess.setPsMin(psMin);
		appprocess.setPsMax(psMax);
		logger.debug("Process" + ":" + appprocess);
		logger.info("exiting");
		return appprocess;
	}

	@RequestMapping(value = "addProcess", method = RequestMethod.POST)
	public @ResponseBody Status AppProcess(@RequestBody Process process) {
		logger.info("addFileSystem entered");

		if (!validateProcess(process)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
		logger.debug("FileSystem" + ":" + process);
		try {
			appprocessService.addProcess(process);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_ADDED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
	}

	@RequestMapping(value = "processNameList", method = RequestMethod.GET)
	public @ResponseBody List<Process> getProcessNames() {
		logger.info("processNameList entered");

		List<Process> processNameList = new ArrayList<Process>();
		try {
			processNameList = appprocessService.getProcessNameList();
			logger.debug("process Name List :" + processNameList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		logger.info("exiting");
		return processNameList;
	}

	@RequestMapping(value = "appProcess/{id}", method = RequestMethod.GET)
	public @ResponseBody List<AppProcess> getProcessDetails(
			@PathVariable("id") long id) throws Exception {
		logger.info("getProcessByMapperAppId entered");
		logger.debug("getProcessByMapperAppId" + ":" + id);
		List<AppProcess> appProcessList = new ArrayList<AppProcess>();
		try {
			appProcessList = appprocessService.getappProcessList(id);
			logger.debug("App ProcessList :" + appProcessList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return appProcessList;
	}

	public boolean validateProcess(Process appProcess) {
		logger.info("entered");
		logger.debug("process" + ":" + appProcess);
		if (StringUtils.isBlank(appProcess.getPsName())) {
			logger.debug("Name is empty or null");
			return false;
		} else if (StringUtils.isBlank(appProcess.getPsHost())) {
			logger.debug("Host name  is empty or null");
			return false;
		} else if (StringUtils.isBlank(appProcess.getPsFilter())) {
			logger.debug("psFilter is empty or null");
			return false;
		}

		else if (StringUtils.isBlank(appProcess.getPsMax())) {
			logger.debug("fsMax is empty or null");
			return false;
		}

		else if (StringUtils.isBlank(appProcess.getPsMin())) {
			logger.debug("fsMinis empty or null");
			return false;
		} else {
			logger.debug("Every data is valid");
			logger.info("exiting");
			return true;
		}

	}
}
