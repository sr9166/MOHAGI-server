package com.mohagi.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class small_category {
	@Id
	@Column
	String small_ctg_id;		//소분류아이디(pk)
	@Column
	String small_ctg_name;		//소분류이름
	@Column
	String big_ctg_id;			//대분류아이디(fk)
	@Override
	public String toString() {
		return "Small_Category {"
				+ "\"small_ctg_id\":\"" + small_ctg_id + 
				"\", \"small_ctg_name\":\"" + small_ctg_name +
				"\", \"big_ctg_id\":\"" + big_ctg_id + 
				"\"}";
	}
	public String getSmall_ctg_id() {
		return small_ctg_id;
	}
	public void setSmall_ctg_id(String small_ctg_id) {
		this.small_ctg_id = small_ctg_id;
	}
	public String getSmall_ctg_name() {
		return small_ctg_name;
	}
	public void setSmall_ctg_name(String small_ctg_name) {
		this.small_ctg_name = small_ctg_name;
	}
	public String getBig_ctg_id() {
		return big_ctg_id;
	}
	public void setBig_ctg_id(String big_ctg_id) {
		this.big_ctg_id = big_ctg_id;
	}
	
	
}
