package com.qq.common;
/**
 * 
 * 定义一个数据信信息包,用来区分用户所发的信息的类形,进行不同的包的操作
 *
 */
public interface MessageType {
	String message_succeed="1";//登录成功
	String message_login_fail="2";//登录失败
	String message_comm_mes="3";//普通信息包
	String message_get_onLineFriend="4";//获取在线好友的包的标识
	String message_ret_onLineFriend="5";//返回在线好友的包标识
}
