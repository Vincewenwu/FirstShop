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
		 * 根据昵称查询
		 * @param p
		 * @param pageSize
		 * @param username
		 * @return
		 */
		public abstract JSONArray nameSel(int p, int pageSize, String username);
/**
 * 添加用户
 * 
 */
	int add(UserBean u);
   /**
    * 修改用户
    */
	int update(UserBean u);

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
	 * 根据ID查询用户
	 */
	UserBean find(int id);
	
	/**
	 * 根据用户名查询数据
	 */
	public abstract UserBean findOnName(String name);
	UserBean login(String userName, String password);
	int nameIsId(String userName);
	/*查询用户资料*/
	UserBean select(String sql);
	int selectid(String sql);
}
