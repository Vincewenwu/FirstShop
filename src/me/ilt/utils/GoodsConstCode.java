package me.ilt.utils;

public class GoodsConstCode {

	 /*	
	 	<c:when test="${ordrList.state==1 }">�ȴ�����</c:when>
	 	<c:when test="${ordrList.state==2 }">�ȴ�����</c:when>
	 	<c:when test="${ordrList.state==3 }">�ѷ���</c:when>
	 	<c:when test="${ordrList.state==4 }">���׹ر�</c:when>
	 	<c:when test="${ordrList.state==5 }">���׳ɹ�</c:when>
	 	<c:when test="${ordrList.state==6 }">��������</c:when>
	*/
	
	/**
	 * ������     �ȴ�����
	 */
	public static final int CUST_ORDER_STATUS_WAIT_PAY = 1;
	/**
	 * ����֧��     �ȴ�����
	 */
	public static final int CUST_ORDER_STATUS_HAD_PAY = 2;
	
	/**
	 * ��������
	 */
	public static final int CUST_ORDER_STATUS_HAD_SEND = 3;//���ջ�
	
	/**
	 * ȡ������   ���׹ر� cancel
	 */
	public static final int CUST_ORDER_STATUS_TRADE_CANCEL = 4;
	
	/**
	 * ȷ���ջ� �������
	 */
	public static final int CUST_ORDER_STATUS_TRADE_SUCCESS = 5; 
	
	/**
	 * ��������
	 */
	public static final int CUST_ORDER_STATUS_RECEIVE_PAY = 6;
}
