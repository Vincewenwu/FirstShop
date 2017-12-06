package me.ilt.goods.bean;
	
	public class OrderItemBean {
	private int id; //订单项目ID
	private int goodsId;  //商拼ID
	private String goodsName;  //商拼名称
	private String proPic;  //商品图片
	private double goodsPrice; //商品价格
	private int sum;  //购买数量
	private double subTotal;  //小计金额
	private String orderId;  //订单号ID
	private String orderIdd;  //订单号ID
	public String getOrderIdd() {
		return orderIdd;
	}
	public void setOrderIdd(String orderIdd) {
		this.orderIdd = orderIdd;
	}
	private int id1; //订单项目ID
	public OrderItemBean() {
	}
	public OrderItemBean(int goodsId, String goodsName, String proPic,
			double goodsPrice, int sum, double subTotal, String orderId) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.proPic = proPic;
		this.goodsPrice = goodsPrice;
		this.sum = sum;
		this.subTotal = subTotal;
		this.orderId = orderId;
	}
	public OrderItemBean(int goodsId, String goodsName, String proPic,
			double goodsPrice, int sum, double subTotal, String orderId,String orderIdd) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.proPic = proPic;
		this.goodsPrice = goodsPrice;
		this.sum = sum;
		this.subTotal = subTotal;
		this.orderId = orderId;
		this.orderIdd = orderIdd;
	}
	public int getId1() {
		return id1;
	}
	public void setId1(int id1) {
		this.id1 = id1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "orderItemBean [id=" + id + ", goodsId=" + goodsId + ", goodsName="
				+ goodsName + ", proPic=" + proPic + ", goodsPrice=" + goodsPrice
				+ ", sum=" + sum + ", subTotal=" + subTotal + ", orderId="
				+ orderId + "]";
	}

}
