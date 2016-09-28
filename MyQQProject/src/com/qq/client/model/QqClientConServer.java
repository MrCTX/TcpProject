package com.qq.client.model;
/**
 * 
 * ���ܣ����ǿͻ������ӷ������ĺ�̨
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

	//Ҫ��QQCHAR�е���SOCKET
	//1����̬����
	//public static Socket s;
	public Socket s;
	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	//���͵�һ������(��½�ɹ�����1��ʧ���򷵻�2����ͨ��Ϣ���3��
	public boolean sendLoginInfoToServer(Object o){
		Boolean b=false;
		try{
			//�����û��ĵ�½��Ϣ
			System.out.println("Send");
			//������������ӵ�SOCKET
			s=new Socket("127.0.0.1",9999);
			oos=new ObjectOutputStream(s.getOutputStream());
//			User u=(User)o;
//			System.out.println("Id="+u.getUserId()+"Passwd="+u.getPasswd());
			oos.writeObject(o);
			
			System.out.println("Send");
//			���շ��ص�ֵ
			ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();
			//�û��ɹ���¼
			if(ms.getMesType().equals("1")){
				//�û��ɹ���¼��������Ӧ�������߳�,�ѹ������Ӻ��ѵ�SOCKET����SOCKET�߳���
				QqClientConServerThread qccs=new QqClientConServerThread(s);
				//������ͨ���߳�
				qccs.start();
				//�ѽ����ĺ��������̼߳��뵽HASHMAP��
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
