package me.ilt.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.ilt.user.bean.ShoppingCart;
import me.ilt.user.dao.ShoppingCartDao;
import me.ilt.utils.JdbcUtil;

public class ShoppingCartDaoImpl implements ShoppingCartDao {
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.ShoppingCartDao#add(me.ilt.bean.ShoppingCart)
	 */
	@Override
	public  int add(ShoppingCart u){
		String sql = "insert into t_shoppingCart (userId, goodsId, num, goodsPrice,color) values(?,?,?,?,?)";
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, u.getUserId());
			ps.setInt(2, u.getGoodsId());
			ps.setInt(3, u.getNum());
			ps.setDouble(4, u.getGoodsPrice());
			ps.setString(5, u.getColor());
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
	 * @see me.ilt.dao.impl.ShoppingCartDao#del(int, int)
	 */
	@Override
	public  int del(int userId,int goodsId){
		String sql = "delete from t_shoppingCart where userId=? and goodsId=?";
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, goodsId);
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
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.ShoppingCartDao#count(int, int)
	 */
	@Override
	public  int count(int userId,int goodsId){
		String sql = "select num from t_shoppingCart where userId=? and goodsId=?";
		Connection con = JdbcUtil.getCon();
		int i = 0;
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, goodsId);
			rs = ps.executeQuery();
			
			if(rs.next()){
				i = rs.getInt("num");
			}else{
				i = 0;
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
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.ShoppingCartDao#updateNum(me.ilt.bean.ShoppingCart)
	 */
	@Override
	public int updateNum(ShoppingCart u){
		String sql = "update t_shoppingCart set num=num+? ,goodsPrice=? where userId=? and goodsId=?";
		
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, u.getNum());
			ps.setDouble(2, u.getGoodsPrice());
			ps.setInt(3, u.getUserId());
			ps.setInt(4, u.getGoodsId());
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
	 * @see me.ilt.dao.impl.ShoppingCartDao#selList(int)
	 */
	@Override
	public List<ShoppingCart> selList(int userId){
		String sql = "select * from t_shoppingCart where userId=?";
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		List<ShoppingCart> list = new ArrayList<ShoppingCart>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				ShoppingCart s = new ShoppingCart(userId, rs.getInt("goodsId"), rs.getInt("num"), rs.getDouble("goodsPrice"),rs.getString("color"));
				System.out.println(s);
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
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.ShoppingCartDao#goodsIdSel(int, int)
	 */
	@Override
	public ShoppingCart goodsIdSel(int userId,int goodsId){
		String sql = "select * from t_shoppingCart where userId=? and goodsId=?";
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		ShoppingCart s = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, goodsId);
			rs = ps.executeQuery();
			if(rs.next()){
				s = new ShoppingCart(userId, rs.getInt("goodsId"), rs.getInt("num"), rs.getDouble("goodsPrice"),rs.getString("color"));
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
		return s;
		
	}
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.ShoppingCartDao#main(java.lang.String[])
	 */
	@Override
	public  void main(String[] args) {
		//add(new shoppingCart(10001,12,1,12.6));
		//del(10001, 12);
		//count(10001, 12);
		//updateNum(new shoppingCart(10001, 12, 2, 0));
		//selList(10001);
	}
	@Override
	public List<String> userIdIsordercount(int userId) {
		String sql = "select state from t_order where userId ="+userId ;
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		int one=0;
		int two=0;
		int three=0;
		int five=0;
		int six=0;
		List<String> ListOrder=new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int i = rs.getInt("state"); //收货地址ID
				if(i==1){
					one++;
				  }else{}
				if(i==2){
					two++;
				  }else{}
				if(i==3){
					three++;
				  }else{}
				if(i==5){
					five++;
				  }else{}
				if(i==6){
					six++;
				  }else{}
				}
			ListOrder.add(Integer.toString(one));
			ListOrder.add(Integer.toString(two));
			ListOrder.add(Integer.toString(three));
			ListOrder.add(Integer.toString(five));
			ListOrder.add(Integer.toString(six));
			}catch (Exception e) {
				e.printStackTrace();
			}
		return ListOrder;
	}
}
