package com.eidiko.monitoring.alerts.controller;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eidiko.monitoring.alerts.model.Admin;
import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.model.User;
import com.eidiko.monitoring.alerts.services.AdminService;

@SuppressWarnings("unused")
@Controller
public class LoginController {

	public static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	AdminService adminService;

	public Admin getAdminFromJson(JSONObject jsonObject) {
		logger.info("entered");

		Admin admin = new Admin();

		String name = jsonObject.getString("name");
		String password = jsonObject.getString("password");

		admin.setName(name);
		admin.setPassword(password);

		logger.debug("Application" + ":" + admin);
		logger.info("exiting");
		return admin;

	}

	@RequestMapping(value = "getAdmin", method = RequestMethod.POST)
	public @ResponseBody Status getAdmin(@RequestBody Admin loginData) {
		logger.info("Get Admin Configuration");

		Admin adminCfg = null;
		try {

			boolean validUser = adminService.isValidUser(loginData);
			if (validUser) {
				return Status.getSuccesStatus(Messages.VALID_USER);
			} else {
				return Status.getFailureStatus(Messages.INVALID_USER);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.INVALID_USER);
		}

	}

	@RequestMapping(value = "userAuthentication", method = RequestMethod.POST)
	public @ResponseBody User getAdmin(@RequestBody User loginData) {
		User validUser = new User();
		try {

			logger.info("Get User Configuration");
			validUser = adminService.userAuthentication(loginData);
			validUser.setPassword("");

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			// return null;
		}
		return validUser;
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public @ResponseBody Status updatePassword(@RequestBody User user) {
		try {
			logger.info("password updating");
			boolean isUpdated = adminService.updatePassword(user);
			if (isUpdated) {
				logger.info("password updated");
				return Status.getSuccesStatus(Messages.DATA_MODIFIED);
			} else {
				return Status.getFailureStatus(Messages.INVALID_DATA);
			}
		}

		catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.INVALID_DATA);
		}

	}

}
