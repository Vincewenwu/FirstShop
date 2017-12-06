package me.ilt.sys.dao;

import java.util.List;

import net.sf.json.JSONArray;
import me.ilt.sys.bean.SmallTypeBean;

public interface SmallTypeDao {
/**
 * ����С����Ʒ
 */
	int add(SmallTypeBean s);
	
	/**
	 * ��ѯС�������
	 */
	int count(String sql);
	/**
	 * ��ѯ����С���¼
	 */
	JSONArray selAll(int p,int pageSize);
	
	/**
	 * �޸�
	 */
	int update(SmallTypeBean u);
	
	/**
	 * �����ǳƲ�ѯ
	 * @param p
	 * @param pageSize
	 * @param username
	 * @return
	 */
	public abstract JSONArray nameSel(int p, int pageSize, String name);
	
	
	/**
	 * ����Idɾ��
	 */
	int del(int id);
	
	/**
	 * ɾ�����
	 */
      int manyDel(String ids);
      /**
  	 * ��ѯsql ����list����
  	 * @param sql
  	 * @return
  	 */
  	public abstract List<SmallTypeBean> bigTypeIdselList(int bigTypeId);

  	
  	
	JSONArray bigTypeIdsel(int bigTypeId);

}
