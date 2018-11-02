package com.mohagi.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {
	@Id
	@Column
	String user_id;		//유저아이디(pk)
	@Column
	String user_pw;		//유저비밀번호
	@Column
	String user_name;	//유저이름
	@Column
	String birthdate;	//유저생년월일
	@Column
	String gender;	//유저성별
	@Column
	String latest_filter;	//최근 선택한 필터 (fk)
	@Override
	public String toString() {
		return "User {"
				+ "\"user_id\":\"" + user_id + 
				"\", \"user_pw\":\"" + user_pw + 
				"\", \"user_name\":\"" + user_name +
				"\", \"birthdate\":\"" + birthdate +
				"\", \"gender\":\"" + gender +
				"\", \"latest_filter\":\"" + latest_filter +
				"\"}";
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLatest_filter() {
		return latest_filter;
	}
	public void setLatest_filter(String latest_filter) {
		this.latest_filter = latest_filter;
	}
	
	
}
