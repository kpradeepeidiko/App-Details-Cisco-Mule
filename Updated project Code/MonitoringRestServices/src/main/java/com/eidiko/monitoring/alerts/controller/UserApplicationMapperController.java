package com.eidiko.monitoring.alerts.controller;

import java.util.ArrayList;
import java.util.HashSet;
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
import com.eidiko.monitoring.alerts.model.QueueManager;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.model.User;
import com.eidiko.monitoring.alerts.model.UserApplicationMapper;
import com.eidiko.monitoring.alerts.services.QueueManagerServices;
import com.eidiko.monitoring.alerts.services.UserApplicationMapperService;

@Controller
public class UserApplicationMapperController {
	public static final Logger logger = Logger
			.getLogger(UserApplicationMapperController.class);

	@Autowired
	UserApplicationMapperService userApplicationService;

	@Autowired
	QueueManagerServices queueManagerService;

	public UserApplicationMapper getUserApplicationMapperFromJson(
			JSONObject jsonObject) {

		logger.info("entered");
		UserApplicationMapper mapper = new UserApplicationMapper();

		long appUserId = Long.parseLong(jsonObject.getString("app_user_id"));
		long applicationId = Long.parseLong(jsonObject
				.getString("app_application_id"));

		mapper.setAppuserId(appUserId);
		mapper.setAppapplicationId(applicationId);
		logger.debug("User Application Mapper :" + mapper);
		logger.info("exiting");
		return mapper;
	}

	@RequestMapping(value = "/userApplicationMapper", method = RequestMethod.POST)
	public @ResponseBody Status addUserApplicationMapper(
			@RequestBody String values) {
		logger.info("addUserApplicationMapper entered");

		JSONObject js = JSONObject.fromObject(values);

		long applicationId = Long.parseLong(js.getString("app_application_id"));
		long appUserId;
		JSONArray appUSerData = js.getJSONArray("app_user_id");
		logger.debug("appUserdata" + ":" + appUSerData);

		for (int i = 0; i < appUSerData.size(); i++) {
			UserApplicationMapper mapper = new UserApplicationMapper();
			appUserId = Long.parseLong(appUSerData.getString(i));
			mapper.setAppuserId(appUserId);
			mapper.setAppapplicationId(applicationId);
			if (!validateUserApplicationMapper(mapper)) {
				return Status.getFailureStatus(Messages.EXCEPTION);
			}
			logger.debug("Mapper" + ":" + mapper);

			try {
				userApplicationService.addUserApplicationMapperId(mapper);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				return Status.getFailureStatus(Messages.EXCEPTION);
			}
		}
		logger.info("exiting");
		return Status.getSuccesStatus(Messages.DATA_ADDED);
	}

	@RequestMapping(value = "/userApplicationMapperList", method = RequestMethod.GET)
	public @ResponseBody List<UserApplicationMapper> getUserApplicationMapper() {
		logger.info("userApplicationMapperList entered");

		List<UserApplicationMapper> userApplicationMapperList = new ArrayList<UserApplicationMapper>();
		try {
			userApplicationMapperList = userApplicationService
					.getUserApplicationMapperList();
			logger.debug("User Applicatio List :" + userApplicationMapperList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return userApplicationMapperList;
	}

	@RequestMapping(value = "deleteUserApplicationMapper/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteUserApplicationMapper(
			@PathVariable("id") long id) {

		logger.info("deleteUserApplicationMapper entered");
		logger.debug("deleteUserApplicationMapper By Id" + ":" + id);
		try {
			userApplicationService.deleteUserApplicationMapperId(id);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "/updateUserApplicationMapper", method = RequestMethod.POST)
	public @ResponseBody Status updateUserApplicationMapper(
			@RequestBody String values) {
		logger.info("updateApplicationMapper entered");

		JSONObject js = JSONObject.fromObject(values);

		UserApplicationMapper mapper = new UserApplicationMapper();

		mapper = getUserApplicationMapperFromJson(js);

		if (!validateUserApplicationMapper(mapper)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

		long id = Long.parseLong(js.getString("id"));

		mapper.setId(id);

		logger.debug("UserApplicationMapper" + ":" + mapper);

		try {
			if (mapper.getId() == 0) {
				userApplicationService.addUserApplicationMapperId(mapper);
			} else {
				userApplicationService.updateUserApplicationMapper(mapper);
			}
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_MODIFIED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "UserApplicationMapper/{id}", method = RequestMethod.GET)
	public @ResponseBody UserApplicationMapper getUserApplicationMapperById(
			@PathVariable("id") long id) throws Exception {
		logger.info("userApplicationMapperById entered");
		logger.debug("userApplicationmapperById" + ":" + id);
		UserApplicationMapper getUserappuId = null;
		try {

			getUserappuId = userApplicationService
					.getUserApplicationMapperId(id);
			logger.debug("User Aplication Mapper By Id :" + getUserappuId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return getUserappuId;

	}

	@RequestMapping(value = "/getQueueManagersByUserId", method = RequestMethod.POST)
	public @ResponseBody List<QueueManager> getApplicationsByUserId(
			@RequestBody User user) throws Exception {

		List getUserappuId = new ArrayList();
		List appList = new ArrayList();
		List qmList = new ArrayList();
		List qmDetails = new ArrayList();

		if (user.getFlag().equalsIgnoreCase("true")) {

			List<QueueManager> queueManagerList = new ArrayList<QueueManager>();
			return queueManagerService.getQueueManagerList();

		} else {

			getUserappuId = userApplicationService.getApplicationsByUserId(user
					.getId());

			if (getUserappuId != null) {

				for (int i = 0; i < getUserappuId.size(); i++) {

					appList = userApplicationService
							.getQuemanagerId((long) getUserappuId.get(i));

					for (int j = 0; j < appList.size(); j++) {
						qmList.add((long) appList.get(j));
					}
				}

				qmList = new ArrayList<String>(new HashSet<String>(qmList));

				qmDetails = userApplicationService
						.getQueueManagerDetails(qmList);

				/*
				 * for(int k=0;k<qmList.size();k++) {
				 * qmDetails.add(userApplicationService
				 * .getQueueManagerDetails((long)qmList.get(k))); }
				 */

			}
		}

		return qmDetails;
	}

	public boolean validateUserApplicationMapper(
			UserApplicationMapper userAppMapper) {
		logger.info("entered");
		logger.debug("UserApplicationMapper" + ":" + userAppMapper);
		if (StringUtils.isBlank(String.valueOf(userAppMapper.getAppuserId()))) {
			logger.debug("User MappeerId is empty or null");
			return false;
		} else if (StringUtils.isBlank(String.valueOf(userAppMapper
				.getAppapplicationId()))) {
			logger.debug("User ApplicationId is empty or null");
			return false;
		} else {
			logger.debug("Every data is valid");
			logger.info("exiting");
			return true;
		}

	}

}
