package com.qq.server.model;
/**
 * 功能：单独与每一个客户端连接的线程
 * 保持与客户端的连连接的线程
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.MessageType;
import com.qq.common.Message;

public class serverConClientThread extends Thread{
	Socket s;
	public Socket getS() {
		return s;
	}
	public void setS(Socket s) {
		this.s = s;
	}
	//把该Socket的连接传入赋给S
	public serverConClientThread(Socket s){
		//把服务器建立的连接赋给S
		this.s=s;
	}
	//通知在线好友，我上线了的方法,
	public void notifyother(String args){
		HashMap hm=ManageClientThread.hm;
		//取出在线好友的线程
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			//取出在线好友的ID号
			String online=it.next().toString();
			//建立信息包,信息包应该发到好友的客户端
			Message m=new Message();
			m.setCon(args);//我的ID号
			m.setMesType(MessageType.message_ret_onLineFriend);
			//取出好友的SOCKET，建立连接
			try{
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientThread.getClientThrea(online).getS().getOutputStream());
				m.setGetter(online);
				oos.writeObject(m);
				
			}catch(Exception ex){ex.printStackTrace();}
		}
	}
	public void run(){
	
			//这里可以接收客户端的信息
		ObjectInputStream ois=null;
			try{
				//不断地接收数据包
				while(true){
					//如果是普通信息包,则转发
					
					//如果是请求在线好友信息包,则取的在线好友的信息并返回
					ois=new ObjectInputStream(s.getInputStream());
					//收到的信息包
					Message ms=(Message)ois.readObject();
					//后台收到的信息包
					if(ms.getMesType().equals(MessageType.message_comm_mes)){
						System.out.println(ms.getSender()+"对"+ms.getGetter()+"说"+ms.getCon());
						
						//取得接收人的通信线程，实现信息包的转发
						serverConClientThread scct=ManageClientThread.getClientThrea(ms.getGetter());//通信好友的线程
						//信息包的转发
						ObjectOutputStream oos=new ObjectOutputStream(scct.s.getOutputStream());
						oos.writeObject(ms);
					}else if(ms.getMesType().equals(MessageType.message_get_onLineFriend)){
						//获得在线好友的情况,并把在线好友的情况返回
						//获取好友的在线情况应该在启动好友的SOCKET线程时有记录,每个好友上线都分配到相应的SOCKET
	
						String str=ManageClientThread.getOnLineFriends();
						//构建信息包
						Message m=new Message();
						m.setMesType(MessageType.message_ret_onLineFriend);
						m.setCon(str);
						m.setGetter(ms.getSender());
						System.out.println("好友在线请求："+ms.getSender());
						//信息包的返回
						ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
						oos.writeObject(m);
						
						
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
	}
}
