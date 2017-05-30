package com.eidiko.monitoring.alerts.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "APP_PROCESS_EVENT")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PSEvents implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	//@SequenceGenerator(name = "APP_PROCESS_EVENT_SEQ", sequenceName = "APP_PROCESS_EVENT_SEQ", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_PS_EVENT_ID")
	private long id;

	@Column(name = "PS_OBJECT_TYPE")
	private String psObjectType;

	@Column(name = "PS_OBJECT_NAME")
	private String psObjectName;

	@Column(name = "PS_HOST_NAME")
	private String psHostName;

	@Column(name = "PS_OBJECT_STATUS")
	private String psObjectstatus;

	@Column(name = "PS_EVENT_DATE")
	private String psEventDate;

	@Column(name = "PS_EVENT_LEVEL")
	private String psEventLevel;

	@Column(name = "REASON_CODE")
	private String resonCode;

	@Column(name = "THRESHOLD_LIMIT")
	private String thresholdLimit;

	@Column(name = "CURRENT_USAGE")
	private String currentUsage;

	@Column(name = "PS_FLAG")
	private int psFlag;

	public int getPsFlag() {
		return psFlag;
	}

	public void setPsFlag(int psFlag) {
		this.psFlag = psFlag;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPsObjectType() {
		return psObjectType;
	}

	public void setPsObjectType(String psObjectType) {
		this.psObjectType = psObjectType;
	}

	public String getPsObjectName() {
		return psObjectName;
	}

	public void setPsObjectName(String psObjectName) {
		this.psObjectName = psObjectName;
	}

	public String getPsHostName() {
		return psHostName;
	}

	public void setPsHostName(String psHostName) {
		this.psHostName = psHostName;
	}

	public String getPsObjectstatus() {
		return psObjectstatus;
	}

	public void setPsObjectstatus(String psObjectstatus) {
		this.psObjectstatus = psObjectstatus;
	}

	public String getPsEventDate() {
		return psEventDate;
	}

	public void setPsEventDate(String psEventDate) {
		this.psEventDate = psEventDate;
	}

	public String getPsEventLevel() {
		return psEventLevel;
	}

	public void setPsEventLevel(String psEventLevel) {
		this.psEventLevel = psEventLevel;
	}

	public String getResonCode() {
		return resonCode;
	}

	public void setResonCode(String resonCode) {
		this.resonCode = resonCode;
	}

	public String getThresholdLimit() {
		return thresholdLimit;
	}

	public void setThresholdLimit(String thresholdLimit) {
		this.thresholdLimit = thresholdLimit;
	}

	public String getCurrentUsage() {
		return currentUsage;
	}

	public void setCurrentUsage(String currentUsage) {
		this.currentUsage = currentUsage;
	}

}
