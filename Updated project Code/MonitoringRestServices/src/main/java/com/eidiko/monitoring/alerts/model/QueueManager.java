package com.eidiko.monitoring.alerts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "app_qmgr")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QueueManager extends EntityObject {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "SEQ_APP_QMGR", sequenceName = "SEQ_APP_QMGR", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_QMGR_ID")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "host")
	private String host;

	@Column(name = "svrconn")
	private String svrConn;

	@Column(name = "listener_port")
	private int listnerPort;

	@Column(name = "mca_user")
	private String mcaUser;

	@Column(name = "ACTIVE")
	private String status;

	@Column(name = "SECONDARY_HOST")
	private String shost;

	public String getShost() {
		return shost;
	}

	public void setShost(String shost) {
		this.shost = shost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getListnerPort() {
		return listnerPort;
	}

	public void setListnerPort(int listnerPort) {
		this.listnerPort = listnerPort;
	}

	public String getMcaUser() {
		return mcaUser;
	}

	public void setMcaUser(String mcaUser) {
		this.mcaUser = mcaUser;
	}

	public String getSvrConn() {
		return svrConn;
	}

	public void setSvrConn(String svrConn) {
		this.svrConn = svrConn;
	}

	@Override
	public String toString() {
		return "QueueManager [id=" + id + ", name=" + name + ", host=" + host
				+ ", svrConn=" + svrConn + ", listnerPort=" + listnerPort
				+ ", mcaUser=" + mcaUser + "]";
	}

}
