package com.eidiko.monitoring.alerts.test;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.eidiko.monitoring.alerts.dao.QueueManagerDaoImpl;
import com.eidiko.monitoring.alerts.model.QueueManager;

public class QueueManagerDaoTest extends TestCase {
	private SessionFactory sessionFactory;
	private Session session;
	private Query query;
	
	@Before
	public void setUp()
	{
		sessionFactory = mock(SessionFactory.class); 
		session = mock(Session.class); 
		query = mock(Query.class);
	}
	@Test
	public void testQueueManager()
	{
		String hql = "select id from QueueManager";
		List<QueueManager> qmgrListExpected = new ArrayList<QueueManager>();
		QueueManager qmgr = new QueueManager();
		qmgr.setId(1);
		qmgr.getId();
		qmgrListExpected.add(qmgr);
		
		
		QueueManagerDaoImpl queueManagerDaoImpl = new QueueManagerDaoImpl();
		queueManagerDaoImpl.setSessionFactory(sessionFactory);
		//when(sessionFactory.getCurrentSession())
		
		
	}
	@After
	public void tearDown()
	{
		sessionFactory.close();	
		session.close();
	}

}
