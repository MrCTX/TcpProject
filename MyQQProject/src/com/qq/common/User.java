package com.qq.common;

/**
 * 
 * �û���Ϣ�Ķ���
 *
 */
public class User implements java.io.Serializable{
	private String userId;
	private String passwd;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	
}
