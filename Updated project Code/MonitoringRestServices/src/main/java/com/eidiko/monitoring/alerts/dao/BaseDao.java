package com.eidiko.monitoring.alerts.dao;

import com.eidiko.monitoring.alerts.model.EntityObject;

public interface BaseDao {

	public void saveOrUpdate(EntityObject entity) throws Exception;

	public EntityObject getEntityById(Class cls, long id) throws Exception;

	public void delete(Class cls, long id) throws Exception;
}
