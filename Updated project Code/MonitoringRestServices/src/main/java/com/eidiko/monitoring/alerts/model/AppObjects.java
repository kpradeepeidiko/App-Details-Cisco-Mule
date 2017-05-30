package com.eidiko.monitoring.alerts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "app_mq_objects")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AppObjects extends EntityObject {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "SEQ_app_mq_objects", sequenceName = "SEQ_app_mq_objects", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_MQ_OBJECTS_ID")
	private long id;

	@Column(name = "object_name")
	private String objectName;

	@Column(name = "object_type")
	private String objectType;

	@Column(name = "app_qmgr_app_application_id")
	private long appQmgrAppId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public long getAppQmgrAppId() {
		return appQmgrAppId;
	}

	public void setAppQmgrAppId(long appQmgrAppId) {
		this.appQmgrAppId = appQmgrAppId;
	}

	@Override
	public String toString() {
		return "AppObjects [id=" + id + ", objectName=" + objectName
				+ ", objectType=" + objectType + ", appQmgrAppId="
				+ appQmgrAppId + "]";
	}

}
