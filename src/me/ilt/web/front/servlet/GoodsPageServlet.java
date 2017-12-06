package me.ilt.web.front.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.ilt.goods.bean.GoodsBean;
import me.ilt.goods.dao.GoodsDao;
import me.ilt.goods.dao.impl.GoodsDaoImpl;
import me.ilt.sys.bean.BigTypeBean;
import me.ilt.sys.dao.impl.BigTypeDaoImpl;
import me.ilt.user.bean.ShoppingCart;
import me.ilt.user.dao.ShoppingCartDao;
import me.ilt.user.dao.UsersDao;
import me.ilt.user.dao.impl.ShoppingCartDaoImpl;
import me.ilt.user.dao.impl.UserDaoImpl;


public class GoodsPageServlet extends HttpServlet {

	GoodsDao goodsDao = new GoodsDaoImpl();
	ShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
	UsersDao usersDao = new UserDaoImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id")); //获取宝贝的ID
		GoodsBean g = goodsDao.goodsIdSel(id);
		System.out.println("商品名称："+g.getName()+"  商品价格："+g.getPrice()+"  图片路径："+g.getProPic()+"  商品分类："+g.getBrand()+"  商品销量："+g.getSales()+"  浏览量："+g.getViews()+"  商品库存："+g.getStock()+"  商品描述："+g.getContents()+"  大类ID："+g.getBigTypeId()+"  大类名称："+g.getBigTypeName()+"  小类ID："+g.getSmallTypeId()+"  小类名称："+g.getSmallTypeName());
		List<GoodsBean> xgGoods = goodsDao.bigTypeIdSelxg(g.getBigTypeId()); //获取相关商品
		
		request.setAttribute("goodsBean", g);
		request.setAttribute("xgGoods", xgGoods);
		goodsDao.addViews(id); //浏览量加1
		
		//获取购物车
		String username = (String)request.getSession().getAttribute("userName"); //获取登录的用户名
		System.out.println(username);
		List<GoodsBean> goodsList = new ArrayList<GoodsBean>(); //购物车集合
		if(username==null){
			//用户没有登录
			//获取购物车
			Map<String, GoodsBean> gwc = (HashMap<String, GoodsBean>)(request.getSession().getAttribute("gwc"));
			
			Set<String> keyList = gwc.keySet();
			Iterator<String> it = keyList.iterator();
			
			while(it.hasNext()){
				String hid = (String)it.next();
				GoodsBean hgoods = gwc.get(hid);
				int num = hgoods.getNum(); //获取这个商品的数量
				GoodsBean hgoods2 = goodsDao.gwcGoodsIdSel(Integer.parseInt(hid)); //从数据库查找
				hgoods2.setNum(num);
				goodsList.add(hgoods2);
			}
			
		}else{
			//用户已经登录
			int userId = usersDao.nameIsId(username);
			//获取购物车中所有购物项目
			List<ShoppingCart> list = shoppingCartDao.selList(userId);
			for(ShoppingCart s: list){
				GoodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //从数据库查找
				hgoods2.setNum(s.getNum());
				goodsList.add(hgoods2);
			}
		}
		request.setAttribute("gwcGoodsList", goodsList);
		
		request.getRequestDispatcher("goods.jsp").forward(request, response);
	}

}
