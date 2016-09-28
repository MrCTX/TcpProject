package com.qq.common;
/**
 * 传送的信息包
 * mesType->1登陆成功
 * mesType->2登陆失败
 * mesType->3普通信息包
 *
 */
//以对象流的方式传送，需要实序列化（实现java.io.Serializable接口）
public class Message implements java.io.Serializable{
	//定义信息的类型
	private String mesType;
	private String sender;//发送用户ID
	private String getter;//接收用户ID
	private String con;//发送的信息
	private String Date;//发送的日期
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
}
