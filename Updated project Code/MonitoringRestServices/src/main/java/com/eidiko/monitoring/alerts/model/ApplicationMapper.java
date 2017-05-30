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
@Table(name = "app_qmgr_app_mapper")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ApplicationMapper extends EntityObject {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "SEQ_app_qmgr_app_mapper", sequenceName = "SEQ_app_qmgr_app_mapper", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_QMGR_APP_MAPPER_ID")
	private long id;

	@Column(name = "app_qmgr_id")
	private long appQmgrId;

	@Column(name = "app_application_id")
	private long appApplicationId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAppQmgrId() {
		return appQmgrId;
	}

	public void setAppQmgrId(long appQmgrId) {
		this.appQmgrId = appQmgrId;
	}

	public long getAppApplicationId() {
		return appApplicationId;
	}

	public void setAppApplicationId(long appApplicationId) {
		this.appApplicationId = appApplicationId;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id =" + id + " appQmgrId = " + appQmgrId
				+ " appApplicationId=" + appApplicationId);
		return sb.toString();
	}
}
