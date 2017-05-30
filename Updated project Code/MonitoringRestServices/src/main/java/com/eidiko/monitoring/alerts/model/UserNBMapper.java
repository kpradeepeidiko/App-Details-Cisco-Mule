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
@Table(name = "APP_NIGHTLY_USERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserNBMapper extends EntityObject {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "APP_NIGHTLY_USERS_SEQ", sequenceName = "APP_NIGHTLY_USERS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "NIGHTLY_USER_ID")
	private long nbid;

	@Column(name = "USER_ID")
	private long nbuserid;

	public long getNbid() {
		return nbid;
	}

	public void setNbid(long nbid) {
		this.nbid = nbid;
	}

	public long getNbuserid() {
		return nbuserid;
	}

	public void setNbuserid(long nbuserid) {
		this.nbuserid = nbuserid;
	}

	@Override
	public String toString() {
		return "UserNBMapper [nbid=" + nbid + ", nbuserid=" + nbuserid + "]";
	}
}
