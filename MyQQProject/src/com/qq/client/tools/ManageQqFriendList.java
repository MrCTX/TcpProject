package com.qq.client.tools;
/**
 * 
 * ���ܣ�������ѣ������� �Ľ�����
 * ��HASHMAP�����ѣ�����������Ϣ�������ͳһ��������
 */
import java.util.*;

import com.qq.client.view.QqMyFriends;

public class ManageQqFriendList {
	//�ú��ѵ�ID��
	public static HashMap hm=new HashMap<String ,QqMyFriends>();
	//�������
	public static void addManageQqFriendList(String qqid,QqMyFriends qqMyFriends){
		hm.put(qqid, qqMyFriends);
	}
	//ȡ������
	public static QqMyFriends getManageQqFriendList(String qqid){
		return (QqMyFriends)hm.get(qqid);
	}
}
