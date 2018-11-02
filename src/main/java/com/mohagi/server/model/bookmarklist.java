package com.mohagi.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class bookmarklist {
	@Id
	@Column
	String id;
	@Column
	String user_id;
	@Column
	String loc_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBookmark() {
		return loc_id;
	}
	public void setBookmark(String loc_id) {
		this.loc_id = loc_id;
	}
	public bookmarklist(String id, String user_id, String loc_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.loc_id = loc_id;
	}
	public bookmarklist() {}
	@Override
	public String toString() {
		return "BookmarkList {"
				+ "\"id\":\"" + id + 
				"\", \"user_id\":\"" + user_id +
				"\", \"loc_id\":\"" + loc_id + 
				"\"}";
	}
}
