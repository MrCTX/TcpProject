package com.qq.client.tools;
/**
 * 
 * ���ܣ������û�����������������Ϣ������
 *��HASHMAP���û���ÿһ�����ѵ������������Ӧ����Ϣ������������ͳһ����
 *����:���û������������Ķ���ʱ�Ѹ����������뵽HASHMAP��
 */
import java.util.*;
import com.qq.client.model.*;
import com.qq.client.view.*;

public class ManageQqChat {
	//�õ�ǰ�û���ID������ѵ�ID����Ϊ��ʶֵ ��Ӧÿһ���������
	private static HashMap hm=new HashMap<String ,QqChat>();
	//����������浽HASHMAP�з���
	public static void addManageQqChat(String loginandfriendID,QqChat qqChat){
		hm.put(loginandfriendID, qqChat);
	}
	//ȡ���������ķ���,ͨ���û��ͺ��ѵ�ID��ȡ��ȷ�����������
	public static QqChat getManageQqChat(String loginandfriendID){
		return (QqChat)hm.get(loginandfriendID);
	}
	
	public static QqChat rmManageQqChat(String loginandfriendID){
		return (QqChat)hm.remove(loginandfriendID);
	}
}
