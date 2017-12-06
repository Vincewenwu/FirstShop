package me.ilt.web.goods.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import me.ilt.goods.bean.AddressBean;
import me.ilt.goods.bean.GoodsBean;
import me.ilt.goods.bean.OrderBean;
import me.ilt.goods.bean.OrderItemBean;
import me.ilt.goods.dao.AddressDao;
import me.ilt.goods.dao.GoodsDao;
import me.ilt.goods.dao.OrderDao;
import me.ilt.goods.dao.OrderItemDao;
import me.ilt.goods.dao.impl.AddressDaoImpl;
import me.ilt.goods.dao.impl.GoodsDaoImpl;
import me.ilt.goods.dao.impl.OrderDaoImpl;
import me.ilt.goods.dao.impl.OrderItemDaoImpl;
import me.ilt.sys.dao.BigTypeDao;
import me.ilt.sys.dao.impl.BigTypeDaoImpl;
import me.ilt.user.dao.UsersDao;
import me.ilt.user.dao.impl.UserDaoImpl;
import me.ilt.utils.ResponseUtil;

public class OrderServlet extends HttpServlet {

	private UsersDao usersDao = new UserDaoImpl();
	private OrderDao orderDao = new OrderDaoImpl();
	private BigTypeDao bigTypeDao = new BigTypeDaoImpl();
	private GoodsDao goodsDao = new GoodsDaoImpl();
	private AddressDao  addressDao = new AddressDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String MethodName = request.getServletPath();
		MethodName = MethodName.substring(1, MethodName.length() - 6);
		System.out.println("MethodName："+MethodName);
		try {
			Method method = getClass().getDeclaredMethod(MethodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	/**
	 * 查询所有 and 模糊搜索
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		System.out.println("进入了订单查询");
		String p = request.getParameter("page"); //需求页码
		String rows = request.getParameter("rows"); //每页多少条
		String sel = request.getParameter("name"); //如果是查询这不为空
		System.out.println("收到请求："+p+"  "+rows+"  "+sel);
		if(sel==null){
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_order";
			int count = orderDao.count(sql); //获取条数
			
			JSONArray jsonArray = orderDao.selAll(Integer.parseInt(p), Integer.parseInt(rows)); //获取dao返回的json集合
			
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}else{
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_goods where name like '%"+sel+"%'";
			System.out.println("查询行数sql为："+sql);
			int count = bigTypeDao.count(sql); //获取条数
			JSONArray jsonArray = goodsDao.nameSel(Integer.parseInt(p), Integer.parseInt(rows),sel); //获取dao返回的json集合
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}
	}
	public void oidSel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入了查看订单详情");
		String orderId = request.getParameter("orderNo");
		JSONObject result = new JSONObject();
		JSONArray jsonArray = orderItemDao.orderIdSel(orderId);
		
		result.put("rows", jsonArray);
		ResponseUtil.write(response, result);
		
	}
	/**
	 * 订单发货
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ddfh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderNos");
		//修改订单状态
		orderDao.qxdd(orderId, 3);
		//success
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
	}
	/**
	 * 确认收货
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ddhh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderNos");
		//修改订单状态
		int i=orderDao.qxdd(orderId, 5);
		//success
		if(i==1){
			request.getRequestDispatcher("userMain.page?type=1").forward(request, response);	
		}
	
	}
	/**
	 * 提交订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void tjdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入了提交订单");
		String cartItemIds = request.getParameter("cartItemIds");//订单编号
	    String [] ids=cartItemIds.split(",");
		String addressId = request.getParameter("sender");//地址ID
		String payType ="2";
		String liuyan ="我很高兴";
		int state = 0;
		GoodsBean goods=null;
		List<GoodsBean> hgood=new ArrayList<GoodsBean>();
		List<AddressBean> addressList=null;
		if(payType.equals("1")){
			//货到付款
			state = 6;
		}else{
			//在线支付
			state = 2;
		}
		int i=0;int j=0;int z=0;
		//根据订单编号查询订单商品  减去库存
		for(String zj:ids){
			List<OrderItemBean> list = orderItemDao.orderIdSelItemId(Integer.parseInt(zj));
			for(OrderItemBean g:list){
				   z++;
			      goodsDao.stockJian(g.getGoodsId(), g.getSum()); //减去库存
			      if(ids.length==1){
			    	  i=orderDao.update(g.getOrderId(), addressId, state,Integer.parseInt(payType), liuyan); 
			        }else{
				   i=orderDao.update(g.getOrderIdd(), addressId, state,Integer.parseInt(payType), liuyan); 
			      }
			      if(i==1){
					  j++;
					System.out.println("修改订单状态成功");
				  	 }else{
					System.out.println("修改订单状态失败");
				}
			  	 addressList= addressDao.selAlldantiao(Integer.parseInt(addressId.toString().trim())); 
			  	 goods= goodsDao.gwcGoodsIdSel(g.getGoodsId());
			      goods.setOrdetId(g.getOrderId());
			      goods.setTotal(g.getSum()*g.getGoodsPrice());
			      hgood.add(goods);
			}
		}
		if(z==j){
			request.setAttribute("addressList", addressList);
			request.setAttribute("orderState", 1);
			request.setAttribute("hgood", hgood);
			request.getRequestDispatcher("pay.jsp").forward(request, response);
		}
	}
	/**
	 * 支付订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void zfdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入了支付订单");
		String orderId = request.getParameter("orderId");
		int  orderitemId =Integer.parseInt(request.getParameter("orderitem")) ;
		String username = (String)request.getSession().getAttribute("userName");
		System.out.println("获取到的订单id为："+orderId);
		System.out.println("获取到的当前登录人为："+username);
		int userId = usersDao.nameIsId(username); //根据用户名获取ID
		System.out.println("根据用户名查询id为："+userId);
		OrderBean o = orderDao.orderIdSel(orderId);
		double hj=0.0;
		GoodsBean hgoods =null;
		if(o.getUserId()==userId){
			System.out.println("当前登录人为本人");
			DecimalFormat df = new DecimalFormat("#.00");  //防止价格出现异常
			//查询订单信息
			List<OrderItemBean> list=null;
			if(orderitemId==001){
				 list = orderItemDao.orderIdSelItem(orderId);
			}else{
				 list = orderItemDao.orderIdSelItemId(orderitemId);
			}
			List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
			for(OrderItemBean oi : list){
				System.out.println("商品名称为："+oi.getGoodsName()+"商品ID为："+oi.getGoodsId());
				 hgoods = goodsDao.gwcGoodsIdSel(oi.getGoodsId()); //从数据库查找
				System.out.println("小计未格式化："+oi.getSum()*hgoods.getPrice());
				 hj = Double.parseDouble(df.format(oi.getSum()*hgoods.getPrice()));
				System.out.println(oi.getGoodsName()+"小计为："+hj);
				 String orderidnew=goodsDao.selectOrdernew(orderId);
				hgoods.setNum(oi.getSum());
				hgoods.setOrdetId(orderId); 
				if(orderitemId==001){
					hgoods.setOrder(Integer.parseInt(orderidnew));
				}else{
					hgoods.setOrder(orderitemId);
				}
				hgoods.setTotal(hj);
				goodsList.add(hgoods);
			}
			
			//根据用户ID获取地址信息
			List<AddressBean> addressList= addressDao.selAll(userId); 
			request.setAttribute("addressList", addressList); //地址
			request.setAttribute("ze", hj);  //总额
			request.setAttribute("gwcGoodsList", goodsList);  //购物车中的商品
			request.setAttribute("orderitemId", orderId); //订单id
			request.getRequestDispatcher("order.jsp").forward(request, response);  //转发到jsp
		}else{
			//非法操作
			response.sendRedirect("userMain.page");
		}
	}
	public void zfdddangezhifu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入了支付订单");
		String orderId = request.getParameter("orderId");
		int  orderitemId =Integer.parseInt(request.getParameter("orderitem")) ;
		String username = (String)request.getSession().getAttribute("userName");
		System.out.println("获取到的订单id为："+orderId);
		System.out.println("获取到的当前登录人为："+username);
		int userId = usersDao.nameIsId(username); //根据用户名获取ID
		System.out.println("根据用户名查询id为："+userId);
		OrderBean o = orderDao.orderIdSel(orderId);
		double hj=0.0;
		GoodsBean hgoods =null;
		if(o.getUserId()==userId){
			System.out.println("当前登录人为本人");
			DecimalFormat df = new DecimalFormat("#.00");  //防止价格出现异常
			//查询订单信息
			List<OrderItemBean> list=null;
			if(orderitemId==001){
				 list = orderItemDao.orderIdSelItem(orderId);
			}else{
				 list = orderItemDao.orderIdSelItemId(orderitemId);
			}
			List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
			for(OrderItemBean oi : list){
				System.out.println("商品名称为："+oi.getGoodsName()+"商品ID为："+oi.getGoodsId());
				 hgoods = goodsDao.gwcGoodsIdSel(oi.getGoodsId()); //从数据库查找
				System.out.println("小计未格式化："+oi.getSum()*hgoods.getPrice());
				 hj = Double.parseDouble(df.format(oi.getSum()*hgoods.getPrice()));
				System.out.println(oi.getGoodsName()+"小计为："+hj);
				 String orderidnew=goodsDao.selectdangezifu(orderId);
				hgoods.setNum(oi.getSum());
				hgoods.setOrdetId(orderId); 
				if(orderitemId==001){
					hgoods.setOrder(Integer.parseInt(orderidnew));
				}else{
					hgoods.setOrder(orderitemId);
				}
				hgoods.setTotal(hj);
				goodsList.add(hgoods);
			}
			
			//根据用户ID获取地址信息
			List<AddressBean> addressList= addressDao.selAll(userId); 
			request.setAttribute("addressList", addressList); //地址
			request.setAttribute("ze", hj);  //总额
			request.setAttribute("gwcGoodsList", goodsList);  //购物车中的商品
			request.setAttribute("orderitemId", orderId); //订单id
			request.getRequestDispatcher("order.jsp").forward(request, response);  //转发到jsp
		}else{
			//非法操作
			response.sendRedirect("userMain.page");
		}
	}
	public void cxsy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入了支付订单");
		String id= request.getParameter("orderId");
		id=id.substring(1);
		id=id.substring(0, id.length()-1);
		String [] ids = id.split(",");
		List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
		List<String> ListOrder=new ArrayList<String>();
		for(String zj : ids){
		String username = (String)request.getSession().getAttribute("userName");
		int userId = usersDao.nameIsId(username); //根据用户名获取ID
		System.out.println("根据用户名查询id为："+userId);
		OrderBean o = orderDao.orderIdSel(zj.toString().trim());
	
		double hj=0.0;
		if(o.getUserId()==userId){
			System.out.println("当前登录人为本人");
			DecimalFormat df = new DecimalFormat("#.00");  //防止价格出现异常
			//查询订单信息
			List<OrderItemBean> list = orderItemDao.orderIdSelItemidd(zj.toString().trim());
			for(OrderItemBean oi : list){
				System.out.println("商品名称为："+oi.getGoodsName()+"商品ID为："+oi.getGoodsId());
				GoodsBean hgoods = goodsDao.gwcGoodsIdSel(oi.getGoodsId()); //从数据库查找
				 String orderId=goodsDao.selectOrderIdbyorderidd(zj.toString().trim());
				 String orderidnew=goodsDao.selectOrdernew(zj.toString().trim());
	             ListOrder.add(orderId);
				System.out.println("小计未格式化："+oi.getSum()*hgoods.getPrice());
				 hj = Double.parseDouble(df.format(oi.getSum()*hgoods.getPrice()));
				System.out.println(oi.getGoodsName()+"小计为："+hj);
				hgoods.setNum(oi.getSum());
				hgoods.setOrdetId(orderId); 
				hgoods.setOrder(Integer.parseInt(orderidnew));
				hgoods.setTotal(hj);
				goodsList.add(hgoods);
			}
			for (int i = 0; i < ListOrder.size() - 1; i++) {                             //循环遍历集体中的元素
		         for (int j = ListOrder.size() - 1; j > i; j--) {                         //这里非常巧妙，这里是倒序的是比较
		              if (ListOrder.get(j).equals(ListOrder.get(i))) {
		            	  ListOrder.remove(j);
		              }
		        }
		    }
			//根据用户ID获取地址信息
			List<AddressBean> addressList= addressDao.selAll(userId); 
			request.setAttribute("addressList", addressList); //地址
     		
     		request.setAttribute("orderitemId", ListOrder);
			request.setAttribute("addressId", zj.toString().trim()); //订单id
	      }else{
			//非法操作
			response.sendRedirect("userMain.page");
		}
		}
		request.setAttribute("gwcGoodsList", goodsList);  //购物车中的商品
		request.getRequestDispatcher("order.jsp").forward(request, response);  //转发到jsp
	}
	public void ljgm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入了支付订单");
		String orderitemId= request.getParameter("orderitemId");
		String [] orderitem = orderitemId.split(",");
		String id= request.getParameter("orderId");
		String [] ids = id.split(",");
		GoodsBean hgoods=null;
		List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
		List<String> ListOrder=new ArrayList<String>();
		for(String orid : orderitem){
			ListOrder.add(orid);
		}
		for(String zj : ids){
		String username = (String)request.getSession().getAttribute("userName");
		int userId = usersDao.nameIsId(username); //根据用户名获取ID
		System.out.println("根据用户名查询id为："+userId);
		/*OrderBean o = orderDao.orderIdSel(zj.toString().trim());
		if(o.getUserId()==userId){*/
			System.out.println("当前登录人为本人");
			DecimalFormat df = new DecimalFormat("#.00");  //防止价格出现异常
			
			//查询订单信息
			List<OrderItemBean> list = orderItemDao.orderIdSelItemId(Integer.parseInt(zj));
			for(OrderItemBean oi : list){
				System.out.println("商品名称为："+oi.getGoodsName()+"商品ID为："+oi.getGoodsId());
				 hgoods = goodsDao.gwcGoodsIdSel(oi.getGoodsId()); //从数据库查找
                String orderId=goodsDao.selectOrderId(zj);
               
                ListOrder.add(orderId);
				System.out.println("小计未格式化："+oi.getSum()*hgoods.getPrice());
				double hj = Double.parseDouble(df.format(oi.getSum()*hgoods.getPrice()));
				System.out.println(oi.getGoodsName()+"小计为："+hj);
				hgoods.setNum(oi.getSum());
				hgoods.setOrdetId(orderId); 
				hgoods.setOrder(Integer.parseInt(zj));
				hgoods.setTotal(hj);
				goodsList.add(hgoods);
			}
			for (int i = 0; i < ListOrder.size() - 1; i++) {                             //循环遍历集体中的元素
		         for (int j = ListOrder.size() - 1; j > i; j--) {                         //这里非常巧妙，这里是倒序的是比较
		              if (ListOrder.get(j).equals(ListOrder.get(i))) {
		            	  ListOrder.remove(j);
		              }
		        }
		    }
			//根据用户ID获取地址信息
			List<AddressBean> addressList= addressDao.selAll(userId); 
			request.setAttribute("addressList", addressList); //地址 
			request.setAttribute("orderitemId", ListOrder);
			request.setAttribute("gwcGoodsList", goodsList);  //购物车中的商品
			request.setAttribute("addressId", zj.toString().trim()); //订单id
		}
		request.getRequestDispatcher("order.jsp").forward(request, response);  //转发到jsp
	}
	public void address(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("userName");
		int userId = usersDao.nameIsId(username); //根据用户名获取ID
	     if(userId!=0){
			//根据用户ID获取地址信息
			List<AddressBean> addressList= addressDao.selAll(userId); 
			int i=addressDao.selcount(userId);
			request.setAttribute("addressList", addressList); //地址
			request.setAttribute("count", i); //地址
	      }else{
			//非法操作
			response.sendRedirect("manager.jsp");
		}
		request.getRequestDispatcher("address.jsp").forward(request, response);  //转发到jsp
	}
}
