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
@Table(name = "APP_PROCESS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Process extends EntityObject {
	private static final long serialVersionUID = 1L;
	@Id
	//@SequenceGenerator(name = "SEQ_APP_PROCESS", sequenceName = "SEQ_APP_PROCESS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_PROCESS_ID")
	private long id;

	@Column(name = "APP_PROCESS_HOST")
	private String psHost;

	@Column(name = "PROCESS_NAME")
	private String psName;

	@Column(name = "PROCESS_FILTER")
	private String psFilter;

	@Column(name = "PROCESS_MIN")
	private String psMin;

	@Column(name = "PROCESS_MAX")
	private String psMax;

	public String getPsHost() {
		return psHost;
	}

	public void setPsHost(String psHost) {
		this.psHost = psHost;
	}

	public String getPsName() {
		return psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	public String getPsFilter() {
		return psFilter;
	}

	public void setPsFilter(String psFilter) {
		this.psFilter = psFilter;
	}

	public String getPsMin() {
		return psMin;
	}

	public void setPsMin(String psMin) {
		this.psMin = psMin;
	}

	public String getPsMax() {
		return psMax;
	}

	public void setPsMax(String psMax) {
		this.psMax = psMax;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Process [id=" + id + ", psHost=" + psHost + ", psName="
				+ psName + ", psFilter=" + psFilter + ", psMin=" + psMin
				+ ", psMax=" + psMax + "]";
	}
}
