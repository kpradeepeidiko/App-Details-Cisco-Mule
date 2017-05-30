package com.eidiko.monitoring.alerts.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.eidiko.monitoring.alerts.dao.AdminDao;
import com.eidiko.monitoring.alerts.model.Admin;
import com.eidiko.monitoring.alerts.model.Application;
import com.eidiko.monitoring.alerts.model.User;

@SuppressWarnings("unused")
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminDao adminDao;

	@Override
	public boolean isValidUser(Admin loginData) {

		return adminDao.isValidUser(loginData);
	}

	@Override
	public User userAuthentication(User loginData) {

		return adminDao.userAuthentication(loginData);
	}

	@Override
	public Boolean updatePassword(User user) {

		return adminDao.updatePassword(user);
	}

	@Override
	public Boolean addpassword(User user) {

		return adminDao.updatePassword(user);
	}

}
