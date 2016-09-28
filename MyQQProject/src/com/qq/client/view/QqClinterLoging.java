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
 * �ҵ�QQ�ĵ�½����
 * 
 *
 */
public class QqClinterLoging extends JFrame implements ActionListener{
	//������һ��JPane
	//�м���һ��ѡ�
	//�ϲ���һ��JPane
	JPanel jp1,jp2,jp3,jp4;
	JButton jb1,jb2,jb3,jp2_jb1;
	JLabel jl1,jp2_jl1,jp2_jl2,jp2_jl3,jp2_jl4;
	JTextField jtf;
	JPasswordField jpf;
	JCheckBox jcb1,jcb2;
	JTabbedPane jtp;//ѡ����
	String myid,friendid;
	
	public QqClinterLoging(){
		jl1=new JLabel(new ImageIcon("images/top.png"));
		this.add(jl1,"North");
		
		jp1=new JPanel();
		jp1.setLayout(new FlowLayout());
		jb1=new JButton("��½");
		//��Ӧ��½
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb3=new JButton("ע����");
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		this.add(jp1,"South");
		
		jtp=new JTabbedPane();
		jp2_jl1=new JLabel("QQ����",JLabel.CENTER);
		jp2_jl2=new JLabel("QQ����",JLabel.CENTER);
		jp2_jl3=new JLabel("��������");
		jp2_jl3.setForeground(Color.blue);
		jp2_jl4=new JLabel("�������뱣��");
		jp2_jl4.setForeground(Color.blue);
		jp2_jb1=new JButton("�������");
		jcb1=new JCheckBox("�����½");
		jcb2=new JCheckBox("��ס����");
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
		jtp.add("QQ����",jp2);
		jp3=new JPanel();
		jtp.add("QQ����",jp3);
		jp4=new JPanel();
		jtp.add("QQ����",jp4);
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
		//�û���½�¼�
		if(e.getSource()==jb1){
			QqClientUser qqClientUser =new QqClientUser();
			//�����û���Ϣ����,���ڷ��͸�������,�����֤
			User u=new User();
			u.setUserId(myid=jtf.getText().trim());//ȥ�����ߵĿո�
			u.setPasswd(new String(jpf.getPassword()));
			//�����û�����Ϣ��(�û��Ϸ��򷵻�true)
			if(qqClientUser.checkUser(u)){
				//��֤�ɹ�,�Ȱ��û������������뵽HASHMAP��,�ٷ�����Ϣ��,
				//��Ϊ����Ϣ����ȥ��,������Ϣ�����ص���������о͸��º���������Ϣ
				
				//��������б����
				QqMyFriends qqMyFriends=new QqMyFriends(myid);
				//�ѽ����ĺ��ѽ�����뵽��������HASHMAP��
				ManageQqFriendList.addManageQqFriendList(myid, qqMyFriends);
				
				
				//�����������һ��Ҫ��õ����ߺ����б����Ϣ����
				try{
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getQqClientConServerThread(u.getUserId()).getS().getOutputStream());
					//����һ�����ߺ���������Ϣ��,Ȼ����
					Message m=new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
					//ָ����Ҫ�������QQ�˺ŵĺ������
					m.setSender(u.getUserId());
					
					oos.writeObject(m);
					
					
					
				}catch(Exception ex){ex.printStackTrace();}
				
				//�رյ�½����
				this.dispose();
			}else{
				//��ʾ�û������������
				JOptionPane.showMessageDialog(this, "�û������������!");
				
			}
		}
	}

}
