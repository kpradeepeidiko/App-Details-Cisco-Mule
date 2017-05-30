package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.eidiko.monitoring.alerts.model.Admin;
import com.eidiko.monitoring.alerts.model.EntityObject;
import com.eidiko.monitoring.alerts.model.User;

public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {

	public User userAuthentication(User loginData) {
		User user = new User();
		String queryStr = "from com.eidiko.monitoring.alerts.model.User user where user.name='"
				+ loginData.getName()
				+ "' and user.password='"
				+ loginData.getPassword() + "'";
		System.out.println(queryStr + ":::::");
		Query query = getSessionFactory().getCurrentSession().createQuery(
				queryStr);
		List list = query.list();
		if (list.size() > 0) {
			user = (User) list.get(0);

		}
		return user;
	}

	@Override
	public boolean isValidUser(Admin loginData) {
		String queryStr = "from com.eidiko.monitoring.alerts.model.Admin admin where admin.name='"
				+ loginData.getName()
				+ "' and admin.password='"
				+ loginData.getPassword() + "'";
		System.out.println(queryStr + ":::::");
		Query query = getSessionFactory().getCurrentSession().createQuery(
				queryStr);
		List list = query.list();
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean updatePassword(User user) {
		logger.info(" password entered");
		try {
			Session session = getSessionFactory().getCurrentSession();
			User updatedUser = (User) session.load(User.class, user.getId());
			updatedUser.setPassword(user.getPassword());
			session.update(updatedUser);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
