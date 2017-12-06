package me.ilt.goods.dao.impl;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Order;

import me.ilt.common.bean.PageBean;
import me.ilt.goods.bean.OrderBean;
import me.ilt.goods.bean.OrderItemBean;
import me.ilt.goods.dao.OrderDao;
import me.ilt.goods.dao.OrderItemDao;
import me.ilt.utils.GoodsConstCode;
import me.ilt.utils.JdbcUtil;
import me.ilt.utils.JsonUtil;
import net.sf.json.JSONArray;

public class OrderDaoImpl implements OrderDao {
	
     private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.OrderDao#orderIdSel(java.lang.String)
	 */
	@Override
	public OrderBean orderIdSel(String orderId){
		String sql = "select * from t_order where id =?";
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		OrderBean order = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, orderId);
			rs = ps.executeQuery();
			rs.next();
			int userId = rs.getInt("userId");
			String id = rs.getString("id"); //订单ID
			double total = rs.getDouble("total"); //订单总额
			int addressId = rs.getInt("addressId"); //收货地址ID
			String remarks = rs.getString("remarks");  //买家留言
			Date orderTime = rs.getTimestamp("time");  //下单时间
			int state = rs.getInt("state");  //当前状态
			int payType = rs.getInt("payType");
			Date modifyTime = rs.getTimestamp("theAppointment");
			order = new OrderBean(id, userId, total, addressId, remarks, orderTime, state,payType,modifyTime);
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
		return order;
	}
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.OrderDao#userIdIsorder(int, int, java.lang.String)
	 */
	@Override
	public List<String> userIdIsordercount(int userId){
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
	@Override
	public PageBean userIdIsorder(int userId,int p,String type){
		PageBean pb = new PageBean();
		int count = 0; //查询的总条数
		String sql = null;
		switch (Integer.parseInt(type)) {
		case 1:
			sql = "select count(*) count from t_order where userId ="+userId;
			break;
		case 2:
			sql = "select count(*) count from t_order where userId ="+userId+"  and state = 1";
			break;
		case 3:
			sql = "select count(*) count from t_order where userId ="+userId+"  and state = 2";
			break;
		case 4:
			sql = "select count(*) count from t_order where userId ="+userId+"  and state = 3";
			break;
		default:
			break;
		}
		count = count(sql); //获取条数
		System.out.println("获取到查询的条数为："+count);
		pb.setCount(count); //放入总条数
		pb.setP(p); //放入当前页码
		String sql2 = null;
		switch (Integer.parseInt(type)) {
		case 1:
			sql2 = "select  * from t_order where userId ="+userId
			+" order by time desc limit "+(pb.getP()-1)*pb.getPagesize()+","+pb.getPagesize();
			break;
		case 2:
			sql2 = "select  * from t_order where userId ="+userId
			+" and state = 1"
			+" order by time desc limit "+(pb.getP()-1)*pb.getPagesize()+","+pb.getPagesize();
			break;
		case 3:
			sql2 = "select * from t_order where userId ="+userId
			+" and state = 2"
			+" order by time desc limit "+(pb.getP()-1)*pb.getPagesize()+","+pb.getPagesize();
			break;
		case 4:
			sql2 = "select  * from t_order where userId ="+userId
			+" and state = 3"
			+" order by time desc limit "+(pb.getP()-1)*pb.getPagesize()+","+pb.getPagesize();
			break;
		default:
			break;
		}
		
		System.out.println("发送的sql:"+sql2);
		List<OrderBean> list = new ArrayList<OrderBean>();
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()){
				String id = rs.getString("id"); //订单ID
				System.out.println(id);
				double total = rs.getDouble("total"); //订单总额
				int addressId = rs.getInt("addressId"); //收货地址ID
				String remarks = rs.getString("remarks");  //买家留言
				Date orderTime = rs.getTimestamp("time");  //下单时间
				int state = rs.getInt("state");  //当前状态
				int payType = rs.getInt("payType");
				Date modifyTime = rs.getTimestamp("theAppointment");
				List<OrderItemBean> itemList = orderItemDao.orderIdSelItemss(id);
				OrderBean order = new OrderBean(id, userId, total, addressId, remarks, orderTime, state,payType,modifyTime);
      			order.setItemList(itemList);
				list.add(order);
				
			}
			pb.setData(list);
			
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
		return pb;
	}
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.OrderDao#selAll(int, int)
	 */
	@Override
	public JSONArray selAll(int p,int pageSize){
		String sql = "select t_order.*,t_user.userName,t_user.trueName,t_address.province,t_address.city,t_address.area,t_address.address,t_address.phone,t_address.username from t_order,t_user,t_address where t_order.userId=t_user.id and t_address.id=t_order.addressId "
	           +"order by time desc limit "+(p-1)*pageSize+","+pageSize;
				return sel(sql);
	}
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.OrderDao#sel(java.lang.String)
	 */
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
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.OrderDao#count(java.lang.String)
	 */
	@Override
	public int count(String sql){
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
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.OrderDao#add(me.ilt.bean.OrderBean)
	 */
	@Override
	public int add(OrderBean u){

		String sql = "insert into t_order (id,userId, total, addressId, remarks, Time, state,payType,theAppointment) values(?,?,?,?,?,?,?,?,?)";
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getId());
			ps.setDouble(2, u.getUserId());
			ps.setDouble(3, u.getTotal());
			ps.setInt(4, u.getAddressId());
			ps.setString(5, u.getRemarks());
			ps.setTimestamp(6,new java.sql.Timestamp(u.getOrderTime().getTime()));
			ps.setInt(7, u.getState());
			ps.setInt(8, u.getPayType());
			ps.setTimestamp(9,new java.sql.Timestamp(u.getModifyTime().getTime()));
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
	 * @see me.ilt.dao.impl.OrderDao#update(java.lang.String, java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public int update(String orderId,String addressId,int state,int payType,String liuyan){
		
		//int state = 0;
		/*if(payType.equals("1")){
			//货到付款
			state = 6;
		}else{
			//在线支付
			state = 2;
		}*/
		String sql = "update t_order set addressId=?,remarks=?,state=?,payType=? where id=?";
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(addressId));
			ps.setString(2, liuyan);
			ps.setInt(3, state);
			ps.setInt(4, payType);
			ps.setString(5, orderId);
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
	 * @see me.ilt.dao.impl.OrderDao#qxdd(java.lang.String, int)
	 */
	@Override
	public int qxdd(String orderId,int state){
		
		String sql = "update t_order set state=? where id=?";
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, state);
			ps.setString(2, orderId);
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
	 * @see me.ilt.dao.impl.OrderDao#selQueryList()
	 */
	@Override
	public List<OrderBean> selQueryList(){
		StringBuffer selectSb = new StringBuffer();
		selectSb.append("select * from t_order where 1=1 ");
		selectSb.append(" and state= "+GoodsConstCode.CUST_ORDER_STATUS_WAIT_PAY);
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<OrderBean> goodsBeanList = new ArrayList<OrderBean>();
		try {
			ps = con.prepareStatement(selectSb.toString());
			rs = ps.executeQuery();
			String id ; //订单ID
			int userId ;
			double total; //订单总额
			int addressId; //收货地址ID
			String remarks;   //买家留言
			Date orderTime  ;  //下单时间
			int state ;  //当前状态
			int payType;//支付方式
			Date modifyTime ;
			while(rs.next()){
				id = rs.getString("id"); //订单ID
				userId = rs.getInt("userId");
				total = rs.getDouble("total"); //订单总额
				addressId = rs.getInt("addressId"); //收货地址ID
				remarks = rs.getString("remarks");  //买家留言
				orderTime = rs.getTimestamp("time");  //下单时间
				//System.out.println(time);
				state = rs.getInt("state");  //当前状态
				payType = rs.getInt("payType");
				modifyTime = rs.getTimestamp("theAppointment");
				//List<OrderItemBean> itemList = orderItemDao.orderIdSelItem(id);
				OrderBean order = new OrderBean(id, userId, total, addressId, remarks, orderTime, state,payType,modifyTime);
				//order.setItemList(itemList);
				goodsBeanList.add(order);
			}
			return goodsBeanList;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.OrderDao#main(java.lang.String[])
	 */
	@Override
	public void main(String[] args) {
		
		//orderBean or = new orderBean("12345", 12, 6.6, 0, "", "2015-01-01", 1);
		//add(or);
		//List<orderBean> list =userIdIsorder(10006);
		
		//PageBean pb = userIdIsorder(10026, 1,"3");
		//System.out.println(pb);
		
		//orderIdSel("201602201723459");
		
	}
	@Override
	public ArrayList<String> orderstateSel(int userId){
		String sql = "select state from t_order where userId =?";
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		ArrayList<String>  strArray = new ArrayList<String> (); 
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
		 if(rs.next()){
			String state=Integer.toString(rs.getInt("state")) ;  //当前状态
			strArray.add(state);
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
		System.out.println(strArray);
		return strArray;
	}
	
}
