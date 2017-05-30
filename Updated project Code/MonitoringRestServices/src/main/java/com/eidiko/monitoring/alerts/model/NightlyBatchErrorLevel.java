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
@Table(name = "APP_NIGHTLYBATCH_ERROR_LEVEL")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NightlyBatchErrorLevel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	//@SequenceGenerator(name = "APP_NIGHTLY_ERROR_LEVEL_SEQ", sequenceName = "APP_NIGHTLY_ERROR_LEVEL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ERROR_ID")
	private int id;
	@Column(name = "ERROR_CODE")
	private String errorCode;
	@Column(name = "ERROR_LEVEL")
	private String errorLevel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorLevel() {
		return errorLevel;
	}

	public void setErrorLevel(String errorLevel) {
		this.errorLevel = errorLevel;
	}

	@Override
	public String toString() {
		return "NightlyBatchErrorLevel [id=" + id + ", errorCode=" + errorCode
				+ ", errorLevel=" + errorLevel + "]";
	}

	

	

	

}
