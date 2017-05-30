package com.eidiko.monitoring.alerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.eidiko.monitoring.alerts.model.AppObjects;
import com.eidiko.monitoring.alerts.model.AppQueueMangers;
import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.QueueManager;

@Transactional
public class AppObjectsDaoImpl extends BaseDaoImpl implements AppObjectsDao {
	public static final Logger logger = Logger
			.getLogger(AppObjectsDaoImpl.class);

	@Override
	public List<AppObjects> getAppObjectsList() {
		logger.info("entered");
		return getSessionFactory().getCurrentSession()
				.createCriteria(AppObjects.class).list();
	}

	@Override
	public List<AppObjects> getAppObjectsById(long id) throws Exception {
		Session session = getSessionFactory().getCurrentSession();

		List<AppObjects> appObjects = new ArrayList<AppObjects>();
		logger.debug("getAppObjects By Id" + ":" + id);
		Query query = session
				.createQuery("SELECT q.id,q.objectName,q.objectType,q.appQmgrAppId from AppObjects q where q.appQmgrAppId ="
						+ id);

		List<Object[]> appList = query.list();

		for (Object[] queueManager : appList) {
			AppObjects appObj = new AppObjects();
			appObj.setId((Long) queueManager[0]);
			appObj.setObjectName((String) queueManager[1]);
			appObj.setObjectType((String) queueManager[2]);
			appObj.setAppQmgrAppId((Long) queueManager[3]);
			appObjects.add(appObj);
			logger.debug("App Object List :" + appObjects);
		}
		// session.close();
		return (List<AppObjects>) appObjects;
	}

	@Override
	public QueueManager getQueueManger(AppQueueMangers appqmgr) {
		Session session = getSessionFactory().getCurrentSession();
		String strQuery = "select qm.host,qm.listnerPort,qm.name,qm.svrConn ,qm.id FROM com.eidiko.monitoring.alerts.model.QueueManager qm,com.eidiko.monitoring.alerts.model.ApplicationMapper qam where  qm.id =qam.appQmgrId and qam.id="
				+ appqmgr.getMapperId();
		System.out.println(strQuery + "");
		Query query = session.createQuery(strQuery);
		Object[] object = (Object[]) query.uniqueResult();
		QueueManager queueManager = null;
		if (object != null) {
			queueManager = new QueueManager();
			queueManager.setHost((String) object[0]);
			queueManager.setListnerPort((int) object[1]);
			queueManager.setName((String) object[2]);
			queueManager.setSvrConn((String) object[3]);
			queueManager.setId((long) object[4]);
		}
		logger.debug("Quemanager  :" + queueManager);
		return queueManager;
	}

}
