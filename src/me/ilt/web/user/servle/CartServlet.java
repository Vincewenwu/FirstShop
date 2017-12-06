package me.ilt.web.user.servle;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import me.ilt.user.bean.ShoppingCart;
import me.ilt.user.dao.ShoppingCartDao;
import me.ilt.user.dao.impl.ShoppingCartDaoImpl;
import me.ilt.user.dao.impl.UserDaoImpl;
import me.ilt.utils.GoodsConstCode;
import me.ilt.utils.ResponseUtil;

public class CartServlet extends HttpServlet {
	private ShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
	private AddressDao addressDao = new AddressDaoImpl();
	private GoodsDao goodsDao = new GoodsDaoImpl();
	UserDaoImpl userdao=new UserDaoImpl();
	ShoppingCartDao shopping=new ShoppingCartDaoImpl();
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
               doGet(request, response);
	}

	public void doGet (HttpServletRequest request, HttpServletResponse response)
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
	public void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取购物车
		String username = (String)request.getSession().getAttribute("userName"); //获取登录的用户名
		List<GoodsBean> goodsList = new ArrayList<GoodsBean>(); //购物车集合
		if(username==null){
			//没有用户登录
			//获取购物车
		HashMap<String,GoodsBean> gwc=(HashMap)request.getSession().getAttribute("gwc");
		Set keyList =gwc.keySet();
		Iterator it=keyList.iterator();
		while(it.hasNext()){
			String hid=(String) it.next();
			GoodsBean hgoods=gwc.get(hid);
			int num=hgoods.getNum();
			GoodsBean hgoods2 = goodsDao.gwcGoodsIdSel(Integer.parseInt(hid)); //从数据库查找
			hgoods2.setNum(num);
			goodsList.add(hgoods2);	
		  }
		}else{
			//用户已登录
			int userId=userdao.nameIsId(username);
			//获取购物车中的所有选项
			List<ShoppingCart> list=shopping.selList(userId);
			for(ShoppingCart s:list){
				GoodsBean hgoods2=goodsDao.gwcGoodsIdSel(s.getGoodsId());
				hgoods2.setNum(s.getNum());
				goodsList.add(hgoods2);
			}
		}
		request.setAttribute("gwcGoodsList", goodsList);
		request.getRequestDispatcher("cart.jsp").forward(request, response);  //转发到jsp
		
	}
	public void tijiao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username=(String) request.getSession().getAttribute("userName");
		int userId = userdao.nameIsId(username);
		String  j=request.getParameter("id");
		if(j==null){
			String id = request.getParameter("cartItemIds");
			String [] ids = id.split(",");
			//获取购物车	
			List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
			 DecimalFormat df = new DecimalFormat("#.00");  //防止价格出现异常
			 double ze = 0; //总额
			 List<String> Danhao=new ArrayList<String>();
			 String orderId ="";
			for(String zj : ids){
				//根据 gwc集合判断购物车中的购物项是在session中 还是在数据库中
				int num = 0;
				//购物项存放在数据库中
				ShoppingCart s = shoppingCartDao.goodsIdSel(userId, Integer.parseInt(zj));
				num = s.getNum();
				GoodsBean hgoods2 = goodsDao.gwcGoodsIdSel(Integer.parseInt(zj)); //从数据库查找
				hgoods2.setNum(num);
				double hj = num*hgoods2.getPrice();
				ze +=hj;
				hgoods2.setTotal(Double.parseDouble(df.format(hj)));
				System.out.println("处理之前总额："+hj+"处理之后："+df.format(hj));
				orderId = sjs(); //生成订单号
				Danhao.add(orderId);
				hgoods2.setOrdetIdd(orderId);
				goodsList.add(hgoods2);
				/*if(gwc.size()==0){
					shoppingCartDao.del(userId, Integer.parseInt(j));
				}else{
					gwc.remove(j);//删除商品  购物车
				}*/
				shoppingCartDao.del(userId, Integer.parseInt(zj));
				
				Date d = new Date();
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//插入一条订单
				OrderBean or = new OrderBean(orderId, userId, Double.parseDouble(df.format(ze)), 1, "", d, GoodsConstCode.CUST_ORDER_STATUS_WAIT_PAY ,0,d);
				orderDao.add(or);
			}
			//打印购物车
			System.out.println("打印订单购物车");
			for (int i = 0; i < goodsList.size(); i++) {
				GoodsBean gb = goodsList.get(i);
				System.out.println("ID="+gb.getId()+",name="+gb.getName()+",proce="+gb.getPrice()+",proPic="+gb.getProPic()+",num="+gb.getNum());
			}
			for(GoodsBean g : goodsList){
				OrderItemBean o = new OrderItemBean(g.getId(), g.getName(), g.getProPic(), g.getPrice(), g.getNum(), g.getTotal(), orderId,g.getOrdetIdd());
				orderItemDao.add1(o);
			}
			//插入到商品订单表中
			//根据用户ID获取地址信息
			List<AddressBean> addressList= addressDao.selAll(userId); 
			request.setAttribute("addressList", addressList);
			request.setAttribute("addressId", orderId);
			request.setAttribute("ze", df.format(ze));
			request.setAttribute("gwcGoodsList", goodsList);
		
			response.sendRedirect("cxsy.order?orderId="+Danhao); //转发到jsp
		
		}else{
			double total=Integer.decode(request.getParameter("total")) ;
			//获取购物车
			List<GoodsBean> goodsList=new ArrayList<GoodsBean>();
			DecimalFormat df=new DecimalFormat("#.00");
			int num = 0;
			ShoppingCart s = shoppingCartDao.goodsIdSel(userId, Integer.parseInt(j));
			num = s.getNum();
			GoodsBean hgoods2 = goodsDao.gwcGoodsIdSel(Integer.parseInt(j)); //从数据库查找
			hgoods2.setNum(num);
			hgoods2.setTotal(Double.parseDouble(df.format(total)));
			System.out.println("处理之前总额："+total+"处理之后："+df.format(total));
			String orderId = sjs(); //生成订单号
			hgoods2.setOrdetIdd(orderId);
			goodsList.add(hgoods2);
			shoppingCartDao.del(userId, Integer.parseInt(j));
			//插入一条订单System.out.println("打印订单购物车");
			for (int i = 0; i < goodsList.size(); i++) {
				GoodsBean gb = goodsList.get(i);
				System.out.println("ID="+gb.getId()+",name="+gb.getName()+",proce="+gb.getPrice()+",proPic="+gb.getProPic()+",num="+gb.getNum());
			}
			Date d = new Date();
		
			OrderBean or = new OrderBean(orderId, userId, Double.parseDouble(df.format(total)), 1, null, d, GoodsConstCode.CUST_ORDER_STATUS_WAIT_PAY ,0,d);
			int count=orderDao.add(or);
			//根据用户ID获取地址信息
			for(GoodsBean g : goodsList){
				OrderItemBean o = new OrderItemBean(g.getId(), g.getName(), g.getProPic(), g.getPrice(), g.getNum(), g.getTotal(), orderId,g.getOrdetIdd());
				orderItemDao.add1(o);
			}
			List<AddressBean> addressList= addressDao.selAll(userId); 
			request.setAttribute("gwcGoodsList", goodsList);
			request.setAttribute("addressList", addressList);
			JSONObject result = new JSONObject();
			result.put("result", count);
			result.put("orderId", orderId);
			ResponseUtil.write(response, result);
		}	
	}
	public void zhijiegoumai(HttpServletRequest request,HttpServletResponse response){
		
		String username=(String) request.getSession().getAttribute("userName");
		int userId = userdao.nameIsId(username);
		String  j=request.getParameter("id");
		double total=Integer.decode(request.getParameter("total")) ;
		//获取购物车
		List<GoodsBean> goodsList=new ArrayList<GoodsBean>();
		DecimalFormat df=new DecimalFormat("#.00");
		int num =Integer.parseInt(request.getParameter("num")) ;
		/*ShoppingCart s = shoppingCartDao.goodsIdSel(userId, Integer.parseInt(j));
		num = s.getNum();*/
		GoodsBean hgoods2 = goodsDao.gwcGoodsIdSel(Integer.parseInt(j)); //从数据库查找
		hgoods2.setNum(num);
		hgoods2.setTotal(Double.parseDouble(df.format(total)));
		System.out.println("处理之前总额："+total+"处理之后："+df.format(total));
		goodsList.add(hgoods2);
		shoppingCartDao.del(userId, Integer.parseInt(j));
		//插入一条订单System.out.println("打印订单购物车");
		for (int i = 0; i < goodsList.size(); i++) {
			GoodsBean gb = goodsList.get(i);
			System.out.println("ID="+gb.getId()+",name="+gb.getName()+",proce="+gb.getPrice()+",proPic="+gb.getProPic()+",num="+gb.getNum());
		}
		Date d = new Date();
		String orderId = sjs(); //生成订单号
		OrderBean or = new OrderBean(orderId, userId, Double.parseDouble(df.format(total)), 1, null, d, GoodsConstCode.CUST_ORDER_STATUS_WAIT_PAY ,0,d);
		int count=orderDao.add(or);
		//根据用户ID获取地址信息
		for(GoodsBean g : goodsList){
			OrderItemBean o = new OrderItemBean(g.getId(), g.getName(), g.getProPic(), g.getPrice(), g.getNum(), g.getTotal(), orderId);
			orderItemDao.add(o);
		}
		List<AddressBean> addressList= addressDao.selAll(userId); 
		request.setAttribute("gwcGoodsList", goodsList);
		request.setAttribute("addressList", addressList);
		JSONObject result = new JSONObject();
		result.put("result", count);
		result.put("orderId", orderId);
		ResponseUtil.write(response, result);
	}
	/**
	 * 生成订单号  当前时间加上两位随机数
	 * @return
	 */
	public static String sjs() {
		String sjs = "";
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//Math.random()返回0.0-1.0之间的随机小数
		int x=(int)(Math.random()*100);
		if(x<10){
			x=x+9;
		}else if(x==100){
			x--;
		}
		sjs=sdf.format(d)+x;
		return sjs;
	}
	
}
