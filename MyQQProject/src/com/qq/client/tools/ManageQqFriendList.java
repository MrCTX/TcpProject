package com.qq.client.tools;
/**
 * 
 * 功能：管理好友，黑名单 的界面类
 * 用HASHMAP将好友，黑名单的信息与其界面统一管理起来
 */
import java.util.*;

import com.qq.client.view.QqMyFriends;

public class ManageQqFriendList {
	//用好友的ID号
	public static HashMap hm=new HashMap<String ,QqMyFriends>();
	//加入好友
	public static void addManageQqFriendList(String qqid,QqMyFriends qqMyFriends){
		hm.put(qqid, qqMyFriends);
	}
	//取出好友
	public static QqMyFriends getManageQqFriendList(String qqid){
		return (QqMyFriends)hm.get(qqid);
	}
}
