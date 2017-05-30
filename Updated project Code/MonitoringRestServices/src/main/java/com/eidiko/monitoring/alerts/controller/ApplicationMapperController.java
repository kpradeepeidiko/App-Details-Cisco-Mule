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

import com.eidiko.monitoring.alerts.model.ApplicationMapper;
import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.services.ApplicationMapperServices;

@Controller
public class ApplicationMapperController {
	public static final Logger logger = Logger
			.getLogger(ApplicationMapperController.class);
	@Autowired
	ApplicationMapperServices applicationMappingServices;

	public ApplicationMapper getApplicationMapperFromJson(JSONObject jsonObject) {
		logger.info("entered");
		ApplicationMapper applicationMapper = new ApplicationMapper();

		long appQmgrId = Long.parseLong(jsonObject.getString("appQmgrId"));
		long appApplicationId = Long.parseLong(jsonObject
				.getString("appApplicationId"));

		applicationMapper.setAppQmgrId(appQmgrId);
		applicationMapper.setAppApplicationId(appApplicationId);
		logger.debug("Application Mapper" + ":" + applicationMapper);
		logger.info("exiting");
		return applicationMapper;
	}

	@RequestMapping(value = "/applicationMapper", method = RequestMethod.POST)
	public @ResponseBody Status addApplicationMapper(@RequestBody String values) {
		logger.info("addApplicationMapper entered");

		JSONObject js = JSONObject.fromObject(values);

		long appApplicationId = Long
				.parseLong(js.getString("appApplicationId"));
		long appQmgrId;
		JSONArray appQmgrData = js.getJSONArray("appQmgrId");
		logger.debug("appQmgrData" + appQmgrData);
		for (int i = 0; i < appQmgrData.size(); i++) {
			ApplicationMapper applicationMapper = new ApplicationMapper();
			appQmgrId = Long.parseLong(appQmgrData.getString(i));
			applicationMapper.setAppQmgrId(appQmgrId);
			applicationMapper.setAppApplicationId(appApplicationId);
			if (!validateApplicationMapper(applicationMapper)) {
				return Status.getFailureStatus(Messages.EXCEPTION);
			}

			logger.debug("applicationMapper" + applicationMapper);
			try {
				applicationMappingServices
						.addApplicationMapper(applicationMapper);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				return Status.getFailureStatus(Messages.EXCEPTION);
			}
		}
		logger.info("exiting");
		return Status.getSuccesStatus(Messages.DATA_ADDED);
	}

	@RequestMapping(value = "/applicationMapperList", method = RequestMethod.PUT)
	public @ResponseBody List<ApplicationMapper> getApplicationMapper() {
		logger.info("entered");
		List<ApplicationMapper> applicationMapperList = new ArrayList<ApplicationMapper>();
		try {
			applicationMapperList = applicationMappingServices
					.getApplicationMapperList();
			logger.debug("Application MapperList" + ":" + applicationMapperList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return applicationMapperList;
	}

	@RequestMapping(value = "deleteApplicationMapper/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteApplicationMapper(
			@PathVariable("id") long id) {
		logger.info("entered");
		logger.debug("Deleting ApplicationMapper" + ":" + id);
		try {
			applicationMappingServices.deleteApplicationMapper(id);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
	}

	@RequestMapping(value = "/updateApplicationMapper", method = RequestMethod.POST)
	public @ResponseBody Status updateApplicationMapper(
			@RequestBody String values) {
		logger.info("entered");
		JSONObject js = JSONObject.fromObject(values);

		ApplicationMapper applicationMapper = new ApplicationMapper();

		applicationMapper = getApplicationMapperFromJson(js);

		if (!validateApplicationMapper(applicationMapper)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
		logger.debug("ApplicationMapper" + ":" + applicationMapper);
		int id = Integer.parseInt(js.getString("id"));

		applicationMapper.setId(id);

		try {
			if (applicationMapper.getId() == 0) {
				applicationMappingServices
						.addApplicationMapper(applicationMapper);
			} else {
				applicationMappingServices
						.updateApplicationMapper(applicationMapper);
			}
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_MODIFIED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
	}

	@RequestMapping(value = "getApplicationMapper/{id}", method = RequestMethod.PUT)
	public @ResponseBody ApplicationMapper getApplicationMapperById(
			@PathVariable("id") long id) throws Exception {
		logger.info("getApplicationMapperById entered");
		logger.debug("getApplicationMapperById" + ":" + id);
		ApplicationMapper getApplicationMapperById = null;
		try {
			long id1 = id;
			getApplicationMapperById = applicationMappingServices
					.getApplicationMapperById(id1);
			logger.debug(getApplicationMapperById);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return getApplicationMapperById;
	}

	public boolean validateApplicationMapper(ApplicationMapper appMapper) {
		logger.info("entered");
		logger.debug("ApplicationMapper" + ":" + appMapper);
		if (StringUtils.isBlank(String.valueOf(appMapper.getAppQmgrId()))) {
			logger.debug("Application QueueManager Id is empty or null");
			return false;
		} else if (StringUtils.isBlank(String.valueOf(appMapper
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
