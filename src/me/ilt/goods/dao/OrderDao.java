package me.ilt.goods.dao;

import java.util.ArrayList;
import java.util.List;

import me.ilt.common.bean.PageBean;
import me.ilt.goods.bean.OrderBean;
import me.ilt.goods.bean.OrderItemBean;
import net.sf.json.JSONArray;

public interface OrderDao {

	/**
	 * ���ݶ���ID��ѯ������Ϣ
	 * @param userId
	 * @return
	 */
	public abstract OrderBean orderIdSel(String orderId);

	/**
	 * �����û�ID��ѯ������Ϣ
	 * @param userId
	 * @param p ����ҳ
	 * @return
	 */
	public abstract PageBean userIdIsorder(int userId, int p, String type);

	/**
	 * ��ѯȫ��
	 * @param p
	 * @param pageSize
	 * @return
	 */
	public abstract JSONArray selAll(int p, int pageSize);

	/**
	 * ��ѯsql ����json����
	 * @param sql
	 * @return
	 */
	public abstract JSONArray sel(String sql);

	/**
	 * ��ѯ������
	 * @return
	 */
	public abstract int count(String sql);

	/**
	 * ��Ӷ���
	 * @param u
	 * @return
	 */
	public abstract int add(OrderBean u);

	/**
	 * �޸Ķ�����Ϣ
	 * @param u
	 * @return
	 */
	public abstract int update(String orderId, String addressId, int state,
			int payType, String liuyan);

	/**
	 * ���ȡ������
	 * @param u
	 * @return
	 */
	public abstract int qxdd(String orderId, int state);

	public abstract List<OrderBean> selQueryList();

	public abstract void main(String[] args);

	ArrayList<String> orderstateSel(int userId);

	List<String> userIdIsordercount(int userId);



}