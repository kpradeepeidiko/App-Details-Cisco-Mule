package com.eidiko.monitoring.alerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.QueueManager;
import com.eidiko.monitoring.alerts.model.UserApplicationMapper;

@Transactional
public class UserApplicationMapperDaoImpl extends BaseDaoImpl implements
		UserApplicationMapperDao {
	public static final Logger logger = Logger
			.getLogger(UserApplicationMapperDaoImpl.class);

	@Override
	public List<UserApplicationMapper> getUserApplicationMapperList() {
		logger.info("entered");
		return getSessionFactory().getCurrentSession()
				.createCriteria(UserApplicationMapper.class).list();

	}

	@Override
	public List<UserApplicationMapper> getApplicationsByUserId(long id) {

		List<UserApplicationMapper> appList = new ArrayList<UserApplicationMapper>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getApplications By Id" + ":" + id);
		Query query = session
				.createQuery("SELECT qa.appapplicationId from com.eidiko.monitoring.alerts.model.UserApplicationMapper qa where qa.appuserId ="
						+ id);
		appList = query.list();
		System.out.println("appList............. " + appList);

		return appList;
	}

	@Override
	public List<Application> getQuemanagerId(long id) {

		List<Application> appList = new ArrayList<Application>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getApplications By Id" + ":" + id);
		Query query = session
				.createQuery("SELECT qa.appQmgrId from com.eidiko.monitoring.alerts.model.ApplicationMapper qa where qa.appApplicationId ="
						+ id);
		appList = query.list();

		return appList;
	}

	@Override
	public List<QueueManager> getQueueManagerDetails(List list) {

		List<QueueManager> qmDetails = new ArrayList<QueueManager>();
		Session session = sessionFactory.getCurrentSession();

		for (int k = 0; k < list.size(); k++) {

			logger.debug("getApplications By Id" + ":" + list.get(k));
			Query query = session
					.createQuery("SELECT qm.id, qm.name,qm.host,qm.svrConn,qm.listnerPort,qm.mcaUser,qm.status from com.eidiko.monitoring.alerts.model.QueueManager qm where qm.id ="
							+ list.get(k));
			List<Object[]> appList = query.list();

			for (Object[] qm : appList) {
				QueueManager queueManager = new QueueManager();
				queueManager.setId((long) qm[0]);
				queueManager.setName((String) qm[1]);
				queueManager.setHost((String) qm[2]);
				queueManager.setSvrConn((String) qm[3]);
				queueManager.setListnerPort((int) qm[4]);
				queueManager.setMcaUser((String) qm[5]);
				queueManager.setStatus((String) qm[6]);
				qmDetails.add(queueManager);

				logger.debug("QueueManager List :" + qmDetails);
			}
		}

		return qmDetails;
	}

}
