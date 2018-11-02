package com.mohagi.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class location {
	
	@Id
	@Column
	String loc_id;		//장소아이디(pk)
	@Column
	String loc_name;	//장소이름
	@Column
	String loc_addr;	//장소주소
	@Column
	String loc_number;	//장소전화번호
	@Column
	String loc_time;	//장소운영시간
	@Column
	String big_ctg;		//대분류(fk)
	@Column
	String small_ctg;	//소분류(fk)
	@Column
	String latitude;	//장소위도
	@Column
	String longitude;	//장소경도
	@Column
	String star;		//장소별점
	
	/*
	 * 맛집 카테고리에만 있는 부분
	 */
	@Column
	String category=null;	//장소분류
	@Column
	String fav_man=null;	//남자선호도
	@Column
	String fav_woman=null;	//여자선호도
	@Column
	String atmosphere=null;	//분위기
	@Column
	String topic=null;		//인기토픽
	@Column
	String purpose=null;	//찾는목적
	@Column
	String fav_10=null;		//10대 선호도
	@Column
	String fav_20=null;		//20대 선호도
	@Column
	String fav_30=null;		//30대 선호도
	@Column
	String fav_40=null;		//40대 선호도
	@Column
	String fav_50=null;		//50대 선호도
	@Column
	String fav_60=null;		//60대 선호도
	@Override
	public String toString() {
		return "Location {"
				+ "\"loc_id\":\"" + loc_id + 
				"\", \"loc_name\":\"" + loc_name + 
				"\", \"loc_addr\":\"" + loc_addr +
				"\", \"loc_number\":\"" + loc_number +
				"\", \"loc_time\":\"" + loc_time +
				"\", \"big_ctg\":\"" + big_ctg + 
				"\", \"small_ctg\":\"" + small_ctg +
				"\", \"latitude\":\"" + latitude +
				"\", \"longitude\":\"" + longitude +
				"\", \"star\":\"" + star +
				"\", \"category\":\"" + category +
				"\", \"fav_man\":\"" + fav_man +
				"\", \"fav_woman\":\"" + fav_woman +
				"\", \"atmosphere\":\"" + atmosphere +
				"\", \"topic\":\"" + topic +
				"\", \"purpose\":\"" + purpose +
				"\", \"fav_10\":\"" + fav_10 +
				"\", \"fav_20\":\"" + fav_20 +
				"\", \"fav_30\":\"" + fav_30 +
				"\", \"fav_40\":\"" + fav_40 +
				"\", \"fav_50\":\"" + fav_50 +
				"\", \"fav_60\":\"" + fav_60 +
				"\"}";
	}
	public String getLoc_id() {
		return loc_id;
	}
	public void setLoc_id(String loc_id) {
		this.loc_id = loc_id;
	}
	public String getLoc_name() {
		return loc_name;
	}
	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}
	public String getLoc_addr() {
		return loc_addr;
	}
	public void setLoc_addr(String loc_addr) {
		this.loc_addr = loc_addr;
	}
	public String getLoc_number() {
		return loc_number;
	}
	public void setLoc_number(String loc_number) {
		this.loc_number = loc_number;
	}
	public String getLoc_time() {
		return loc_time;
	}
	public void setLoc_time(String loc_time) {
		this.loc_time = loc_time;
	}
	public String getBig_ctg() {
		return big_ctg;
	}
	public void setBig_ctg(String big_ctg) {
		this.big_ctg = big_ctg;
	}
	public String getSmall_ctg() {
		return small_ctg;
	}
	public void setSmall_ctg(String small_ctg) {
		this.small_ctg = small_ctg;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public location()	{}
	public location(String loc_id, String loc_name, String loc_addr, String loc_number, String loc_time, String big_ctg,
			String small_ctg, String latitude, String longitude, String star) {
		super();
		this.loc_id = loc_id;
		this.loc_name = loc_name;
		this.loc_addr = loc_addr;
		this.loc_number = loc_number;
		this.loc_time = loc_time;
		this.big_ctg = big_ctg;
		this.small_ctg = small_ctg;
		this.latitude = latitude;
		this.longitude = longitude;
		this.star = star;
	}
	public location(String loc_id, String loc_name, String loc_addr, String loc_time, String big_ctg,
			String small_ctg, String latitude, String longitude, String star, String category, String fav_man,
			String fav_woman, String atmosphere, String topic, String purpose, String fav_10, String fav_20,
			String fav_30, String fav_40, String fav_50, String fav_60) {
		super();
		this.loc_id = loc_id;
		this.loc_name = loc_name;
		this.loc_addr = loc_addr;
		this.loc_time = loc_time;
		this.big_ctg = big_ctg;
		this.small_ctg = small_ctg;
		this.latitude = latitude;
		this.longitude = longitude;
		this.star = star;
		this.category = category;
		this.fav_man = fav_man;
		this.fav_woman = fav_woman;
		this.atmosphere = atmosphere;
		this.topic = topic;
		this.purpose = purpose;
		this.fav_10 = fav_10;
		this.fav_20 = fav_20;
		this.fav_30 = fav_30;
		this.fav_40 = fav_40;
		this.fav_50 = fav_50;
		this.fav_60 = fav_60;
	}
	
}
