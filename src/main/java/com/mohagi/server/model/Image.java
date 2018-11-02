package com.mohagi.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Image {
	@Id
	@Column
	String id;
	@Column
	String name;
	@Column
	byte[] imgbuffer;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getImagebuffer() {
		return imgbuffer;
	}
	public void setImagebuffer(byte[] imagebuffer) {
		this.imgbuffer = imagebuffer;
	}
	public Image()	 {}
	public Image(String id, String name, byte[] imagebuffer) {
		super();
		this.id = id;
		this.name = name;
		this.imgbuffer = imagebuffer;
	}
	
}
