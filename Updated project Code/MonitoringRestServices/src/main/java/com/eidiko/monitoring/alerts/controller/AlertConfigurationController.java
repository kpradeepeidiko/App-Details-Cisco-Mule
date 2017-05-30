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

import com.eidiko.monitoring.alerts.model.AlertConfiguration;
import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.services.AlertConfigurationService;

@Controller
public class AlertConfigurationController {
	public static final Logger logger = Logger
			.getLogger(AlertConfigurationController.class);
	@Autowired
	AlertConfigurationService alertCfgService;

	public AlertConfiguration getAlertCfgFromJson(JSONObject jsonObject) {
		logger.info("entered");

		AlertConfiguration alertCfg = new AlertConfiguration();

		String sms_flag = jsonObject.getString("sms_flag");
		String email_flag = jsonObject.getString("email_flag");
		String mode = jsonObject.getString("mode");

		alertCfg.setSms_flag(sms_flag);
		alertCfg.setEmail_flag(email_flag);
		alertCfg.setMode(mode);

		logger.debug("Application" + ":" + alertCfg);
		logger.info("exiting");
		return alertCfg;

	}

	@RequestMapping(value = "/addAlertCfg", method = RequestMethod.POST)
	public @ResponseBody Status addAlertCfg(
			@RequestBody AlertConfiguration alertCfg) {

		logger.info("Add Alert configuration entered");

		if (!validateAlertConfiguration(alertCfg)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
		logger.debug("Alert Configuration :" + alertCfg);
		try {
			alertCfgService.addAlertCfg(alertCfg);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_ADDED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "/deleteAlertCfg/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteAlertCfg(@PathVariable("id") long id) {
		logger.info("Delete Alert Configuration Entered");
		logger.debug("Delete Alert Configuration By Id :" + id);
		try {
			alertCfgService.deleteAlertCfg(id);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);

		}
	}

	@RequestMapping(value = "/updateAlertCfg", method = RequestMethod.POST)
	public @ResponseBody Status updateAlertCfg(
			@RequestBody AlertConfiguration alertCfg) {
		logger.info("Update Alert Configuration entered");
		if (!validateAlertConfiguration(alertCfg)) {
			return Status.getFailureStatus(Messages.EXCEPTION);

		}
		logger.debug("AlertConfiguration :" + alertCfg);

		try {
			if (alertCfg.getId() == 0) {
				alertCfgService.addAlertCfg(alertCfg);
			} else {
				alertCfgService.updateAlertCfg(alertCfg);
			}
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_MODIFIED);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "getAlertCfg/{id}", method = RequestMethod.GET)
	public @ResponseBody AlertConfiguration getAlertCfgById(
			@PathVariable("id") long id) {
		logger.info("Get Alert Configuration By Id entered");
		logger.debug("Get Alert Configuration By Id :" + id);
		AlertConfiguration alertCfg = null;
		try {

			alertCfg = alertCfgService.getAlertCfgById(id);
			logger.debug("Alert Configuration Details :" + alertCfg);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

		}
		logger.info("exiting");

		return alertCfg;

	}

	@RequestMapping(value = "getAlertCfgn", method = RequestMethod.GET)
	public @ResponseBody AlertConfiguration getAlertCfgn() {
		logger.info("Get Alert Configuration");

		AlertConfiguration alertCfg = null;
		try {

			alertCfg = alertCfgService.getAlertCfg();
			logger.debug("Alert Configuration Details :" + alertCfg);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

		}
		logger.info("exiting");

		return alertCfg;

	}

	@RequestMapping(value = "getAlertCfg", method = RequestMethod.GET)
	public @ResponseBody AlertConfiguration getAlertCfg(
			@PathVariable("id") long id) {
		logger.info("Get Alert Configuration By Id entered");
		logger.debug("Get Alert Configuration By Id :" + id);
		AlertConfiguration alertCfg = null;
		try {

			alertCfg = alertCfgService.getAlertCfgById(id);
			logger.debug("Alert Configuration Details :" + alertCfg);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

		}
		logger.info("exiting");

		return alertCfg;

	}

	@RequestMapping(value = "/alertCfgList", method = RequestMethod.GET)
	public @ResponseBody List<AlertConfiguration> getAlertCfg() {
		logger.info("Get Alert Configuratiion Entered ");
		List<AlertConfiguration> listAlertCfg = new ArrayList<AlertConfiguration>();
		try {
			listAlertCfg = alertCfgService.getAlertCfgList();
			logger.debug("Get Alert Configuration List :" + listAlertCfg);

		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();

		}
		logger.info("exiting");
		return listAlertCfg;

	}

	private boolean validateAlertConfiguration(AlertConfiguration alertCfg) {
		logger.info("entered");
		logger.debug("Alert Configuration" + ":" + alertCfg);
		if (StringUtils.isBlank(alertCfg.getSms_flag())) {
			logger.debug("SMS is empty or null");
			return false;
		} else if (StringUtils.isBlank(alertCfg.getEmail_flag())) {
			logger.debug("Email name  is empty or null");
			return false;
		} else if (StringUtils.isBlank(alertCfg.getMode())) {
			logger.debug("Mode is empty or null");
			return false;
		}

		else {
			logger.debug("Every data is valid");
			logger.info("exiting");
			return true;
		}
	}

}
