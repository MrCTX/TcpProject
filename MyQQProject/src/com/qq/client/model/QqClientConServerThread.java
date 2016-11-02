package com.qq.client.model;
import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.JLabel;

import com.qq.client.msg.MsgAction;
import com.qq.client.tools.ManageQqChat;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.client.view.QqChat;
import com.qq.client.view.QqMyFriends;
import com.qq.common.*;
/**
 * 
 * 功能：保持与服务器的连接的客户端的线程
 *当用户成功登录后就启动其相应的线程 
 */
public class QqClientConServerThread extends Thread{
	private Socket s=null;
	public static QqMyFriends qqMyFriends=null;
	public Socket getS() {
		return s;
	}
	public void setS(Socket s) {
		this.s = s;
	}
	//构造函数时把连接的SOCKET传入
	public QqClientConServerThread(Socket s){
		this.s=s;
	}
	public void run(){
		//启动线程后从传入的SOCKET中不断读取服务器中的信息包
		while(true){
			try{
				//读取服务器上的信息
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				//读到的信息是来自全部的用户的信息包,要把每一个信息包与每一个用户的聊天界面对应并显示信息
				
				//System.out.println("接收到从服务器传来的信息 :"+m.getSender()+"对"+m.getGetter()+"说"+m.getCon());
				//普通信息包
				if(m.getMesType().equals(MessageType.message_comm_mes)){
					//把从服务器中收到的信息包显示在用户的聊天界面上
					//首先要找出服务器中的信息包的哪一个好友发给用户的,再从聊天界面中取出该聊天界面把信息包加入到里面显示
					//显示在我的聊天界面上的应该的好友对我说的话也就是我的ID+" "+好友的ID(Getter是我的ID,Sender是好友的ID)
					QqChat qqChat=ManageQqChat.getManageQqChat(m.getGetter()+" "+m.getSender());
					
					if(qqChat!=null){
						//MsgAction.addmsg_msgfile(m);
						//在相应的QQCHAT中显示信息(传入一个MESSAGE并显示信息)SHOWMESSAGE方法
						MsgAction.addmsg_msgfile(m);
						qqChat.showmessage(m);
					}
					else{
						int sender_id=Integer.valueOf(m.getSender());
						JLabel jLabel= ManageQqFriendList.getManageQqFriendList(m.getGetter()).hy[sender_id-1];
						jLabel.setForeground(Color.GREEN);
						MsgAction.addmsg_msgfile(m);
					}
				}
				//返回的是在线好友的信息包,在这里应该调用更新好在线好友的方法
				else if (m.getMesType().equals(MessageType.message_ret_onLineFriend)){
					//获取好友在线的信息
					//String con=m.getCon();//好友信息表示方法 "1"+" "+"2"+" "...... 
					//String friends[]=con.split(" ");//字符串分组
					String getter=m.getGetter();
					//System.out.println(m.getGetter());
					
					//获的我的好友在线情况,修改我的好友的在线情况信息列表
					//根椐getter的账号()返回我的账号,取出一个getter的好友界面,取出我的聊天界面
					QqMyFriends qqMyFriends=ManageQqFriendList.getManageQqFriendList(getter);
					//调用更新在线好友的方法 
					if(qqMyFriends!=null){
						qqMyFriends.upDateFriend(m);
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
	}
}
