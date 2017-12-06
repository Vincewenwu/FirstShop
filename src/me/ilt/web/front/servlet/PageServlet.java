package me.ilt.web.front.servlet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.ilt.common.bean.PageBean;
import me.ilt.goods.bean.GoodsBean;
import me.ilt.goods.dao.AddressDao;
import me.ilt.goods.dao.GoodsDao;
import me.ilt.goods.dao.OrderDao;
import me.ilt.goods.dao.OrderItemDao;
import me.ilt.goods.dao.impl.AddressDaoImpl;
import me.ilt.goods.dao.impl.GoodsDaoImpl;
import me.ilt.goods.dao.impl.OrderDaoImpl;
import me.ilt.goods.dao.impl.OrderItemDaoImpl;
import me.ilt.sys.dao.BigTypeDao;
import me.ilt.sys.dao.SlideDao;
import me.ilt.sys.dao.impl.BigTypeDaoImpl;
import me.ilt.sys.dao.impl.SlideDaoImpl;
import me.ilt.user.bean.ShoppingCart;
import me.ilt.user.dao.ShoppingCartDao;
import me.ilt.user.dao.UsersDao;
import me.ilt.user.dao.impl.ShoppingCartDaoImpl;
import me.ilt.user.dao.impl.UserDaoImpl;

public class PageServlet extends HttpServlet {

	private UsersDao usersDao = new UserDaoImpl();
	private OrderDao orderDao = new OrderDaoImpl();
	private BigTypeDao bigTypeDao = new BigTypeDaoImpl();
	private GoodsDao goodsDao = new GoodsDaoImpl();
	private AddressDao  addressDao = new AddressDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	private ShoppingCartDao shoppingCartDao= new ShoppingCartDaoImpl();
//	private CollectDao  collectDao = new CollectDaoImpl();
	private SlideDao slideDao = new SlideDaoImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String MethodName = request.getServletPath();
		MethodName = MethodName.substring(1, MethodName.length() - 5);
		System.out.println("MethodName："+MethodName);
		try {
			Method method = getClass().getDeclaredMethod(MethodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	public void userMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入了用户中心");
		String username = (String)request.getSession().getAttribute("userName");
		String p = request.getParameter("page");
		ArrayList<String>  strArray = new ArrayList<String> (); 
		List<String> ListOrder=new ArrayList<String>();
		if(p==null){ //如果未收到请求页码  则赋值1
			p="1";
		}
		String type = request.getParameter("type");
		/**
		 * type说明
		 * 1：全部订单
		 * 2：待支付订单
		 * 3：代发货订单
		 * 4：待确认订单
		 */
		if(type==null){
			type="1";
		}
		if(username==null){
			response.sendRedirect("login.jsp");
		}else{
			int userId = usersDao.nameIsId(username);
			PageBean pageBean =orderDao.userIdIsorder(userId,Integer.parseInt(p),type);
		
			request.setAttribute("PageDate", pageBean); //放入订单集合
			
			ListOrder = shoppingCartDao.userIdIsordercount(userId);
			
			//获取购物车
			List<GoodsBean> goodsList = new ArrayList<GoodsBean>(); //购物车集合
			//获取购物车中所有购物项目
			List<ShoppingCart> list = shoppingCartDao.selList(userId);
			for(ShoppingCart s: list){
				GoodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //从数据库查找
				hgoods2.setNum(s.getNum());
				goodsList.add(hgoods2);
			}
			request.setAttribute("type", type); //放入请求类型
			request.setAttribute("p", p); //放入请求页码
			request.setAttribute("ListOrder", ListOrder); //状态数量
			request.setAttribute("gwcGoodsList", goodsList); //放入购物车集合
			request.setAttribute("rightPage", "orderAll");
			request.getRequestDispatcher("userMain.jsp").forward(request, response);
		}
	}
	
}