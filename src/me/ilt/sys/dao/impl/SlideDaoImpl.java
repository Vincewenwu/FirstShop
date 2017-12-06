package me.ilt.sys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import me.ilt.sys.bean.SlideBean;
import me.ilt.sys.dao.SlideDao;
import me.ilt.utils.JdbcUtil;
import me.ilt.utils.JsonUtil;

public class SlideDaoImpl implements SlideDao{

	@Override
	public JSONArray selAll() {
		String sql="select * from t_slide";
		return sel(sql);
		
	}
	@Override
	public JSONArray sel(String sql){
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
	public List<SlideBean> selList() {
	String sql="select * from t_slide";
	System.out.println("sql查询语句："+sql);
	Connection conn=JdbcUtil.getCon();
	PreparedStatement ps=null;
	ResultSet rs=null;
	List<SlideBean> list = new ArrayList<SlideBean>();
	try{
		ps=conn.prepareStatement(sql);
		rs=ps.executeQuery(); 
		while(rs.next()){
			String name = rs.getString("name");
			String url = rs.getString("url");
			String proPic = rs.getString("proPic");
			System.out.println("name="+name+",url="+url+",proPic="+proPic);
			SlideBean slide = new SlideBean(name, proPic, url);
			list.add(slide);
		    }
	    } catch (SQLException e) {
	     	e.printStackTrace();
	    } catch (Exception e) {
		e.printStackTrace();
	   }finally{
		try {
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	}
	return list;
	}
	@Override
	public  int update(int id,String name,String url,String proPic){
		String sql = null;
		if(proPic==null){
			 sql = "update t_slide "
					+"set name=?,url=? "
					+"where id=? ";
		}else{
			 sql = "update t_slide "
					+"set name=?,url=?,proPic=? "
					+"where id=? ";
		}
		System.out.println("数据库dao中sql:"+sql);
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, url);
			if(proPic!=null){
				ps.setInt(4, id);
				ps.setString(3, proPic);
			}else{
				ps.setInt(3, id);
			}
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
}
