package me.ilt.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import net.sf.json.JSONArray;



import me.ilt.user.bean.UserBean;
import me.ilt.user.dao.UsersDao;
import me.ilt.utils.JdbcUtil;
import me.ilt.utils.JsonUtil;
import me.ilt.utils.UserConstCode;

public class UserDaoImpl implements UsersDao {
/**
 * adminlogin用户登录 用户名 密码验证
 */
	@Override
	public  UserBean adminlogin(String userName,String password){
		String sql = "select * from t_user where userName=? and password=? and userType="+UserConstCode.USER_TYPE_ADMIN;
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		UserBean u = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				u = new UserBean(rs.getString("userName"), rs.getString("trueName"),
						rs.getString("sex"), rs.getString("birthday"), rs.getString("idCard"), 
						rs.getString("phone"), null, rs.getString("email"), null, null,null);
				u.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	}
	@Override
	public  UserBean login(String userName,String password){
		String sql = "select * from t_user where userName=? and password=? and userType="+UserConstCode.USER_TYPE_NORMAL;
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		UserBean u = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				u = new UserBean(rs.getString("userName"), rs.getString("trueName"),
						rs.getString("sex"), rs.getString("birthday"), rs.getString("idCard"), 
						rs.getString("phone"), null, rs.getString("email"), null, null,null);
				u.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	}
/**
 * 异步检测用户名
 */
	@Override
	public UserBean find(String userName) {
//		String sql = "select * from t_user where userName=? and password=? and userType="+UserConstCode.USER_TYPE_ADMIN;
//		Connection con = JdbcUtil.getCon();
//		PreparedStatement ps = null;
//		UserBean u = null;
//		try {
//			ps = con.prepareStatement(sql);
//			ps.setString(1, userName);
//			ps.setString(2, password);
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()){
//				u = new UserBean(rs.getString("userName"), rs.getString("trueName"),
//						rs.getString("sex"), rs.getString("birthday"), rs.getString("idCard"), 
//						rs.getString("phone"), null, rs.getString("email"), null, null);
//				u.setId(rs.getInt("id"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			try {
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		return null;
	}
     @Override
     public JSONArray selAll(int p, int pageSize) {
          String sql="select * from t_user limit "+(p-1)*pageSize+","+pageSize;
	        return sel(sql);
}

	public JSONArray sel(String sql){
		System.out.println("sql查询语句："+sql);
		Connection conn=JdbcUtil.getCon();
		ResultSet rs=null;
		JSONArray jsonarray =null;
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			jsonarray =JsonUtil.formatRsToJsonArray(rs);
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jsonarray;
		
	}
	@Override
	public int count(String sql) {
		Connection conn=JdbcUtil.getCon();
		int i=0;
		ResultSet rs=null;	
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();	
            if(rs!=null){
            	i=rs.getInt("count");
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		System.out.println("查询到的用户行数为："+i);
		return i;
	}
	@Override
	public UserBean select(String sql) {
		Connection conn=JdbcUtil.getCon();
		int i=0;
		ResultSet rs=null;	
		UserBean u = null;
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();	
            if(rs!=null){
            	u = new UserBean(rs.getString("userName"), rs.getString("trueName"),
						rs.getString("sex"), rs.getString("birthday"), rs.getString("idCard"), 
						rs.getString("phone"), null, rs.getString("email"), null, null,null);
				u.setId(rs.getInt("id"));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		System.out.println("查询到的用户行数为："+i);
		return u;
	}
	@Override
	public int selectid(String name) {
		Connection conn=JdbcUtil.getCon();
		int i=0;
		String sql= "select id from t_user where userName=?";
		ResultSet rs=null;	
		UserBean u = null;
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			rs=ps.executeQuery();
			while (rs.next()) {
				i=rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		System.out.println("查询到的用户行数为："+i);
		return i;
	}
	@Override
	public JSONArray nameSel(int p, int pageSize, String username) {
		String sql = "select * from t_user where userName like '%"+username+"%'"
				+" limit "+(p-1)*pageSize+","+pageSize;
		return sel(sql);
	}
	@Override
	public int add(UserBean u) {
		String sql = "insert into t_user (userName, trueName, sex, birthday, idCard, phone, address, email, userType, password,proPic) values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn=JdbcUtil.getCon();
		PreparedStatement ps=null;
		int i=0;
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getTrueName());
			ps.setString(3, u.getSex());
			ps.setString(4, u.getBirthday());
			ps.setString(5, u.getIdCard());
			ps.setString(6, u.getPhone());
			ps.setString(7, u.getAddress());
			ps.setString(8, u.getEmail());
			ps.setInt(9, Integer.parseInt(u.getUserType()));
			ps.setString(10, u.getPassword());
			ps.setString(11, u.getProPic());
			i=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	@Override
	public int update(UserBean u) {
		String sql = "update t_user "+"set userName=?,trueName=?,sex=?,birthday=?,idCard=?,phone=?,address=?,email=?,password=?,proPic=? "
				+"where id=? ";
	Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getTrueName());
			ps.setString(3, u.getSex());
			ps.setString(4, u.getBirthday());
			ps.setString(5, u.getIdCard());
			ps.setString(6, u.getPhone());
			ps.setString(7, u.getAddress());
			ps.setString(8, u.getEmail());
			ps.setString(9, u.getPassword());
			ps.setString(10, u.getProPic());
			ps.setInt(11, u.getId());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	@Override
	public UserBean find(int id) {
		String sql="select *from t_user where id=?";
		Connection conn =JdbcUtil.getCon();
		PreparedStatement ps=null;
		UserBean u=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				 u=new UserBean(rs.getString("userName"), rs.getString("trueName"),
						rs.getString("sex"), rs.getString("birthday"), rs.getString("idCard"), 
						rs.getString("phone"), null, rs.getString("email"), null, null,null);
				u.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	}
	@Override
	public   int del(int id){
		System.out.println("接受到要删除的ID："+id);
		String sql = "delete from t_user where id=?";
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.UsersDao#manyDel(java.lang.String)
	 */
	@Override
	public   int manyDel(String ids){
		String sql = "delete from t_user where id in("+ids+")";
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	@Override
	public UserBean findOnName(String name) {
		
		return null;
	}
	
	@Override
	public   int nameIsId(String userName){
		String sql = "select id from t_user where userName=?";
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int id = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
			id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
}
