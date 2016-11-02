package com.qq.client.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.qq.client.msg.MsgAction;
import com.qq.client.tools.*;
import com.qq.common.Message;
/**
 * 好友列表界面
 * 
 *
 */
public class QqMyFriends extends JFrame implements ActionListener{
	JPanel jp1,jp2,jp3,
		hy_jp,msr_jp,hmd_jp,
		jp1_1,jp2_1,jp3_1;
	JButton hy_jb1,hy_jb2,hy_jb3,
		msr_jb1,msr_jb2,msr_jb3,
		hmd_jb1,hmd_jb2,hmd_jb3;
	JScrollPane hy_jsb,msr_jsb,hmd_jsb;
	//JTextArea hy_jta;
	CardLayout c1;
	Container c=null;
	String myid,friendid;
	String ownerId;
	public JLabel[] hy;
	public QqMyFriends(String myid){
		//第一个好友的Panel
		jp1=new JPanel(new BorderLayout());
		hy_jb1=new JButton("我的好友");
		jp1_1=new JPanel(new GridLayout(2,1));
		//jp1_1.setLayout(new GridLayout(2,1));
		hy_jb2=new JButton("陌生人");
		hy_jb2.addActionListener(this);
		hy_jb3=new JButton("黑名单");
		hy_jb3.addActionListener(this);
		jp1_1.add(hy_jb2);
		jp1_1.add(hy_jb3);
		
		hy=new JLabel[50];
		hy_jp=new JPanel(new GridLayout(50,1,4,4));//(行，列，行间隔，列间隔）
		
		for(int i=0;i<hy.length;i++){
			hy[i]=new JLabel(i+1+"",new ImageIcon("images/qqlogo.png"),JLabel.LEFT);
			//好友在线时显示彩色头像,反之灰色
			hy[i].setEnabled(false);
			//设置一个好友在线的列表ownerId
			if(hy[i].getText().equals(myid)){
				hy[i].setEnabled(true);
			}
			hy_jp.add(hy[i]);
			hy[i].addMouseListener(new listener());
		}
		hy_jsb=new JScrollPane(hy_jp);
		
		jp1.add(hy_jb1,"North");
		jp1.add(hy_jsb,"Center");		
		jp1.add(jp1_1,"South");
		
		///第二个陌生人的Panel
		
		jp2=new JPanel(new BorderLayout());
		msr_jb1=new JButton("我的好友");
		msr_jb1.addActionListener(this);
		jp2_1=new JPanel(new GridLayout(2,1));
		//jp1_1.setLayout(new GridLayout(2,1));
		msr_jb2=new JButton("陌生人");
		msr_jb3=new JButton("黑名单");
		msr_jb3.addActionListener(this);
		jp2_1.add(msr_jb1);
		jp2_1.add(msr_jb2);
		
		JLabel[]msr=new JLabel[30];
		msr_jp=new JPanel(new GridLayout(30,1,4,4));//(行，列，行间隔，列间隔）
		
		for(int i=0;i<msr.length;i++){
			msr[i]=new JLabel(i+1+"",new ImageIcon("images/tmlogo.png"),JLabel.LEFT);
			//好友在线时显示彩色头像,反之灰色
			msr[i].setEnabled(false);
			//设置一个好友在线的列表ownerId
			if(msr[i].getText().equals(myid)){
				msr[i].setEnabled(true);
			}
			msr_jp.add(msr[i]);
			msr[i].addMouseListener(new listener());
		}
		msr_jsb=new JScrollPane(msr_jp);
		
		jp2.add(jp2_1,"North");
		jp2.add(msr_jsb,"Center");		
		jp2.add(msr_jb3,"South");
		
		//第三个黑名单的Panel
		
		jp3=new JPanel(new BorderLayout());
		hmd_jb1=new JButton("我的好友");
		hmd_jb1.addActionListener(this);
		jp3_1=new JPanel(new GridLayout(3,1));
		//jp1_1.setLayout(new GridLayout(2,1));
		hmd_jb2=new JButton("陌生人");
		hmd_jb2.addActionListener(this);
		hmd_jb3=new JButton("黑名单");
		jp3_1.add(hmd_jb1);
		jp3_1.add(hmd_jb2);
		jp3_1.add(hmd_jb3);
		
		JLabel[]hmd=new JLabel[10];
		hmd_jp=new JPanel(new GridLayout(10,1,4,4));//(行，列，行间隔，列间隔）
		
		for(int i=0;i<hmd.length;i++){
			hmd[i]=new JLabel(i+1+"",new ImageIcon("images/tmlogo.png"),JLabel.LEFT);
			hmd_jp.add(msr[i]);
			hmd[i].addMouseListener(new listener());
		}
		hmd_jsb=new JScrollPane(hmd_jp);
		
		jp3.add(jp3_1,"North");
		jp3.add(hmd_jsb,"Center");		
	//	jp3.add(hmd_jb3,"South");
		
		//this设置为卡片布局
		c1=new CardLayout();
		c=getContentPane();
		c.setLayout(c1);
		c.add(jp1,"1");
		c.add(jp2,"2");
		c.add(jp3,"3");
		
		this.myid=myid;
		this.setIconImage(new ImageIcon("images//framelogo.png").getImage());
		this.setTitle(myid);
		this.setSize(150,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new QqMyFriends(args[0]);
	}
	
	/**
	 * 修改，将所有好友设为下线状态，用户本身不变
	 */
	public void initFrinder(){
		for(int j=0;j<hy.length;j++){
			if(hy[j].getText().equals(myid)){
				
			}
			else
				hy[j].setEnabled(false);
		}
	}
	
	//建立一个更新在线好友的方法 ,应该在管理客户线程的类中调用,当客户端收到服务器返回的在线好友信息包时调用
	public void upDateFriend(Message m){
		initFrinder();
		
		//把信息包的好友在线内容取出,分成字符串数组
		String onlinefriend[]=m.getCon().split(" ");
		for(int i=0;i<onlinefriend.length;i++){
			//取得在线好友的ID,更新好友的列表,好友的ID是从 1 开始的,而好友的LABEL是从0 开始
			hy[Integer.parseInt(onlinefriend[i])-1].setEnabled(true);
			
		}
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==hy_jb2){
			c1.show(c,"2");
		}
		if(e.getSource()==hy_jb3){
			c1.show(c, "3");
		}
		if(e.getSource()==msr_jb1){
			c1.show(c, "1");
		}
		if(e.getSource()==msr_jb3){
			c1.show(c, "3");
		}
		if(e.getSource()==hmd_jb2){
			c1.show(c, "2");
		}
		if(e.getSource()==hmd_jb1){
			c1.show(c, "1");
		}
	}
	class listener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getClickCount()==2){
				friendid=((JLabel)e.getSource()).getText();
				((JLabel)e.getSource()).setForeground(Color.black);
				last_color=Color.black;
				//System.out.println("DoubleClick");
				//启动好友聊天界面
				//好友不在线则不能和好友聊天
				if(((JLabel)e.getSource()).isEnabled()){
					//不能和自己聊天
					if(friendid.equals(myid)!=true){
						QqChat qqChat=new QqChat(myid,friendid);
						/*
						 * 读取msg文件，看是否有预存消息
						 */
						ArrayList<String> msg_key=MsgAction.getMsgKey(myid, friendid);
						if(msg_key!=null){
							System.out.println("msg_key不为空...");
							for(int i=0;i<msg_key.size();i++){
								Message message=new Message();
								String[] msgkey=msg_key.get(i).split("_");
								System.out.println(msg_key.get(i));
								message.setGetter(msgkey[1]);
								message.setSender(msgkey[0]);
								try {
									message.setCon(MsgAction.getMsg(msg_key.get(i)));
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								qqChat.showmessage(message);
								
								System.out.println("msg:"+message.getCon());
							}
						}
						//把用户与好友聊天的界面加入到HASHMAP中
						ManageQqChat.addManageQqChat(myid+" "+friendid, qqChat);
					}
				}
			}
		}

		private Color last_color=null;
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
				JLabel jl=(JLabel)e.getSource();
				last_color=jl.getForeground();
				jl.setForeground(Color.PINK);
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			JLabel jl=(JLabel)e.getSource();
			jl.setForeground(last_color);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
