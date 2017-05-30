package com.eidiko.monitoring.alerts.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
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
import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.ProcessAppMapper;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.services.ProcessMapperServices;

@Controller
public class ProcessAppMapperController {
	public static final Logger logger = Logger
			.getLogger(ProcessAppMapperController.class);
	@Autowired
	ProcessMapperServices processMappingServices;

	public ProcessAppMapper getProcessMapperFromJson(JSONObject jsonObject) {
		logger.info("entered");
		ProcessAppMapper processMapper = new ProcessAppMapper();
		long appPSId = Long.parseLong(jsonObject.getString("appPSId"));
		long appApplicationId = Long.parseLong(jsonObject
				.getString("appApplicationId"));
		processMapper.setAppPSId(appPSId);
		processMapper.setAppApplicationId(appApplicationId);
		logger.debug("Application Process Mapper" + ":" + processMapper);
		logger.info("exiting");
		return processMapper;
	}

	@RequestMapping(value = "/appProcessMapper", method = RequestMethod.POST)
	public @ResponseBody Status addApplicationMapper(@RequestBody String values) {
		logger.info("addApplicationProcessMapper entered");
		JSONObject js = JSONObject.fromObject(values);
		long appApplicationId = Long
				.parseLong(js.getString("appApplicationId"));
		long appPSId;
		JSONArray appPSData = js.getJSONArray("appPSId");
		logger.debug("appPSData" + appPSData);
		for (int i = 0; i < appPSData.size(); i++) {
			ProcessAppMapper processMapper = new ProcessAppMapper();
			appPSId = Long.parseLong(appPSData.getString(i));
			processMapper.setAppPSId(appPSId);
			processMapper.setAppApplicationId(appApplicationId);
			if (!validateProcessAppMapper(processMapper)) {
				return Status.getFailureStatus(Messages.EXCEPTION);
			}
			logger.debug("applicationMapper" + processMapper);
			try {
				processMappingServices.addProcessAppMapper(processMapper);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				return Status.getFailureStatus(Messages.EXCEPTION);
			}
		}
		logger.info("exiting");
		return Status.getSuccesStatus(Messages.DATA_ADDED);
	}

	@RequestMapping(value = "deleteApplicationProcessMapper/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteApplicationProcessMapper(
			@PathVariable("id") long id) {
		logger.info("entered");
		logger.debug("Deleting ApplicationProcessMapper" + ":" + id);
		try {
			processMappingServices.deleteProcessMapper(id);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
	}

	@RequestMapping(value = "/appProcessMapperList", method = RequestMethod.PUT)
	public @ResponseBody List<ProcessAppMapper> getApplicationMapper() {
		logger.info("entered");
		List<ProcessAppMapper> appProcessMapperList = new ArrayList<ProcessAppMapper>();
		try {
			appProcessMapperList = processMappingServices
					.getAppProcessMapperList();
			logger.debug("Application ProcessMapperList" + ":"
					+ appProcessMapperList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return appProcessMapperList;
	}

	public boolean validateProcessAppMapper(ProcessAppMapper appPSMapper) {
		logger.info("entered");
		logger.debug("ProcessMapper" + ":" + appPSMapper);
		if (StringUtils.isBlank(String.valueOf(appPSMapper.getAppPSId()))) {
			logger.debug("Process Id is empty or null");
			return false;
		} else if (StringUtils.isBlank(String.valueOf(appPSMapper
				.getAppApplicationId()))) {
			logger.debug("Application Id is empty or null");
			return false;
		} else {
			logger.debug("Every data is valid");
			logger.info("exiting");
			return true;
		}

	}

}
