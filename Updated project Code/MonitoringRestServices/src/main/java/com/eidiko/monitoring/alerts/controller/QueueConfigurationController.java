package com.eidiko.monitoring.alerts.controller;

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
import com.eidiko.monitoring.alerts.model.QueueConfiguration;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.services.QueueConfigurationService;

@Controller
public class QueueConfigurationController {
	public static final Logger logger = Logger
			.getLogger(QueueConfigurationController.class);

	@Autowired
	QueueConfigurationService queueCfgService;

	public QueueConfiguration getQueueConfigurationFromJson(
			JSONObject jsonObject) {
		logger.info("entered");
		QueueConfiguration queueCfg = new QueueConfiguration();
		String name = jsonObject.getString("name");
		String host = jsonObject.getString("host");
		String queue = jsonObject.getString("queue");
		String svrConn = jsonObject.getString("svrConn");
		int port = Integer.parseInt(jsonObject.getString("listener_port"));

		queueCfg.setName(name);
		queueCfg.setHost(host);
		queueCfg.setQueue(queue);
		queueCfg.setSvrConn(svrConn);
		queueCfg.setListener_port(port);

		logger.debug("Queue Configuration :" + queueCfg);
		logger.info("exiting");
		return queueCfg;

	}

	@RequestMapping(value = "/addQueueCfg", method = RequestMethod.POST)
	public @ResponseBody Status addQueueCfg(
			@RequestBody QueueConfiguration queueCfg) {
		logger.debug("Add QueueConfiguartion entered");
		if (!validateQueueConfiguration(queueCfg)) {
			return Status.getFailureStatus(Messages.EXCEPTION);

		}
		logger.debug("Queue Configuration :" + queueCfg);
		try {
			queueCfgService.addQueueCfg(queueCfg);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_ADDED);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);

		}

	}

	@RequestMapping(value = "/deleteQueueCfg/{id}", method = RequestMethod.PUT)
	public @ResponseBody Status deleteQueueCfg(@PathVariable("id") long id) {
		logger.info("Delete Alert Configuration Entered");
		logger.debug("Delete Alert Configuration By Id :" + id);
		try {
			queueCfgService.deleteQueueCfg(id);
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);

		}

	}

	@RequestMapping(value = "/updateQueueCfg", method = RequestMethod.POST)
	public @ResponseBody Status updateQueueCfg(
			@RequestBody QueueConfiguration queueCfg) {
		logger.info("Update Alert Configuration entered");
		if (!validateQueueConfiguration(queueCfg)) {
			return Status.getFailureStatus(Messages.EXCEPTION);

		}
		logger.debug("AlertConfiguration :" + queueCfg);

		try {
			if (queueCfg.getId() == 0) {
				queueCfgService.addQueueCfg(queueCfg);
			} else {
				queueCfgService.updateQueueCfg(queueCfg);
				;
			}
			logger.info("exiting");
			return Status.getSuccesStatus(Messages.DATA_MODIFIED);

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Status.getFailureStatus(Messages.EXCEPTION);
		}

	}

	@RequestMapping(value = "getQueueCfg/{id}", method = RequestMethod.GET)
	public @ResponseBody QueueConfiguration getAlertCfgById(
			@PathVariable("id") long id) {
		logger.info("Get Alert Configuration By Id entered");
		logger.debug("Get Alert Configuration By Id :" + id);
		QueueConfiguration queueCfg = null;
		try {

			queueCfg = queueCfgService.getQueueCfgById(id);
			logger.debug("Alert Configuration Details :" + queueCfg);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

		}
		logger.info("exiting");

		return queueCfg;

	}

	@RequestMapping(value = "getQueueCfgn", method = RequestMethod.GET)
	public @ResponseBody QueueConfiguration getQueueCfgn() {
		logger.info("Get Alert Configuration By Id entered");

		QueueConfiguration queueCfg = null;
		try {

			queueCfg = queueCfgService.getQueueCfg();
			logger.debug("Alert Configuration Details :" + queueCfg);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

		}
		logger.info("exiting");

		return queueCfg;

	}

	private boolean validateQueueConfiguration(QueueConfiguration queuCfg) {
		logger.info("entered");
		logger.debug("Alert Configuration" + ":" + queuCfg);
		if (StringUtils.isBlank(queuCfg.getName())) {
			logger.debug("Name is empty or null");
			return false;
		} else if (StringUtils.isBlank(queuCfg.getHost())) {
			logger.debug("Host name  is empty or null");
			return false;
		} else if (StringUtils.isBlank(queuCfg.getQueue())) {
			logger.debug("Queue is empty or null");
			return false;
		} else if (StringUtils.isBlank(queuCfg.getSvrConn())) {
			logger.debug("Channel is empty or null");
			return false;
		} else if (StringUtils.isBlank(String.valueOf(queuCfg
				.getListener_port()))) {
			logger.debug("Port is empty or null");
			return false;
		}

		else {
			logger.debug("Every data is valid");
			logger.info("exiting");
			return true;
		}
	}

}
