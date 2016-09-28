package com.qq.client.model;
/**
 * 
 * 功能：这是客户端连接服务器的后台
 *
 */
import java.io.*;
import java.net.*;
import java.util.*;

import com.qq.common.Message;
import com.qq.common.User;
import com.qq.client.model.QqClientConServerThread;
import com.qq.client.model.ManageClientConServerThread;

public class QqClientConServer {

	//要在QQCHAR中调用SOCKET
	//1、静态方法
	//public static Socket s;
	public Socket s;
	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	//发送第一次请求(登陆成功返回1；失败则返回2；普通信息则的3）
	public boolean sendLoginInfoToServer(Object o){
		Boolean b=false;
		try{
			//发送用户的登陆信息
			System.out.println("Send");
			//建立与好友连接的SOCKET
			s=new Socket("127.0.0.1",9999);
			oos=new ObjectOutputStream(s.getOutputStream());
//			User u=(User)o;
//			System.out.println("Id="+u.getUserId()+"Passwd="+u.getPasswd());
			oos.writeObject(o);
			
			System.out.println("Send");
//			接收返回的值
			ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();
			//用户成功登录
			if(ms.getMesType().equals("1")){
				//用户成功登录后启动相应的连接线程,把国家连接好友的SOCKET传入SOCKET线程中
				QqClientConServerThread qccs=new QqClientConServerThread(s);
				//启动该通信线程
				qccs.start();
				//把建立的好友连接线程加入到HASHMAP中
				ManageClientConServerThread.addQqClientConServerThread(((User)o).getUserId(), qccs);
				//
				b=true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return b;
	}
}
