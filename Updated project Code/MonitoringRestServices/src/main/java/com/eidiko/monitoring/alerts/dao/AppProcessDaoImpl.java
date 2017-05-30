package com.eidiko.monitoring.alerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import com.eidiko.monitoring.alerts.model.Process;
import com.eidiko.monitoring.alerts.model.AppProcess;

public class AppProcessDaoImpl extends BaseDaoImpl implements AppProcessDao {
	@Override
	public List<com.eidiko.monitoring.alerts.model.Process> getProcessNameList() {
		// TODO Auto-generated method stub
		logger.info("entered");
		return getSessionFactory().getCurrentSession()
				.createCriteria(Process.class).list();
	}

	@Override
	public List<AppProcess> getAppProcessList(long id) throws Exception {

		logger.info("entered");
		List<AppProcess> appPSList = new ArrayList<AppProcess>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getprocess By Id" + ":" + id);
		Query query = session
				.createQuery("SELECT p.id,p.psName,p.psHost,p.psFilter,p.psMin,p.psMax,pm.id from Process p,ProcessAppMapper pm where pm.appPSId = p.id and pm.appApplicationId="
						+ new Long(id));
		@SuppressWarnings("unchecked")
		List<Object[]> psList = query.list();
		for (Object[] process : psList) {
			AppProcess appProcess = new AppProcess();
			appProcess.setId((long) process[0]);
			appProcess.setPsName((String) process[1]);
			appProcess.setPsHost((String) process[2]);
			appProcess.setPsFilter((String) process[3]);
			appProcess.setPsMin((String) process[4]);
			appProcess.setPsMax((String) process[5]);
			appProcess.setMapperId((long) process[6]);
			appPSList.add(appProcess);
			logger.debug("AppProcessList :" + appPSList);
		}
		return appPSList;
	}

}
