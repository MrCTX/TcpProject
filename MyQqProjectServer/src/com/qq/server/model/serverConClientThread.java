package com.qq.server.model;
/**
 * ���ܣ�������ÿһ���ͻ������ӵ��߳�
 * ������ͻ��˵������ӵ��߳�
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.MessageType;
import com.qq.common.Message;

public class serverConClientThread extends Thread{
	Socket s;
	String id ; 
	public Socket getS() {
		return s;
	}
	public void setS(Socket s) {
		this.s = s;
	}
	//�Ѹ�Socket�����Ӵ��븳��S
	public serverConClientThread(Socket s,String id ){
		//�ѷ��������������Ӹ���S
		this.s=s;
		this.id = id ;
	}
	//֪ͨ���ߺ��ѣ��������ߺ��ѵķ���,
	@SuppressWarnings("rawtypes")
	public void notifyother(String args){
		HashMap hm=ManageClientThread.hm;
		//ȡ�����ߺ��ѵ��߳�
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			//ȡ�����ߺ��ѵ�ID��
			String online=it.next().toString();
			//������Ϣ��,��Ϣ��Ӧ�÷������ѵĿͻ���
			Message m=new Message();
			m.setCon(args);//�������ߺ��ѵ�id
			m.setMesType(MessageType.message_ret_onLineFriend);
			//ȡ�����ѵ�SOCKET����������
			try{
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientThread.getClientThrea(online).getS().getOutputStream());
				m.setGetter(online);
				oos.writeObject(m);
				
			}catch(Exception ex){ex.printStackTrace();}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void run(){
	
			//������Խ��տͻ��˵���Ϣ
		ObjectInputStream ois=null;
			try{
				//���ϵؽ������ݰ�
				while(true){
					//�������ͨ��Ϣ��,��ת��
					
					//������������ߺ�����Ϣ��,��ȡ�����ߺ��ѵ���Ϣ������
					ois=new ObjectInputStream(s.getInputStream());
					//�յ�����Ϣ��
					Message ms=(Message)ois.readObject();
					//��̨�յ�����Ϣ��
					if(ms.getMesType().equals(MessageType.message_comm_mes)){
						System.out.println(ms.getSender()+"��"+ms.getGetter()+"˵"+ms.getCon());
						
						//ȡ�ý����˵�ͨ���̣߳�ʵ����Ϣ����ת��
						serverConClientThread scct=ManageClientThread.getClientThrea(ms.getGetter());//ͨ�ź��ѵ��߳�
						//��Ϣ����ת��
						ObjectOutputStream oos=new ObjectOutputStream(scct.s.getOutputStream());
						oos.writeObject(ms);
					}else if(ms.getMesType().equals(MessageType.message_get_onLineFriend)){
						//������ߺ��ѵ����,�������ߺ��ѵ��������
						//��ȡ���ѵ��������Ӧ�����������ѵ�SOCKET�߳�ʱ�м�¼,ÿ���������߶����䵽��Ӧ��SOCKET
	
						String str=ManageClientThread.getOnLineFriends();
						//������Ϣ��
						Message m=new Message();
						m.setMesType(MessageType.message_ret_onLineFriend);
						m.setCon(str);
						m.setGetter(ms.getSender());
						System.out.println("������������"+ms.getSender());
						//��Ϣ���ķ���
						ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
						oos.writeObject(m);
						
						
					}
				}
			}catch(Exception e){
				//�̹߳������б���ͻ����̵߳�HashMap
				HashMap hm = ManageClientThread.hm;
				//�����ߵ��̴߳�HashMap��ȥ��
				hm.remove(id);
				//�������ߺ��ѣ�Ȼ�����ߺ��ѵı�ʶ���͸����к���
				String str = ManageClientThread.getOnLineFriends();
				notifyother(str);
				e.printStackTrace();
			}
	}
}
