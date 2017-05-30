package com.eidiko.monitoring.alerts.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.eidiko.monitoring.alerts.model.AppQueueDetails;
import com.eidiko.monitoring.alerts.model.AppQueueMangers;
import com.eidiko.monitoring.alerts.model.EventMessage;
import com.eidiko.monitoring.alerts.model.EventWithProperties;
import com.eidiko.monitoring.alerts.model.MQEvents;
import com.eidiko.monitoring.alerts.model.QueueManager;
import com.eidiko.monitoring.alerts.model.QueueManagerStatus;
import com.eidiko.monitoring.alerts.model.Process;

@Transactional
public class QueueManagerDaoImpl extends BaseDaoImpl implements QueueManagerDao {
	public static final Logger logger = Logger
			.getLogger(QueueManagerDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<QueueManager> getQueueManagerList() {
		logger.info("entered");
		return getSessionFactory().getCurrentSession()
				.createCriteria(QueueManager.class).list();

	}

	@Override
	public List<AppQueueMangers> getQueueManagerDetails(long id)
			throws Exception {
		logger.info("entered");
		List<AppQueueMangers> appQMngrList = new ArrayList<AppQueueMangers>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getQueueManager By Id" + ":" + id);
		Query query = session
				.createQuery("SELECT q.id,q.name,q.host,q.svrConn,q.listnerPort,q.mcaUser,qa.id from QueueManager q , ApplicationMapper qa where qa.appQmgrId = q.id and qa.appApplicationId="
						+ new Long(id));
		List<Object[]> qmgrList = query.list();

		for (Object[] queueManager : qmgrList) {
			AppQueueMangers appQueueManger = new AppQueueMangers();
			appQueueManger.setId((long) queueManager[0]);
			appQueueManger.setName((String) queueManager[1]);
			appQueueManger.setHost((String) queueManager[2]);
			appQueueManger.setSvrConn((String) queueManager[3]);
			appQueueManger.setListnerPort((int) queueManager[4]);
			appQueueManger.setMcaUser((String) queueManager[5]);
			appQueueManger.setMapperId((long) queueManager[6]);
			appQMngrList.add(appQueueManger);
			logger.debug("AppQueueManager List :" + appQMngrList);
		}

		return appQMngrList;
	}

	@Override
	public List<AppQueueDetails> getQueueDetails(long id) throws Exception {
		logger.info("entered");
		List<AppQueueDetails> appQueueList = new ArrayList<AppQueueDetails>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getQueueDetails By Id" + ":" + id);
		Query query = session
				.createQuery("SELECT ao.id,ao.objectName,ao.objectType,ao.appQmgrAppId from AppObjects ao , ApplicationMapper am where ao.appQmgrAppId=am.id and am.id="
						+ new Long(id));
		List<Object[]> queueList = query.list();
		for (Object[] objList : queueList) {
			AppQueueDetails queueDetails = new AppQueueDetails();
			queueDetails.setId((long) objList[0]);
			queueDetails.setObjectName((String) objList[1]);
			queueDetails.setObjectType((String) objList[2]);
			queueDetails.setAppQmgrAppId((long) objList[3]);
			appQueueList.add(queueDetails);
			logger.debug("Queue Details :" + appQueueList);
		}

		return appQueueList;
	}

	@Override
	public List<MQEvents> getEventDetails(String qmgrName, String queueName,
			String queueType) {
		logger.info("entered");
		List<MQEvents> mqList = new ArrayList<MQEvents>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("QueueManager Name" + ":" + qmgrName);
		logger.debug("Queue Name" + ":" + queueName);
		logger.debug("hostName" + ":" + queueType);
		System.out
				.println("SELECT mq.id,mq.mqObjectType,mq.mqObjectName,mq.mqQmgrName,mq.mqObjectStatus,mq.mqObjectDate,mq.mqEventHostName,mq.mqEventInsertedDate from MQEvents mq where mq.mqQmgrName ='"
						+ qmgrName
						+ "' and "
						+ "  mq.mqObjectName='"
						+ queueName
						+ "' and mq.mqObjectType='"
						+ queueType
						+ "'");
		Query query = session
				.createQuery("SELECT distinct mq.id,mq.mqObjectType,mq.mqObjectName,mq.mqQmgrName,mq.mqObjectStatus,mq.mqObjectDate,mq.mqEventHostName,mq.mqEventInsertedDate,mq.reasonCode from MQEvents mq where mq.mqQmgrName ='"
						+ qmgrName
						+ "' and "
						+ "  mq.mqObjectName='"
						+ queueName
						+ "' and mq.mqObjectType='"
						+ queueType
						+ "'");
		/*
		 * Query query = session.createQuery(
		 * "SELECT mq.id,mq.mqObjectType,mq.mqObjectName,mq.mqQmgrName,mq.mqObjectStatus,mq.mqObjectDate,mq.mqEventHostName,mq.mqEventInsertedDate from MQEvents mq where mq.mqQmgrName ='"
		 * +qmgrName+"' and "+"  mq.mqEventHostName='"+hostName+"'");
		 */
		List<Object[]> MqList = query.list();
		for (Object[] objList : MqList) {
			MQEvents mqEvents = new MQEvents();
			mqEvents.setId((long) objList[0]);
			mqEvents.setMqObjectType((String) objList[1]);
			mqEvents.setMqObjectName((String) objList[2]);
			mqEvents.setMqQmgrName((String) objList[3]);
			mqEvents.setMqObjectStatus((String) objList[4]);
			mqEvents.setMqObjectDate((String) objList[5]);
			mqEvents.setMqEventHostName((String) objList[6]);
			mqEvents.setMqEventInsertedDate((String) objList[7]);
			mqEvents.setReasonCode((int) objList[8]);
			mqList.add(mqEvents);
			logger.debug("MQEvent List :" + mqList);

		}

		return mqList;
	}

	@Override
	public List<MQEvents> getQMGREventDetails(String qmgrName,
			String queueName, String hostName) {
		logger.info("entered");
		List<MQEvents> mqList = new ArrayList<MQEvents>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("QueueManager Name" + ":" + qmgrName);
		logger.debug("Queue Name" + ":" + queueName);
		logger.debug("hostName" + ":" + hostName);
		System.out
				.println("SELECT mq.id,mq.mqObjectType,mq.mqObjectName,mq.mqQmgrName,mq.mqObjectStatus,mq.mqObjectDate,mq.mqEventHostName,mq.mqEventInsertedDate,mq.reasonCode from MQEvents mq where mq.mqQmgrName ='"
						+ qmgrName
						+ "' and "
						+ "  mq.mqEventHostName='"
						+ hostName + "'");
		/*
		 * Query query = session.createQuery(
		 * "SELECT mq.id,mq.mqObjectType,mq.mqObjectName,mq.mqQmgrName,mq.mqObjectStatus,mq.mqObjectDate,mq.mqEventHostName,mq.mqEventInsertedDate from MQEvents mq where mq.mqQmgrName ='"
		 * +qmgrName+"' and "+"  mq.mqObjectName='"+queueName+
		 * "' and mq.mqObjectType='"+queueType+"'");
		 */
		Query query = session
				.createQuery("SELECT mq.id,mq.mqObjectType,mq.mqObjectName,mq.mqQmgrName,mq.mqObjectStatus,mq.mqObjectDate,mq.mqEventHostName,mq.mqEventInsertedDate from MQEvents mq where mq.mqQmgrName ='"
						+ qmgrName
						+ "' and "
						+ "  mq.mqEventHostName='"
						+ hostName + "'");
		List<Object[]> MqList = query.list();
		for (Object[] objList : MqList) {
			MQEvents mqEvents = new MQEvents();
			mqEvents.setId((long) objList[0]);
			mqEvents.setMqObjectType((String) objList[1]);
			mqEvents.setMqObjectName((String) objList[2]);
			mqEvents.setMqQmgrName((String) objList[3]);
			mqEvents.setMqObjectStatus((String) objList[4]);
			mqEvents.setMqObjectDate((String) objList[5]);
			mqEvents.setMqEventHostName((String) objList[6]);
			mqEvents.setMqEventInsertedDate((String) objList[7]);
			mqEvents.setReasonCode((int) objList[8]);
			mqList.add(mqEvents);
			logger.debug("MQEvent List :" + mqList);
		}
		return mqList;
	}

	@Override
	public List<AppQueueMangers> getQueueManagers(long id) throws Exception {
		logger.info("entered");
		List<AppQueueMangers> appQMngrList = new ArrayList<AppQueueMangers>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getQueueManagers By Id" + ":" + id);
		System.out
				.println("SELECT q.id,q.name,q.host,q.svrConn,q.listnerPort,q.mcaUser,qa.id from QueueManager q , ApplicationMapper qa where qa.appQmgrId = q.id and q.id="
						+ new Long(id));
		/*
		 * Query query = session.createQuery(
		 * "SELECT q.id,q.name,q.host,q.svrConn,q.listnerPort,q.mcaUser,qa.id from QueueManager q , ApplicationMapper qa where qa.appQmgrId = q.id and q.id="
		 * +new Long(id));
		 */
		Query query = session
				.createQuery("SELECT q.id,q.name,q.host,q.svrConn,q.listnerPort,q.mcaUser from QueueManager q where q.id="
						+ new Long(id));
		List<Object[]> qmgrList = query.list();
		for (Object[] queueManager : qmgrList) {
			AppQueueMangers appQueueManger = new AppQueueMangers();
			appQueueManger.setId((long) queueManager[0]);
			appQueueManger.setName((String) queueManager[1]);
			appQueueManger.setHost((String) queueManager[2]);
			appQueueManger.setSvrConn((String) queueManager[3]);
			appQueueManger.setListnerPort((int) queueManager[4]);
			appQueueManger.setMcaUser((String) queueManager[5]);
			appQMngrList.add(appQueueManger);
			logger.debug("App Queue Manager List :" + appQMngrList);
		}
		return appQMngrList;
	}

	@Override
	public List<EventMessage> getEvent(String startDate, String endDate,
			long id, long mid, String qmgrName, String queueName,
			String queueType, String hostName) throws ParseException {
		logger.info("entered");

		List<EventMessage> mqEvent = new ArrayList<EventMessage>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("Start Date" + ":" + startDate);
		logger.debug("EndDate" + ":" + endDate);
		logger.debug("Id " + ":" + id);
		logger.debug("QueueManager Name" + ":" + qmgrName);
		logger.debug("Queue Name" + ":" + queueName);
		logger.debug("Queue Type" + ":" + queueType);
		String queryStr = "SELECT distinct mq.id,mq.mqObjectType,mq.mqObjectName,mq.mqQmgrName,mq.mqObjectStatus,mq.mqObjectDate, "
				+ " mq.mqEventHostName,mq.mqEventInsertedDate,mq.reasonCode ,amp.eventlLevel,amp.eventDescription from MQEvents mq,QueueManager q,ApplicationMapper qa, "
				+ " AppObjects ao,AppMQEventsProperties amp where amp.reasonCode=mq.reasonCode and qa.appQmgrId = q.id and qa.appApplicationId="
				+ new Long(id)
				+ " and ao.appQmgrAppId=qa.id and "
				+ " qa.id="
				+ new Long(mid)
				+ " and  mq.mqQmgrName ='"
				+ qmgrName
				+ "' and "
				+ "  mq.mqObjectName='"
				+ queueName
				+ "' and "
				+ " mq.mqEventHostName='"
				+ hostName
				+ "' and mq.mqObjectType='"
				+ queueType
				+ "' and mq.mqEventInsertedDate BETWEEN STR_TO_DATE('"
				+ startDate
				+ "', '%Y-%m-%d %H:%i:%s') AND STR_TO_DATE('"
				+ endDate
				+ "', '%Y-%m-%d %H:%i:%s') ORDER BY mq.mqEventInsertedDate  DESC";
		System.out.println(queryStr + " :::::::::::::::::::::::::::::::");
		// ORDER BY mq.mqEventInsertedDate DESC
		/*
		 * Query query = session.createQuery(
		 * "SELECT distinct mq.id,mq.mqObjectType,mq.mqObjectName,mq.mqQmgrName,mq.mqObjectStatus,mq.mqObjectDate,mq.mqEventHostName,mq.mqEventInsertedDate,mq.reasonCode from MQEvents mq,QueueManager q,ApplicationMapper qa,AppObjects ao where qa.appQmgrId = q.id and qa.appApplicationId="
		 * +new Long(id)+" and ao.appQmgrAppId=qa.id and qa.id="+new
		 * Long(mid)+" and  mq.mqQmgrName ='"
		 * +qmgrName+"' and "+"  mq.mqObjectName='"
		 * +queueName+"' and mq.mqEventHostName='"
		 * +hostName+"' and mq.mqObjectType='"
		 * +queueType+"' and mq.mqEventInsertedDate BETWEEN '"
		 * +startDate+"' AND '"+endDate+"'");
		 */
		Query query = session.createQuery(queryStr);
		List<Object[]> MqList = query.list();
		for (Object[] objList : MqList) {
			/*
			 * EventWithProperties mqEvents = new EventWithProperties();
			 * mqEvents.setId((long)objList[0]);
			 * mqEvents.setMqObjectType((String)objList[1]);
			 * mqEvents.setMqObjectName((String)objList[2]);
			 * mqEvents.setMqQmgrName((String)objList[3]);
			 * mqEvents.setMqObjectStatus((String)objList[4]);
			 * mqEvents.setMqObjectDate((String)objList[5]);
			 * mqEvents.setMqEventHostName((String)objList[6]);
			 * mqEvents.setMqEventInsertedDate((String)objList[7]);
			 * mqEvents.setReasonCode((int)objList[8]);
			 * mqEvents.setEventlLevel(Long.parseLong((String)objList[9]));
			 * mqEvents.setEventDescription((String)objList[10]);
			 * mqEvent.add(mqEvents); logger.debug("MQEvents :"+ mqEvent);
			 */

			EventMessage eventMessage = new EventMessage();
			eventMessage.setObjectName((String) objList[2]);
			eventMessage.setObjectType((String) objList[1]);
			System.out.println((String) objList[2] + "" + (String) objList[1]);
			eventMessage.setEventType("MQEvents");
			eventMessage.setEventDescription((String) objList[10]);
			eventMessage.setEventLevel(Long.parseLong((String) objList[9]));
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			date = dateFormat.parse((String) objList[5]);
			long time = date.getTime();
			Timestamp timestamp = new Timestamp(time);
			SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss,SSS");
			String result = dateFormat.format(new Date(timestamp.getTime()));
			eventMessage.setEventGeneratedDate(result);
			// eventMessage.setEventGeneratedDate((String)objList[5]);
			eventMessage.setQmgrName((String) objList[3]);
			eventMessage.setHostName((String) objList[6]);
			mqEvent.add(eventMessage);
		}

		return mqEvent;

	}

	@Override
	public List<EventWithProperties> getQmgrEvent(String startDate,
			String endDate, long id, long mid, String qmgrName,
			String queueName, String hostName) throws ParseException {

		logger.info("entered");
		List<EventWithProperties> mqEvent = new ArrayList<EventWithProperties>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("Start Date" + ":" + startDate);
		logger.debug("EndDate" + ":" + endDate);
		logger.debug("Id " + ":" + id);
		logger.debug("QueueManager Name" + ":" + qmgrName);
		logger.debug("Queue Name" + ":" + queueName);
		logger.debug("hostName" + ":" + hostName);

		String queryStr = "SELECT distinct mq.id,mq.mqObjectType,mq.mqObjectName,mq.mqQmgrName,mq.mqObjectStatus,mq.mqObjectDate,mq.mqEventHostName,"
				+ " mq.mqEventInsertedDate,mq.reasonCode, amp.eventlLevel,amp.eventDescription from MQEvents mq, "
				+ " AppMQEventsProperties amp where amp.reasonCode=mq.reasonCode and  mq.mqQmgrName ='"
				+ qmgrName
				+ "' and "
				+ " mq.mqEventHostName='"
				+ hostName
				+ "'"
				+ " and mq.mqEventInsertedDate BETWEEN STR_TO_DATE ('"
				+ startDate
				+ "', '%Y-%m-%d %H:%i:%s') AND STR_TO_DATE ('"
				+ endDate
				+ "', '%Y-%m-%d %H:%i:%s') ORDER BY mq.mqEventInsertedDate DESC";
		System.out.println(queryStr);
		/*
		 * Query query = session.createQuery(
		 * "SELECT distinct mq.id,mq.mqObjectType,mq.mqObjectName,mq.mqQmgrName,mq.mqObjectStatus,mq.mqObjectDate,mq.mqEventHostName,mq.mqEventInsertedDate,mq.reasonCode from MQEvents mq,QueueManager q,AppObjects ao where   q.id="
		 * +new Long(id)+" and   mq.mqQmgrName ='"+qmgrName+"' and "+
		 * "  mq.mqEventHostName='"
		 * +hostName+"'  and mq.mqEventInsertedDate BETWEEN '"
		 * +startDate+"' AND '"+endDate+"'");
		 */
		Query query = session.createQuery(queryStr);
		List<Object[]> MqList = query.list();
		for (Object[] objList : MqList) {
			EventWithProperties mqEvents = new EventWithProperties();
			mqEvents.setId((long) objList[0]);
			mqEvents.setMqObjectType((String) objList[1]);
			mqEvents.setMqObjectName((String) objList[2]);
			mqEvents.setMqQmgrName((String) objList[3]);
			mqEvents.setMqObjectStatus((String) objList[4]);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			date = dateFormat.parse((String) objList[5]);
			long time = date.getTime();
			Timestamp timestamp = new Timestamp(time);
			SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss,SSS");
			String result = dateFormat.format(new Date(timestamp.getTime()));
			mqEvents.setMqObjectDate(result);
			// mqEvents.setMqObjectDate((String)objList[5]);
			mqEvents.setMqEventHostName((String) objList[6]);
			mqEvents.setMqEventInsertedDate((String) objList[7]);
			mqEvents.setReasonCode((int) objList[8]);
			mqEvents.setEventLevel(Long.parseLong((String) objList[9]));
			mqEvents.setEventDescription((String) objList[10]);
			mqEvent.add(mqEvents);
			logger.debug("MQEvents :" + mqEvent);
			/*
			 * mqEvents.setMqObjectDate((String)objList[5]);
			 * mqEvents.setMqEventHostName((String)objList[6]);
			 * mqEvents.setMqEventInsertedDate((String)objList[7]);
			 * mqEvents.setReasonCode((int)objList[8]);
			 * mqEvents.setEventLevel(Long.parseLong((String)objList[9]));
			 * mqEvents.setEventDescription((String)objList[10]);
			 * mqEvent.add(mqEvents); logger.debug("MQEvents :"+ mqEvent);
			 */
		}

		return mqEvent;
	}

	@Override
	public List<QueueManagerStatus> getQueueManagerStatus(String qName,
			String hostName) throws Exception {
		logger.info("entered");
		List<QueueManagerStatus> qmgrStatus = new ArrayList<QueueManagerStatus>();
		// Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getQueueManagers By name" + ":" + qName);
		logger.debug("getQueueManagers By hostName" + ":" + hostName);
		String queryStr = "SELECT qm.id,qm.qmName,qm.hostName,qm.upTime,qm.downTime,qm.flagStatus,qm.lastUpdatedTime from QueueManagerStatus qm where qm.qmName='"
				+ qName + "' and qm.hostName='" + hostName + "'";
		System.out.println(queryStr);
		Query query = session.createQuery(queryStr);
		List<Object[]> qmgrList = query.list();
		for (Object[] queueManager : qmgrList) {
			QueueManagerStatus qmStatus = new QueueManagerStatus();
			qmStatus.setId((long) queueManager[0]);
			qmStatus.setQmName((String) queueManager[1]);
			qmStatus.setHostName((String) queueManager[2]);
			qmStatus.setUpTime((String) queueManager[3]);
			qmStatus.setDownTime((String) queueManager[4]);
			qmStatus.setFlagStatus((int) queueManager[5]);
			qmStatus.setLastUpdatedTime((String) queueManager[6]);
			qmgrStatus.add(qmStatus);
			logger.debug("App Queue Manager List :" + qmStatus);
		}
		// session.close();
		return qmgrStatus;
	}

	@Override
	public List<EventMessage> getFileEvents(String startDate, String endDate,
			long id) throws ParseException {
		logger.info("entered");
		List<EventMessage> appFSEventsList = new ArrayList<EventMessage>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getFileEvents By Id" + ":" + id);
		logger.debug("startDate" + ":" + startDate);
		logger.debug("endDate" + ":" + endDate);
		Query query = session
				.createQuery("select afe.fsObjectType,afe.fsObjectName,afe.fsHost,afe.fsEventDate,afe.fsEventLevel,afe.reasonCode,afe.thresholdLimit,afe.currentUsage,af.fsPath,af.fsMount from FSEvents afe,FileSystem af,FileSystemAppMapper afm where afm.appApplicationId='"
						+ new Long(id)
						+ "'and afm.appFSId=af.id and af.fsName=afe.fsObjectName and afe.fsHost=af.fsHost and afe.fsEventDate BETWEEN STR_TO_DATE('"
						+ startDate
						+ "', '%Y-%m-%d %H:%i:%s') AND STR_TO_DATE('"
						+ endDate
						+ "', '%Y-%m-%d %H:%i:%s') ORDER BY afe.fsEventDate DESC");
		System.out.println(query);
		@SuppressWarnings("unchecked")
		List<Object[]> fsList = query.list();
		for (Object[] fileSystem : fsList) {
			/*
			 * EventWithProperties appFileEvents = new EventWithProperties();
			 * appFileEvents.setMqObjectType((String)fileSystem[0]);
			 * appFileEvents.setMqObjectName((String)fileSystem[1]);
			 * appFileEvents.setMqEventHostName((String)fileSystem[2]);
			 * appFileEvents.setMqObjectDate((String)fileSystem[3]);
			 * appFileEvents
			 * .setEventlLevel(Long.parseLong((String)fileSystem[4]));
			 * appFileEvents
			 * .setReasonCode(Integer.parseInt((String)fileSystem[5]));
			 * appFileEvents.setEventDescription((String)fileSystem[6]+(String)
			 * fileSystem[7]); appFileEvents.setFsPath((String) fileSystem[8]);
			 * appFileEvents.setFsMount((String) fileSystem[9]);
			 * appFSEventsList.add(appFileEvents);
			 * logger.debug("FileEventsList :"+ appFSEventsList);
			 */
			EventMessage eventMessage = new EventMessage();
			eventMessage.setObjectName((String) fileSystem[1]);
			eventMessage.setObjectType((String) fileSystem[0]);
			System.out.println((String) fileSystem[2] + ""
					+ (String) fileSystem[1]);
			eventMessage.setEventType("FileSystemEvents");
			eventMessage.setEventDescription("Threshold Limit :"
					+ (String) fileSystem[6] + " Current Usage :"
					+ (String) fileSystem[7]);
			eventMessage.setEventLevel(Long.parseLong((String) fileSystem[4]));
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			date = dateFormat.parse((String) fileSystem[3]);
			long time = date.getTime();
			Timestamp timestamp = new Timestamp(time);
			SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss,SSS");
			String result = dateFormat.format(new Date(timestamp.getTime()));
			eventMessage.setEventGeneratedDate(result);
			// eventMessage.setEventGeneratedDate((String)fileSystem[3]);
			eventMessage.setFsPath((String) fileSystem[8]);
			eventMessage.setHostName((String) fileSystem[2]);
			appFSEventsList.add(eventMessage);
			logger.debug("FileEventsList :" + appFSEventsList);
		}
		return appFSEventsList;
	}

	@Override
	public List<EventMessage> getProcessEvents(String startDate,
			String endDate, long id) throws ParseException {
		logger.info("entered");
		List<EventMessage> appPSEventsList = new ArrayList<EventMessage>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getProcessEvents By Id" + ":" + id);
		logger.debug("startDate" + ":" + startDate);
		logger.debug("endDate" + ":" + endDate);
		Query query = session
				.createQuery("select pe.psObjectType,pe.psObjectName,pe.psHostName,pe.psEventDate,pe.psEventLevel,pe.resonCode,pe.thresholdLimit,pe.currentUsage,p.psFilter,p.psMin,p.psMax from PSEvents pe,Process p,ProcessAppMapper pam where pam.appApplicationId='"
						+ new Long(id)
						+ "'and pam.appPSId=p.id and p.psName=pe.psObjectName and pe.psHostName=p.psHost and pe.psEventDate BETWEEN STR_TO_DATE('"
						+ startDate
						+ "', '%Y-%m-%d %H:%i:%s') AND STR_TO_DATE ('"
						+ endDate
						+ "', '%Y-%m-%d %H:%i:%s') ORDER BY pe.psEventDate");
		System.out.println(query);
		List<Object[]> psList = query.list();
		for (Object[] process : psList) {
			EventMessage eventMessage = new EventMessage();
			eventMessage.setObjectType((String) process[0]);
			eventMessage.setObjectName((String) process[1]);
			eventMessage.setHostName((String) process[2]);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			date = dateFormat.parse((String) process[3]);
			long time = date.getTime();
			Timestamp timestamp = new Timestamp(time);
			SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss,SSS");
			String result = dateFormat.format(new Date(timestamp.getTime()));
			eventMessage.setEventGeneratedDate(result);
			// eventMessage.setEventGeneratedDate((String)process[3]);
			eventMessage.setEventLevel(Long.parseLong((String) process[4]));
			eventMessage.setEventDescription("Threshold Limit :"
					+ (String) process[6] + " Current Usage :"
					+ (String) process[7]);
			eventMessage.setEventType("processEvents");
			appPSEventsList.add(eventMessage);
			logger.debug("ProcessEventsList :" + appPSEventsList);
		}
		return appPSEventsList;
	}

	@Override
	@Transactional
	public boolean deleteQueueManagerDetails(long id) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		try {
			logger.debug("deleteing QueueManager details data");
			/*
			 * Query queryAppQMId = session.createSQLQuery(
			 * "select APP_QMGR_APP_MAPPER_ID from APP_QMGR_APP_MAPPER where APP_QMGR_ID = "
			 * + new Long(id)); List qmAppList = queryAppQMId.list();
			 * logger.debug("Application Queue Manager Mapper list "+qmAppList);
			 * 
			 * for (int i = 0; i < qmAppList.size(); i++) {
			 * logger.debug("deleting APP_QMGR_APP_MAPPER_ID  id "+
			 * qmAppList.get(i)); BigDecimal bigInt = (BigDecimal)
			 * qmAppList.get(i); logger.debug(
			 * " Delete from  APP_QMGR_APP_MAPPER  appQM where appQM.APP_QMGR_APP_MAPPER_ID="
			 * + bigInt); Query queryAppQM = session.createSQLQuery(
			 * "Delete from  APP_QMGR_APP_MAPPER  appQM where appQM.APP_QMGR_APP_MAPPER_ID="
			 * + bigInt); int appQMRes = queryAppQM.executeUpdate();
			 * logger.debug("deleted APP_QMGR_APP_MAPPER_ID res "+appQMRes); }
			 */
			logger.debug("deleteing QueueManager details from APP_QMGR_APP_MAPPER");
			logger.debug(" Delete from  APP_QMGR_APP_MAPPER  appQM where appQM.APP_QMGR_ID = "
					+ new Long(id));
			Query queryAppQM = session
					.createSQLQuery("Delete from  APP_QMGR_APP_MAPPER  appQM where appQM.APP_QMGR_ID = "
							+ new Long(id));
			int appQMRes = queryAppQM.executeUpdate();
			logger.debug("deleted APP_QMGR_ID res " + appQMRes);

			logger.debug("deleteing QueueManager details from APP_QMGR");
			Query queryQM = session
					.createSQLQuery("Delete from APP_QMGR qm where qm.APP_QMGR_ID = "
							+ new Long(id));
			int result = queryQM.executeUpdate();
			logger.debug("deleted QueueManager result " + result);
			if (result > 0) {
				logger.debug("Returning deleted true");
				return true;
			}

		} catch (Exception e) {
			logger.debug("Returning deleted false");

			e.printStackTrace();
			return false;
		}
		logger.debug("Returning deleted false");
		return false;
	}

}
