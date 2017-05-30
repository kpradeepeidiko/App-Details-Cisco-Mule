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
@Table(name = "APP_FILENAME")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AppFileName {
	@Id
	//@SequenceGenerator(name = "APP_FILENAME_SEQ", sequenceName = "APP_FILENAME_SEQ", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	@Column(name = "FILE_NAME", nullable = false)
	private String fileName;
	@Column(name = "FS_PATH", nullable = false)
	private String fsPath;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFsPath() {
		return fsPath;
	}

	public void setFsPath(String fsPath) {
		this.fsPath = fsPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
