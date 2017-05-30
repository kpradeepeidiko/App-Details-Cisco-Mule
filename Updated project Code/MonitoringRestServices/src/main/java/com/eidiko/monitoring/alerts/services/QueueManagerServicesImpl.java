package com.eidiko.monitoring.alerts.services;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.eidiko.monitoring.alerts.dao.QueueManagerDao;
import com.eidiko.monitoring.alerts.model.AppQueueDetails;
import com.eidiko.monitoring.alerts.model.AppQueueMangers;
import com.eidiko.monitoring.alerts.model.EventMessage;
import com.eidiko.monitoring.alerts.model.EventWithProperties;
import com.eidiko.monitoring.alerts.model.MQEvents;
import com.eidiko.monitoring.alerts.model.QueueManager;
import com.eidiko.monitoring.alerts.model.QueueManagerStatus;

public class QueueManagerServicesImpl implements QueueManagerServices {
	public static final Logger logger = Logger
			.getLogger(QueueManagerServicesImpl.class);
	@Autowired
	QueueManagerDao queueManagerDao;

	@Override
	public void addQueueManager(QueueManager queueManager) throws Exception {
		logger.info("entered");
		queueManagerDao.saveOrUpdate(queueManager);
		logger.info("exiting");
	}

	@Override
	public List<QueueManager> getQueueManagerList() {
		logger.info("entered");
		return queueManagerDao.getQueueManagerList();
	}

	@Override
	public void deleteQueueManager(long id) throws Exception {
		logger.info("entered");
		queueManagerDao.delete(QueueManager.class, id);
		logger.info("exiting");
	}

	@Override
	public void updateQueueManager(QueueManager queueManager) throws Exception {
		logger.info("entered");
		queueManagerDao.saveOrUpdate(queueManager);
		logger.info("exiting");

	}

	@Override
	public QueueManager getQueueManagerById(long id) throws Exception {
		logger.info("entered");
		return (QueueManager) queueManagerDao.getEntityById(QueueManager.class,
				id);
	}

	@Override
	public List<AppQueueMangers> getQueueManagerDetails(long id)
			throws Exception {
		logger.info("entered");
		return queueManagerDao.getQueueManagerDetails(id);
	}

	@Override
	public List<AppQueueDetails> getQueueDetails(long id) throws Exception {
		logger.info("entered");
		return queueManagerDao.getQueueDetails(id);
	}

	@Override
	public List<MQEvents> getEventDetails(String qmgrName, String queueName,
			String queueType) {
		logger.info("entered");
		return queueManagerDao.getEventDetails(qmgrName, queueName, queueType);
	}

	@Override
	public List<MQEvents> getQMGREventDetails(String qmgrName,
			String queueName, String queueType) {
		logger.info("entered");
		return queueManagerDao.getQMGREventDetails(qmgrName, queueName,
				queueType);
	}

	@Override
	public List<AppQueueMangers> getQueueManagers(long id) throws Exception {
		logger.info("entered");
		return queueManagerDao.getQueueManagers(id);
	}

	@Override
	public List<EventMessage> getEvent(String startDate, String endDate,
			long id, long mid, String qmgrName, String queueName,
			String queueType, String hostName) throws ParseException {
		logger.info("entered");
		return queueManagerDao.getEvent(startDate, endDate, id, mid, qmgrName,
				queueName, queueType, hostName);
	}

	@Override
	public List<EventWithProperties> getQmgrEvent(String startDate,
			String endDate, long id, long mid, String qmgrName,
			String queueName, String queueType) throws ParseException {
		logger.info("entered");
		return queueManagerDao.getQmgrEvent(startDate, endDate, id, mid,
				qmgrName, queueName, queueType);
	}

	@Override
	public List<QueueManagerStatus> getQueueManagerStatus(String qName,
			String hostName) throws Exception {
		return queueManagerDao.getQueueManagerStatus(qName, hostName);
	}

	@Override
	public List<EventMessage> getFileEvents(String startDate, String endDate,
			long id) throws ParseException {
		// TODO Auto-generated method stub
		logger.info("entered");
		return queueManagerDao.getFileEvents(startDate, endDate, id);
	}

	@Override
	public List<EventMessage> getProcessEvents(String startDate,
			String endDate, long id) throws ParseException {
		logger.info("entered");
		return queueManagerDao.getProcessEvents(startDate, endDate, id);
	}

	@Override
	public boolean deleteQueueManagerDetails(long id) throws Exception {
		logger.info("entered");

		return queueManagerDao.deleteQueueManagerDetails(id);
	}
}
