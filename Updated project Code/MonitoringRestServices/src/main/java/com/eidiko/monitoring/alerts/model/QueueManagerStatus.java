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
@Table(name = "app_qmgr_status")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QueueManagerStatus extends EntityObject {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "SEQ_APP_QMGR_STATUS", sequenceName = "SEQ_APP_QMGR_STATUS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_QMGR_STATUS_ID")
	private long id;

	@Column(name = "QM_NAME")
	private String qmName;

	@Column(name = "HOSTNAME")
	private String hostName;

	@Column(name = "LAST_UPTIME")
	private String upTime;

	@Column(name = "LAST_DOWNTIME")
	private String downTime;

	@Column(name = "FLAG")
	private int flagStatus;

	@Column(name = "LAST_UPDATEDTIME")
	private String lastUpdatedTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQmName() {
		return qmName;
	}

	public void setQmName(String qmName) {
		this.qmName = qmName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUpTime() {
		return upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public String getDownTime() {
		return downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}

	public int getFlagStatus() {
		return flagStatus;
	}

	public void setFlagStatus(int flagStatus) {
		this.flagStatus = flagStatus;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

}
