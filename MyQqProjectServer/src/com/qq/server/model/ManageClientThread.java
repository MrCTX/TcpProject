package com.qq.server.model;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 *功能：管理与客服端连接的所有Socket
 *SOCKET的HASHMAP的集合向量
 */
public class ManageClientThread {
	//把用户登录的SOCKET用用户的ID号作为标识存入HASHMAP中去，要取出时根据ID号将其取出
//							<键值,类型>用户的ID号，用户的SOCKET
	public static HashMap hm =new HashMap<String ,serverConClientThread>();
	//加入用户SOCKET的方法
	public static void addClientThread(String uid,serverConClientThread ct){
//		加入向量(指定键值,对象)
		hm.put(uid,ct);
	}
	//通过用户的ID号取出用户SOCKET的方法
	public static serverConClientThread getClientThrea(String uid){
		return (serverConClientThread)hm.get(uid);
	}
	//取得好友在线的方法
	public static String getOnLineFriends(){
		//使用迭代器来完成
		Iterator it=hm.keySet().iterator();
		String str=new String();//初始为空,用NEW实例化,没好友,若为""会是一个字符串
		//当迭代器中还有无素则返回true
		while(it.hasNext()){
			//返回迭代的下一个元素
			str+=it.next().toString()+" ";
		}
		return str;
	}
	//
}
