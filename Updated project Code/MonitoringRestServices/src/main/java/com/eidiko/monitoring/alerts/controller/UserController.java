package com.eidiko.monitoring.alerts.controller;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eidiko.monitoring.alerts.model.AppUsers;
import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.NBUsers;
import com.eidiko.monitoring.alerts.model.NightlyBatchEvents;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.model.User;
import com.eidiko.monitoring.alerts.services.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	final static Logger logger = Logger.getLogger(UserController.class);

	public User getUserFromJson(JSONObject jsonObject) {
		logger.info("entered");

		User user = new User();

		String name = jsonObject.getString("name");
		String email = jsonObject.getString("email");
		String mobileno = jsonObject.getString("mobileNo");
		String password = jsonObject.getString("password");

		user.setName(name);
		user.setEmail(email);
		user.setMobileNo(mobileno);
		user.setPassword(password);

		logger.debug("User :" + user);
		logger.info("exiting");
		return user;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	/*
	 * public @ResponseBody Status addUser(@RequestBody String values) {
	 */
	public @ResponseBody Status addUser(@RequestBody User user) {
		logger.info("entered");

		if (!validateUser(user)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
		logger.debug("User :" + user);
		try {
			userService.addUser(user);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_ADDED);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

			return Status.getFailureStatus(Messages.EXCEPTION);
		}
	}

	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public @ResponseBody List<User> getUser() {
		logger.info("userList entered");
		List<User> userList = new ArrayList<User>();
		try {

			userList = userService.getUserList();
			logger.debug("User List :" + userList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return userList;
	}

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteUser(@PathVariable("id") long id) {
		logger.info("dleteUser entered");
		logger.debug("deleteUser By Id" + ":" + id);
		try {
			userService.deleteUser(id);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public @ResponseBody Status updateUser(@RequestBody User user) {
		logger.info("updateUser entered");

		if (!validateUser(user)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
		logger.debug("User" + ":" + user);

		try {
			if (user.getId() == 0) {
				userService.addUser(user);
			} else {
				userService.updateUser(user);
			}
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_MODIFIED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "getUser/{id}", method = RequestMethod.GET)
	public @ResponseBody User getUserById(@PathVariable("id") long id)
			throws Exception {
		logger.info("getUserById entered");
		logger.debug("Get User By Id" + ":" + id);
		User getuserid = null;
		try {

			getuserid = userService.getUserById(id);
			logger.debug("Get User By Id :" + getuserid);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return getuserid;
	}

	@RequestMapping(value = "appUser/{id}", method = RequestMethod.GET)
	public @ResponseBody List<AppUsers> getUserDetails(
			@PathVariable("id") long id) throws Exception {
		logger.info("appUser entered");
		logger.debug("AppUser By Id" + ":" + id);
		List<AppUsers> userList = new ArrayList<AppUsers>();
		try {
			userList = userService.getUserDetails(id);
			logger.debug("AppUser By Id :" + userList);
		}

		catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return userList;
	}

	@RequestMapping(value = "/nightlyBatchUser", method = RequestMethod.GET)
	public @ResponseBody List<NBUsers> getNBUserDetails() throws Exception {
		logger.info("nightlyUser entered");
		List<NBUsers> nightlyBatchUserList = new ArrayList<NBUsers>();
		try {
			nightlyBatchUserList = userService.getNBUserDetails();
			logger.debug("NightlyBatchUser  :" + nightlyBatchUserList);
		}

		catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

		}
		logger.info("exiting");
		return nightlyBatchUserList;

	}

	@RequestMapping(value = "deleteUsersDetails/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteUserDetails(@PathVariable("id") long id) {
		logger.info("deleteUser entered " + id);
		try {
			boolean result = userService.deleteUsersDetails(id);
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

	private boolean validateUser(User user) {
		logger.info("entered");
		logger.debug("User" + ":" + user);
		if (StringUtils.isBlank(user.getName())) {
			logger.debug("Name is empty or null");
			return false;
		} else if (StringUtils.isBlank(user.getEmail())) {
			logger.debug("Email is empty or null");
			return false;
		} else if (StringUtils.isBlank(user.getMobileNo())) {
			logger.debug("MobileNo is empty or null");
			return false;
		} else {
			logger.debug("Every data is valid");
			logger.info("exiting");
			return true;
		}
	}

	@RequestMapping(value = "/nightlyBatchEvents/{id}", method = RequestMethod.GET, params = {
			"startDate", "endDate" })
	public @ResponseBody List<NightlyBatchEvents> getNightlyBatchEvents(
			@PathVariable("id") long id,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws Exception {
		String startDateFormat = convertStartDateFormat(startDate);
		String endDateFormat = convertEndDateFormat(endDate);
		logger.info("nightlyBatchEvents entered");
		List<NightlyBatchEvents> nightlyBatchEventList = new ArrayList<NightlyBatchEvents>();

		try {
			nightlyBatchEventList = userService.getNightlyBatchEvents(id,
					startDateFormat, endDateFormat);
			for (NightlyBatchEvents nbe : nightlyBatchEventList) {
				String errMessage = nbe.getErrorMessage();
				Object[] errObject = { " " + nbe.getParameters() + " " };
				MessageFormat mfError = new MessageFormat(errMessage);
				nbe.setErrorMessage(mfError.format(errObject));
			}
			logger.debug("nightlyBatchEventsList  :" + nightlyBatchEventList);
		}

		catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

		}
		logger.info("exiting");
		return nightlyBatchEventList;

	}

	@SuppressWarnings("deprecation")
	private String convertStartDateFormat(String strDate) throws ParseException {
		logger.info("entered");

		String inputDateFormat = "dd/MM/yyyy";
		logger.debug("Input date format :" + inputDateFormat);
		String outputDateFormat = "yyyy-MM-dd HH:mm:ss";
		logger.debug("Output date format :" + outputDateFormat);
		SimpleDateFormat inputFormat = new SimpleDateFormat(inputDateFormat);
		SimpleDateFormat outFormat = new SimpleDateFormat(outputDateFormat);
		Date dateObj = inputFormat.parse(strDate);
		dateObj.setHours(00);
		dateObj.setMinutes(00);
		dateObj.setSeconds(00);
		String strOutFormat = outFormat.format(dateObj);

		logger.info("exiting");
		return strOutFormat;
	}

	@SuppressWarnings("deprecation")
	private String convertEndDateFormat(String endDate) throws ParseException {
		logger.info("entered");

		String inputDateFormat = "dd/MM/yyyy";
		logger.debug("Input date format :" + inputDateFormat);
		String outputDateFormat = "yyyy-MM-dd HH:mm:ss";
		logger.debug("Output date format :" + outputDateFormat);
		SimpleDateFormat inputFormat = new SimpleDateFormat(inputDateFormat);
		SimpleDateFormat outFormat = new SimpleDateFormat(outputDateFormat);
		Date dateObj = inputFormat.parse(endDate);
		dateObj.setHours(23);
		dateObj.setMinutes(59);
		dateObj.setSeconds(59);
		String strOutFormat = outFormat.format(dateObj);

		logger.info("exiting");
		return strOutFormat;
	}
}
