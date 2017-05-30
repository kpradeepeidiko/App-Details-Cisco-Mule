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
@Table(name = "app_alert_configuration")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AlertConfiguration extends EntityObject {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "SEQ_app_alert_configuration", sequenceName = "SEQ_app_alert_configuration", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_ALERT_CONFIGURATION_ID")
	private long id;
	@Column(name = "sms_flag")
	private String sms_flag;
	@Column(name = "email_flag")
	private String email_flag;
	@Column(name = "SENDING_MODE")
	private String mode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSms_flag() {
		return sms_flag;
	}

	public void setSms_flag(String sms_flag) {
		this.sms_flag = sms_flag;
	}

	public String getEmail_flag() {
		return email_flag;
	}

	public void setEmail_flag(String email_flag) {
		this.email_flag = email_flag;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Override
	public String toString() {
		return "AlertConfiguration [id=" + id + ", sms_flag=" + sms_flag
				+ ", email_flag=" + email_flag + ", mode=" + mode + "]";
	}

}
