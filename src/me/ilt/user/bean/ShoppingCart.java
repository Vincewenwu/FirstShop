package me.ilt.user.bean;

public class ShoppingCart {
	private int userId;
	private int goodsId;
	private int num;
	private double goodsPrice;
	String color;
	public ShoppingCart(int userId, int goodsId, int num, double goodsPrice,String color) {
		this.userId = userId;
		this.goodsId = goodsId;
		this.num = num;
		this.goodsPrice = goodsPrice;
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ShoppingCart() {
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	@Override
	public String toString() {
		return "ShoppingCart [userId=" + userId + ", goodsId=" + goodsId
				+ ", num=" + num + ", goodsPrice=" + goodsPrice + ", color="
				+ color + "]";
	}

}
