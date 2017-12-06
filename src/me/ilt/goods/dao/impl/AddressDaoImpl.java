package me.ilt.goods.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import me.ilt.goods.bean.AddressBean;
import me.ilt.goods.dao.AddressDao;
import me.ilt.utils.JdbcUtil;

public class AddressDaoImpl implements AddressDao {
	
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.AddressDao#add(me.ilt.bean.AddressBean)
	 */
	@Override
	public int add(AddressBean u){
		String sql = "insert into t_address (province, city, area, address, phone, username, 是否默认地址, userId) values(?,?,?,?,?,?,?,?)";
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int id = 0;
		try {
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, u.getProvince());
			ps.setString(2, u.getCity());
			ps.setString(3, u.getArea());
			ps.setString(4, u.getAddress());
			ps.setString(5, u.getPhone());
			ps.setString(6, u.getUsername());
			ps.setInt(7, u.getMsg());
			ps.setInt(8, u.getUserId());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();//这一句代码就是得到插入的记录的id
			   while(rs.next()){
			    id=rs.getInt(1);
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
	
		
	
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.AddressDao#setDefeat(int, int)
	 */
	@Override
	public int setDefeat(int userId,int addressId){
		String sql = "update t_address "
				+"set 是否默认地址=? "
				+"where id=? and userId=? ";
			Connection con = JdbcUtil.getCon();
			PreparedStatement ps = null;
			int i = 0;
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, 1);
				ps.setInt(2, addressId);
				ps.setInt(3, userId);
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
	 * @see me.ilt.dao.impl.AddressDao#xgzt(int)
	 */
	@Override
	public void xgzt(int userId){
		String sql = "update t_address set 是否默认地址=0 where userId="+userId;
		Connection con = JdbcUtil.getCon();
		Statement sta = null;
		try {
			sta = con.createStatement();
			sta.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				sta.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.AddressDao#del(int)
	 */
	@Override
	public int del(int id){
		String sql = "delete from t_address where id=?";
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
	/* (non-Javadoc)
	 * @see me.ilt.dao.impl.AddressDao#selAll(int)
	 */
	@Override
	public List<AddressBean> selAll(int userId){
		String sql = "select * from t_address where userId ="+userId;
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		List<AddressBean> list = new ArrayList<AddressBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String province = rs.getString("province"); //省
				String city = rs.getString("city"); //市
				String area = rs.getString("area"); //县
				String address = rs.getString("address"); //详细地址
				String phone = rs.getString("phone");  //手机号
				String username = rs.getString("username"); //收货人姓名
				int msg = rs.getInt("是否默认地址");//是否是默认
				AddressBean addre = new AddressBean(province, city, area, address, phone, username, msg, userId);
				addre.setId(id);
				list.add(addre);
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
	public int selcount(int userId){
		String sql = "select id  from t_address where userId ="+userId;
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		int i=0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				i++;
				
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
		return i;
		
	}
	@Override
	public List<AddressBean> selAlldantiao(int userId){
		String sql = "select * from t_address where userId ="+userId;
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		List<AddressBean> list = new ArrayList<AddressBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String province = rs.getString("province"); //省
				String city = rs.getString("city"); //市
				String area = rs.getString("area"); //县
				String address = rs.getString("address"); //详细地址
				String phone = rs.getString("phone");  //手机号
				String username = rs.getString("username"); //收货人姓名
				int msg = rs.getInt("是否默认地址");//是否是默认
				AddressBean addre = new AddressBean(province, city, area, address, phone, username, msg, userId);
				addre.setId(id);
				list.add(addre);
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
	 * @see me.ilt.dao.impl.AddressDao#idSel(int)
	 */
	@Override
	public AddressBean idSel(int id){
		String sql = "select * from t_address where id ="+id;
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		AddressBean addre = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				String province = rs.getString("province"); //省
				String city = rs.getString("city"); //市
				String area = rs.getString("area"); //县
				String address = rs.getString("address"); //详细地址
				String phone = rs.getString("phone");  //手机号
				String username = rs.getString("username"); //收货人姓名
				int msg = rs.getInt("是否默认地址");//是否是默认
				addre = new AddressBean(province, city, area, address, phone, username, msg, 0);
				System.out.println(addre);
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
		return addre;
	}
	
	@Override
	public int del(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(AddressBean u,int id ) {
		String sql = "update t_address "+"set province=?,city=?,area=?,address=?,username=?,phone=?,是否默认地址=?,userId=? "+ "where id=? ";
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int id1 = 0;
		try {
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, u.getProvince());
			ps.setString(2, u.getCity());
			ps.setString(3, u.getArea());
			ps.setString(4, u.getAddress());
			ps.setString(5, u.getPhone());
			ps.setString(6, u.getUsername());
			ps.setInt(7, u.getMsg());
			ps.setInt(8, u.getUserId());
			ps.setInt(9, id);
			
			id1=ps.executeUpdate();
			/*ResultSet rs = ps.getGeneratedKeys();//这一句代码就是得到插入的记录的id
			   while(rs.next()){
			    id1=rs.getInt(id);
			   }*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id1;
	}
	
	public static void main(String[] args) {
		//add(new addressBean("河南省", "驻马店市", "确山县", "卧龙家园", "15638377962", "刘振兴",0,10006));
		//xgzt(10006);
		//sel(10006);
		//idSel(2);
	}



	@Override
	public int update(AddressBean t) {
		// TODO Auto-generated method stub
		return 0;
	}
}
