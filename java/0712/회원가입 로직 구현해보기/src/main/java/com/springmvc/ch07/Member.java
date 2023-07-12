package com.springmvc.ch07;

import java.util.Date;

public class Member {
	private String id;
	private String passwd;
	private String city;
	private String sex;
	private String[] hobby;
	private Date birth;
	
	public Member() {
		super();
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String paswd) {
		this.passwd = paswd;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
}
