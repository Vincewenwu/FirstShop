package me.ilt.utils;

public class GoodsConstCode {

	 /*	
	 	<c:when test="${ordrList.state==1 }">等待付款</c:when>
	 	<c:when test="${ordrList.state==2 }">等待发货</c:when>
	 	<c:when test="${ordrList.state==3 }">已发货</c:when>
	 	<c:when test="${ordrList.state==4 }">交易关闭</c:when>
	 	<c:when test="${ordrList.state==5 }">交易成功</c:when>
	 	<c:when test="${ordrList.state==6 }">货到付款</c:when>
	*/
	
	/**
	 * 待付款     等待付款
	 */
	public static final int CUST_ORDER_STATUS_WAIT_PAY = 1;
	/**
	 * 在线支付     等待发货
	 */
	public static final int CUST_ORDER_STATUS_HAD_PAY = 2;
	
	/**
	 * 订单发货
	 */
	public static final int CUST_ORDER_STATUS_HAD_SEND = 3;//已收货
	
	/**
	 * 取消订单   交易关闭 cancel
	 */
	public static final int CUST_ORDER_STATUS_TRADE_CANCEL = 4;
	
	/**
	 * 确认收货 交易完成
	 */
	public static final int CUST_ORDER_STATUS_TRADE_SUCCESS = 5; 
	
	/**
	 * 货到付款
	 */
	public static final int CUST_ORDER_STATUS_RECEIVE_PAY = 6;
}
