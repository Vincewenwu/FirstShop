package me.ilt.goods.dao;

import java.util.List;

import me.ilt.goods.bean.OrderItemBean;
import net.sf.json.JSONArray;

public interface OrderItemDao {

	/**
	 * 添加订单项目商品
	 * @param u
	 * @return
	 */
	public abstract int add(OrderItemBean u);

	/**
	 * 查询sql 返回json集合
	 * @param sql
	 * @return
	 */
	public abstract JSONArray orderIdSel(String orderId);

	/**
	 * 查询sql 返回goodsBean list集合
	 * @param sql
	 * @return
	 */
	public abstract List<OrderItemBean> orderIdSelItem(String orderId);

	List<OrderItemBean> orderIdSelItemId(int orderId1);

	int add1(OrderItemBean u);

	List<OrderItemBean> orderIdSelItemidd(String orderId1);

	List<OrderItemBean> orderIdSelItemss(String orderId1);

}