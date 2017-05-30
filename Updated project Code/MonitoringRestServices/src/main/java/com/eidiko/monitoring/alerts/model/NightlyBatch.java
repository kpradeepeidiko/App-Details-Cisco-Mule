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
@Table(name = "APP_NIGHTLY_BATCH")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NightlyBatch implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "APP_NIGHTLY_BATCH_SEQ", sequenceName = "APP_NIGHTLY_BATCH_SEQ", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "NIGHTLY_BATCH_ID")
	private long id;
	@Column(name = "ERROR_CODE")
	private String errorCode;
	@Column(name = "PARAMETERS")
	private String parameters;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "INSERTED_DATE")
	private String insertedDate;
	@Column(name = "ALERT_INSERTED_DATE")
	private String aletInsertedDate;
	@Column(name = "HOST")
	private String host;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(String insertedDate) {
		this.insertedDate = insertedDate;
	}

	public String getAletInsertedDate() {
		return aletInsertedDate;
	}

	public void setAletInsertedDate(String aletInsertedDate) {
		this.aletInsertedDate = aletInsertedDate;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
