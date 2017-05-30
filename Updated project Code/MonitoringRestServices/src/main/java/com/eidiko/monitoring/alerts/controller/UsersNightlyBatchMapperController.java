package com.eidiko.monitoring.alerts.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang.StringUtils;

import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.model.UserNBMapper;
import com.eidiko.monitoring.alerts.services.UserNightlyBatchService;

@Controller
public class UsersNightlyBatchMapperController {
	public static final Logger logger = Logger
			.getLogger(UsersNightlyBatchMapperController.class);

	@Autowired
	UserNightlyBatchService userNightlyBatchService;

	public UserNBMapper getUserNBMapperFromJson(JSONObject jsonObject) {

		logger.info("entered");
		UserNBMapper mapper = new UserNBMapper();

		long nightlyUserId = Long.parseLong(jsonObject
				.getString("nightlyUserid"));

		mapper.setNbuserid(nightlyUserId);
		logger.debug("User Nightly Batch Mapper :" + mapper);
		logger.info("exiting");
		return mapper;
	}

	@RequestMapping(value = "/userNightlyBatchMapper", method = RequestMethod.POST)
	public @ResponseBody Status addUserNightlyBatchMapper(
			@RequestBody String values) {
		logger.info("addNightlyBatchMapper entered");

		JSONObject js = JSONObject.fromObject(values);

		long nightlyBatchUserId;
		JSONArray nightlyUserData = js.getJSONArray("nightlyUserid");
		logger.debug("nightlyUserData" + ":" + nightlyUserData);

		for (int i = 0; i < nightlyUserData.size(); i++) {
			UserNBMapper mapper = new UserNBMapper();
			nightlyBatchUserId = Long.parseLong(nightlyUserData.getString(i));
			mapper.setNbuserid(nightlyBatchUserId);
			if (!validateUserNBMapper(mapper)) {
				return Status.getFailureStatus(Messages.EXCEPTION);
			}
			logger.debug("Mapper" + ":" + mapper);

			try {
				userNightlyBatchService.addUserNightlyBatchMapperId(mapper);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				return Status.getFailureStatus(Messages.EXCEPTION);
			}
		}
		logger.info("exiting");
		return Status.getSuccesStatus(Messages.DATA_ADDED);
	}

	@RequestMapping(value = "deleteUserNightlyBatchMapper/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteUserNightlyBatchMapper(
			@PathVariable("id") long id) {

		logger.info("deleteUserNightlyBatchMapper entered");
		logger.debug("deleteUserNightlyBatchMapper By Id" + ":" + id);
		try {
			userNightlyBatchService.deleteUserNightlyBatchMapperId(id);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	public boolean validateUserNBMapper(UserNBMapper userNBMapper) {
		logger.info("entered");
		logger.debug("UserNBMapper" + ":" + userNBMapper);
		if (StringUtils.isBlank(String.valueOf(userNBMapper.getNbuserid()))) {
			logger.debug("User MappeerId is empty or null");
			return false;
		}

		else {
			logger.debug("Every data is valid");
			logger.info("exiting");
			return true;
		}

	}
}
