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

import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.model.User;
import com.eidiko.monitoring.alerts.services.ApplicationServices;

@Controller
public class ApplicationController {
	public static final Logger logger = Logger
			.getLogger(ApplicationController.class);

	@Autowired
	ApplicationServices applicationService;

	public Application getApplicationFromJson(JSONObject jsonObject) {
		logger.info("entered");

		Application application = new Application();

		String name = jsonObject.getString("name");
		String smsFlag = jsonObject.getString("smsFlag");
		String emailFlag = jsonObject.getString("emailFlag");

		application.setName(name);
		application.setSmsFlag(smsFlag);
		application.setEmailFlag(emailFlag);
		logger.debug("Application" + ":" + application);
		logger.info("exiting");
		return application;

	}

	@RequestMapping(value = "/application", method = RequestMethod.POST)
	public @ResponseBody Status addApplication(
			@RequestBody Application application) {
		logger.info("addApplication entered");

		if (!validateApplications(application)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
		logger.debug("application" + ":" + application);
		try {
			applicationService.addApplication(application);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_ADDED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "/applicationList", method = RequestMethod.POST)
	public @ResponseBody List<Application> getApplication(@RequestBody User user) {

		logger.info("getApplicationList entered");
		List<Application> applicationList = new ArrayList<Application>();
		try {
			applicationList = applicationService.getApplicationList(user);
			logger.debug("ApplicationList" + ":" + applicationList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		logger.info("exiting");

		return applicationList;
	}

	@RequestMapping(value = "deleteApplication/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteApplication(@PathVariable("id") long id) {
		logger.info("deleteApplication entered");
		logger.info("deletingApplication id" + ":" + id);

		try {
			applicationService.deleteApplication(id);
			logger.info("exiting");

			return Status.getSuccesStatus(Messages.DATA_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "/updateApplication", method = RequestMethod.POST)
	public @ResponseBody Status updateApplication(
			@RequestBody Application application) {
		logger.info("updateApplication entered");

		if (!validateApplications(application)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

		/*
		 * int id = Integer.parseInt(js.getString("id"));
		 * 
		 * 
		 * application.setId(id);
		 */
		logger.debug("Application:" + ":" + application);

		try {
			if (application.getId() == 0) {
				applicationService.addApplication(application);
			} else {
				applicationService.updateApplication(application);
			}
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_MODIFIED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "getApplication/{id}", method = RequestMethod.GET)
	public @ResponseBody Application getApplicationById(
			@PathVariable("id") long id) throws Exception {

		logger.info("getApplicationById entered");
		logger.info("getting application by id" + ":" + id);
		Application getApplicationById = null;
		try {
			long id1 = id;
			getApplicationById = applicationService.getApplicationById(id1);
			logger.debug("Get Application By Id :" + getApplicationById);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return getApplicationById;
	}

	public boolean validateApplications(Application application) {
		logger.info("entered");
		logger.info("Validating Application" + ":" + application);
		if (StringUtils.isBlank(application.getName())) {
			logger.debug("Application name is empty or null");
			return false;
		} else {
			logger.debug("Every data is valid");
			logger.info("exiting");
			return true;

		}

	}

	@RequestMapping(value = "deleteApplicationDetails/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteAppDetails(@PathVariable("id") long id) {
		logger.info("deleteApplication entered " + id);
		try {
			boolean result = applicationService.deleteApplicationDetails(id);
			logger.info("exiting");
			if (result) {
				return Status.getSuccesStatus(Messages.DATA_DELETE);
			} else {
				return Status.getFailureStatus(Messages.INVALID_DATA);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

}
