package me.ilt.sys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import me.ilt.goods.bean.GoodsBean;
import me.ilt.goods.dao.GoodsDao;
import me.ilt.goods.dao.impl.GoodsDaoImpl;
import me.ilt.sys.bean.BigTypeBean;
import me.ilt.sys.bean.SmallTypeBean;
import me.ilt.sys.dao.BigTypeDao;
import me.ilt.sys.dao.SmallTypeDao;
import me.ilt.utils.JdbcUtil;
import me.ilt.utils.JsonUtil;

public class BigTypeDaoImpl implements BigTypeDao{
	private GoodsDao goodsDao = new GoodsDaoImpl();
	private SmallTypeDao smallTypeDao = new SmallTypeDaoImpl();
	@Override
	public int add(BigTypeBean u) {
		String sql = "insert into t_bigType(bname, remarks, proPic) values(?,?,?)";
		Connection conn=JdbcUtil.getCon();
		PreparedStatement ps=null;
		int i=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getRemarks());
			ps.setString(3, u.getImgUrl());
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
	public int count(String sql) {
		Connection con = JdbcUtil.getCon();
		int i = 0;
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			if(rs!=null){
				i = rs.getInt("count");
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
			System.out.println("查询到的用户行数为："+i);
		return i;
	
}

	@Override
	public JSONArray selAll(int p, int pageSize) {
       String sql="select * from t_bigType limit "+(p-1)*pageSize+","+pageSize;
       System.out.println(sql);
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
	public JSONArray nameSel(int p,int pageSize,String name){
		//String sql = "select top "+pageSize+" * from t_bigType where id not in(select top "+(p-1)*pageSize+" id from t_bigType where userName like '%"+name+"%') and userName like '%"+name+"%'";
		String sql = "select * from t_bigType where  bname like '%"+name+"%'"+" limit "+(p-1)*pageSize+","+pageSize;
		return sel(sql);
	}

	@Override
	public int update(BigTypeBean u) {
		String sql = null;
		if(u.getImgUrl()==null){
			 sql = "update t_bigType "
					+"set bname=?,remarks=? "
					+"where id=? ";
		}else{
			 sql = "update t_bigType "
					+"set bname=?,remarks=?,proPic=? "
					+"where id=? ";
		}
		System.out.println("数据库dao中sql:"+sql);
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getRemarks());
			if(u.getImgUrl()!=null){
				ps.setInt(4, u.getId());
				ps.setString(3, u.getImgUrl());
			}else{
				ps.setInt(3, u.getId());
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
	@Override
	public int del(int id){
		System.out.println("接受到要删除的ID："+id);
		String sql = "delete from t_bigType where id=?";
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
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	@Override
	public int manyDel(String ids){
		String sql = "delete from t_bigType where id in("+ids+")";
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
	public List<BigTypeBean> selList() {
		String sql = "select * from t_bigType";
		System.out.println("sql查询语句："+sql);
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		List<BigTypeBean> list = new ArrayList<BigTypeBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("bname");
				String proPic = rs.getString("proPic");
				System.out.println("id="+id+",bname="+name+"proPic="+proPic);
			ArrayList<SmallTypeBean> smallTypeList = (ArrayList<SmallTypeBean>) smallTypeDao.bigTypeIdselList(id);
			 ArrayList<GoodsBean> goodsList = (ArrayList<GoodsBean>) goodsDao.bigTypeIdSel(id);
				
			BigTypeBean bigType = new BigTypeBean();
				bigType.setId(id);
				bigType.setName(name);
				bigType.setImgUrl(proPic);
			    bigType.setSmallTypeList(smallTypeList);  //放入小类集合
			    bigType.setGoods(goodsList);  //放入前十商品
			    
				list.add(bigType);
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
	public String bidIsName(String bid){
		String sql = "select bname from t_bigType where id = "+bid;
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		String name = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			name = rs.getString("bname");
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
		return name;
	}

	@Override
	public List<BigTypeBean> bigselList() {
		String sql = "select * from t_bigType";
		System.out.println("sql查询语句："+sql);
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		List<BigTypeBean> list = new ArrayList<BigTypeBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("bname");
				System.out.println("id="+id+",bname="+name);
				ArrayList<SmallTypeBean> smallTypeList = (ArrayList<SmallTypeBean>) smallTypeDao.bigTypeIdselList(id);
				BigTypeBean bigType = new BigTypeBean();
				bigType.setId(id);
				bigType.setName(name);
				bigType.setSmallTypeList(smallTypeList);  //放入小类集合
				list.add(bigType);
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
	

}
