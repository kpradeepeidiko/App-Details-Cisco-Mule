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
@Table(name = "APP_FS_APPLICATION_MAPPER")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FileSystemAppMapper extends EntityObject {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "SEQ_APP_FS_APPLICATION_MAPPER", sequenceName = "SEQ_APP_FS_APPLICATION_MAPPER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_FS_APPLICATION_MAPPER_ID")
	private long id;

	@Column(name = "APP_FILESYSTEM_ID")
	private long appFSId;

	@Column(name = "APP_APPLICATION_ID")
	private long appApplicationId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAppFSId() {
		return appFSId;
	}

	public void setAppFSId(long appFSId) {
		this.appFSId = appFSId;
	}

	public long getAppApplicationId() {
		return appApplicationId;
	}

	public void setAppApplicationId(long appApplicationId) {
		this.appApplicationId = appApplicationId;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id =" + id + " appFSId = " + appFSId + " appApplicationId="
				+ appApplicationId);
		return sb.toString();
	}

}
