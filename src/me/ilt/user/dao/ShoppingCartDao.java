package me.ilt.user.dao;

import java.util.List;

import me.ilt.user.bean.ShoppingCart;

public interface ShoppingCartDao {

	/**
	 * ��ӹ�����
	 * @param u
	 * @return
	 */
	public abstract int add(ShoppingCart u);

	/**
	 * �����û�ID����ƷIDɾ��
	 * @param id
	 * @return
	 */
	public abstract int del(int userId, int goodsId);

	/**
	 * ��ѯ��ǰ����  �Ƿ���ڹ�����Ŀ
	 * @return
	 */
	public abstract int count(int userId, int goodsId);

	/**
	 * �޸Ĺ���������
	 * @param u
	 * @return
	 */
	public abstract int updateNum(ShoppingCart u);

	/**
	 * �����û�ID��ѯȫ�����ﳵ��Ŀ
	 * 
	 */
	public abstract List<ShoppingCart> selList(int userId);

	/**
	 * �����û�ID��ѯȫ�����ﳵ��Ŀ
	 * 
	 */
	public abstract ShoppingCart goodsIdSel(int userId, int goodsId);

	public abstract void main(String[] args);

	public abstract List<String> userIdIsordercount(int userId);

}