package com.qq.common;
/**
 * 
 * ����һ����������Ϣ��,���������û���������Ϣ������,���в�ͬ�İ��Ĳ���
 *����ɽӿ�����,��ô�ӿ��ڵı������Ǿ�̬����
 */
public  interface MessageType {
	//���ɾ�̬�ĳ���,���ڱ�ʶ��Ϣ��������
	String message_succeed="1";//��¼�ɹ�
	String message_login_fail="2";//��¼ʧ��
	String message_comm_mes="3";//��ͨ��Ϣ��
	String message_get_onLineFriend="4";//��ȡ���ߺ��ѵİ��ı�ʶ
	String message_ret_onLineFriend="5";//�������ߺ��ѵİ���ʶ
}
