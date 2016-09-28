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
			//ѭ������
			
				//��������
			ss=new ServerSocket(9999);
			System.out.println("������������,��9999����,�ȴ�����.....");
			//ѭ������
			while(true){
				s=ss.accept();
				//�Զ������ķ�ʽ��ȡ��Ϣ
				ois=new ObjectInputStream(s.getInputStream());//�������յ�����Ϣ
				User u=(User)ois.readObject();
				System.out.println("�������յ������û�Id="+u.getUserId()+"�������յ��û�����="+u.getPasswd());
				//����������Ϣ������
				
				//���ж�,����Ϊ123456��ɹ�
				Message ms=new Message();//��Ϣ��
				oos=new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("1")){
					//���سɹ���½����Ϣ��
					ms.setMesType("1");//�ɹ���Ϣ
					oos.writeObject(ms);	//����Ϣ������
					//����һ���̣߳�������ÿͻ��˱�������
					serverConClientThread scct=new serverConClientThread(s);
					
					//�ѽ����������̼߳��뵽HashMap������
					ManageClientThread.addClientThread(u.getUserId(), scct);
					//�������ӵ��߳�
					scct.start();
					
					//�����û�����ʱ��֪ͨ�����ߵĺ��ѣ��������ˣ�ʵ�����Ƿ�һ���������ߵ���Ϣ��
					//�ں��ѵ������߳������н���֪ͨ��Ҫȡ��ÿһ�����ߺ��ѵ��̣߳�(���ҵ�ID�Ŵ���ȥ)
					scct.notifyother(u.getUserId());
					
				}else{
					//����ʧ����Ϣ
					ms.setMesType("2");
					oos.writeObject(ms);
					//�ر�����
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
