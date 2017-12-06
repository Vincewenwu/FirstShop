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
		System.out.println("MethodName��"+MethodName);
		try {
			Method method = getClass().getDeclaredMethod(MethodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	/**
	 * ��ѯ���� and ģ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		System.out.println("�����˶�����ѯ");
		String p = request.getParameter("page"); //����ҳ��
		String rows = request.getParameter("rows"); //ÿҳ������
		String sel = request.getParameter("name"); //����ǲ�ѯ�ⲻΪ��
		System.out.println("�յ�����"+p+"  "+rows+"  "+sel);
		if(sel==null){
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_order";
			int count = orderDao.count(sql); //��ȡ����
			
			JSONArray jsonArray = orderDao.selAll(Integer.parseInt(p), Integer.parseInt(rows)); //��ȡdao���ص�json����
			
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}else{
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_goods where name like '%"+sel+"%'";
			System.out.println("��ѯ����sqlΪ��"+sql);
			int count = bigTypeDao.count(sql); //��ȡ����
			JSONArray jsonArray = goodsDao.nameSel(Integer.parseInt(p), Integer.parseInt(rows),sel); //��ȡdao���ص�json����
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}
	}
	public void oidSel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�����˲鿴��������");
		String orderId = request.getParameter("orderNo");
		JSONObject result = new JSONObject();
		JSONArray jsonArray = orderItemDao.orderIdSel(orderId);
		
		result.put("rows", jsonArray);
		ResponseUtil.write(response, result);
		
	}
	/**
	 * ��������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ddfh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderNos");
		//�޸Ķ���״̬
		orderDao.qxdd(orderId, 3);
		//success
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
	}
	/**
	 * ȷ���ջ�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ddhh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderNos");
		//�޸Ķ���״̬
		int i=orderDao.qxdd(orderId, 5);
		//success
		if(i==1){
			request.getRequestDispatcher("userMain.page?type=1").forward(request, response);	
		}
	
	}
	/**
	 * �ύ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void tjdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�������ύ����");
		String cartItemIds = request.getParameter("cartItemIds");//�������
	    String [] ids=cartItemIds.split(",");
		String addressId = request.getParameter("sender");//��ַID
		String payType ="2";
		String liuyan ="�Һܸ���";
		int state = 0;
		GoodsBean goods=null;
		List<GoodsBean> hgood=new ArrayList<GoodsBean>();
		List<AddressBean> addressList=null;
		if(payType.equals("1")){
			//��������
			state = 6;
		}else{
			//����֧��
			state = 2;
		}
		int i=0;int j=0;int z=0;
		//���ݶ�����Ų�ѯ������Ʒ  ��ȥ���
		for(String zj:ids){
			List<OrderItemBean> list = orderItemDao.orderIdSelItemId(Integer.parseInt(zj));
			for(OrderItemBean g:list){
				   z++;
			      goodsDao.stockJian(g.getGoodsId(), g.getSum()); //��ȥ���
			      if(ids.length==1){
			    	  i=orderDao.update(g.getOrderId(), addressId, state,Integer.parseInt(payType), liuyan); 
			        }else{
				   i=orderDao.update(g.getOrderIdd(), addressId, state,Integer.parseInt(payType), liuyan); 
			      }
			      if(i==1){
					  j++;
					System.out.println("�޸Ķ���״̬�ɹ�");
				  	 }else{
					System.out.println("�޸Ķ���״̬ʧ��");
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
	 * ֧������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void zfdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("������֧������");
		String orderId = request.getParameter("orderId");
		int  orderitemId =Integer.parseInt(request.getParameter("orderitem")) ;
		String username = (String)request.getSession().getAttribute("userName");
		System.out.println("��ȡ���Ķ���idΪ��"+orderId);
		System.out.println("��ȡ���ĵ�ǰ��¼��Ϊ��"+username);
		int userId = usersDao.nameIsId(username); //�����û�����ȡID
		System.out.println("�����û�����ѯidΪ��"+userId);
		OrderBean o = orderDao.orderIdSel(orderId);
		double hj=0.0;
		GoodsBean hgoods =null;
		if(o.getUserId()==userId){
			System.out.println("��ǰ��¼��Ϊ����");
			DecimalFormat df = new DecimalFormat("#.00");  //��ֹ�۸�����쳣
			//��ѯ������Ϣ
			List<OrderItemBean> list=null;
			if(orderitemId==001){
				 list = orderItemDao.orderIdSelItem(orderId);
			}else{
				 list = orderItemDao.orderIdSelItemId(orderitemId);
			}
			List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
			for(OrderItemBean oi : list){
				System.out.println("��Ʒ����Ϊ��"+oi.getGoodsName()+"��ƷIDΪ��"+oi.getGoodsId());
				 hgoods = goodsDao.gwcGoodsIdSel(oi.getGoodsId()); //�����ݿ����
				System.out.println("С��δ��ʽ����"+oi.getSum()*hgoods.getPrice());
				 hj = Double.parseDouble(df.format(oi.getSum()*hgoods.getPrice()));
				System.out.println(oi.getGoodsName()+"С��Ϊ��"+hj);
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
			
			//�����û�ID��ȡ��ַ��Ϣ
			List<AddressBean> addressList= addressDao.selAll(userId); 
			request.setAttribute("addressList", addressList); //��ַ
			request.setAttribute("ze", hj);  //�ܶ�
			request.setAttribute("gwcGoodsList", goodsList);  //���ﳵ�е���Ʒ
			request.setAttribute("orderitemId", orderId); //����id
			request.getRequestDispatcher("order.jsp").forward(request, response);  //ת����jsp
		}else{
			//�Ƿ�����
			response.sendRedirect("userMain.page");
		}
	}
	public void zfdddangezhifu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("������֧������");
		String orderId = request.getParameter("orderId");
		int  orderitemId =Integer.parseInt(request.getParameter("orderitem")) ;
		String username = (String)request.getSession().getAttribute("userName");
		System.out.println("��ȡ���Ķ���idΪ��"+orderId);
		System.out.println("��ȡ���ĵ�ǰ��¼��Ϊ��"+username);
		int userId = usersDao.nameIsId(username); //�����û�����ȡID
		System.out.println("�����û�����ѯidΪ��"+userId);
		OrderBean o = orderDao.orderIdSel(orderId);
		double hj=0.0;
		GoodsBean hgoods =null;
		if(o.getUserId()==userId){
			System.out.println("��ǰ��¼��Ϊ����");
			DecimalFormat df = new DecimalFormat("#.00");  //��ֹ�۸�����쳣
			//��ѯ������Ϣ
			List<OrderItemBean> list=null;
			if(orderitemId==001){
				 list = orderItemDao.orderIdSelItem(orderId);
			}else{
				 list = orderItemDao.orderIdSelItemId(orderitemId);
			}
			List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
			for(OrderItemBean oi : list){
				System.out.println("��Ʒ����Ϊ��"+oi.getGoodsName()+"��ƷIDΪ��"+oi.getGoodsId());
				 hgoods = goodsDao.gwcGoodsIdSel(oi.getGoodsId()); //�����ݿ����
				System.out.println("С��δ��ʽ����"+oi.getSum()*hgoods.getPrice());
				 hj = Double.parseDouble(df.format(oi.getSum()*hgoods.getPrice()));
				System.out.println(oi.getGoodsName()+"С��Ϊ��"+hj);
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
			
			//�����û�ID��ȡ��ַ��Ϣ
			List<AddressBean> addressList= addressDao.selAll(userId); 
			request.setAttribute("addressList", addressList); //��ַ
			request.setAttribute("ze", hj);  //�ܶ�
			request.setAttribute("gwcGoodsList", goodsList);  //���ﳵ�е���Ʒ
			request.setAttribute("orderitemId", orderId); //����id
			request.getRequestDispatcher("order.jsp").forward(request, response);  //ת����jsp
		}else{
			//�Ƿ�����
			response.sendRedirect("userMain.page");
		}
	}
	public void cxsy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("������֧������");
		String id= request.getParameter("orderId");
		id=id.substring(1);
		id=id.substring(0, id.length()-1);
		String [] ids = id.split(",");
		List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
		List<String> ListOrder=new ArrayList<String>();
		for(String zj : ids){
		String username = (String)request.getSession().getAttribute("userName");
		int userId = usersDao.nameIsId(username); //�����û�����ȡID
		System.out.println("�����û�����ѯidΪ��"+userId);
		OrderBean o = orderDao.orderIdSel(zj.toString().trim());
	
		double hj=0.0;
		if(o.getUserId()==userId){
			System.out.println("��ǰ��¼��Ϊ����");
			DecimalFormat df = new DecimalFormat("#.00");  //��ֹ�۸�����쳣
			//��ѯ������Ϣ
			List<OrderItemBean> list = orderItemDao.orderIdSelItemidd(zj.toString().trim());
			for(OrderItemBean oi : list){
				System.out.println("��Ʒ����Ϊ��"+oi.getGoodsName()+"��ƷIDΪ��"+oi.getGoodsId());
				GoodsBean hgoods = goodsDao.gwcGoodsIdSel(oi.getGoodsId()); //�����ݿ����
				 String orderId=goodsDao.selectOrderIdbyorderidd(zj.toString().trim());
				 String orderidnew=goodsDao.selectOrdernew(zj.toString().trim());
	             ListOrder.add(orderId);
				System.out.println("С��δ��ʽ����"+oi.getSum()*hgoods.getPrice());
				 hj = Double.parseDouble(df.format(oi.getSum()*hgoods.getPrice()));
				System.out.println(oi.getGoodsName()+"С��Ϊ��"+hj);
				hgoods.setNum(oi.getSum());
				hgoods.setOrdetId(orderId); 
				hgoods.setOrder(Integer.parseInt(orderidnew));
				hgoods.setTotal(hj);
				goodsList.add(hgoods);
			}
			for (int i = 0; i < ListOrder.size() - 1; i++) {                             //ѭ�����������е�Ԫ��
		         for (int j = ListOrder.size() - 1; j > i; j--) {                         //����ǳ���������ǵ�����ǱȽ�
		              if (ListOrder.get(j).equals(ListOrder.get(i))) {
		            	  ListOrder.remove(j);
		              }
		        }
		    }
			//�����û�ID��ȡ��ַ��Ϣ
			List<AddressBean> addressList= addressDao.selAll(userId); 
			request.setAttribute("addressList", addressList); //��ַ
     		
     		request.setAttribute("orderitemId", ListOrder);
			request.setAttribute("addressId", zj.toString().trim()); //����id
	      }else{
			//�Ƿ�����
			response.sendRedirect("userMain.page");
		}
		}
		request.setAttribute("gwcGoodsList", goodsList);  //���ﳵ�е���Ʒ
		request.getRequestDispatcher("order.jsp").forward(request, response);  //ת����jsp
	}
	public void ljgm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("������֧������");
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
		int userId = usersDao.nameIsId(username); //�����û�����ȡID
		System.out.println("�����û�����ѯidΪ��"+userId);
		/*OrderBean o = orderDao.orderIdSel(zj.toString().trim());
		if(o.getUserId()==userId){*/
			System.out.println("��ǰ��¼��Ϊ����");
			DecimalFormat df = new DecimalFormat("#.00");  //��ֹ�۸�����쳣
			
			//��ѯ������Ϣ
			List<OrderItemBean> list = orderItemDao.orderIdSelItemId(Integer.parseInt(zj));
			for(OrderItemBean oi : list){
				System.out.println("��Ʒ����Ϊ��"+oi.getGoodsName()+"��ƷIDΪ��"+oi.getGoodsId());
				 hgoods = goodsDao.gwcGoodsIdSel(oi.getGoodsId()); //�����ݿ����
                String orderId=goodsDao.selectOrderId(zj);
               
                ListOrder.add(orderId);
				System.out.println("С��δ��ʽ����"+oi.getSum()*hgoods.getPrice());
				double hj = Double.parseDouble(df.format(oi.getSum()*hgoods.getPrice()));
				System.out.println(oi.getGoodsName()+"С��Ϊ��"+hj);
				hgoods.setNum(oi.getSum());
				hgoods.setOrdetId(orderId); 
				hgoods.setOrder(Integer.parseInt(zj));
				hgoods.setTotal(hj);
				goodsList.add(hgoods);
			}
			for (int i = 0; i < ListOrder.size() - 1; i++) {                             //ѭ�����������е�Ԫ��
		         for (int j = ListOrder.size() - 1; j > i; j--) {                         //����ǳ���������ǵ�����ǱȽ�
		              if (ListOrder.get(j).equals(ListOrder.get(i))) {
		            	  ListOrder.remove(j);
		              }
		        }
		    }
			//�����û�ID��ȡ��ַ��Ϣ
			List<AddressBean> addressList= addressDao.selAll(userId); 
			request.setAttribute("addressList", addressList); //��ַ 
			request.setAttribute("orderitemId", ListOrder);
			request.setAttribute("gwcGoodsList", goodsList);  //���ﳵ�е���Ʒ
			request.setAttribute("addressId", zj.toString().trim()); //����id
		}
		request.getRequestDispatcher("order.jsp").forward(request, response);  //ת����jsp
	}
	public void address(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("userName");
		int userId = usersDao.nameIsId(username); //�����û�����ȡID
	     if(userId!=0){
			//�����û�ID��ȡ��ַ��Ϣ
			List<AddressBean> addressList= addressDao.selAll(userId); 
			int i=addressDao.selcount(userId);
			request.setAttribute("addressList", addressList); //��ַ
			request.setAttribute("count", i); //��ַ
	      }else{
			//�Ƿ�����
			response.sendRedirect("manager.jsp");
		}
		request.getRequestDispatcher("address.jsp").forward(request, response);  //ת����jsp
	}
}
