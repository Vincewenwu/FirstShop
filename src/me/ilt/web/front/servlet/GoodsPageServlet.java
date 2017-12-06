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
		int id = Integer.parseInt(request.getParameter("id")); //��ȡ������ID
		GoodsBean g = goodsDao.goodsIdSel(id);
		System.out.println("��Ʒ���ƣ�"+g.getName()+"  ��Ʒ�۸�"+g.getPrice()+"  ͼƬ·����"+g.getProPic()+"  ��Ʒ���ࣺ"+g.getBrand()+"  ��Ʒ������"+g.getSales()+"  �������"+g.getViews()+"  ��Ʒ��棺"+g.getStock()+"  ��Ʒ������"+g.getContents()+"  ����ID��"+g.getBigTypeId()+"  �������ƣ�"+g.getBigTypeName()+"  С��ID��"+g.getSmallTypeId()+"  С�����ƣ�"+g.getSmallTypeName());
		List<GoodsBean> xgGoods = goodsDao.bigTypeIdSelxg(g.getBigTypeId()); //��ȡ�����Ʒ
		
		request.setAttribute("goodsBean", g);
		request.setAttribute("xgGoods", xgGoods);
		goodsDao.addViews(id); //�������1
		
		//��ȡ���ﳵ
		String username = (String)request.getSession().getAttribute("userName"); //��ȡ��¼���û���
		System.out.println(username);
		List<GoodsBean> goodsList = new ArrayList<GoodsBean>(); //���ﳵ����
		if(username==null){
			//�û�û�е�¼
			//��ȡ���ﳵ
			Map<String, GoodsBean> gwc = (HashMap<String, GoodsBean>)(request.getSession().getAttribute("gwc"));
			
			Set<String> keyList = gwc.keySet();
			Iterator<String> it = keyList.iterator();
			
			while(it.hasNext()){
				String hid = (String)it.next();
				GoodsBean hgoods = gwc.get(hid);
				int num = hgoods.getNum(); //��ȡ�����Ʒ������
				GoodsBean hgoods2 = goodsDao.gwcGoodsIdSel(Integer.parseInt(hid)); //�����ݿ����
				hgoods2.setNum(num);
				goodsList.add(hgoods2);
			}
			
		}else{
			//�û��Ѿ���¼
			int userId = usersDao.nameIsId(username);
			//��ȡ���ﳵ�����й�����Ŀ
			List<ShoppingCart> list = shoppingCartDao.selList(userId);
			for(ShoppingCart s: list){
				GoodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //�����ݿ����
				hgoods2.setNum(s.getNum());
				goodsList.add(hgoods2);
			}
		}
		request.setAttribute("gwcGoodsList", goodsList);
		
		request.getRequestDispatcher("goods.jsp").forward(request, response);
	}

}
