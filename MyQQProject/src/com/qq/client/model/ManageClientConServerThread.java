package com.qq.client.model;
/**
 * 
 * 功能：这是一个管理客户端中多个SOCKET类线程与服务器的SOCKET连接的线程
 *用HASHMAP管理用于连接的线程
 *必须做成静态的方法,才能在..使用
 */
import java.util.*;

public class ManageClientConServerThread {
	//以用户的好友的账号作为标识值，把好友的SOCKET线程加入HASHMAP
	private static HashMap hm=new HashMap<String,QqClientConServerThread>();
	
	//加入好友的SOCKET线程 的方法 ,传入好友的账号ID，通信的SOCKET
	public static void addQqClientConServerThread(String id,QqClientConServerThread q){
		//加入HASHMAP中
		hm.put(id, q);
	}
	//通过好友的账号ID,取出好友的SOCKET通信线程
	public static QqClientConServerThread getQqClientConServerThread(String id){
		return (QqClientConServerThread)hm.get(id);
	}
}
