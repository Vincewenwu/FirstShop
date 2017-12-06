package me.ilt.goods.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import me.ilt.goods.bean.GoodsBean;
import me.ilt.goods.dao.GoodsDao;
import me.ilt.utils.JdbcUtil;
import me.ilt.utils.JsonUtil;

public class GoodsDaoImpl implements GoodsDao {

	@Override
	public int count(String sql) {
		   Connection conn=JdbcUtil.getCon();
		   PreparedStatement ps=null;
		   ResultSet rs=null;
		   int i =0;
		   try{
			   ps=conn.prepareStatement(sql);
			rs= ps.executeQuery();
			while(rs.next()){
				i = rs.getInt(1);
			}
		   }catch (Exception e) {
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
	public JSONArray selAll(int p, int pageSize) {
		  String sql="select t_goods.*,t_smallType.sname smallTypeName,t_bigType.bname bigTypeName from t_goods,t_smallType,t_bigType "
		+"where t_smallType.id = t_goods.smallTypeId and t_bigType.id = t_goods.bigTypeId "
		+" order by t_goods.id desc limit " +(p-1)*pageSize+","+pageSize;
		  return sel(sql);
	}
	@Override
	public  JSONArray nameSel(int p,int pageSize,String name){
		String sql = "select  t_goods.*,t_smallType.sname smallTypeName,t_bigType.bname bigTypeName "
				+"from t_goods,t_smallType,t_bigType where t_smallType.id = t_goods.smallTypeId and t_goods.name like '%"+name+"%' and t_bigType.id = t_goods.bigTypeId "
				+" order by t_goods.id desc limit " +(p-1)*pageSize+","+pageSize;
		return sel(sql);
	}
	@Override
	public  String selectOrderId(String orderId){
		Connection conn=JdbcUtil.getCon();
		String sql = "select  orderId from t_orderitem where id=? ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		String i="";
		try{
			 ps=conn.prepareStatement(sql);
			 ps.setString(1, orderId);
	        rs= ps.executeQuery();
	         while (rs.next()) {
				i = rs.getString("orderId");
				
			}
		}catch (Exception e) {
			// TODO: handle exception
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
	public  int OrderId(String orderId){
		Connection conn=JdbcUtil.getCon();
		String sql = "select  addressId from t_order where id=? ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try{
			 ps=conn.prepareStatement(sql);
			 ps.setString(1, orderId);
	        rs= ps.executeQuery();
	         while (rs.next()) {
				i = rs.getInt("addressId");
				
			}
		}catch (Exception e) {
			// TODO: handle exception
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
	public  String selectOrderIdbyorderidd(String orderIdd){
		Connection conn=JdbcUtil.getCon();
		String sql = "select  orderId from t_orderitem where orderIdd=? ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		String i="";
		try{
			 ps=conn.prepareStatement(sql);
			 ps.setString(1, orderIdd);
	        rs= ps.executeQuery();
	         while (rs.next()) {
				i = rs.getString("orderId");
				
			}
		}catch (Exception e) {
			// TODO: handle exception
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
	public  String selectOrdernew(String id){
		Connection conn=JdbcUtil.getCon();
		String sql = "select  id from t_orderitem where orderIdd=? ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		String i="";
		try{
			 ps=conn.prepareStatement(sql);
			 ps.setString(1, id);
	        rs= ps.executeQuery();
	         while (rs.next()) {
				i = rs.getString("id");
				
			}
		}catch (Exception e) {
			// TODO: handle exception
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
	public  String selectdangezifu(String id){
		Connection conn=JdbcUtil.getCon();
		String sql = "select  id from t_orderitem where orderId=? ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		String i="";
		try{
			 ps=conn.prepareStatement(sql);
			 ps.setString(1, id);
	        rs= ps.executeQuery();
	         while (rs.next()) {
				i = rs.getString("id");
				
			}
		}catch (Exception e) {
			// TODO: handle exception
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
  public JSONArray sel(String sql){
		Connection conn=JdbcUtil.getCon();
		PreparedStatement ps=null;
		ResultSet rs=null;
		JSONArray jsonArray=null;
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			jsonArray=JsonUtil.formatRsToJsonArray(rs);
		}catch (SQLException e) {
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
		return jsonArray;
     }
	@Override
	public int add(GoodsBean u){
		Connection conn=JdbcUtil.getCon();
		String sql="insert into t_goods (name, price, proPic, brand, sales, views, stock, contents, bigTypeId, smallTypeId,color, state) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		int i=0;
		try{
			 ps=conn.prepareStatement(sql);
			 ps.setString(1, u.getName());
				ps.setDouble(2, u.getPrice());
				ps.setString(3, u.getProPic());
				ps.setString(4, u.getBrand());
				ps.setInt(5, u.getSales());
				ps.setInt(6, u.getViews());
				ps.setInt(7, u.getStock());
				ps.setString(8, u.getContents());
				ps.setInt(9, u.getBigTypeId());
				ps.setInt(10, u.getSmallTypeId());
				ps.setString(11, u.getColor());
				ps.setString(12, u.getState());
				i=ps.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}@Override
	public  int update(GoodsBean u){
		
		String sql = null;
		if(u.getProPic()==null){
			 sql = "update t_goods "
					+"set name=?,price=?,brand=?,sales=?,views=?,stock=?,contents=?,bigTypeId=?,smallTypeId=?,state=? "
					+"where id=? ";
		}else{
			 sql = "update t_goods "
					 +"set name=?,price=?,brand=?,sales=?,views=?,stock=?,contents=?,bigTypeId=?,smallTypeId=?,state=?,proPic=? "
					 +"where id=? ";
		}
		System.out.println("数据库dao中sql:"+sql);
		Connection con = JdbcUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setDouble(2, u.getPrice());
			ps.setString(3, u.getBrand());
			ps.setInt(4, u.getSales());
			ps.setInt(5, u.getViews());
			ps.setInt(6, u.getStock());
			ps.setString(7, u.getContents());
			ps.setInt(8, u.getBigTypeId());
			ps.setInt(9, u.getSmallTypeId());
			ps.setString(10, u.getState());
			if(u.getProPic()!=null){
				ps.setString(11, u.getProPic());
				ps.setInt(12, u.getId());
			}else{
				ps.setInt(11, u.getId());
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
	public int manyDel(String ids){
		String sql = "delete from t_goods where id in("+ids+")";
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
	public List<GoodsBean> bigTypeIdSel(int bigTypeId){
		String sql = "select id,name,price,proPic,brand,sales,views,stock,contents,bigTypeid,smallTypeid,color, state from t_goods where bigTypeId="+bigTypeId+"  order by id desc limit 0,10";
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		List<GoodsBean> list = new ArrayList<GoodsBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				String proPic = rs.getString("proPic");
				String brand=rs.getString("brand");
				int sales= rs.getInt("sales");
				int views= rs.getInt("views");
				int stock= rs.getInt("stock");
				String contents=rs.getString("contents");
				int bigTypeid= rs.getInt("bigTypeid");
				int smallTypeid= rs.getInt("smallTypeid");
				String color=rs.getString("color");
				String state=rs.getString("state");
				System.out.println("id="+id+"    name="+name+"    price="+price+"    proPic="+proPic);
				GoodsBean g = new  GoodsBean( id, name,  price,  proPic,  brand,
						 sales,  views,  stock,  contents, bigTypeid,  smallTypeid, color, state);
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
public  GoodsBean goodsIdSel(int id){
		
		String sql = "select *,t_bigType.bname bigTypeName,t_smallType.sname smallTypeName from t_goods,t_bigType,t_smallType where t_smallType.id = t_goods.smallTypeId and t_bigType.id = t_goods.bigTypeId and t_goods.id=?";
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		GoodsBean goods = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				String name = rs.getString("name");  //商品名称
				double price = rs.getDouble("price");  //商品价格
				String proPic = rs.getString("proPic");  //商品图片
				String brand = rs.getString("brand");  //商品品牌
				int sales = rs.getInt("sales");  //商品销量
				int views = rs.getInt("views");  //商品浏览量
				int stock =  rs.getInt("stock");  //商品库存
				String contents = rs.getString("contents");  //商品描述
				int bigTypeId = rs.getInt("bigTypeId");  //大类ID
				String bigTypeName = rs.getString("bigTypeName");  //大类名称
				int smallTypeId = rs.getInt("smallTypeId");  //小类ID
				String smallTypeName = rs.getString("smallTypeName");  //小类名称
				String color = rs.getString("color");  //小类颜色
				goods = new GoodsBean(name, price, proPic, brand, sales, views, stock, contents, bigTypeId, smallTypeId,color, null);
				goods.setBigTypeName(bigTypeName);
				goods.setSmallTypeName(smallTypeName);	
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
		return goods;
	}
@Override
public  List<GoodsBean> bigTypeIdSelxg(int bigTypeId){
	String sql = "select * from t_goods where bigTypeId="+bigTypeId+"  order by sales desc limit 0,6";
	Connection con = JdbcUtil.getCon();
	ResultSet rs = null;
	List<GoodsBean> list = new ArrayList<GoodsBean>();
	try {
		PreparedStatement ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			double price = rs.getDouble("price");
			String proPic = rs.getString("proPic");
			String brand=rs.getString("brand");
			int sales= rs.getInt("sales");
			int views= rs.getInt("views");
			int stock= rs.getInt("stock");
			String contents=rs.getString("contents");
			int bigTypeid= rs.getInt("bigTypeid");
			int smallTypeid= rs.getInt("smallTypeid");
			String color=rs.getString("color");
			String state=rs.getString("state");
			System.out.println("相关商品: id="+id+"    name="+name+"    price="+price+"    proPic="+proPic);
			GoodsBean g = new  GoodsBean( id, name,  price,  proPic,  brand,
					 sales,  views,  stock,  contents, bigTypeid,  smallTypeid, color, state);
			list.add(g);
			System.out.println("商品页相关商品6个查询完毕");
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
public void addViews(int id){
	String sql = "update t_goods set views=views+1 where id = ?";
	Connection con = JdbcUtil.getCon();
	PreparedStatement ps = null;
	try {
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 }

@Override
public GoodsBean gwcGoodsIdSel(int id){
		//名称  单价  ID 图片
		String sql = "select * from t_goods where id=?";
		Connection con = JdbcUtil.getCon();
		ResultSet rs = null;
		GoodsBean goods = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				int nid = rs.getInt("id");  //商品名称
				String name = rs.getString("name");  //商品名称
				double price = rs.getDouble("price");  //商品价格
				String proPic = rs.getString("proPic");  //商品图片
				String brand = rs.getString("brand");  //商品品牌	
				String color = rs.getString("color");  //商品名称
				goods = new GoodsBean();
				goods.setId(nid);
				goods.setName(name);
				goods.setPrice(price);
				goods.setProPic(proPic);
				goods.setBrand(brand);
				goods.setColor(color);
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
		return goods;
	}

@Override
public void addSales(int goodsId, int sum) {
	// TODO Auto-generated method stub
	
}
@Override
public void stockJian(int id,int num){
	String sql = "update t_goods set stock=stock-? where id = ?";
	Connection con = JdbcUtil.getCon();
	PreparedStatement ps = null;
	try {
		ps = con.prepareStatement(sql);
		ps.setInt(1, num);
		ps.setInt(2, id);
		ps.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


}
