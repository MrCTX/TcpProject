package com.qq.client.view;
import javax.swing.*;

import com.qq.client.model.ManageClientConServerThread;
import com.qq.client.model.QqClientUser;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.common.Message;
import com.qq.common.User;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.qq.common.MessageType;;
/**
 * 我的QQ的登陆界面
 * 
 *
 */
public class QqClinterLoging extends JFrame implements ActionListener{
	//北部是一个JPane
	//中间是一个选项卡
	//南部是一个JPane
	JPanel jp1,jp2,jp3,jp4;
	JButton jb1,jb2,jb3,jp2_jb1;
	JLabel jl1,jp2_jl1,jp2_jl2,jp2_jl3,jp2_jl4;
	JTextField jtf;
	JPasswordField jpf;
	JCheckBox jcb1,jcb2;
	JTabbedPane jtp;//选项卡面板
	String myid,friendid;
	
	public QqClinterLoging(){
		jl1=new JLabel(new ImageIcon("images/top.png"));
		this.add(jl1,"North");
		
		jp1=new JPanel();
		jp1.setLayout(new FlowLayout());
		jb1=new JButton("登陆");
		//响应登陆
		jb1.addActionListener(this);
		jb2=new JButton("取消");
		jb3=new JButton("注册向导");
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		this.add(jp1,"South");
		
		jtp=new JTabbedPane();
		jp2_jl1=new JLabel("QQ号码",JLabel.CENTER);
		jp2_jl2=new JLabel("QQ密码",JLabel.CENTER);
		jp2_jl3=new JLabel("忘记密码");
		jp2_jl3.setForeground(Color.blue);
		jp2_jl4=new JLabel("申请密码保护");
		jp2_jl4.setForeground(Color.blue);
		jp2_jb1=new JButton("清除号码");
		jcb1=new JCheckBox("隐身登陆");
		jcb2=new JCheckBox("记住密码");
		jp2=new JPanel();
		jtf=new JTextField();
		jpf=new JPasswordField();
		jp2.setLayout(new GridLayout(3,3));
		jp2.add(jp2_jl1);
		jp2.add(jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jl2);
		jp2.add(jpf);
		jp2.add(jp2_jl3);
		jp2.add(jcb1);
		jp2.add(jcb2);
		jp2.add(jp2_jl4);
		jtp.add("QQ号码",jp2);
		jp3=new JPanel();
		jtp.add("QQ号码",jp3);
		jp4=new JPanel();
		jtp.add("QQ号码",jp4);
		this.setIconImage(new ImageIcon("images//framelogo.png").getImage());
		this.add(jtp,"Center");
		this.setSize(340,250);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new QqClinterLoging();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//用户登陆事件
		if(e.getSource()==jb1){
			QqClientUser qqClientUser =new QqClientUser();
			//建立用户信息对象,用于发送给服务器,完成验证
			User u=new User();
			u.setUserId(myid=jtf.getText().trim());//去除两边的空格
			u.setPasswd(new String(jpf.getPassword()));
			//发送用户的信息包(用户合法则返回true)
			if(qqClientUser.checkUser(u)){
				//认证成功,先把用户的聊天界面加入到HASHMAP中,再发送信息包,
				//因为发信息包过去后,将在信息包返回到界面管理中就更新好友在线信息
				
				//进入好友列表界面
				QqMyFriends qqMyFriends=new QqMyFriends(myid);
				//把建立的好友界面加入到聊天界面的HASHMAP中
				ManageQqFriendList.addManageQqFriendList(myid, qqMyFriends);
				
				
				//向服务器发送一个要求得到在线好友列表的信息包，
				try{
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getQqClientConServerThread(u.getUserId()).getS().getOutputStream());
					//建立一个在线好友请求信息包,然后发送
					Message m=new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
					//指明我要的是这个QQ账号的好友情况
					m.setSender(u.getUserId());
					
					oos.writeObject(m);
					
					
					
				}catch(Exception ex){ex.printStackTrace();}
				
				//关闭登陆界面
				this.dispose();
			}else{
				//提示用户名，密码错误
				JOptionPane.showMessageDialog(this, "用户名，密码错误!");
				
			}
		}
	}

}
