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
@Table(name = "app_application")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Application extends EntityObject {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "SEQ_APP_APPLICATION", sequenceName = "SEQ_APP_APPLICATION", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_APPLICATION_ID")
	private long id;

	@Column(name = "app_name")
	private String name;
	@Column(name = "sms_flag")
	private String smsFlag;
	@Column(name = "email_flag")
	private String emailFlag;

	public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public String getEmailFlag() {
		return emailFlag;
	}

	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", name=" + name + "]";
	}

}
