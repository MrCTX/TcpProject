package com.qq.client.view;
/**
 * �������,��һ����ȡ����������Ϣ�����߳� ��1.���϶�ȡ�յ�����Ϣ
 * 2.��һ��ͨ�ŵ��̶߳�ȡ�������˵���Ϣ��
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
		send=new JButton("����");
		//ע������
		send.addActionListener(this);
		jp=new JPanel(new FlowLayout());
		jp.add(jtf);
		jp.add(send);
		this.setIconImage(new ImageIcon("images//qqlogo.png").getImage());
		this.setTitle(""+myid+"���ں�"+friendid+"����");
		this.setLayout(new BorderLayout());
		this.add(message,"Center");
		this.add(jp,"South");
		this.setSize(300,200);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ϵͳ�˳�
		this.setVisible(true);
		
		
	}
	public static void main(String []args){
		QqChat aqChat=new QqChat("","");
	}
	//�������Է���������Ϣ��
	public void showmessage(Message ms){
		String info=(""+ms.getSender()+"��"+ms.getGetter()+"˵:"+ms.getCon()+"\r\n");
		//���뵽���������
		message.append(info);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==send){
			//����һ���µ���Ϣ�����������ͳ�ȥ
			Message ms=new Message();
			ms.setMesType(MessageType.message_comm_mes);
			ms.setSender(myid);
			ms.setGetter(friendid);
			ms.setCon(jtf.getText().toString());
			Date date=new Date();
			ms.setDate(date.getYear()+"."+date.getMonth()+"."+date.getDay()+"."+date.getHours()+"."+date.getMinutes()+"."+date.getSeconds());//��ǰʱ��
			//���͵���������
			try{
				//��HASHMAP���̹߳�����ȡ����������ӵ��߳�
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getQqClientConServerThread(myid).getS().getOutputStream());
				oos.writeObject(ms);
				System.out.println("��Ϣ��!");
			}catch(Exception ex){ex.printStackTrace();}finally{}
			//����˵�Ļ���������Ի���
			message.append(""+myid+"˵��"+jtf.getText()+"\r\n");
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
