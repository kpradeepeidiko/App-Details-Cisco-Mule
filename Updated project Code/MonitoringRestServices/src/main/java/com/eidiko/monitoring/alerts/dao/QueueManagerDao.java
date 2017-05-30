package com.eidiko.monitoring.alerts.dao;

import java.text.ParseException;
import java.util.List;

import com.eidiko.monitoring.alerts.model.AppQueueDetails;
import com.eidiko.monitoring.alerts.model.AppQueueMangers;
import com.eidiko.monitoring.alerts.model.EventMessage;
import com.eidiko.monitoring.alerts.model.EventWithProperties;
import com.eidiko.monitoring.alerts.model.MQEvents;
import com.eidiko.monitoring.alerts.model.QueueManager;
import com.eidiko.monitoring.alerts.model.QueueManagerStatus;

public interface QueueManagerDao extends BaseDao {

	public List<QueueManager> getQueueManagerList();

	public List<AppQueueMangers> getQueueManagerDetails(long id)
			throws Exception;

	public List<AppQueueDetails> getQueueDetails(long id) throws Exception;

	public List<MQEvents> getEventDetails(String qmgrName, String queueName,
			String queueType);

	public List<MQEvents> getQMGREventDetails(String qmgrName,
			String queueName, String queueType);

	public List<AppQueueMangers> getQueueManagers(long id) throws Exception;

	public List<EventMessage> getEvent(String startDate, String endDate,
			long id, long mid, String qmgrName, String queueName,
			String queueType, String hostName) throws ParseException;

	public List<EventWithProperties> getQmgrEvent(String startDate,
			String endDate, long id, long mid, String qmgrName,
			String queueName, String queueType) throws ParseException;

	public List<QueueManagerStatus> getQueueManagerStatus(String qName,
			String hostName) throws Exception;

	public List<EventMessage> getFileEvents(String startDate, String endDate,
			long id) throws ParseException;

	public List<EventMessage> getProcessEvents(String startDate,
			String endDate, long id) throws ParseException;

	public boolean deleteQueueManagerDetails(long id) throws Exception;
}
