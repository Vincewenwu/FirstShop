package me.ilt.goods.dao;

import java.util.ArrayList;
import java.util.List;

import me.ilt.common.bean.PageBean;
import me.ilt.goods.bean.OrderBean;
import me.ilt.goods.bean.OrderItemBean;
import net.sf.json.JSONArray;

public interface OrderDao {

	/**
	 * 根据订单ID查询订单信息
	 * @param userId
	 * @return
	 */
	public abstract OrderBean orderIdSel(String orderId);

	/**
	 * 根据用户ID查询订单信息
	 * @param userId
	 * @param p 需求页
	 * @return
	 */
	public abstract PageBean userIdIsorder(int userId, int p, String type);

	/**
	 * 查询全部
	 * @param p
	 * @param pageSize
	 * @return
	 */
	public abstract JSONArray selAll(int p, int pageSize);

	/**
	 * 查询sql 返回json集合
	 * @param sql
	 * @return
	 */
	public abstract JSONArray sel(String sql);

	/**
	 * 查询总行数
	 * @return
	 */
	public abstract int count(String sql);

	/**
	 * 添加订单
	 * @param u
	 * @return
	 */
	public abstract int add(OrderBean u);

	/**
	 * 修改订单信息
	 * @param u
	 * @return
	 */
	public abstract int update(String orderId, String addressId, int state,
			int payType, String liuyan);

	/**
	 * 买家取消订单
	 * @param u
	 * @return
	 */
	public abstract int qxdd(String orderId, int state);

	public abstract List<OrderBean> selQueryList();

	public abstract void main(String[] args);

	ArrayList<String> orderstateSel(int userId);

	List<String> userIdIsordercount(int userId);



}