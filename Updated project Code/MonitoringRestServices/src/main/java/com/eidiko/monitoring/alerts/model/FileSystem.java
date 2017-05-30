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
@Table(name = "APP_FILESYSTEM")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FileSystem extends EntityObject {

	private static final long serialVersionUID = 1L;
	@Id
	//@SequenceGenerator(name = "SEQ_APP_FILESYSTEM", sequenceName = "SEQ_APP_FILESYSTEM", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_FILESYSTEM_ID")
	private long id;
	@Column(name = "FS_NAME")
	private String fsName;

	@Column(name = "HOST")
	private String fsHost;

	@Column(name = "FS_MOUNT")
	private String fsMount;

	@Column(name = "FS_PATH")
	private String fsPath;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName;
	}

	public String getFsMount() {
		return fsMount;
	}

	public void setFsMount(String fsMount) {
		this.fsMount = fsMount;
	}

	public String getFsPath() {
		return fsPath;
	}

	public void setFsPath(String fsPath) {
		this.fsPath = fsPath;
	}

	public String getFsHost() {
		return fsHost;
	}

	public void setFsHost(String fsHost) {
		this.fsHost = fsHost;
	}

	@Override
	public String toString() {
		return "FileSystem [id=" + id + ", fsName=" + fsName + ", fsHost="
				+ fsHost + ", fsMount=" + fsMount + ", fsPath=" + fsPath + "]";
	}
}
