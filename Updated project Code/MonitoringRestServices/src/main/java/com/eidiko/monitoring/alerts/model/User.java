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
@Table(name = "app_user")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User extends EntityObject {

	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "SEQ_app_user", sequenceName = "SEQ_app_user", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APP_USER_ID")
	private long id;

	@Column(name = "APP_USER_NAME")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "flag")
	private String flag = "false";

	@Column(name = "app_user_password")
	private String password;

	@Column(name = "email_flag")
	private String email_flag;

	@Column(name = "sms_flag")
	private String sms_flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail_flag() {
		return email_flag;
	}

	public void setEmail_flag(String email_flag) {
		this.email_flag = email_flag;
	}

	public String getSms_flag() {
		return sms_flag;
	}

	public void setSms_flag(String sms_flag) {
		this.sms_flag = sms_flag;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email
				+ ", mobileNo=" + mobileNo + ", email_flag=" + email_flag
				+ ", sms_flag=" + sms_flag + ",password =" + password + "]";
	}

}
