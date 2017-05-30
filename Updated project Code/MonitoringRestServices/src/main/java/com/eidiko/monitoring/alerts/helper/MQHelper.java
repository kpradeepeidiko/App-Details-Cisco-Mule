package com.eidiko.monitoring.alerts.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;


import org.apache.log4j.Logger;

import com.eidiko.monitoring.alerts.model.QueueStatus;
import com.eidiko.monitoring.alerts.model.Status;
import com.ibm.mq.MQC;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.headers.pcf.PCFMessage;
import com.ibm.mq.headers.pcf.PCFMessageAgent;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.pcf.MQCFH;
import com.ibm.mq.pcf.MQCFSL;
import com.ibm.mq.pcf.PCFAgent;
import com.ibm.mq.pcf.PCFParameter;
import com.ibm.mq.constants.CMQCFC;

public class MQHelper {
	public static Logger logger = Logger.getLogger(MQHelper.class);

	public static String getQueueManagerName(int port, String hostName,
			String channelName) {
		logger.info("entered");
	PCFAgent pcfAgent;
		String qmgrName = "";
		try {
			// pcfAgent = new PCFAgent(hostName, port, channelName);
			pcfAgent = new PCFAgent(hostName, port, channelName);
			qmgrName = pcfAgent.getQManagerName();
		} catch (Exception e) {
			logger.error("exception occured");
			e.printStackTrace();
		}
		return qmgrName;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static MQQueueManager getQueueManager(String qManager, int port_num,
			String hostName, String channelName) {
		logger.info("entered");
		try {
			Hashtable props = new Hashtable();

			props.put(CMQC.HOST_NAME_PROPERTY, hostName);
			props.put(CMQC.PORT_PROPERTY, port_num);
			props.put(CMQC.CHANNEL_PROPERTY, channelName);
			MQQueueManager qMgr = null;

			qMgr = new MQQueueManager(qManager, props);
			return qMgr;
		} catch (MQException e) {
			logger.error("exception occured");
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			logger.error("exception occured");
			e.printStackTrace();
			return null;
		}

	}

	public static void getQueueNames(MQQueueManager qMgr,
			List<QueueDetails> queueDetailsList) throws MQException,
			IOException {
		logger.info("entered");
		// MQQueueManager qMgr;
		// qMgr = new MQQueueManager("QM");
		List<QueueDetails> queueDetails = new ArrayList<QueueDetails>();
		System.out.println("quemanager ::::::::::" + qMgr);
		PCFAgent agent = new PCFAgent(qMgr);
		MQMessage[] responses;
		PCFParameter[] parameters = {
				new com.ibm.mq.pcf.MQCFST(CMQC.MQCA_Q_NAME, "*"),
				new com.ibm.mq.pcf.MQCFIN(CMQC.MQIA_Q_TYPE, MQC.MQQT_LOCAL) };
		MQCFH cfh;
		MQCFSL cfsl;
		responses = agent.send(CMQCFC.MQCMD_INQUIRE_Q_NAMES, parameters);
		cfh = new MQCFH(responses[0]);
		if (cfh.reason == 0) {
			cfsl = new MQCFSL(responses[0]);
			for (int i = 0; i < cfsl.strings.length; i++) {
				QueueDetails details = new QueueDetails();
				if (!cfsl.strings[i].trim().startsWith("SYSTEM")
						&& !cfsl.strings[i].trim().startsWith("AMQ")) {
					details.setObjectName(cfsl.strings[i].trim());
					details.setObjectType("queue");

					System.out.println("Queue: " + cfsl.strings[i].trim());

					queueDetailsList.add(details);
				}
			}
		}
		// return queueDetails;
	}

	public static void getChannelNames(MQQueueManager qMgr,
			List<QueueDetails> queueDetailsList) throws Exception {
		logger.info("entered");
		// MQQueueManager qMgr;
		// qMgr = new MQQueueManager("QM");

		PCFAgent agent = new PCFAgent(qMgr);
		MQMessage[] responses;
		PCFParameter[] parameters = { new com.ibm.mq.pcf.MQCFST(
				CMQCFC.MQCACH_CHANNEL_NAME, "*"),
		// new com.ibm.mq.pcf.MQCFIN (CMQC.MQIA_Q_TYPE, MQC.MQTT_EVERY)
		};
		MQCFH cfh;
		MQCFSL cfsl;
		responses = agent.send(CMQCFC.MQCMD_INQUIRE_CHANNEL_NAMES, parameters);
		cfh = new MQCFH(responses[0]);
		System.out.println(cfh.reason);
		if (cfh.reason == 0) {
			cfsl = new MQCFSL(responses[0]);
			for (int i = 0; i < cfsl.strings.length; i++) {
				QueueDetails details = new QueueDetails();
				// if(!cfsl.strings[i].trim().startsWith("SYSTEM")){
				details.setObjectName(cfsl.strings[i].trim());
				details.setObjectType("channel");

				System.out.println("channel: " + cfsl.strings[i].trim());

				queueDetailsList.add(details);
			}
		}
		logger.info("exiting");
	}

	public static QueueStatus inquireQueue(MQQueueManager qMgr,String queueName) throws Exception {

		QueueStatus queueStatus=new QueueStatus();
		PCFAgent agent = new PCFAgent(qMgr);
		PCFMessage[] responses;
	
		PCFMessageAgent pcfAgent = new PCFMessageAgent(qMgr);
		PCFMessage inquireQueueStatus = new PCFMessage(
				CMQCFC.MQCMD_INQUIRE_Q_STATUS);
		inquireQueueStatus.addParameter(CMQC.MQCA_Q_NAME,queueName);
		inquireQueueStatus.addParameter(CMQCFC.MQIACF_Q_STATUS_TYPE,
				CMQCFC.MQIACF_Q_STATUS);
		inquireQueueStatus.addParameter(CMQCFC.MQIACF_Q_STATUS_ATTRS,
				new int[] {
				/*
				 * CMQC.MQCA_Q_NAME, CMQC.MQIA_CURRENT_Q_DEPTH,
				 * CMQCFC.MQCACF_LAST_GET_DATE, CMQCFC.MQCACF_LAST_GET_TIME,
				 * CMQCFC.MQCACF_LAST_PUT_DATE, CMQCFC.MQCACF_LAST_PUT_TIME,
				 * CMQCFC.MQIACF_OLDEST_MSG_AGE, CMQC.MQIA_OPEN_INPUT_COUNT,
				 * CMQC.MQIA_OPEN_OUTPUT_COUNT,
				 * CMQCFC.MQIACF_UNCOMMITTED_MSGS,CMQCFC.MQIACF_Q_TIME_INDICATOR
				 * , CMQC.MQIA_APPL_TYPE
				 */CMQCFC.MQIACF_ALL });
		responses = pcfAgent.send(inquireQueueStatus);
		for (PCFMessage p : responses) {// MQCACF_APPL_TAG

			p.getIntParameterValue(CMQC.MQIA_CURRENT_Q_DEPTH);

			System.out.println("Queue Name: "
					+ p.getParameterValue(CMQC.MQCA_Q_NAME));
			System.out.println("MQIA_CURRENT_Q_DEPTH: "
					+ p.getParameterValue(CMQC.MQIA_CURRENT_Q_DEPTH));
			System.out.println("MQCACF_LAST_GET_DATE: "
					+ p.getParameterValue(CMQCFC.MQCACF_LAST_GET_DATE));
			System.out.println("MQCACF_LAST_GET_TIME: "
					+ p.getParameterValue(CMQCFC.MQCACF_LAST_GET_TIME));
			System.out.println("MQCACF_LAST_PUT_DATE: "
					+ p.getParameterValue(CMQCFC.MQCACF_LAST_PUT_DATE));
			System.out.println("MQCACF_LAST_PUT_TIME: "
					+ p.getParameterValue(CMQCFC.MQCACF_LAST_PUT_TIME));
			System.out.println("MQIACF_OLDEST_MSG_AGE: "
					+ p.getParameterValue(CMQCFC.MQIACF_OLDEST_MSG_AGE));
			System.out.println("MQIA_OPEN_INPUT_COUNT: "
					+ p.getParameterValue(CMQC.MQIA_OPEN_INPUT_COUNT));
			System.out.println("MQIA_OPEN_OUTPUT_COUNT: "
					+ p.getParameterValue(CMQC.MQIA_OPEN_OUTPUT_COUNT));
			System.out.println("MQIACF_UNCOMMITTED_MSGS: "
					+ p.getParameterValue(CMQCFC.MQIACF_UNCOMMITTED_MSGS));
			System.out.println("MQIACF_Q_TIME_INDICATOR: "
					+ p.getParameterValue(CMQCFC.MQIACF_Q_TIME_INDICATOR));
			System.out.println("MQIA_APPL_TYPE: "
					+ p.getParameterValue(CMQC.MQIA_APPL_TYPE));
			System.out.println("MQCACF_MEDIA_LOG_EXTENT_NAME: "
					+ p.getParameterValue(CMQCFC.MQCACF_MEDIA_LOG_EXTENT_NAME));

			System.out.println("MQIA_MONITORING_Q: "
					+ p.getParameterValue(CMQC.MQIA_MONITORING_Q));
			System.out.println("MQIACF_HANDLE_STATE: "
					+ p.getParameterValue(CMQCFC.MQIACF_HANDLE_STATE));
			System.out.println("MQIA_MONITORING_Q: "
					+ p.getParameterValue(CMQC.MQIA_MONITORING_Q));
			System.out.println("MQBACF_EXTERNAL_UOW_ID: "
					+ p.getParameterValue(CMQCFC.MQBACF_EXTERNAL_UOW_ID));

			System.out.println("MQBACF_Q_MGR_UOW_ID: "
					+ p.getParameterValue(CMQCFC.MQBACF_Q_MGR_UOW_ID));
			System.out.println("MQCACF_APPL_TAG: "
					+ p.getParameterValue(CMQCFC.MQCACF_APPL_TAG));

			System.out.println("MQCACF_ASID: "
					+ p.getParameterValue(CMQCFC.MQCACF_ASID));
			System.out.println("MQCACF_PSB_NAME: "
					+ p.getParameterValue(CMQCFC.MQCACF_PSB_NAME));

			System.out.println("MQCACF_TASK_NUMBER: "
					+ p.getParameterValue(CMQCFC.MQCACF_TASK_NUMBER));
			System.out.println("MQCACF_TRANSACTION_ID: "
					+ p.getParameterValue(CMQCFC.MQCACF_TRANSACTION_ID));

			System.out.println("MQCACF_USER_IDENTIFIER: "
					+ p.getParameterValue(CMQCFC.MQCACF_USER_IDENTIFIER));
			System.out.println("MQCACH_CHANNEL_NAME: "
					+ p.getParameterValue(CMQCFC.MQCACH_CHANNEL_NAME));

			System.out.println("MQCACH_CONNECTION_NAME: "
					+ p.getParameterValue(CMQCFC.MQCACH_CONNECTION_NAME));
			System.out.println("MQIACF_OPEN_BROWSE: "
					+ p.getParameterValue(CMQCFC.MQIACF_OPEN_BROWSE));

			System.out.println("MQIACF_OPEN_INPUT_TYPE: "
					+ p.getParameterValue(CMQCFC.MQIACF_OPEN_INPUT_TYPE));
			System.out.println("MQIACF_OPEN_INQUIRE: "
					+ p.getParameterValue(CMQCFC.MQIACF_OPEN_INQUIRE));

			System.out.println("MQIACF_OPEN_OUTPUT: "
					+ p.getParameterValue(CMQCFC.MQIACF_OPEN_OUTPUT));
			System.out.println("MQIACF_OPEN_SET: "
					+ p.getParameterValue(CMQCFC.MQIACF_OPEN_SET));

			System.out.println("MQIACF_PROCESS_ID: "
					+ p.getParameterValue(CMQCFC.MQIACF_PROCESS_ID));
			System.out.println("MQIACF_ASYNC_STATE: "
					+ p.getParameterValue(CMQCFC.MQIACF_ASYNC_STATE));
			System.out.println("MQIACF_THREAD_ID: "
					+ p.getParameterValue(CMQCFC.MQIACF_THREAD_ID));
			System.out.println("MQIACF_UOW_TYPE: "
					+ p.getParameterValue(CMQCFC.MQIACF_UOW_TYPE));

			System.out.println("MQIACF_Q_STATUS: "
					+ p.getParameterValue(CMQCFC.MQIACF_Q_STATUS));
			System.out.println("MQIACF_Q_HANDLE: "
					+ p.getParameterValue(CMQCFC.MQIACF_Q_HANDLE));
			
			
			queueStatus.setQueueName(p.getStringParameterValue(CMQC.MQCA_Q_NAME));
			queueStatus.setQueueCurrentDepth(p.getIntParameterValue(CMQC.MQIA_CURRENT_Q_DEPTH));
			queueStatus.setApplicationType(p.getParameterValue(CMQC.MQIA_APPL_TYPE)+"");
			queueStatus.setApplicationTag(p.getParameterValue(CMQCFC.MQCACF_APPL_TAG)+"");
			queueStatus.setChannelName(p.getParameterValue(CMQCFC.MQCACH_CHANNEL_NAME)+"");
			queueStatus.setConnectionName(p.getParameterValue(CMQCFC.MQCACH_CONNECTION_NAME)+"");
			queueStatus.setOpenInputCount(p.getIntParameterValue(CMQC.MQIA_OPEN_INPUT_COUNT));
			queueStatus.setOpenOutputCount(p.getIntParameterValue(CMQC.MQIA_OPEN_OUTPUT_COUNT));
			queueStatus.setProcessId(p.getParameterValue(CMQCFC.MQIACF_PROCESS_ID)+"");
		}
		return queueStatus;
	}
	
	/*
	
	 public static void sendingMail(String to,String body)
	   {    
	      // Recipient's email ID needs to be mentioned.
		 Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("username","password");
					}
				});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("from@no-spam.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse("to@no-spam.com"));
				message.setSubject("Testing Subject");
				message.setText("Dear Mail Crawler," +
						"\n\n No spam to my email, please!");

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	   }*/
}
