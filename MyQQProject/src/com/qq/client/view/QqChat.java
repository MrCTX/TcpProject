package com.qq.client.view;
/**
 * �������,��һ����ȡ����������Ϣ�����߳� ��1.���϶�ȡ�յ�����Ϣ
 * 2.��һ��ͨ�ŵ��̶߳�ȡ�������˵���Ϣ��
 */
import javax.swing.*;

import com.qq.client.model.QqClientConServer;
import com.qq.common.Message;
import com.qq.common.MessageType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import com.qq.client.model.ManageClientConServerThread;
public class QqChat extends JFrame implements ActionListener{
	JTextArea message;
	JTextField jtf;
	JButton send;
	JPanel jp;
	String myid,friendid;
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
			ms.setDate(new java.util.Date().toString());//��ǰʱ��
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
//	��ȡ�����ϵ���Ϣ���߳�
//	public void run() {
//		ObjectInputStream ois=null;
//		while(true){
//			try{
//				ois=new ObjectInputStream(QqClientConServer.s.getInputStream());
//				Message ms=(Message)ois.readObject();
//				//��ȡ������Ϣ��,ȡ��������Ϣ
//				String info=(""+ms.getSender()+"��"+ms.getGetter()+"˵:"+ms.getCon()+"\r\n");
//				//���뵽���������
//				message.append(info);
//				
//			}catch(Exception e){e.printStackTrace();}
//		}
//	}
}
