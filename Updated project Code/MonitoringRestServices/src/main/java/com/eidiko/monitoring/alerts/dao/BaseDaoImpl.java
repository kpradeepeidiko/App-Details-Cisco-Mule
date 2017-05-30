package com.eidiko.monitoring.alerts.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.eidiko.monitoring.alerts.model.EntityObject;

@Transactional
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
	public static Logger logger = Logger.getLogger(BaseDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveOrUpdate(EntityObject entity) throws Exception {
		logger.info("entered");
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
		logger.info("exiting");
	}

	@Override
	public EntityObject getEntityById(Class cls, long id) throws Exception {
		logger.info("entered");
		logger.debug("getEntity By Id" + ":" + id);
		EntityObject obj = (EntityObject) getSessionFactory()
				.getCurrentSession().load(cls, id);
		Class<?> clazz = Class.forName(cls.getName());
		Object object = clazz.newInstance();
		BeanUtils.copyProperties(obj, object);
		System.out.println("hii" + (EntityObject) object);
		return (EntityObject) object;
	}

	public void delete(Class cls, long id) throws Exception {
		logger.info("entered");
		logger.debug("Delete By Id" + ":" + id);
		EntityObject entity = (EntityObject) getSessionFactory()
				.getCurrentSession().load(cls, id);
		getSessionFactory().getCurrentSession().delete(entity);
		logger.info("exiting");
	}

}
