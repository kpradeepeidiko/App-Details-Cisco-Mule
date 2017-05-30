package com.eidiko.monitoring.alerts.controller;

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

import com.eidiko.monitoring.alerts.helper.MQHelper;
import com.eidiko.monitoring.alerts.helper.QueueDetails;
import com.eidiko.monitoring.alerts.model.AppQueueDetails;
import com.eidiko.monitoring.alerts.model.AppQueueMangers;
import com.eidiko.monitoring.alerts.model.EventMessage;
import com.eidiko.monitoring.alerts.model.EventWithProperties;
import com.eidiko.monitoring.alerts.model.MQEvents;
import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.QueueManager;
import com.eidiko.monitoring.alerts.model.QueueManagerStatus;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.services.QueueManagerServices;
import com.ibm.mq.MQQueueManager;

@Controller
public class QueueManagerController {
	private static final Logger logger = Logger
			.getLogger(QueueManagerController.class);

	@Autowired
	QueueManagerServices queueManagerService;

	public QueueManager getQueueManagerFromJson(JSONObject jsonObject) {
		logger.info("entered");

		QueueManager queueManager = new QueueManager();

		String name = jsonObject.getString("name");
		String host = jsonObject.getString("host");
		String svrConn = jsonObject.getString("svrConn");
		int port = Integer.parseInt(jsonObject.getString("listnerPort"));
		String mcaUser = jsonObject.getString("mcaUser");

		queueManager.setName(name);
		queueManager.setHost(host);
		queueManager.setSvrConn(svrConn);
		queueManager.setListnerPort(port);
		queueManager.setMcaUser(mcaUser);
		logger.debug("QueueManager" + ":" + queueManager);
		logger.info("exiting");
		return queueManager;

	}

	@RequestMapping(value = "/queuemanager", method = RequestMethod.POST)
	public @ResponseBody Status addQueueManager(
			@RequestBody QueueManager queueManager) {

		logger.info("addQueueManager entered");
		/*
		 * QueueManager queueManager = new QueueManager(); JSONObject
		 * js=JSONObject.fromObject(values); queueManager =
		 * getQueueManagerFromJson(js);
		 */
		if (!validateQueueManager(queueManager)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
		logger.debug("queueManager :" + queueManager);
		try {
			queueManagerService.addQueueManager(queueManager);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_ADDED);
		} catch (Exception e) {

			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}
	}

	@RequestMapping(value = "/queuemanagerlist", method = RequestMethod.GET)
	public @ResponseBody List<QueueManager> getEmployee() {
		logger.info("queueManagaerList entered");

		List<QueueManager> queueManagerList = new ArrayList<QueueManager>();
		try {
			queueManagerList = queueManagerService.getQueueManagerList();
			logger.debug("QueueManager List :" + queueManagerList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		logger.info("exiting");
		return queueManagerList;
	}

	@RequestMapping(value = "/queuemanagerlistwithstatus", method = RequestMethod.GET)
	public @ResponseBody List<QueueManager> getQMGRListWithStatus() {
		logger.info("queueManagaerList entered");

		List<QueueManager> queueManagerList = new ArrayList<QueueManager>();
		List<QueueManager> newQueueManagerList = new ArrayList<QueueManager>();
		try {
			queueManagerList = queueManagerService.getQueueManagerList();
			logger.debug("QueueManager List :" + queueManagerList);

			for (QueueManager queueManagerById : queueManagerList) {
				try {
					MQQueueManager mqQueueMgr = MQHelper.getQueueManager(
							queueManagerById.getName(),
							queueManagerById.getListnerPort(),
							queueManagerById.getHost(),
							queueManagerById.getSvrConn());
					if (mqQueueMgr != null) {

						queueManagerById.setStatus("true");
						mqQueueMgr.disconnect();
					} else {
						queueManagerById.setStatus("false");
					}
					newQueueManagerList.add(queueManagerById);
				} catch (Exception e) {
					logger.error(e.getMessage());
					queueManagerById.setStatus("false");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return newQueueManagerList;
	}

	@RequestMapping(value = "deleteQueueManager/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteQueueManager(@PathVariable("id") long id) {
		logger.info("deleteQueueManager entered");
		logger.debug("Delete QueueManager By Id" + ":" + id);
		try {
			queueManagerService.deleteQueueManager(id);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "/updateQueueManager", method = RequestMethod.POST)
	public @ResponseBody Status updateQueueManager(
			@RequestBody QueueManager queueManager) {
		logger.info("updateQueueManager entered");
		/*
		 * JSONObject js=JSONObject.fromObject(values);
		 * 
		 * QueueManager queueManager = new QueueManager();
		 * 
		 * queueManager = getQueueManagerFromJson(js);
		 * 
		 * long id = Long.parseLong(js.getString("id"));
		 */

		if (!validateQueueManager(queueManager)) {
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

		/* queueManager.setId(id); */

		logger.debug("QueueManager :" + queueManager);

		try {
			if (queueManager.getId() == 0) {
				queueManagerService.addQueueManager(queueManager);
			} else {
				queueManagerService.updateQueueManager(queueManager);
			}

			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_MODIFIED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "getQueueManager/{id}", method = RequestMethod.GET)
	public @ResponseBody QueueManager getQueueManagerById(
			@PathVariable("id") long id) throws Exception {
		logger.info("getQueueManagerById entered");
		logger.debug("getQueueManager By Id " + id);
		QueueManager queueManagerById = null;
		try {

			queueManagerById = queueManagerService.getQueueManagerById(id);
			logger.debug("Queue Manager :" + queueManagerById);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		logger.info("exiting");
		return queueManagerById;

	}

	@RequestMapping(value = "appQueueManager/{id}", method = RequestMethod.GET)
	public @ResponseBody List<AppQueueMangers> getQueueManagerDetails(
			@PathVariable("id") long id) throws Exception {
		logger.info("getQueueManagerByMapperAppId entered");
		logger.debug("getQueueManagerByMapperAppId" + ":" + id);
		List<AppQueueMangers> queueManagerList = new ArrayList<AppQueueMangers>();
		try {
			queueManagerList = queueManagerService.getQueueManagerDetails(id);
			logger.debug("App QueueManager List :" + queueManagerList);
		}

		catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		logger.info("exiting");
		return queueManagerList;
	}

	@RequestMapping(value = "getMQQueueManager/{id}", method = RequestMethod.GET)
	public @ResponseBody List<QueueDetails> getMQQueueManagerById(
			@PathVariable("id") long id) throws Exception {
		logger.info("getQueueDetails entered");
		logger.debug("get QueueDetails By Id" + id);
		QueueManager queueManagerById = null;
		List<QueueDetails> queueDetailsList = new ArrayList<QueueDetails>();

		try {
			queueManagerById = queueManagerService.getQueueManagerById(id);
			logger.debug("Queue Managers By Id :" + queueManagerById);
			MQQueueManager mqQueueMgr = MQHelper.getQueueManager(
					queueManagerById.getName(),
					queueManagerById.getListnerPort(),
					queueManagerById.getHost(), queueManagerById.getSvrConn());
			logger.debug("QueueManager :" + mqQueueMgr);
			MQHelper.getQueueNames(mqQueueMgr, queueDetailsList);
			MQHelper.getChannelNames(mqQueueMgr, queueDetailsList);
			logger.debug("QueueDetails size :" + queueDetailsList.size());
			logger.debug("QueueDetails :" + queueDetailsList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return queueDetailsList;
	}

	@RequestMapping(value = "dashBoard/alerts/{id}", method = RequestMethod.GET, params = {
			"startDate", "endDate" })
	public @ResponseBody List<EventMessage> getMQEventsDetails(
			@PathVariable("id") long id,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws Exception {
		logger.info("dashBoardAlertsWithDate entered");
		List<EventMessage> mqEvents = new ArrayList<EventMessage>();
		List<AppQueueDetails> appObjList = new ArrayList<AppQueueDetails>();
		List<AppQueueMangers> queueManagerList = new ArrayList<AppQueueMangers>();
		List<EventMessage> dummyList = new ArrayList<EventMessage>();
		List<EventMessage> dummyList1 = new ArrayList<EventMessage>();
		String startDateFormat = convertStartDateFormat(startDate);
		String endDateFormat = convertEndDateFormat(endDate);
		logger.debug("dashBoardAlert By Id" + ":" + id);
		logger.debug("Start date :" + startDate);
		logger.debug("End date :" + endDate);
		try {
			queueManagerList = queueManagerService.getQueueManagerDetails(id);
			logger.debug("Queue Manager List :" + queueManagerList);
			for (AppQueueMangers appQeueMgrList : queueManagerList) {
				String qmgrName = appQeueMgrList.getName();
				String hostName = appQeueMgrList.getHost();
				logger.debug("Queue Manager :" + appQeueMgrList);
				appObjList = queueManagerService.getQueueDetails(appQeueMgrList
						.getMapperId());
				logger.debug("Object List :" + appObjList);
				for (AppQueueDetails appObject : appObjList) {
					String queueName = appObject.getObjectName();
					String queueType = appObject.getObjectType();
					logger.debug("Queue Details :" + appObject);
					/*
					 * mqEvents = queueManagerService.getEventDetails(qmgrName,
					 * queueName, queueType);
					 */
					dummyList = queueManagerService.getEvent(startDateFormat,
							endDateFormat, id, appQeueMgrList.getMapperId(),
							qmgrName, queueName, queueType, hostName);
					// dummyList1=queueManagerService.getFileEvents(startDate,
					// endDate, id);
					mqEvents.addAll(dummyList);
					// mqEvents.addAll(dummyList1);
					logger.debug("dummyList :" + mqEvents);
				}
			}
			dummyList = queueManagerService.getFileEvents(startDateFormat,
					endDateFormat, id);
			mqEvents.addAll(dummyList);
			dummyList = queueManagerService.getProcessEvents(startDateFormat,
					endDateFormat, id);
			mqEvents.addAll(dummyList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		logger.info("exiting");
		return mqEvents;
	}

	@RequestMapping(value = "dashBoard/alerts/{id}", method = RequestMethod.GET)
	public @ResponseBody List<MQEvents> getMQEventsDetail(
			@PathVariable("id") long id) throws Exception {
		logger.info("dashBoardsAlerts entered");
		List<MQEvents> mqEvents = new ArrayList<MQEvents>();
		List<AppQueueDetails> appObjList = new ArrayList<AppQueueDetails>();
		List<AppQueueMangers> queueManagerList = new ArrayList<AppQueueMangers>();
		List<MQEvents> dummyList = new ArrayList<MQEvents>();
		logger.debug("dashBoardAlert By Id" + ":" + id);

		try {
			queueManagerList = queueManagerService.getQueueManagerDetails(id);
			logger.debug("Queue Manager List :" + queueManagerList);
			for (AppQueueMangers appQeueMgrList : queueManagerList) {

				String qmgrName = appQeueMgrList.getName();
				logger.debug("Queue Manager :" + appQeueMgrList);
				appObjList = queueManagerService.getQueueDetails(appQeueMgrList
						.getMapperId());
				logger.debug("Object List :" + appObjList);
				for (AppQueueDetails appObject : appObjList) {
					String queueName = appObject.getObjectName();
					String queueType = appObject.getObjectType();
					logger.debug("Queue Details :" + appObject);
					dummyList = queueManagerService.getEventDetails(qmgrName,
							queueName, queueType);
					mqEvents.addAll(dummyList);
					logger.debug("dummyList :" + mqEvents);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		logger.info("exiting");

		return mqEvents;

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

	@RequestMapping(value = "dashBoard/qmgrAlerts/{id}", method = RequestMethod.GET)
	public @ResponseBody List<MQEvents> getMQEvents(@PathVariable("id") long id)
			throws Exception {
		logger.info("dashBoardQmgrAlerts entered");

		List<MQEvents> mqEvents = new ArrayList<MQEvents>();
		List<AppQueueMangers> appQueueMgr = new ArrayList<AppQueueMangers>();
		List<AppQueueDetails> appObjList = new ArrayList<AppQueueDetails>();
		logger.debug("dashBoardQmgrAlerts By Id" + ":" + id);
		try {
			appQueueMgr = queueManagerService.getQueueManagers(id);
			logger.debug("QueueManager :" + appQueueMgr);
			for (AppQueueMangers appQueueMgrList : appQueueMgr) {
				String qmgrName = appQueueMgrList.getName();
				String hostName = appQueueMgrList.getHost();
				logger.debug("AppQueueManagers :" + appQueueMgrList);
				appObjList = queueManagerService
						.getQueueDetails(appQueueMgrList.getMapperId());
				logger.debug("Object List :" + appObjList);

				/*
				 * for(AppQueueDetails appObject:appObjList) { String queueName
				 * =appObject.getObjectName();
				 * 
				 * String queueType = appObject.getObjectType();
				 * logger.debug("Queue Details :"+ appObject);
				 */

				mqEvents = queueManagerService.getQMGREventDetails(qmgrName,
						"", hostName);
				logger.debug("MQEvents Details :" + mqEvents);
				/* } */
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return mqEvents;
	}

	@RequestMapping(value = "dashBoard/qmgrAlerts/{id}", method = RequestMethod.GET, params = {
			"startDate", "endDate" })
	public @ResponseBody List<EventWithProperties> getMQEventDetail(
			@PathVariable("id") long id,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws Exception {
		logger.info("dashBoardQmgrAlertsWithDate entered");

		List<EventWithProperties> mqEvents = new ArrayList<EventWithProperties>();
		List<AppQueueMangers> appQueueMgr = new ArrayList<AppQueueMangers>();
		List<AppQueueDetails> appObjList = new ArrayList<AppQueueDetails>();

		String startDateFormat = convertStartDateFormat(startDate);
		String endDateFormat = convertEndDateFormat(endDate);
		logger.debug("dashBoardQmgrAlertsWithDate By id" + ":" + id);
		logger.debug("Start Date :" + startDate);
		logger.debug("End date :" + endDate);
		try {
			appQueueMgr = queueManagerService.getQueueManagers(id);
			logger.debug("QueueManager :" + appQueueMgr);
			for (AppQueueMangers appQueueMgrList : appQueueMgr) {
				String qmgrName = appQueueMgrList.getName();
				String hostName = appQueueMgrList.getHost();
				logger.debug("AppQueueManagers :" + appQueueMgrList);
				appObjList = queueManagerService
						.getQueueDetails(appQueueMgrList.getMapperId());
				logger.debug("Object List :" + appObjList);

				/*
				 * for(AppQueueDetails appObject:appObjList) { String queueName
				 * =appObject.getObjectName(); String queueType =
				 * appObject.getObjectType(); logger.debug("Queue Details :"+
				 * appObject);
				 */

				/*
				 * mqEvents = queueManagerService.getEventDetails(qmgrName, "",
				 * hostName);
				 */
				logger.debug("MQEvents Details :" + mqEvents);
				mqEvents = queueManagerService.getQmgrEvent(startDateFormat,
						endDateFormat, id, appQueueMgrList.getMapperId(),
						qmgrName, "", hostName);
				logger.debug("MQEvents Details :" + mqEvents);
				/* } */
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return mqEvents;

	}

	@RequestMapping(value = "getQueueManagerStatus/{id}", method = RequestMethod.GET)
	public @ResponseBody List<QueueManagerStatus> getQmStatus(
			@PathVariable("id") long id) throws Exception {

		logger.info("entered");
		logger.debug("QueueManager Id : " + id);

		List<QueueManagerStatus> qmStatusDetails = new ArrayList<QueueManagerStatus>();

		QueueManager listQm = queueManagerService.getQueueManagerById(id);

		String qmName = listQm.getName();
		String hostName = listQm.getHost();

		qmStatusDetails = queueManagerService.getQueueManagerStatus(qmName,
				hostName);

		logger.debug("Queue Manager Status Details : " + qmStatusDetails);

		return qmStatusDetails;
	}

	private boolean validateQueueManager(QueueManager qmgr) {
		logger.info("entered");
		logger.debug("QueueManager" + ":" + qmgr);
		if (StringUtils.isBlank(qmgr.getName())) {
			logger.debug("Name is empty or null");
			return false;
		} else if (StringUtils.isBlank(qmgr.getHost())) {
			logger.debug("Host name  is empty or null");
			return false;
		} else if (StringUtils.isBlank(qmgr.getSvrConn())) {
			logger.debug("SvrConn is empty or null");
			return false;
		} else if (StringUtils.isBlank(String.valueOf(qmgr.getListnerPort()))) {
			logger.debug("Listener port is empty or null");
			return false;
		} else if (StringUtils.isBlank(qmgr.getMcaUser())) {
			logger.debug("McaUser is empty or null");
			return false;
		} else {
			logger.debug("Every data is valid");
			logger.info("exiting");
			return true;
		}
	}

	@RequestMapping(value = "testConnection/{id}", method = RequestMethod.GET)
	public @ResponseBody Status testConnection(@PathVariable("id") long id) {

		logger.info("testConnection entered");
		QueueManager queueManagerById = new QueueManager();
		Status sts = new Status();
		logger.debug("testConnection with Id" + ":" + id);

		try {

			queueManagerById = queueManagerService.getQueueManagerById(id);

			MQQueueManager mqQueueMgr = MQHelper.getQueueManager(
					queueManagerById.getName(),
					queueManagerById.getListnerPort(),
					queueManagerById.getHost(), queueManagerById.getSvrConn());
			if (mqQueueMgr != null) {
				sts = Status.getSuccesStatus("Connection Sucess");

			}

			else {

				sts = Status.getFailureStatus("Connection Failed");

			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("exiting");
		return sts;
	}

	@RequestMapping(value = "deleteQueueManagerDetails/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteQMDetails(@PathVariable("id") long id) {
		logger.info("deleteQueueManager entered " + id);
		try {
			boolean result = queueManagerService.deleteQueueManagerDetails(id);
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
