package me.ilt.goods.dao;

import java.util.List;


import me.ilt.basedao.BaseDao;
import me.ilt.goods.bean.AddressBean;

public interface AddressDao extends BaseDao<AddressBean>{

	/**
	 * ����ջ���ַ
	 * @param u
	 * @return
	 */
	public abstract int add(AddressBean u);

	/**
	 * ����Ĭ�ϵ�ַ
	 * @param userId
	 * @param addressId
	 * @return
	 */
	public abstract int setDefeat(int userId, int addressId);

	/**
	 * ȥ��������Ĭ��ֵ
	 * @param userId
	 */
	public abstract void xgzt(int userId);

	/**
	 * ����IDɾ��
	 * @param id
	 * @return
	 */
	public abstract int del(int id);

	/**
	 * �����û�ID��ѯ���ĵ�ַ��Ϣ
	 * @param sql
	 * @return
	 */
	public abstract List<AddressBean> selAll(int userId);

	/**
	 * ����ID��ѯ���ĵ�ַ��Ϣ
	 * @param sql
	 * @return
	 */
	public abstract AddressBean idSel(int id);

	int update(AddressBean u, int id);

	List<AddressBean> selAlldantiao(int address);
/*��ѯ����*/
	int selcount(int userId);

}