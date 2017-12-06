package me.ilt.sys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import me.ilt.sys.bean.SmallTypeBean;
import me.ilt.sys.dao.SmallTypeDao;
import me.ilt.utils.JdbcUtil;
import me.ilt.utils.JsonUtil;

public class SmallTypeDaoImpl implements SmallTypeDao{

	@Override
	public int add(SmallTypeBean s) {
		Connection conn=JdbcUtil.getCon();
		PreparedStatement ps=null;
		int i=0;
		String sql="insert into t_smalltype (sname,bigTypeId,remarks)  values(?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, s.getName());
			ps.setInt(2, s.getBigTypeId());
			ps.setString(3, s.getRemarks());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int count(String sql) {
		Connection con = JdbcUtil.getCon();
		int i = 0;
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				if(rs!=null){
					i= rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		System.out.println("查询到的小类行数为："+i);
		return i;
	}

	@Override
	public JSONArray selAll(int p, int pageSize) {
		String sql = "select t_smallType.*,t_bigType.bname bigTypeName from t_smallType,t_bigType "
				+"where t_smallType.bigTypeId=t_bigType.id "
				+" limit "+(p-1)*pageSize+","+pageSize;
			return sel(sql);
	}
	JSONArray sel(String sql){
		System.out.println("sql查询语句："+sql);
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		JSONArray jsonArray = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			jsonArray = JsonUtil.formatRsToJsonArray(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jsonArray;
		
		
	}

	@Override
	public int update(SmallTypeBean u) {
		Connection conn=JdbcUtil.getCon();
		PreparedStatement ps=null;
		String sql="update t_smallType set sname=?,remarks=?,bigTypeId=? where id=?";
		int i=0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getRemarks());
			ps.setInt(3, u.getBigTypeId());
			ps.setInt(4, u.getId());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public JSONArray nameSel(int p, int pageSize, String name) {
		
	String sql = "select t_smallType.*,t_bigType.bname bigTypeName from t_smallType,t_bigType "
			+"where t_smallType.bigTypeId=t_bigType.id and t_smallType.sname like '%"+name+"%' "
			+" limit "+(p-1)*pageSize+","+pageSize;

	return sel(sql);
	}

	@Override
	public int del(int id) {
		Connection conn=JdbcUtil.getCon();
		PreparedStatement ps=null;
		String sql="delete from t_smallType where id=?";
		int i=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			i=ps.executeUpdate();
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
		return i;
	}

	@Override
	public int manyDel(String ids) {
		Connection conn=JdbcUtil.getCon();
		PreparedStatement ps=null;
		String sql="delete from t_smallType where id in("+ids+")";
		int i=0;
		try {
			ps=conn.prepareStatement(sql);
			i=ps.executeUpdate();
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
		return i;
	}

	@Override
	public List<SmallTypeBean> bigTypeIdselList(int bigTypeId) {
		String sql = "select * from t_smallType where bigTypeId = "+bigTypeId;
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		List<SmallTypeBean> list = new ArrayList<SmallTypeBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				SmallTypeBean s = new SmallTypeBean();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("sname"));
				System.out.println("小类ID："+s.getId()+"    小类名称："+s.getName());
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public  JSONArray bigTypeIdsel(int bigTypeId){
		String sql = "select * from t_smallType where bigTypeId = "+bigTypeId;
		return sel(sql);
	}

}
