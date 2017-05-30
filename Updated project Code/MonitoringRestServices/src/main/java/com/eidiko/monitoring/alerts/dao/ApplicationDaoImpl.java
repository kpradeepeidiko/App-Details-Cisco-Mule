package com.eidiko.monitoring.alerts.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.eidiko.monitoring.alerts.controller.ApplicationController;
import com.eidiko.monitoring.alerts.model.AppObjects;
import com.eidiko.monitoring.alerts.model.AppUsers;
import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.Messages;
import com.eidiko.monitoring.alerts.model.Status;
import com.eidiko.monitoring.alerts.model.User;

@Transactional
public class ApplicationDaoImpl extends BaseDaoImpl implements ApplicationDao {

	public static final Logger logger = Logger
			.getLogger(ApplicationDaoImpl.class);

	// @Autowired
	// SessionFactory sessionFactory;
	@Override
	public List<Application> getApplicationList(User user) {
		logger.info("entered");
		Session session = getSessionFactory().getCurrentSession();
		if (user.getFlag().equalsIgnoreCase("true")) {

			return session.createCriteria(Application.class).list();
		} else {
			String strQuery = "select ap.id, ap.name, ap.emailFlag, ap.smsFlag from com.eidiko.monitoring.alerts.model.Application  ap ,com.eidiko.monitoring.alerts.model.UserApplicationMapper  apm  where ap.id = apm.appapplicationId  and apm.appuserId="
					+ user.getId();
			Query queryMq = session.createQuery(strQuery);
			List<Object[]> objList = queryMq.list();
			List<Application> applicationList = new ArrayList<Application>();
			for (Object[] object : objList) {
				System.out.println(object[0]);
				Application application = new Application();
				application.setId((long) object[0]);
				application.setName((String) object[1]);
				application.setEmailFlag((String) object[2]);
				application.setSmsFlag((String) object[3]);

				applicationList.add(application);
				logger.debug("User List :" + applicationList);
			}
			return applicationList;
		}
	}

	@Override
	@Transactional
	public boolean deleteApplicationDetails(long id) throws Exception {
		// Session session = getSessionFactory().openSession();
		Session session = getSessionFactory().getCurrentSession();

		// Transaction tx = session.getTransaction();
		// session.beginTransaction();
		try {
			logger.debug("deleteing Application details data");
			Query query = session
					.createSQLQuery("select APP_QMGR_APP_MAPPER_ID from APP_QMGR_APP_MAPPER where app_application_id = "
							+ new Long(id));
			List list = query.list();
			logger.debug("app_qmgr_application_mapper list " + list);
			for (int i = 0; i < list.size(); i++) {
				logger.debug("deleting app_mq_objects id " + list.get(i));
				BigDecimal bigInt = (BigDecimal) list.get(i);
				logger.debug("********* Delete from  app_mq_objects  mqObj where mqObj.APP_QMGR_APP_APPLICATION_ID="
						+ bigInt);
				Query queryMq = session
						.createSQLQuery("Delete from  app_mq_objects  mqObj where mqObj.APP_QMGR_APP_APPLICATION_ID="
								+ bigInt);
				int res = queryMq.executeUpdate();
				logger.debug("deleted app_mq_objects res " + res);

			}
			logger.debug("deleting qmgrMapper app_application_id " + id);
			Query queryQmgr = session
					.createSQLQuery("Delete from  APP_QMGR_APP_MAPPER  qmgrMapper where qmgrMapper.app_application_id="
							+ new Long(id));
			int resQmgrMapper = queryQmgr.executeUpdate();
			logger.debug("deleted qmgrMapper resQmgrMapper " + resQmgrMapper);
			logger.debug("deleting APP_USER_APP_MAPPER app_application_id "
					+ id);
			Query queryUser = session
					.createSQLQuery("Delete from   APP_USER_APP_MAPPER  userMapper where userMapper.app_application_id="
							+ new Long(id));
			int resUserMapper = queryUser.executeUpdate();
			logger.debug("deleted APP_USER_APP_MAPPER resUserMapper "
					+ resUserMapper);

			logger.debug("*********************deleteing APP_FS_APPLICATION_MAPPER_ID details from APP_FS_APPLICATION_MAPPER");
			logger.debug(" Delete from  APP_FS_APPLICATION_MAPPER  appFS where appFS.APP_APPLICATION_ID = "
					+ new Long(id));
			Query queryAppFS = session
					.createSQLQuery("Delete from  APP_FS_APPLICATION_MAPPER  appFS where appFS.APP_APPLICATION_ID = "
							+ new Long(id));
			int appFSRes = queryAppFS.executeUpdate();
			logger.debug("deleted APP_FS_APPLICATION_MAPPER_ID res " + appFSRes);

			logger.debug("deleting app_application app_id" + id);
			Query queryApp = session
					.createSQLQuery("Delete from   app_application  app where app.APP_APPLICATION_ID="
							+ new Long(id));
			int resApp = queryApp.executeUpdate();
			logger.debug("deleted APP_USER_APP_MAPPER resApp " + resApp);
			// tx.commit();

			if (resApp > 0) {
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
