package com.qq.server.view;
/**
 * 
 * ���ܣ�����ͼ�ط�����
 * �����������������رշ�����
 * ��������:
 *1.���ý���
 *2.��ͨ��������ͻ���
 *  ����û����¼��ťʱ,���û����ʺź����뷢��������������֤
 *3.������ݰ��Ķ����ͷ�ʽ
 *4.������ݰ��ӷ�������ת��,��������
 *5.���������������ʾ��ɫͷ��,��֮,��ʾ��ɫͷ��
 *6.��ɺ��ѻ������칦�ܣ���������>>>>>>>>>
 *7.����������ʱ����ʾ
 */
import javax.swing.*;

import com.qq.server.model.QqServer;
import java.awt.*;
import java.awt.event.*;

public class QqServerFrame extends JFrame implements ActionListener{
	JButton start,stop;
	JPanel jp;
	
	public QqServerFrame(){
		start=new JButton("����������");
		start.addActionListener(this);
		stop=new JButton("�رշ�����");
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
