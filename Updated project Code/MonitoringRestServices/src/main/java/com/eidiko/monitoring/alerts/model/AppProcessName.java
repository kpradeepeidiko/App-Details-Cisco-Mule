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
@Table(name = "APP_PROCESS_NAME")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AppProcessName {
	@Id
	//@SequenceGenerator(name = "APP_PROCESS_NAME_SEQ", sequenceName = "APP_PROCESS_NAME_SEQ", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	@Column(name = "PROCESS_NAME")
	private String processName;

	@Column(name = "PS_HOST")
	private String psHost;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getPsHost() {
		return psHost;
	}

	public void setPsHost(String psHost) {
		this.psHost = psHost;
	}

	@Override
	public String toString() {
		return "AppProcessName [id=" + id + ", processName=" + processName
				+ ", psHost=" + psHost + "]";
	}

}
