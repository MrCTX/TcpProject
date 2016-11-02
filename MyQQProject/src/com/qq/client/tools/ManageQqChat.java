package com.qq.client.tools;
/**
 * 
 * 功能：管理用户与各个好友聊天的信息包的类
 *用HASHMAP将用户与每一个好友的聊天界面与相应的信息包关联起来，统一管理
 *加入:当用户点了与好聊天的动作时把该聊天界面加入到HASHMAP中
 */
import java.util.*;
import com.qq.client.model.*;
import com.qq.client.view.*;

public class ManageQqChat {
	//用当前用户的ID号与好友的ID号作为标识值 对应每一个聊天界面
	private static HashMap hm=new HashMap<String ,QqChat>();
	//加入聊天界面到HASHMAP中方法
	public static void addManageQqChat(String loginandfriendID,QqChat qqChat){
		hm.put(loginandfriendID, qqChat);
	}
	//取出聊天界面的方法,通过用户和好友的ID号取出确定的聊天界面
	public static QqChat getManageQqChat(String loginandfriendID){
		return (QqChat)hm.get(loginandfriendID);
	}
	
	public static QqChat rmManageQqChat(String loginandfriendID){
		return (QqChat)hm.remove(loginandfriendID);
	}
}
