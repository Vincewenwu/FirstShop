package me.ilt.goods.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.ilt.goods.bean.OrderItemBean;
import me.ilt.goods.dao.GoodsDao;
import me.ilt.goods.dao.OrderItemDao;
import me.ilt.utils.JdbcUtil;
import me.ilt.utils.JsonUtil;
import net.sf.json.JSONArray;

public class OrderItemDaoImpl implements OrderItemDao {
	
	private GoodsDao goodsDao = new GoodsDaoImpl();
	
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.OrderItemDao#add(me.ilt.bean.OrderItemBean)
	 */
	@Override
	public int add(OrderItemBean u){
		goodsDao.addSales(u.getGoodsId(), u.getSum()); //增加销量
		String sql = "insert into t_orderItem (goodsId, goodsName, proPic, goodsPrice, sum, subTotal, orderId) values(?,?,?,?,?,?,?)";
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, u.getGoodsId());
			ps.setString(2, u.getGoodsName());
			ps.setString(3, u.getProPic());
			ps.setDouble(4, u.getGoodsPrice());
			ps.setInt(5, u.getSum());
			ps.setDouble(6, u.getSubTotal());
			ps.setString(7, u.getOrderId());
			
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
	public int add1(OrderItemBean u){
		goodsDao.addSales(u.getGoodsId(), u.getSum()); //增加销量
		String sql = "insert into t_orderItem (goodsId, goodsName, proPic, goodsPrice, sum, subTotal, orderId,orderIdd) values(?,?,?,?,?,?,?,?)";
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, u.getGoodsId());
			ps.setString(2, u.getGoodsName());
			ps.setString(3, u.getProPic());
			ps.setDouble(4, u.getGoodsPrice());
			ps.setInt(5, u.getSum());
			ps.setDouble(6, u.getSubTotal());
			ps.setString(7, u.getOrderId());
			ps.setString(8, u.getOrderIdd());
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
	 * @see me.ilt.dao.impl.OrderItemDao#orderIdSel(java.lang.String)
	 */
	@Override
	public JSONArray orderIdSel(String orderId){
		String sql = "select * from t_orderItem where orderId=?";
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		JSONArray jsonArray = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, orderId);
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
	public List<OrderItemBean> orderIdSelItemss(String orderId1){
		String sql = "select * from t_orderItem where orderIdd=?";
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		List<OrderItemBean> list = new ArrayList<OrderItemBean>();
		OrderItemBean g = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, orderId1);
			
			rs = ps.executeQuery();
			int id ;
			int id1;
			int sum ;
			String proPic;
			String goodsName ;
			double price ;
			String orderId;
			String orderIdd;
			while(rs.next()){
				id = rs.getInt("goodsId");
				id1 = rs.getInt("id");
				sum = rs.getInt("sum");
				proPic = rs.getString("proPic");
				goodsName = rs.getString("goodsName");
				price = rs.getDouble("goodsPrice");
				orderId=rs.getString("orderId");
				orderIdd=rs.getString("orderIdd");
				g = new OrderItemBean();
				g.setGoodsId(id);
				g.setId(id1);
				g.setSum(sum);
				g.setGoodsPrice(price);
				g.setProPic(proPic);
				g.setGoodsName(goodsName);
				g.setOrderId(orderId);
				g.setOrderIdd(orderIdd);
				list.add(g);
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
	 * @see me.ilt.dao.impl.OrderItemDao#orderIdSelItem(java.lang.String)
	 */
	@Override
	public List<OrderItemBean> orderIdSelItem(String orderId1){
		String sql = "select * from t_orderItem where orderId=?";
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		List<OrderItemBean> list = new ArrayList<OrderItemBean>();
		OrderItemBean g = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, orderId1);
			rs = ps.executeQuery();
			int id ;
			int id1;
			int sum ;
			String proPic;
			String goodsName ;
			double price ;
			String orderId;
			String orderIdd;
			while(rs.next()){
				id = rs.getInt("goodsId");
				id1 = rs.getInt("id");
				sum = rs.getInt("sum");
				proPic = rs.getString("proPic");
				goodsName = rs.getString("goodsName");
				price = rs.getDouble("goodsPrice");
				orderId=rs.getString("orderId");
				orderIdd=rs.getString("orderIdd");
				g = new OrderItemBean();
				g.setGoodsId(id);
				g.setId(id1);
				g.setSum(sum);
				g.setGoodsPrice(price);
				g.setProPic(proPic);
				g.setGoodsName(goodsName);
				g.setOrderId(orderId);
				g.setOrderIdd(orderIdd);
				list.add(g);
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
	public List<OrderItemBean> orderIdSelItemId(int orderId1){
		String sql = "select * from t_orderItem where id=?";
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		List<OrderItemBean> list = new ArrayList<OrderItemBean>();
		OrderItemBean g = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, orderId1);
			rs = ps.executeQuery();
			int id ;
			int id1;
			int sum ;
			String proPic;
			String goodsName ;
			double price ;
			String orderId;
			String orderIdd;
			while(rs.next()){
				id = rs.getInt("goodsId");
				id1 = rs.getInt("id");
				sum = rs.getInt("sum");
				proPic = rs.getString("proPic");
				goodsName = rs.getString("goodsName");
				price = rs.getDouble("goodsPrice");
				orderId=rs.getString("orderId");
				orderIdd=rs.getString("orderIdd");
				g = new OrderItemBean();
				g.setGoodsId(id);
				g.setId(id1);
				g.setSum(sum);
				g.setGoodsPrice(price);
				g.setProPic(proPic);
				g.setGoodsName(goodsName);
				g.setOrderId(orderId);
				g.setOrderIdd(orderIdd);
				list.add(g);
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
	public List<OrderItemBean> orderIdSelItemidd(String orderId1){
		String sql = "select * from t_orderItem where orderIdd=?";
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		List<OrderItemBean> list = new ArrayList<OrderItemBean>();
		OrderItemBean g = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, orderId1);
			rs = ps.executeQuery();
			int id ;
			int id1;
			int sum ;
			String proPic;
			String goodsName ;
			double price ;
			String orderId;
			String orderIdd;
			while(rs.next()){
				id = rs.getInt("goodsId");
				id1 = rs.getInt("id");
				sum = rs.getInt("sum");
				proPic = rs.getString("proPic");
				goodsName = rs.getString("goodsName");
				price = rs.getDouble("goodsPrice");
				orderId=rs.getString("orderId");
				orderIdd=rs.getString("orderIdd");
				g = new OrderItemBean();
				g.setGoodsId(id);
				g.setId(id1);
				g.setSum(sum);
				g.setGoodsPrice(price);
				g.setProPic(proPic);
				g.setGoodsName(goodsName);
				g.setOrderId(orderId);
				g.setOrderIdd(orderIdd);
				list.add(g);
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
	public static void main(String[] args) {
		//OrderItemBean o = new OrderItemBean(1, "西瓜", "image", 1.2, 2, 2.4, "20151111");
		//add(o);
	}
}
