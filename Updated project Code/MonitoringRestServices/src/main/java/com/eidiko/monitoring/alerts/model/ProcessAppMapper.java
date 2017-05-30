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
@Table(name = "APP_PROCESS_APP_MAPPER")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProcessAppMapper extends EntityObject {
	private static final long serialVersionUID = 1L;
	@Id
	//@SequenceGenerator(name = "SEQ_APP_PROCESS_APP_MAPPER", sequenceName = "SEQ_APP_PROCESS_APP_MAPPER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_PROCESS_APP_MAPPER_ID")
	private long id;

	@Column(name = "APP_PROCESS_ID")
	private long appPSId;

	@Column(name = "APP_APPLICATION_ID")
	private long appApplicationId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAppPSId() {
		return appPSId;
	}

	public void setAppPSId(long appPSId) {
		this.appPSId = appPSId;
	}

	public long getAppApplicationId() {
		return appApplicationId;
	}

	public void setAppApplicationId(long appApplicationId) {
		this.appApplicationId = appApplicationId;
	}

	@Override
	public String toString() {
		return "ProcessAppMapper [id=" + id + ", appPSId=" + appPSId
				+ ", appApplicationId=" + appApplicationId + "]";
	}
}
