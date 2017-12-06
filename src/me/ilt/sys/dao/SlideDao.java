package me.ilt.sys.dao;

import java.util.List;

import me.ilt.sys.bean.SlideBean;
import net.sf.json.JSONArray;

public interface SlideDao {
	/**
	 * ��ѯsql ����json����
	 * @param sql
	 * @return
	 */
	public abstract JSONArray selAll();

	
	/**
	 * ��ѯsql ����json����
	 * @param sql
	 * @return
	 */
	JSONArray sel(String sql);
    
	/**
	 * ��ѯ���лõ�
	 * @param sql
	 * @return slide �õƶ��󼯺�
	 */
	public abstract List<SlideBean> selList();
	
	/**
	 * �޸�����
	 * @param u
	 * @return
	 */
	public abstract int update(int id, String name, String url, String proPic);

}
