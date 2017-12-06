package me.ilt.sys.dao;

import java.util.List;

import net.sf.json.JSONArray;
import me.ilt.sys.bean.BigTypeBean;

public interface BigTypeDao {

	/**
	 * ����û�
	 * @param u
	 * @return
	 */
	public abstract int add(BigTypeBean u);
	
	/**
	 * ��ѯ����
	 */
	int count(String sql);
	
	/**
	 * ��ѯȫ��
	 */
	JSONArray selAll(int p,int pageSize);
	
	/**
	 * �������ֲ�ѯ
	 */
	JSONArray nameSel(int p,int pageSize ,String bname);
	
	/**
	 * ��ѯsql ����json����
	 * @param sql
	 * @return
	 */
	public abstract JSONArray sel(String sql);

	/**
	 * �޸�����
	 * @param u
	 * @return
	 */
	public abstract int update(BigTypeBean u);
	/**
	 * ����IDɾ��
	 * @param id
	 * @return
	 */
	public abstract int del(int id);

	/**
	 * ɾ�����
	 * @param ids
	 * @return
	 */
	public abstract int manyDel(String ids);
	
	/**
	 * ��ѯ���д���  ��С�� ������ǰʮ��Ʒ
	 * @return bigTypeʵ�弯��
	 */
	public abstract List<BigTypeBean> selList();
	
	/**
	 * ���ݴ���ID��ѯ��������
	 * @return
	 */
	public abstract String bidIsName(String bid);
	
	/**
	 * ��ѯ���д���  ��С��  ����ҳ�����
	 * @return bigTypeʵ�弯��
	 */
	public abstract List<BigTypeBean> bigselList();
}
