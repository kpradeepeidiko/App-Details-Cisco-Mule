package com.eidiko.monitoring.alerts.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.eidiko.monitoring.alerts.model.AppFileName;
import com.eidiko.monitoring.alerts.model.AppFileSystem;
import com.eidiko.monitoring.alerts.model.FileSystem;

public class AppFileSystemDaoImp extends BaseDaoImpl implements
		AppFileSystemDao {
	public static final Logger logger = Logger
			.getLogger(AppFileSystemDaoImp.class);

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<FileSystem> getFileSystemList() {
		logger.info("entered");
		return getSessionFactory().getCurrentSession()
				.createCriteria(FileSystem.class).list();
	}

	@Override
	public List<AppFileSystem> getFileSystemDetails(long id) throws Exception {
		logger.info("entered");
		List<AppFileSystem> appFSList = new ArrayList<AppFileSystem>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getFileSystem By Id" + ":" + id);
		Query query = session
				.createQuery("SELECT f.id,f.fsName,f.fsHost,f.fsMount,f.fsPath,fm.id from FileSystem f,FileSystemAppMapper fm where fm.appFSId = f.id and fm.appApplicationId="
						+ new Long(id));
		@SuppressWarnings("unchecked")
		List<Object[]> fsList = query.list();
		for (Object[] fileSystem : fsList) {
			AppFileSystem appFileSystem = new AppFileSystem();
			appFileSystem.setId((long) fileSystem[0]);
			appFileSystem.setFsName((String) fileSystem[1]);
			appFileSystem.setFsHost((String) fileSystem[2]);
			appFileSystem.setFsMount((String) fileSystem[3]);
			appFileSystem.setFsPath((String) fileSystem[4]);
			appFileSystem.setMapperId((long) fileSystem[5]);
			appFSList.add(appFileSystem);
			logger.debug("AppFileSystem List :" + appFSList);
		}
		return appFSList;
	}

	@Override
	public List<AppFileSystem> getFileSystem(long id) {
		// TODO Auto-generated method stub
		logger.info("entered");
		List<AppFileSystem> appFSList = new ArrayList<AppFileSystem>();
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getFileSystem By Id" + ":" + id);
		logger.debug("SELECT f.id,f.fsName,f.fsHost,f.fsMount,f.fsPath,qa.id from FileSystem f , FileSystemAppMapper fa where qa.appFSId = f.id and f.id="
				+ new Long(id));
		Query query = session
				.createQuery("SELECT f.id,f.fsName,f.fsHost,f.fsMount,f.fsPath,fa.id from FileSystem f , FileSystemAppMapper fa where fa.appFSId = f.id and f.id="
						+ new Long(id));
		List<Object[]> fsList = query.list();
		for (Object[] fileSystem : fsList) {
			AppFileSystem appFileSystem = new AppFileSystem();
			appFileSystem.setId((long) fileSystem[0]);
			appFileSystem.setFsName((String) fileSystem[1]);
			appFileSystem.setFsHost((String) fileSystem[2]);
			appFileSystem.setFsMount((String) fileSystem[3]);
			appFileSystem.setFsPath((String) fileSystem[4]);
			appFSList.add(appFileSystem);
			logger.debug("AppFileSystem List :" + appFSList);
		}
		return appFSList;
	}

	@Override
	public List<AppFileName> getFileNameList() {
		logger.info("entered");
		return getSessionFactory().getCurrentSession()
				.createCriteria(AppFileName.class).list();
	}

	@Override
	@Transactional
	public boolean deleteFileSystemDetails(long id) throws Exception {
		// Session session = getSessionFactory().openSession();
		Session session = getSessionFactory().getCurrentSession();

		// Transaction tx = session.getTransaction();
		// session.beginTransaction();
		try {
			logger.debug("deleteing FileSystem details data");
			/*
			 * Query queryAppFileSystemId = session.createSQLQuery(
			 * "select APP_FS_APPLICATION_MAPPER_ID from APP_FS_APPLICATION_MAPPER where APP_FILESYSTEM_ID = "
			 * + new Long(id)); List fsAppList = queryAppFileSystemId.list();
			 * logger.debug("Application FileSystem Mapper list "+fsAppList);
			 * 
			 * for (int i = 0; i < fsAppList.size(); i++) {
			 * logger.debug("deleting APP_FS_APPLICATION_MAPPER_ID  id "+
			 * fsAppList.get(i)); BigDecimal bigInt = (BigDecimal)
			 * fsAppList.get(i); logger.debug(
			 * " Delete from  APP_FS_APPLICATION_MAPPER  appFS where appFS.APP_FS_APPLICATION_MAPPER_ID="
			 * + bigInt); Query queryAppFS = session.createSQLQuery(
			 * "Delete from  APP_FS_APPLICATION_MAPPER  appFS where appFS.APP_FS_APPLICATION_MAPPER_ID="
			 * + bigInt); int appFSRes = queryAppFS.executeUpdate();
			 * logger.debug("deleted APP_FILESYSTEM_ID res "+appFSRes); }
			 */
			logger.debug("deleteing FileSystem details from APP_FS_APPLICATION_MAPPER");
			logger.debug(" Delete from  APP_FS_APPLICATION_MAPPER  appFS where appFS.APP_FILESYSTEM_ID = "
					+ new Long(id));
			Query queryAppFS = session
					.createSQLQuery("Delete from  APP_FS_APPLICATION_MAPPER  appFS where appFS.APP_FILESYSTEM_ID = "
							+ new Long(id));
			int appFSRes = queryAppFS.executeUpdate();
			logger.debug("deleted APP_FILESYSTEM_ID res " + appFSRes);

			logger.debug("deleteing FileSystem details from APP_FILESYSTEM");
			Query queryFS = session
					.createSQLQuery("Delete from APP_FILESYSTEM fs where fs.APP_FILESYSTEM_ID = "
							+ new Long(id));
			int result = queryFS.executeUpdate();
			logger.debug("deleted FileSystem result " + result);
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

}
