package com.eidiko.monitoring.alerts.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.eidiko.monitoring.alerts.model.ApplicationMapper;

@Transactional
public class ApplicationMapperDaoImpl extends BaseDaoImpl implements
		ApplicationMapperDao {

	public static final Logger logger = Logger
			.getLogger(ApplicationMapperDaoImpl.class);

	@Override
	public List<ApplicationMapper> getApplicationMapperList() {
		logger.info("entered");

		return getSessionFactory().getCurrentSession()
				.createCriteria(ApplicationMapper.class).list();
	}

}
