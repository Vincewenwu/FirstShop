package me.ilt.goods.bean;

import java.util.Date;
import java.util.List;

public class OrderBean {
	private String id; //订单ID
	private int userId; //用户ID
	private double total; //订单总额
	private int addressId; //收货地址ID
	private String remarks;  //买家留言
	private Date orderTime;  //下单时间
	private int state;  //当前状态
	private int payType;
	private Date modifyTime;
	private List<OrderItemBean> itemList;//订单商品集合
public OrderBean() {
	super();
}

public OrderBean(String id, int userId, double total, int addressId,
		String remarks, Date orderTime, int state, int payType, Date modifyTime) {
	super();
	this.id = id;
	this.userId = userId;
	this.total = total;
	this.addressId = addressId;
	this.remarks = remarks;
	this.orderTime = orderTime;
	this.state = state;
	this.payType = payType;
	this.modifyTime = modifyTime;
}


public List<OrderItemBean> getItemList() {
	return itemList;
}
public void setItemList(List<OrderItemBean> itemList) {
	this.itemList = itemList;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}

public int getAddressId() {
	return addressId;
}
public void setAddressId(int addressId) {
	this.addressId = addressId;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}

public Date getOrderTime() {
	return orderTime;
}
public void setOrderTime(Date orderTime) {
	this.orderTime = orderTime;
}
public Date getModifyTime() {
	return modifyTime;
}
public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}

public int getPayType() {
	return payType;
}
public void setPayType(int payType) {
	this.payType = payType;
}
@Override
public String toString() {
	return "OrderBean [id=" + id + ", userId=" + userId + ", total=" + total
			+ ", addressId=" + addressId + ", remarks=" + remarks
			+ ", orderTime=" + orderTime + ", state=" + state + ", payType="
			+ payType + ", modifyTime=" + modifyTime + ", itemList=" + itemList
			+ "]";
}
}
