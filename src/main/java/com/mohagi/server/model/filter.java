package com.mohagi.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class filter {
	@Id
	@Column
	String filter_id;		//필터아이디(pk)
	@Column
	String filter_name;		//필터이름
	@Override
	public String toString() {
		return "Filter {"
				+ "\"filter_id\":\"" + filter_id + 
				"\", \"filter_name\":\"" + filter_name +
				"\"}";
	}
	public String getFilter_id() {
		return filter_id;
	}
	public void setFilter_id(String filter_id) {
		this.filter_id = filter_id;
	}
	public String getFilter_name() {
		return filter_name;
	}
	public void setFilter_name(String filter_name) {
		this.filter_name = filter_name;
	}
	
	
}
