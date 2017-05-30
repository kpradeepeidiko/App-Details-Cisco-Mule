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
@Table(name = "APP_MSG_QUEUE_CONFIG")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QueueConfiguration extends EntityObject {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "SEQ_APP_MSG_QUEUE_CONFIG", sequenceName = "SEQ_APP_MSG_QUEUE_CONFIG", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_MSG_QUEUE_CONFIG_ID")
	private long id;
	@Column(name = "QMGR_NAME")
	private String name;
	@Column(name = "HOST_NAME")
	private String host;
	@Column(name = "QUEUE_NAME")
	private String queue;
	@Column(name = "SVRCONN")
	private String svrConn;

	@Column(name = "LISTENER_PORT")
	private int listener_port;

	public int getListener_port() {
		return listener_port;
	}

	public void setListener_port(int listener_port) {
		this.listener_port = listener_port;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getSvrConn() {
		return svrConn;
	}

	public void setSvrConn(String svrConn) {
		this.svrConn = svrConn;
	}

	@Override
	public String toString() {
		return "QueueConfiguration [id=" + id + ", name=" + name + ", host="
				+ host + ", queue=" + queue + ", svrConn=" + svrConn
				+ ", listener_port=" + listener_port + "]";
	}

}
