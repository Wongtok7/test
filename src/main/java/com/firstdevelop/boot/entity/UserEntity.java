package com.firstdevelop.boot.entity;
/**
 * 
 * @author study エンティティクラスの定義
 *
 */
public class UserEntity {

	private Long id;
	
	private String username;
	
	private String password;
	
	private int stuID;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStuID(int stuID) {
		this.stuID = stuID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public int getStuID() {
		return stuID;
	}

	public void setstuID(int stuID) {
		this.stuID = stuID;
	}

}
