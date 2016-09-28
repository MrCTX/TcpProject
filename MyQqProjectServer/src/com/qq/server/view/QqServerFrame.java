package com.qq.server.view;
/**
 * 
 * 功能：管理和监控服务器
 * 可以启动服务器，关闭服务器
 * 开发步骤:
 *1.设置界面
 *2.连通服务器与客户端
 *  完成用户点登录按钮时,把用户的帐号和密码发到服务器进行验证
 *3.完成数据包的对象传送方式
 *4.完成数据包从服务器的转发,单人聊天
 *5.如果好友在线则显示彩色头像,反之,显示灰色头像
 *6.完成好友互相聊天功能，多人聊天>>>>>>>>>
 *7.当好友上线时，提示
 */
import javax.swing.*;

import com.qq.server.model.QqServer;
import java.awt.*;
import java.awt.event.*;

public class QqServerFrame extends JFrame implements ActionListener{
	JButton start,stop;
	JPanel jp;
	
	public QqServerFrame(){
		start=new JButton("启动服务器");
		start.addActionListener(this);
		stop=new JButton("关闭服务器");
		jp=new JPanel(new FlowLayout());
		jp.add(start);
		jp.add(stop);
		this.add(jp,"North");
		this.setSize(500,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==start){
			new QqServer();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqServerFrame qqServerFrame=new QqServerFrame();
	}

}
