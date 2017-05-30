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
@Table(name = "app_mqevent_properties")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AppMQEventsProperties implements Serializable {

	@Id
	//@SequenceGenerator(name = "SEQ_app_mqevent_properties", sequenceName = "SEQ_app_mqevent_properties", allocationSize = 1)
	// @SequenceGenerator(name = "SEQ_APP_QMGR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@Column(name = "EVENT_LEVEL")
	private String eventlLevel;

	@Column(name = "EVENT_DESCRIPTION")
	private String eventDescription;

	@Column(name = "REASON_CODE")
	private String reasonCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEventlLevel() {
		return eventlLevel;
	}

	public void setEventlLevel(String eventlLevel) {
		this.eventlLevel = eventlLevel;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

}
