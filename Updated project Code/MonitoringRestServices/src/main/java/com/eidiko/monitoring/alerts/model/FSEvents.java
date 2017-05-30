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
@Table(name = "app_fs_event")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FSEvents implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "SEQ_APP_FS_EVENT", sequenceName = "SEQ_APP_FS_EVENT", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_FS_EVENT_ID")
	private long id;

	@Column(name = "FS_OBJECT_TYPE")
	private String fsObjectType;

	@Column(name = "FS_OBJECT_NAME")
	private String fsObjectName;

	@Column(name = "FS_HOST_NAME")
	private String fsHost;

	@Column(name = "FS_OBJECT_STATUS")
	private String fsObjecStatus;

	@Column(name = "FS_EVENT_DATE")
	private String fsEventDate;

	@Column(name = "FS_EVENT_LEVEL")
	private String fsEventLevel;

	@Column(name = "REASON_CODE")
	private String reasonCode;

	@Column(name = "THRESHOLD_LIMIT")
	private String thresholdLimit;

	@Column(name = "CURRENT_USAGE")
	private String currentUsage;
	@Column(name = "FS_FLAG")
	private int fsFlag;

	public int getFsFlag() {
		return fsFlag;
	}

	public void setFsFlag(int fsFlag) {
		this.fsFlag = fsFlag;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFsObjectType() {
		return fsObjectType;
	}

	public void setFsObjectType(String fsObjectType) {
		this.fsObjectType = fsObjectType;
	}

	public String getFsObjectName() {
		return fsObjectName;
	}

	public void setFsObjectName(String fsObjectName) {
		this.fsObjectName = fsObjectName;
	}

	public String getFsHost() {
		return fsHost;
	}

	public void setFsHost(String fsHost) {
		this.fsHost = fsHost;
	}

	public String getFsObjecStatus() {
		return fsObjecStatus;
	}

	public void setFsObjecStatus(String fsObjecStatus) {
		this.fsObjecStatus = fsObjecStatus;
	}

	public String getFsEventDate() {
		return fsEventDate;
	}

	public void setFsEventDate(String fsEventDate) {
		this.fsEventDate = fsEventDate;
	}

	public String getFsEventLevel() {
		return fsEventLevel;
	}

	public void setFsEventLevel(String fsEventLevel) {
		this.fsEventLevel = fsEventLevel;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
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

	@Override
	public String toString() {
		return "FSEvents [id=" + id + ", fsObjectType=" + fsObjectType
				+ ", fsObjectName=" + fsObjectName + ", fsHost=" + fsHost
				+ ", fsObjecStatus=" + fsObjecStatus + ", fsEventDate="
				+ fsEventDate + ", fsEventLevel=" + fsEventLevel
				+ ", reasonCode=" + reasonCode + ", thresholdLimit="
				+ thresholdLimit + ", currentUsage=" + currentUsage + "]";
	}

}
