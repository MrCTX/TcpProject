package com.qq.server.model;

import java.io.*;
import java.net.*;

import com.qq.common.Message;
import com.qq.common.User;


public class QqServer {
	ServerSocket ss;
	Socket s;
	BufferedReader bfr;
	ObjectInputStream ois;
	ObjectOutputStream oos=null;
	
	public QqServer(){
		
		try{
			//循环侦听
			
				//建立连接
			ss=new ServerSocket(9999);
			System.out.println("服务器已启动,在9999侦听,等待连接.....");
			//循环侦听
			while(true){
				s=ss.accept();
				//以对象流的方式读取信息
				ois=new ObjectInputStream(s.getInputStream());//服务器收到的信息
				User u=(User)ois.readObject();
				System.out.println("服务器收到的用用户Id="+u.getUserId()+"服务器收到用户密码="+u.getPasswd());
				//服务器把信息包传发
				
				//简单判断,密码为123456则成功
				Message ms=new Message();//信息包
				oos=new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("1")){
					//返回成功登陆的信息包
					ms.setMesType("1");//成功信息
					oos.writeObject(ms);	//把信息包返回
					//单开一个线程，让其与该客户端保持连接
					serverConClientThread scct=new serverConClientThread(s);
					
					//把建立的连接线程加入到HashMap向量中
					ManageClientThread.addClientThread(u.getUserId(), scct);
					//启动连接的线程
					scct.start();
					
					//当有用户上线时就通知已在线的好友，我上线了，实际上是发一个好友在线的信息包
					//在好友的连接线程连接中进行通知，要取出每一个在线好友的线程，(把我的ID号传进去)
					scct.notifyother(u.getUserId());
					
				}else{
					//返回失败信息
					ms.setMesType("2");
					oos.writeObject(ms);
					//关闭链接
					s.close();
				}
			
		
				
			}
			}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(s!=null)
				s.close();
				if(ss!=null)
				ss.close();
				oos.close();
			}catch(Exception ex){}
		}
		
	}

}
