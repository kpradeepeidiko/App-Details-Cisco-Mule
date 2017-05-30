package com.eidiko.monitoring.alerts.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.eidiko.monitoring.alerts.dao.UserDao;
import com.eidiko.monitoring.alerts.model.AppUsers;
import com.eidiko.monitoring.alerts.model.NBUsers;
import com.eidiko.monitoring.alerts.model.NightlyBatchEvents;
import com.eidiko.monitoring.alerts.model.User;

public class UserServiceImpl implements UserService {
	public static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	UserDao userDao;

	@Override
	public User getUserById(long id) throws Exception {
		logger.info("entered");
		return (User) userDao.getEntityById(User.class, id);
	}

	@Override
	public void deleteUser(long id) throws Exception {
		logger.info("entered");
		userDao.delete(User.class, id);
		logger.info("exiting");
	}

	@Override
	public void addUser(User user) throws Exception {
		logger.info("entered");
		userDao.saveOrUpdate(user);
		logger.info("exiting");
	}

	@Override
	public List<User> getUserList() {
		logger.info("entered");
		return userDao.getUserList();

	}

	@Override
	public void updateUser(User user) throws Exception {
		logger.info("entered");
		userDao.saveOrUpdate(user);
		logger.info("exiting");
	}

	@Override
	public List<AppUsers> getUserDetails(long id) throws Exception {
		logger.info("entered");
		return userDao.getUserDetails(id);

	}

	@Override
	public void updatePassword(User user) throws Exception {
		logger.info("entered");
		userDao.saveOrUpdate(user);
		logger.info("exiting");
	}

	@Override
	public List<NBUsers> getNBUserDetails() throws Exception {
		logger.info("entered");
		return userDao.getNBUserDetails();

	}

	@Override
	public boolean deleteUsersDetails(long id) throws Exception {
		logger.info("entered");

		return userDao.deleteUsersDetails(id);
	}

	@Override
	public List<NightlyBatchEvents> getNightlyBatchEvents(long id,
			String startDate, String endDate) throws Exception {
		// TODO Auto-generated method stub
		logger.info("entered");
		return userDao.getNightlyBatchEvents(id, startDate, endDate);
	}
}
