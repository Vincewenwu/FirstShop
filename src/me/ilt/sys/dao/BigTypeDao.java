package me.ilt.sys.dao;

import java.util.List;

import net.sf.json.JSONArray;
import me.ilt.sys.bean.BigTypeBean;

public interface BigTypeDao {

	/**
	 * 添加用户
	 * @param u
	 * @return
	 */
	public abstract int add(BigTypeBean u);
	
	/**
	 * 查询条数
	 */
	int count(String sql);
	
	/**
	 * 查询全部
	 */
	JSONArray selAll(int p,int pageSize);
	
	/**
	 * 根据名字查询
	 */
	JSONArray nameSel(int p,int pageSize ,String bname);
	
	/**
	 * 查询sql 返回json集合
	 * @param sql
	 * @return
	 */
	public abstract JSONArray sel(String sql);

	/**
	 * 修改数据
	 * @param u
	 * @return
	 */
	public abstract int update(BigTypeBean u);
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public abstract int del(int id);

	/**
	 * 删除多个
	 * @param ids
	 * @return
	 */
	public abstract int manyDel(String ids);
	
	/**
	 * 查询所有大类  和小类 和最新前十商品
	 * @return bigType实体集合
	 */
	public abstract List<BigTypeBean> selList();
	
	/**
	 * 根据大类ID查询大类名称
	 * @return
	 */
	public abstract String bidIsName(String bid);
	
	/**
	 * 查询所有大类  和小类  宝贝页面调用
	 * @return bigType实体集合
	 */
	public abstract List<BigTypeBean> bigselList();
}
