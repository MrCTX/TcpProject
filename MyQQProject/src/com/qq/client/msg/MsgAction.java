package com.qq.client.msg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import com.qq.common.Message;

public class MsgAction {
	public static ArrayList<String> SENDER_ID=new ArrayList<String>();      //保存未读消息的发送者id
	public static ArrayList<String> MSG_KEY=new ArrayList<String>();   //保存properties的key
	public static FileOutputStream outputStream;
	public static FileOutputStream getOutputStream() throws FileNotFoundException{
		return outputStream= new FileOutputStream("msg//message.properties",true);
	}

	/**
	 * 创建properties键值对，格式为sender_getter_Date=con
	 * @param m Message对象，从服务端得到
	 * @throws FileNotFoundException 
	 */
	public static void addmsg_msgfile(Message m){
		SENDER_ID.add(m.getSender());
		Properties properties=new Properties();
		try {
			String key=m.getSender()+"_"+m.getGetter()+"_"+m.getDate();
			properties.setProperty(key, m.getCon());
			properties.store(getOutputStream(), null);
			MSG_KEY.add(key);
		} catch (Exception e) {
			System.out.println("addmsg_msgfile方法出错。。。");
		}
	}
	
	/**
	 * 传入指定格式的键(sender_getter_Date)得到消息内容并清除键值对
	 * @param sender_getter_Date
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static String getMsg(String sender_getter_Date) throws FileNotFoundException, IOException{
		Properties properties=new Properties();
		properties.load(new FileInputStream("msg//message.properties"));
		String con=properties.getProperty(sender_getter_Date);
		properties.remove(sender_getter_Date);
		return con;
	}
	
	public static ArrayList<String> getMsgKey(String user_id,String friend_id){
		Properties properties = new Properties();
		ArrayList<String> key=new ArrayList<String>();
		try {
			System.out.println("getMsgKey...");
			properties.load(new FileInputStream("msg//message.properties"));
			for(int i=0;i<MSG_KEY.size();i++){
				String user,friend;
				String[] msg_key=MSG_KEY.get(i).split("_");
				System.out.println(MSG_KEY.get(i));
				friend=msg_key[0];
				user=msg_key[1];
				if(user.equals(user_id)&&friend.equals(friend_id)){
					key.add(MSG_KEY.get(i));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}
}
