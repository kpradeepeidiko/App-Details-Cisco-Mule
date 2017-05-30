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

import com.eidiko.monitoring.alerts.helper.MQHelper;
import com.eidiko.monitoring.alerts.model.AppObjects;
import com.eidiko.monitoring.alerts.model.AppQueueMangers;
import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.QueueManager;
import com.eidiko.monitoring.alerts.model.QueueStatus;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.services.AppObjectsServices;
import com.ibm.mq.MQQueueManager;

@Controller
public class AppObjectsController {
	public static final Logger logger = Logger
			.getLogger(AppObjectsController.class);

	@Autowired
	AppObjectsServices appObjectsServices;

	public AppObjects getAppObjectsFromJson(JSONObject jsonObject) {
		logger.info("entered");

		AppObjects appObjects = new AppObjects();

		String objectName = jsonObject.getString("objectName");
		String objectType = jsonObject.getString("objectType");
		long appQmgrAppId = Long
				.parseLong(jsonObject.getString("appQmgrAppId"));

		appObjects.setObjectName(objectName);
		appObjects.setObjectType(objectType);
		appObjects.setAppQmgrAppId(appQmgrAppId);
		logger.debug("AppObjects" + ":" + appObjects);
		logger.info("exiting");
		return appObjects;

	}

	@RequestMapping(value = "/appObjects", method = RequestMethod.POST)
	public @ResponseBody Status addAppObjects(@RequestBody String values) {
		logger.info("addAppObjects entered");

		JSONObject js = JSONObject.fromObject(values);

		long appQmgrAppId = Long.parseLong(js.getString("appQmgrAppId"));
		String objectName;
		String objectType;
		JSONArray objNameData = js.getJSONArray("objectName");
		logger.debug("objectName" + ":" + objNameData);
		JSONArray objTypeData = js.getJSONArray("objectType");
		logger.debug("objectType" + ":" + objTypeData);

		for (int i = 0; i < objNameData.size(); i++) {
			AppObjects appObjects = new AppObjects();
			objectName = objNameData.getString(i);
			logger.debug("Object Name" + ":" + objectName);
			objectType = objTypeData.getString(i);
			logger.debug("Object Type" + ":" + objectType);

			appObjects.setAppQmgrAppId(appQmgrAppId);
			appObjects.setObjectName(objectName);
			appObjects.setObjectType(objectType);

			if (!validateAppObject(appObjects)) {
				return Status.getFailureStatus(Messages.EXCEPTION);
			}

			logger.debug("appObjects" + ":" + appObjects);

			try {
				appObjectsServices.addAppObject(appObjects);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				return Status.getFailureStatus(Messages.EXCEPTION);
			}
		}

		logger.info("exiting");
		return Status.getSuccesStatus(Messages.DATA_ADDED);

	}

	@RequestMapping(value = "/appObjectsList", method = RequestMethod.GET)
	public @ResponseBody List<AppObjects> getAppObjects() {
		logger.info("appObjectList entered");

		List<AppObjects> appObjectsList = new ArrayList<AppObjects>();
		try {
			appObjectsList = appObjectsServices.getAppObjectsList();
			logger.debug("Object List:" + appObjectsList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

		}
		logger.info("exiting");
		return appObjectsList;
	}

	@RequestMapping(value = "deleteAppObjects/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteAppObjects(@PathVariable("id") long id) {
		logger.info("deleteAppObjects entered");
		logger.debug("Delete AppObjects By Id" + ":" + id);

		try {
			appObjectsServices.deleteAppObject(id);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
	}

	@RequestMapping(value = "/updateAppObjects", method = RequestMethod.POST)
	public @ResponseBody Status updateAppObjects(@RequestBody String values) {
		logger.info("updateAppObjects entered");

		JSONObject js = JSONObject.fromObject(values);

		AppObjects appObjects = new AppObjects();
		appObjects = getAppObjectsFromJson(js);

		if (!validateAppObject(appObjects)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
		int id = Integer.parseInt(js.getString("id"));

		appObjects.setId(id);

		logger.debug("AppObjects" + ":" + appObjects);

		try {
			if (appObjects.getId() == 0) {
				appObjectsServices.addAppObject(appObjects);
			} else {
				appObjectsServices.updateAppObjects(appObjects);
			}
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_MODIFIED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
	}

	@RequestMapping(value = "getAppObjects/{id}", method = RequestMethod.GET)
	public @ResponseBody List<AppObjects> getAppObjectsById(
			@PathVariable("id") long id) throws Exception {

		logger.info("getAppObjects entered");
		logger.debug("getAppObjects By Id" + ":" + id);
		List<AppObjects> getAppObjectsById = new ArrayList<AppObjects>();
		try {
			long id1 = id;
			getAppObjectsById = appObjectsServices.getAppObjectsById(id1);
			logger.debug("Get Objects By ID" + ":" + getAppObjectsById);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return getAppObjectsById;
	}

	@RequestMapping(value = "getQueue/{id}", method = RequestMethod.PUT)
	public @ResponseBody AppObjects getQueueById(@PathVariable("id") long id)
			throws Exception {

		logger.info("getQueue entered");
		logger.debug("getQueue By Id" + ":" + id);
		AppObjects getQueueById = null;
		try {
			long id1 = id;
			getQueueById = appObjectsServices.getQueueById(id1);
			logger.debug("Queue Details :" + getQueueById);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return getQueueById;
	}

	public boolean validateAppObject(AppObjects appObjects) {
		logger.info("entered");
		logger.debug("AppObjects" + ":" + appObjects);
		if (StringUtils.isBlank(appObjects.getObjectName())) {
			logger.debug("Object name is empty or null");
			return false;
		} else if (StringUtils.isBlank(appObjects.getObjectType())) {
			logger.debug("Object type is empty or null");
			return false;
		} else if (StringUtils.isBlank(String.valueOf(appObjects
				.getAppQmgrAppId()))) {
			logger.debug("AppQueueManagr Id is empty or null");
			return false;
		} else {
			logger.debug("Every data is valid");
			logger.info("exiting");
			return true;
		}
	}

	@RequestMapping(value = "getQueueStatus", method = RequestMethod.POST)
	public @ResponseBody QueueStatus getQueueStatus(
			@RequestBody AppQueueMangers appqmgr) {

		QueueManager queueManager = null;
		QueueStatus queueStatus = null;
		try {
			queueManager = appObjectsServices.getQueueManger(appqmgr);
			MQQueueManager mqQueueManager = MQHelper.getQueueManager(
					queueManager.getName(), queueManager.getListnerPort(),
					queueManager.getHost(), queueManager.getSvrConn());
			queueStatus = MQHelper.inquireQueue(mqQueueManager,
					appqmgr.getQueueName());
			logger.debug("queueStatus :" + queueStatus);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

		}
		logger.info("exiting");
		return queueStatus;
	}

}
