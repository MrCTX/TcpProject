package com.qq.server.model;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 *���ܣ�������ͷ������ӵ�����Socket
 *SOCKET��HASHMAP�ļ�������
 */
public class ManageClientThread {
	//���û���¼��SOCKET���û���ID����Ϊ��ʶ����HASHMAP��ȥ��Ҫȡ��ʱ����ID�Ž���ȡ��
//							<��ֵ,����>�û���ID�ţ��û���SOCKET
	public static HashMap hm =new HashMap<String ,serverConClientThread>();
	//�����û�SOCKET�ķ���
	public static void addClientThread(String uid,serverConClientThread ct){
//		��������(ָ����ֵ,����)
		hm.put(uid,ct);
	}
	//ͨ���û���ID��ȡ���û�SOCKET�ķ���
	public static serverConClientThread getClientThrea(String uid){
		return (serverConClientThread)hm.get(uid);
	}
	//ȡ�ú������ߵķ���
	public static String getOnLineFriends(){
		//ʹ�õ����������
		Iterator it=hm.keySet().iterator();
		String str=new String();//��ʼΪ��,��NEWʵ����,û����,��Ϊ""����һ���ַ���
		//���������л��������򷵻�true
		while(it.hasNext()){
			//���ص�������һ��Ԫ��
			str+=it.next().toString()+" ";
		}
		return str;
	}
	//
}
