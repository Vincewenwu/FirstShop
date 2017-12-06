package me.ilt.goods.dao;

import java.util.List;

import me.ilt.goods.bean.OrderItemBean;
import net.sf.json.JSONArray;

public interface OrderItemDao {

	/**
	 * ��Ӷ�����Ŀ��Ʒ
	 * @param u
	 * @return
	 */
	public abstract int add(OrderItemBean u);

	/**
	 * ��ѯsql ����json����
	 * @param sql
	 * @return
	 */
	public abstract JSONArray orderIdSel(String orderId);

	/**
	 * ��ѯsql ����goodsBean list����
	 * @param sql
	 * @return
	 */
	public abstract List<OrderItemBean> orderIdSelItem(String orderId);

	List<OrderItemBean> orderIdSelItemId(int orderId1);

	int add1(OrderItemBean u);

	List<OrderItemBean> orderIdSelItemidd(String orderId1);

	List<OrderItemBean> orderIdSelItemss(String orderId1);

}