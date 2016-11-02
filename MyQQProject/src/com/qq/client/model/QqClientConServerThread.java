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
 * ���ܣ�����������������ӵĿͻ��˵��߳�
 *���û��ɹ���¼�����������Ӧ���߳� 
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
	//���캯��ʱ�����ӵ�SOCKET����
	public QqClientConServerThread(Socket s){
		this.s=s;
	}
	public void run(){
		//�����̺߳�Ӵ����SOCKET�в��϶�ȡ�������е���Ϣ��
		while(true){
			try{
				//��ȡ�������ϵ���Ϣ
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				//��������Ϣ������ȫ�����û�����Ϣ��,Ҫ��ÿһ����Ϣ����ÿһ���û�����������Ӧ����ʾ��Ϣ
				
				//System.out.println("���յ��ӷ�������������Ϣ :"+m.getSender()+"��"+m.getGetter()+"˵"+m.getCon());
				//��ͨ��Ϣ��
				if(m.getMesType().equals(MessageType.message_comm_mes)){
					//�Ѵӷ��������յ�����Ϣ����ʾ���û������������
					//����Ҫ�ҳ��������е���Ϣ������һ�����ѷ����û���,�ٴ����������ȡ��������������Ϣ�����뵽������ʾ
					//��ʾ���ҵ���������ϵ�Ӧ�õĺ��Ѷ���˵�Ļ�Ҳ�����ҵ�ID+" "+���ѵ�ID(Getter���ҵ�ID,Sender�Ǻ��ѵ�ID)
					QqChat qqChat=ManageQqChat.getManageQqChat(m.getGetter()+" "+m.getSender());
					
					if(qqChat!=null){
						//MsgAction.addmsg_msgfile(m);
						//����Ӧ��QQCHAT����ʾ��Ϣ(����һ��MESSAGE����ʾ��Ϣ)SHOWMESSAGE����
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
				//���ص������ߺ��ѵ���Ϣ��,������Ӧ�õ��ø��º����ߺ��ѵķ���
				else if (m.getMesType().equals(MessageType.message_ret_onLineFriend)){
					//��ȡ�������ߵ���Ϣ
					//String con=m.getCon();//������Ϣ��ʾ���� "1"+" "+"2"+" "...... 
					//String friends[]=con.split(" ");//�ַ�������
					String getter=m.getGetter();
					//System.out.println(m.getGetter());
					
					//����ҵĺ����������,�޸��ҵĺ��ѵ����������Ϣ�б�
					//���getter���˺�()�����ҵ��˺�,ȡ��һ��getter�ĺ��ѽ���,ȡ���ҵ��������
					QqMyFriends qqMyFriends=ManageQqFriendList.getManageQqFriendList(getter);
					//���ø������ߺ��ѵķ��� 
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
