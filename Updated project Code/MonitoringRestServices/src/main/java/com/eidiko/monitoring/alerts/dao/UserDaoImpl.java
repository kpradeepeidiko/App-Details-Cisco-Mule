package com.eidiko.monitoring.alerts.dao;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.eidiko.monitoring.alerts.model.AppUsers;
import com.eidiko.monitoring.alerts.model.NBUsers;
import com.eidiko.monitoring.alerts.model.NightlyBatchEvents;
import com.eidiko.monitoring.alerts.model.User;

@Transactional
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	public static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Override
	public List<User> getUserList() {
		logger.info("entered");
		return getSessionFactory().getCurrentSession()
				.createCriteria(User.class).list();
	}

	@Override
	public List<AppUsers> getUserDetails(long id) throws Exception {
		logger.info("entered");
		List<AppUsers> appUserList = new ArrayList<AppUsers>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getUserDetails" + ":" + id);
		Query query = session
				.createQuery("SELECT u.id,u.name,u.email,u.mobileNo,ua.id from User u,UserApplicationMapper ua where ua.appuserId=u.id and ua.appapplicationId="
						+ new Long(id));
		List<Object[]> userList = query.list();

		for (Object[] user : userList) {
			System.out.println(user[0]);
			AppUsers appUser = new AppUsers();
			appUser.setId((long) user[0]);
			appUser.setName((String) user[1]);
			appUser.setEmail((String) user[2]);
			appUser.setMobileNo((String) user[3]);
			appUser.setUserMapperId((long) user[4]);
			appUserList.add(appUser);
			logger.debug("User List :" + appUserList);
		}

		return appUserList;
	}

	@Override
	public List<NBUsers> getNBUserDetails() throws Exception {
		logger.info("entered");
		List<NBUsers> nbUserList = new ArrayList<NBUsers>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getNBUserDetails" + ":");
		Query query = session
				.createQuery("SELECT u.id,u.name,ua.nbid from User u,UserNBMapper ua where ua.nbuserid=u.id");
		List<Object[]> nightlyBatchUserList = query.list();

		for (Object[] user : nightlyBatchUserList) {
			System.out.println(user[0]);
			NBUsers nbUser = new NBUsers();
			nbUser.setId((long) user[0]);
			nbUser.setName((String) user[1]);
			nbUser.setUsersMapperId((long) user[2]);
			nbUserList.add(nbUser);
			logger.debug("User List :" + nbUserList);
		}

		return nbUserList;
	}

	@Override
	@Transactional
	public boolean deleteUsersDetails(long id) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		try {
			/*
			 * logger.debug("deleteing Users details data"); Query query =
			 * session.createSQLQuery(
			 * "select NIGHTLY_USER_ID from APP_NIGHTLY_USERS where USER_ID = "+
			 * new Long(id)); List userslist = query.list();
			 * logger.debug("Nightly User Mapper list "+userslist);
			 * 
			 * for (int i = 0; i < userslist.size(); i++) {
			 * logger.debug("deleting NIGHTLY_USER_ID  id "+ userslist.get(i));
			 * BigDecimal bigInt = (BigDecimal) userslist.get(i); logger.debug(
			 * " Delete from  APP_NIGHTLY_USERS  nightlyUsers where nightlyUsers.NIGHTLY_USER_ID="
			 * + bigInt); Query queryNU = session.createSQLQuery(
			 * "Delete from  APP_NIGHTLY_USERS  nightlyUsers where nightlyUsers.NIGHTLY_USER_ID="
			 * + bigInt); int res = queryNU.executeUpdate();
			 * logger.debug("deleted NIGHTLY_USER_ID res "+res); }
			 */
			logger.debug("deleteing Users details data");
			Query queryNU = session
					.createSQLQuery("Delete from  APP_NIGHTLY_USERS  nightlyUsers where nightlyUsers.USER_ID = "
							+ new Long(id));
			int res = queryNU.executeUpdate();
			logger.debug("deleted NIGHTLY_USER_ID res " + res);

			/*
			 * Query userAppQuery = session.createSQLQuery(
			 * "select APP_USER_APP_MAPPER_ID from APP_USER_APP_MAPPER where APP_USER_ID = "
			 * + new Long(id)); List usersAppList = userAppQuery.list();
			 * logger.debug("App User Mapper list "+usersAppList);
			 * 
			 * for (int i = 0; i < usersAppList.size(); i++) {
			 * logger.debug("deleting APP_USER_APP_MAPPER_ID  id "+
			 * usersAppList.get(i)); BigDecimal bigInt = (BigDecimal)
			 * usersAppList.get(i); logger.debug(
			 * " Delete from  APP_USER_APP_MAPPER  appUser where appUser.APP_USER_APP_MAPPER_ID="
			 * + bigInt); Query queryAppUser = session.createSQLQuery(
			 * "Delete from  APP_USER_APP_MAPPER  appUser where appUser.APP_USER_APP_MAPPER_ID="
			 * + bigInt); int appUseRes = queryAppUser.executeUpdate();
			 * logger.debug("deleted APP_USER_APP_MAPPER_ID res "+appUseRes); }
			 */
			logger.debug("deleteing Users details from APP_USER_APP_MAPPER");
			logger.debug(" Delete from  APP_USER_APP_MAPPER  appUser where appUser.APP_USER_ID = "
					+ new Long(id));
			Query queryAppUser = session
					.createSQLQuery("Delete from  APP_USER_APP_MAPPER  appUser where appUser.APP_USER_ID = "
							+ new Long(id));
			int appUseRes = queryAppUser.executeUpdate();
			logger.debug("deleted APP_USER_APP_MAPPER_ID res " + appUseRes);

			Query queryUser = session
					.createSQLQuery("Delete from APP_USER userData where userData.APP_USER_ID = "
							+ new Long(id));
			int result = queryUser.executeUpdate();
			logger.debug("deleted User resUser " + result);
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

	@Override
	public List<NightlyBatchEvents> getNightlyBatchEvents(long id,
			String startDate, String endDate) throws Exception {
		logger.info("entered");
		List<NightlyBatchEvents> nightlyEventsList = new ArrayList<NightlyBatchEvents>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getNightlyEventsDetails" + ":" + id);

		logger.debug("startDate" + ":" + startDate);
		logger.debug("endDate" + ":" + endDate);
		// Query query =
		// session.createQuery("select distinct  em.errorMessage,nb.description,nb.parameters,nb.insertedDate, nb.host from NightlyUsers nu ,NBErrorMessage em ,NightlyBatch nb, User u where u.id ='"+new
		// Long(id)+"' and u.flag='true' and em.errorCode= nb.errorCode  and  nb.insertedDate  BETWEEN  TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"', 'YYYY-MM-DD HH24:MI:SS')  or ( em.errorCode= nb.errorCode and nu.userid ='"+new
		// Long(id)+"') and  nb.insertedDate  BETWEEN  TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') and TO_DATE('"+endDate+"', 'YYYY-MM-DD HH24:MI:SS')")
		// ;

		Query query = session
				.createQuery("select DISTINCT em.errorMessage,nb.description,nb.parameters,nb.insertedDate,nb.host,nbl.errorLevel from NightlyUsers nu,NBErrorMessage em,NightlyBatch nb,User u,NightlyBatchErrorLevel nbl where(u.id ='"
						+ new Long(id)
						+ "' AND u.flag='true' AND em.errorCode= nb.errorCode AND nb.errorCode=nbl.errorCode AND nb.insertedDate BETWEEN STR_TO_DATE('"
						+ startDate
						+ "','%Y-%m-%d %H:%i:%s') AND STR_TO_DATE('"
						+ endDate
						+ "','%Y-%m-%d %H:%i:%s') OR em.errorCode=nb.errorCode AND nb.errorCode=nbl.errorCode AND nu.userid='"
						+ new Long(id)
						+ "' AND nb.insertedDate BETWEEN STR_TO_DATE('"
						+ startDate
						+ "','%Y-%m-%d %H:%i:%s') AND STR_TO_DATE('"
						+ endDate
						+ "','%Y-%m-%d %H:%i:%s')) ORDER BY nb.insertedDate DESC");

		// Query query =
		// session.createSQLQuery("SELECT DISTINCT em.ERROR_MESSAGE,nb.DESCRIPTION,nb.PARAMETERS,nb.INSERTED_DATE,nb.HOST from NIGHTLYBATCH_ERROR_MESSAGE em,APP_NIGHTLY_BATCH nb,APP_NIGHTLY_USERS nu,APP_USER u where(u.APP_USER_ID ='"+new
		// Long(id)+"' AND u.FLAG='true' AND em.ERROR_CODE=nb.ERROR_CODE AND nb.INSERTED_DATE BETWEEN TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+endDate+"', 'YYYY-MM-DD HH24:MI:SS') OR em.ERROR_CODE=nb.ERROR_CODE AND nu.NIGHTLY_USER_ID='"+new
		// Long(id)+"' AND nb.INSERTED_DATE BETWEEN TO_DATE('"+startDate+"','YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"+endDate+"', 'YYYY-MM-DD HH24:MI:SS')) ORDER BY nb.INSERTED_DATE desc");

		System.out.println(query);
		List<Object[]> NEList = query.list();

		for (Object[] nightly : NEList) {
			NightlyBatchEvents nightlyEvents = new NightlyBatchEvents();
			nightlyEvents.setErrorMessage((String) nightly[0]);
			nightlyEvents.setDescription((String) nightly[1]);
			nightlyEvents.setParameters((String) nightly[2]);
			nightlyEvents.setErrorLevel(Long.parseLong((String) nightly[5]));
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			date = dateFormat.parse((String) nightly[3]);
			long time = date.getTime();
			Timestamp timestamp = new Timestamp(time);
			SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss,SSS");
			String result = dateFormat.format(new Date(timestamp.getTime()));
			nightlyEvents.setInsertedDate(result);
			// nightlyEvents.setInsertedDate((String)nightly[3]);
			nightlyEvents.setHost((String) nightly[4]);
			nightlyEventsList.add(nightlyEvents);

			logger.debug("NightlyEvents List :" + nightlyEventsList);
		}
		return nightlyEventsList;
	}
}
