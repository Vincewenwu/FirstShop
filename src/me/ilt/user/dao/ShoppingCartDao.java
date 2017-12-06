package me.ilt.user.dao;

import java.util.List;

import me.ilt.user.bean.ShoppingCart;

public interface ShoppingCartDao {

	/**
	 * 添加购物项
	 * @param u
	 * @return
	 */
	public abstract int add(ShoppingCart u);

	/**
	 * 根据用户ID和商品ID删除
	 * @param id
	 * @return
	 */
	public abstract int del(int userId, int goodsId);

	/**
	 * 查询当前行数  是否存在购物项目
	 * @return
	 */
	public abstract int count(int userId, int goodsId);

	/**
	 * 修改购物项数量
	 * @param u
	 * @return
	 */
	public abstract int updateNum(ShoppingCart u);

	/**
	 * 根据用户ID查询全部购物车项目
	 * 
	 */
	public abstract List<ShoppingCart> selList(int userId);

	/**
	 * 根据用户ID查询全部购物车项目
	 * 
	 */
	public abstract ShoppingCart goodsIdSel(int userId, int goodsId);

	public abstract void main(String[] args);

	public abstract List<String> userIdIsordercount(int userId);

}