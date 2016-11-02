package com.qq.client.view;
/**
 * 聊天界面,做一个读取服务器的信息包的线程 ，1.不断读取收到的信息
 * 2.从一个通信的线程读取服务器端的信息包
 */
import javax.swing.*;

import com.qq.client.model.QqClientConServer;
import com.qq.client.msg.MsgAction;
import com.qq.client.tools.ManageQqChat;
import com.qq.common.Message;
import com.qq.common.MessageType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import com.qq.client.model.ManageClientConServerThread;
public class QqChat extends JFrame implements ActionListener,WindowListener{
	JTextArea message;
	JTextField jtf;
	JButton send;
	JPanel jp;
	public String myid,friendid;
	public QqChat(String myid,String friendid){
		this.myid=myid;
		this.friendid=friendid;
		message=new JTextArea();
		jtf=new JTextField(15);
		send=new JButton("发送");
		//注册侦听
		send.addActionListener(this);
		jp=new JPanel(new FlowLayout());
		jp.add(jtf);
		jp.add(send);
		this.setIconImage(new ImageIcon("images//qqlogo.png").getImage());
		this.setTitle(""+myid+"正在和"+friendid+"聊天");
		this.setLayout(new BorderLayout());
		this.add(message,"Center");
		this.add(jp,"South");
		this.setSize(300,200);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//系统退出
		this.setVisible(true);
		
		
	}
	public static void main(String []args){
		QqChat aqChat=new QqChat("","");
	}
	//接收来自服务器的信息包
	public void showmessage(Message ms){
		String info=(""+ms.getSender()+"对"+ms.getGetter()+"说:"+ms.getCon()+"\r\n");
		//加入到聊天界面上
		message.append(info);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==send){
			//建立一个新的信息包，用来发送出去
			Message ms=new Message();
			ms.setMesType(MessageType.message_comm_mes);
			ms.setSender(myid);
			ms.setGetter(friendid);
			ms.setCon(jtf.getText().toString());
			Date date=new Date();
			ms.setDate(date.getYear()+"."+date.getMonth()+"."+date.getDay()+"."+date.getHours()+"."+date.getMinutes()+"."+date.getSeconds());//当前时间
			//发送到服务器中
			try{
				//从HASHMAP的线程管理中取出与好友连接的线程
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getQqClientConServerThread(myid).getS().getOutputStream());
				oos.writeObject(ms);
				System.out.println("信息包!");
			}catch(Exception ex){ex.printStackTrace();}finally{}
			//把我说的话加入聊天对话框
			message.append(""+myid+"说："+jtf.getText()+"\r\n");
		}
	}
	@Override
	public void windowOpened(WindowEvent e) {
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		
		ManageQqChat.rmManageQqChat(myid+" "+friendid);
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
