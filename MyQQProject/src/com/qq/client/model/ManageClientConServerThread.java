package com.qq.client.model;
/**
 * 
 * ���ܣ�����һ������ͻ����ж��SOCKET���߳����������SOCKET���ӵ��߳�
 *��HASHMAP�����������ӵ��߳�
 *�������ɾ�̬�ķ���,������..ʹ��
 */
import java.util.*;

public class ManageClientConServerThread {
	//���û��ĺ��ѵ��˺���Ϊ��ʶֵ���Ѻ��ѵ�SOCKET�̼߳���HASHMAP
	private static HashMap hm=new HashMap<String,QqClientConServerThread>();
	
	//������ѵ�SOCKET�߳� �ķ��� ,������ѵ��˺�ID��ͨ�ŵ�SOCKET
	public static void addQqClientConServerThread(String id,QqClientConServerThread q){
		//����HASHMAP��
		hm.put(id, q);
	}
	//ͨ�����ѵ��˺�ID,ȡ�����ѵ�SOCKETͨ���߳�
	public static QqClientConServerThread getQqClientConServerThread(String id){
		return (QqClientConServerThread)hm.get(id);
	}
}
