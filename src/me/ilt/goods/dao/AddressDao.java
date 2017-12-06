package me.ilt.goods.dao;

import java.util.List;


import me.ilt.basedao.BaseDao;
import me.ilt.goods.bean.AddressBean;

public interface AddressDao extends BaseDao<AddressBean>{

	/**
	 * 添加收货地址
	 * @param u
	 * @return
	 */
	public abstract int add(AddressBean u);

	/**
	 * 设置默认地址
	 * @param userId
	 * @param addressId
	 * @return
	 */
	public abstract int setDefeat(int userId, int addressId);

	/**
	 * 去除其他的默认值
	 * @param userId
	 */
	public abstract void xgzt(int userId);

	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public abstract int del(int id);

	/**
	 * 根据用户ID查询他的地址信息
	 * @param sql
	 * @return
	 */
	public abstract List<AddressBean> selAll(int userId);

	/**
	 * 根据ID查询他的地址信息
	 * @param sql
	 * @return
	 */
	public abstract AddressBean idSel(int id);

	int update(AddressBean u, int id);

	List<AddressBean> selAlldantiao(int address);
/*查询条数*/
	int selcount(int userId);

}