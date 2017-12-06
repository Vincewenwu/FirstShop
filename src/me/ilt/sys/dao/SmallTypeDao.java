package me.ilt.sys.dao;

import java.util.List;

import net.sf.json.JSONArray;
import me.ilt.sys.bean.SmallTypeBean;

public interface SmallTypeDao {
/**
 * 新增小类商品
 */
	int add(SmallTypeBean s);
	
	/**
	 * 查询小类的总数
	 */
	int count(String sql);
	/**
	 * 查询所有小类记录
	 */
	JSONArray selAll(int p,int pageSize);
	
	/**
	 * 修改
	 */
	int update(SmallTypeBean u);
	
	/**
	 * 根据昵称查询
	 * @param p
	 * @param pageSize
	 * @param username
	 * @return
	 */
	public abstract JSONArray nameSel(int p, int pageSize, String name);
	
	
	/**
	 * 根据Id删除
	 */
	int del(int id);
	
	/**
	 * 删除多个
	 */
      int manyDel(String ids);
      /**
  	 * 查询sql 返回list集合
  	 * @param sql
  	 * @return
  	 */
  	public abstract List<SmallTypeBean> bigTypeIdselList(int bigTypeId);

  	
  	
	JSONArray bigTypeIdsel(int bigTypeId);

}
