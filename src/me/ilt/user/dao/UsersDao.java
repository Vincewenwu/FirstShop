package me.ilt.user.dao;

import java.util.List;

import net.sf.json.JSONArray;

import me.ilt.user.bean.UserBean;

public interface UsersDao {
	public abstract  UserBean  adminlogin(String userName,String passowrd);
	public abstract  UserBean  find(String userName);
	public abstract JSONArray selAll(int p, int pageSize);
	int count (String sql);
	 
	 /**
		 * �����ǳƲ�ѯ
		 * @param p
		 * @param pageSize
		 * @param username
		 * @return
		 */
		public abstract JSONArray nameSel(int p, int pageSize, String username);
/**
 * ����û�
 * 
 */
	int add(UserBean u);
   /**
    * �޸��û�
    */
	int update(UserBean u);

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
	 * ����ID��ѯ�û�
	 */
	UserBean find(int id);
	
	/**
	 * �����û�����ѯ����
	 */
	public abstract UserBean findOnName(String name);
	UserBean login(String userName, String password);
	int nameIsId(String userName);
	/*��ѯ�û�����*/
	UserBean select(String sql);
	int selectid(String sql);
}
