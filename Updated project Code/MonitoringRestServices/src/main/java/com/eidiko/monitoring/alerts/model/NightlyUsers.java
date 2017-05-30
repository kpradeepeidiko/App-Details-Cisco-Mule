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
public class NightlyUsers {
	@Id
	//@SequenceGenerator(name = "APP_NIGHTLY_USERS_SEQ", sequenceName = "APP_NIGHTLY_USERS_SEQ", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "NIGHTLY_USER_ID")
	private long nightlyUserid;

	@Column(name = "USER_ID")
	private long userid;

	public long getNightlyUserid() {
		return nightlyUserid;
	}

	public void setNightlyUserid(long nightlyUserid) {
		this.nightlyUserid = nightlyUserid;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}
}
