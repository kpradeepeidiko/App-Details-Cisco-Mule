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
@Table(name = "app_user_app_mapper")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserApplicationMapper extends EntityObject {

	@Id
	//@SequenceGenerator(name = "SEQ_app_user_app_mapper", sequenceName = "SEQ_app_user_app_mapper", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_USER_APP_MAPPER_ID")
	private long id;

	@Column(name = "app_user_id")
	private long appuserId;

	@Column(name = "app_application_id")
	private long appapplicationId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAppuserId() {
		return appuserId;
	}

	public void setAppuserId(long appuserId) {
		this.appuserId = appuserId;
	}

	public long getAppapplicationId() {
		return appapplicationId;
	}

	public void setAppapplicationId(long appapplicationId) {
		this.appapplicationId = appapplicationId;
	}

	@Override
	public String toString() {
		return "UserApplicationMapper [id=" + id + ", appuserId=" + appuserId
				+ ", appapplicationId=" + appapplicationId + "]";
	}

}
