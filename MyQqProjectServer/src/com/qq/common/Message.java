package com.qq.common;
/**
 * ���͵���Ϣ��
 * mesType->1��½�ɹ�
 * mesType->2��½ʧ��
 * mesType->3��ͨ��Ϣ��
 *
 */
//�Զ������ķ�ʽ���ͣ���Ҫʵ���л���ʵ��java.io.Serializable�ӿڣ�
public class Message implements java.io.Serializable{
	//������Ϣ������
	private String mesType;
	private String sender;//�����û�ID
	private String getter;//�����û�ID
	private String con;//���͵���Ϣ
	private String Date;//���͵�����
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
