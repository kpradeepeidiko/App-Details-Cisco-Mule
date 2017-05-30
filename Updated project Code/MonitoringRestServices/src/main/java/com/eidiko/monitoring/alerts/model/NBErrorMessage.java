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
@Table(name = "NIGHTLYBATCH_ERROR_MESSAGE")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NBErrorMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	//@SequenceGenerator(name = "NIGHTLYBATCH_ERROR_MESSAG_SEQ", sequenceName = "NIGHTLYBATCH_ERROR_MESSAG_SEQ", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "NIGHTLYBATCH_ERROR_MESSAGE_ID")
	private long id;
	@Column(name = "ERROR_CODE")
	private String errorCode;
	@Column(name = "ERROR_MESSAGE")
	private String errorMessage;

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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
