package com.mohagi.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class big_category {
	@Id
	@Column
	String big_ctg_id;		//대분류아이디(pk)
	@Column
	String big_ctg_name;	//대분류이름
	@Override
	public String toString() {
		return "Big_Category {\""
				+ "\"big_ctg_id\":\"" + big_ctg_id + 
				"\", \"big_ctg_name\":\"" + big_ctg_name + 
				"\"}";
	}
	public String getBig_ctg_id() {
		return big_ctg_id;
	}
	public void setBig_ctg_id(String big_ctg_id) {
		this.big_ctg_id = big_ctg_id;
	}
	public String getBig_ctg_name() {
		return big_ctg_name;
	}
	public void setBig_ctg_name(String big_ctg_name) {
		this.big_ctg_name = big_ctg_name;
	}
	
	
}
