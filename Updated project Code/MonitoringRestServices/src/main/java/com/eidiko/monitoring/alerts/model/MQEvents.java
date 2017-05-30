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
@Table(name = "app_mq_event")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MQEvents implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "SEQ_app_mq_event", sequenceName = "SEQ_app_mq_event", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_MQ_EVENT_ID")
	private long id;

	@Column(name = "mq_object_type")
	private String mqObjectType;
	@Column(name = "mq_object_name")
	private String mqObjectName;
	@Column(name = "mq_qmgr_name")
	private String mqQmgrName;
	@Column(name = "mq_object_status")
	private String mqObjectStatus;
	@Column(name = "mq_object_date")
	private String mqObjectDate;
	@Column(name = "MQ_REASON_CODE")
	private int reasonCode;
	@Column(name = "mq_event_hostname")
	private String mqEventHostName;
	@Column(name = "mq_event_inserted_date")
	private String mqEventInsertedDate;
	@Column(name = "mq_flag")
	private String mqflag;

	public String getMqflag() {
		return mqflag;
	}

	public void setMqflag(String mqflag) {
		this.mqflag = mqflag;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMqObjectType() {
		return mqObjectType;
	}

	public void setMqObjectType(String mqObjectType) {
		this.mqObjectType = mqObjectType;
	}

	public String getMqObjectName() {
		return mqObjectName;
	}

	public void setMqObjectName(String mqObjectName) {
		this.mqObjectName = mqObjectName;
	}

	public String getMqQmgrName() {
		return mqQmgrName;
	}

	public void setMqQmgrName(String mqQmgrName) {
		this.mqQmgrName = mqQmgrName;
	}

	public String getMqObjectStatus() {
		return mqObjectStatus;
	}

	public void setMqObjectStatus(String mqObjectStatus) {
		this.mqObjectStatus = mqObjectStatus;
	}

	public String getMqObjectDate() {
		return mqObjectDate;
	}

	public void setMqObjectDate(String mqObjectDate) {
		this.mqObjectDate = mqObjectDate;
	}

	public int getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getMqEventHostName() {
		return mqEventHostName;
	}

	public void setMqEventHostName(String mqEventHostName) {
		this.mqEventHostName = mqEventHostName;
	}

	public String getMqEventInsertedDate() {
		return mqEventInsertedDate;
	}

	public void setMqEventInsertedDate(String mqEventInsertedDate) {
		this.mqEventInsertedDate = mqEventInsertedDate;
	}

	@Override
	public String toString() {
		return "MQEvents [id=" + id + ", mqObjectType=" + mqObjectType
				+ ", mqObjectName=" + mqObjectName + ", mqQmgrName="
				+ mqQmgrName + ", mqObjectStatus=" + mqObjectStatus
				+ ", mqObjectDate=" + mqObjectDate + ", mqEventLevel="
				+ reasonCode + ", mqEventHostName=" + mqEventHostName
				+ ", mqEventInsertedDate=" + mqEventInsertedDate + "]";
	}

}
