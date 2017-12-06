package me.ilt.sys.dao;

import java.util.List;

import me.ilt.sys.bean.SlideBean;
import net.sf.json.JSONArray;

public interface SlideDao {
	/**
	 * 查询sql 返回json集合
	 * @param sql
	 * @return
	 */
	public abstract JSONArray selAll();

	
	/**
	 * 查询sql 返回json集合
	 * @param sql
	 * @return
	 */
	JSONArray sel(String sql);
    
	/**
	 * 查询所有幻灯
	 * @param sql
	 * @return slide 幻灯对象集合
	 */
	public abstract List<SlideBean> selList();
	
	/**
	 * 修改数据
	 * @param u
	 * @return
	 */
	public abstract int update(int id, String name, String url, String proPic);

}
