package me.ilt.web.goods.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;

import me.ilt.goods.bean.GoodsBean;
import me.ilt.goods.dao.GoodsDao;
import me.ilt.goods.dao.impl.GoodsDaoImpl;
import me.ilt.user.bean.ShoppingCart;
import me.ilt.user.dao.ShoppingCartDao;
import me.ilt.user.dao.UsersDao;
import me.ilt.user.dao.impl.ShoppingCartDaoImpl;
import me.ilt.user.dao.impl.UserDaoImpl;
import me.ilt.utils.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GoodsServlet extends HttpServlet {
   GoodsDao goodsdao=new GoodsDaoImpl();
	private UsersDao usersDao = new UserDaoImpl();
	private ShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
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
		System.out.println("��������Ʒ��ѯ");
		String p=request.getParameter("page");
		String rows=request.getParameter("rows");
		String sel=request.getParameter("name");
		
		System.out.println("�յ�����"+p+"  "+rows+"  "+sel);
		if(sel==null){
			JSONObject result=new JSONObject();
			String sql="select count(*)  from t_goods";
		 int count =goodsdao.count(sql);
		 JSONArray jsonArray=goodsdao.selAll(Integer.parseInt(p),Integer.parseInt(rows));
		 
		 result.put("rows", jsonArray);
		 result.put("total", count);
		 ResponseUtil.write(response, result);
		}else{
			JSONObject result = new JSONObject();
			String sql = "select count(*) from t_goods where name like '%"+sel+"%'";
			System.out.println("��ѯ����sqlΪ��"+sql);
			int count = goodsdao.count(sql); //��ȡ����
			JSONArray jsonArray = goodsdao.nameSel(Integer.parseInt(p), Integer.parseInt(rows),sel); //��ȡdao���ص�json����
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}
		
	}
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("��������Ʒ���");
		SmartUpload su=new SmartUpload();
		PageContext pc=JspFactory.getDefaultFactory().getPageContext(this, request, response,null, true, 8192, true);
		su.initialize(pc);//��ʼ��
		su.setMaxFileSize(1024*2048);//���ƴ�С2M
		try {
			su.setAllowedFilesList("jpg,JPG,png,PNG,bmp,gif,jpeg,txt,doc,xls,ppt");//�����ϴ�����
			su.setDeniedFilesList("exe,bat,jsp,html,htm,java,class");//�������ϴ�������
			su.upload();//�ϴ������
			String name = su.getRequest().getParameter("name");
			String price = su.getRequest().getParameter("price");
			String brand = su.getRequest().getParameter("brand");
			String sales = su.getRequest().getParameter("sales");
			String views = su.getRequest().getParameter("views");
			String stock = su.getRequest().getParameter("stock");
			String color=su.getRequest().getParameter("color");
			String state = su.getRequest().getParameter("state");
			String smallTypeId = su.getRequest().getParameter("smallTypeId");
			String bigTypeId = su.getRequest().getParameter("bigTypeId");
			String contents = su.getRequest().getParameter("contents");
		
			Files fs=su.getFiles();//��ȡ�����ϴ����ļ�����
			File f=fs.getFile(0);//��ȡ�ϴ����ļ�
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH-mm-ss");
			String url = "images/goodsImg/"+sdf.format(new Date())+"."+f.getFileExt();
			
			GoodsBean g = new GoodsBean(name, Double.parseDouble(price), url, brand, Integer.parseInt(sales), Integer.parseInt(views), Integer.parseInt(stock), contents, Integer.parseInt(bigTypeId), Integer.parseInt(smallTypeId),color, state);
			
			int i = goodsdao.add(g);
			JSONObject result=new JSONObject();
			if(i==0){
				result.put("errorMsg", "���ʧ��");
			}else{
				f.saveAs(url);//����
				result.put("success", "true");
			}
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
         public void update(HttpServletRequest request , HttpServletResponse response) throws Exception{
        		SmartUpload su=new SmartUpload();
        		PageContext pc=JspFactory.getDefaultFactory().getPageContext(this, request, response,null, true, 8192, true);
        		su.initialize(pc);//��ʼ��
        		su.setMaxFileSize(1024*2048);//���ƴ�С2M
        		try {
        			su.setAllowedFilesList("jpg,png,bmp,gif,jpeg,txt,doc,xls,ppt");//�����ϴ�����
        			su.setDeniedFilesList("exe,bat,jsp,html,htm,java,class");//�������ϴ�������
        			su.upload();//�ϴ������
        			
        			
        			String name = su.getRequest().getParameter("name");
        			String price = su.getRequest().getParameter("price");
        			String brand = su.getRequest().getParameter("brand");
        			String sales = su.getRequest().getParameter("sales");
        			String views = su.getRequest().getParameter("views");
        			String stock = su.getRequest().getParameter("stock");
        			String color=su.getRequest().getParameter("color");
        			String state = su.getRequest().getParameter("state");
        			String smallTypeId = su.getRequest().getParameter("smallTypeId");
        			String bigTypeId = su.getRequest().getParameter("bigTypeId");
        			String contents = su.getRequest().getParameter("contents");
        			int id = Integer.parseInt(request.getParameter("id"));
        			Files fs=su.getFiles();//��ȡ�����ϴ����ļ�����
        			File f=fs.getFile(0);//��ȡ�ϴ����ļ�
        			if(f.getSize()==0){
        				GoodsBean g = new GoodsBean(name, Double.parseDouble(price), null, brand, Integer.parseInt(sales), Integer.parseInt(views), Integer.parseInt(stock), contents, Integer.parseInt(bigTypeId), Integer.parseInt(smallTypeId),color, state);
        				g.setId(id);
        				int i = goodsdao.update(g);
        				JSONObject result=new JSONObject();
        				if(i==0){
        					result.put("errorMsg", "����ʧ��");
        				}else{
        					result.put("success", "true");
        				}
        				ResponseUtil.write(response, result);
        				//δ���յ�ͼƬ
        				return;
        			}else{
        				//���յ�ͼƬ
        				SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH-mm-ss");
        				String url = "images/goodsImg/"+sdf.format(new Date())+"."+f.getFileExt();
        				f.saveAs(url);//����
        				GoodsBean g = new GoodsBean(name, Double.parseDouble(price), url, brand, Integer.parseInt(sales), Integer.parseInt(views), Integer.parseInt(stock), contents, Integer.parseInt(bigTypeId), Integer.parseInt(smallTypeId),color, state);
        				g.setId(id);
        				
        				int i = goodsdao.update(g);
        				JSONObject result=new JSONObject();
        				if(i==0){
        					result.put("errorMsg", "����ʧ��");
        				}else{
        					result.put("success", "true");
        				}
        				ResponseUtil.write(response, result);
        			}
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
      }/**
     	 * ����ɾ�� and ���ɾ��
     	 * @param request
     	 * @param response
     	 * @throws ServletException
     	 * @throws IOException
     	 */
     	public void del(HttpServletRequest request, HttpServletResponse response)
     			throws ServletException, IOException {
     		String id = request.getParameter("ids");
     		
     		System.out.println("���յ���Ϊ��"+id);
     		int i = goodsdao.manyDel(id);
     		JSONObject result=new JSONObject();
     		if(i==0){
     			result.put("errorMsg", "ɾ��ʧ��");
     		}else{
     			result.put("success", "true");
     		}
     		ResponseUtil.write(response, result);
     	}
     	public void addShoppingCart(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
     		String username = (String)request.getSession().getAttribute("userName"); //��ȡ��¼���û���
    		String id = request.getParameter("id"); //��ƷID
    		int num =Integer.parseInt( request.getParameter("num")); //��Ʒ��ǰ����
    		double price = Double.parseDouble(request.getParameter("price")); //��Ʒ����
    		String color = request.getParameter("color"); //��Ʒ��ɫ
    		
    		String exsit = ""; // ������1  ��������0
    		double fee = 0; //���ﳵȫ���ϼ�
    		int status = 1;//��״   1
    		int sum = 0; //���ﳵ��ȫ����Ʒ������
    		String msg = "";
    		DecimalFormat df=new DecimalFormat(".##");
    		int userId=0;
    		int newrow=0;
//    		System.out.println(username+id+num+price+color);
    		HashMap<String, GoodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
    		if(username==null){
    			//û�е�¼
    			System.out.println("�û�û�е�¼  ���ﳵ����session��");
    			if(!gwc.containsKey(id)){
    				GoodsBean goods = new GoodsBean();  //ʵ����һ����Ʒ����
    				goods.setId(Integer.parseInt(id));
    				goods.setNum(num);
    				goods.setPrice(price);
    				gwc.put(id, goods);
    				exsit = "0"; //������
    				msg = "���ޣ�����ɹ�";
    			}else{
    				exsit = "1";
    				GoodsBean hgoods = gwc.get(id); //�������  �Ӽ����л�ȡ��
    				num = hgoods.getNum()+num;
    				hgoods.setNum(num);
    				gwc.put(id, hgoods); //���·���
    				msg = "���У�����ɹ�";
    			}
    			
    			Set keyList = gwc.keySet();
    			Iterator it = keyList.iterator();
    			
    			while(it.hasNext()){
    				String hid = (String)it.next();
    				GoodsBean hgoods = gwc.get(hid);
    				sum+=hgoods.getNum(); //��ȡ������Ʒ������
    				fee+=hgoods.getNum()*hgoods.getPrice(); //���ﳵ�ϼ�
    			}
    			JSONObject result=new JSONObject();
    			result.put("exsit",exsit);
    			result.put("fee", df.format(fee));
    			result.put("status", status);
    			result.put("num", num);
    			result.put("sum", sum);
    			result.put("msg", msg);
    			
    			System.out.println("exsit="+exsit+",fee="+df.format(fee)+",status="+status+",num="+num+",sum="+sum+",msg="+msg);
    			ResponseUtil.write(response, result);
    		}else{
    		//�Ѿ���¼
			System.out.println("�û��Ѿ���¼  �û���Ϊ��"+username);
			 userId = usersDao.nameIsId(username); //��ȡ�û�ID
			int i = shoppingCartDao.count(userId, Integer.parseInt(id));
			
			if(i>0){
				//���ڹ�����  ��������
				exsit = "1"; //����
				newrow=shoppingCartDao.updateNum(new ShoppingCart(userId, Integer.parseInt(id), num, 0,color));
				num=num+i; //���µ�ǰ������Ŀ����
			}else{
				//������  ��ӹ�����
				exsit = "0"; //������
				newrow=	shoppingCartDao.add(new ShoppingCart(userId, Integer.parseInt(id), num, price,color));
			}
			}
			//��ȡ���ﳵ�����й�����Ŀ
			List<ShoppingCart> list = shoppingCartDao.selList(userId);
			for(ShoppingCart s:list){
				sum+=s.getNum();
				fee+=s.getNum()*s.getGoodsPrice();
			}
			JSONObject result=new JSONObject();
			result.put("exsit",exsit);
			result.put("fee", df.format(fee));
			result.put("status", status);
			result.put("num", num);
			result.put("sum", sum);
			result.put("msg", msg);
			result.put("newrow", newrow);
			System.out.println("exsit="+exsit+",fee="+df.format(fee)+",status="+status+",num="+num+",sum="+sum+",msg="+msg);
			ResponseUtil.write(response, result);
			
     	}
     	/**
    	 * ��ӹ��ﳵ����������
    	 * @param request
    	 * @param response
    	 * @throws ServletException
    	 * @throws IOException
    	 */
    	public void jiaCart(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		System.out.println("��ӹ��ﳵ����������");
    		String username=(String) request.getSession().getAttribute("userName");
    		String id=request.getParameter("id"); //��ȡID
    		double goodsPrice=Integer.decode(request.getParameter("goodsPrice")); //��ȡID
    		if(username==null){
    			System.out.println("�����˹��ﳵ��������");
    			HashMap<String, GoodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
    			GoodsBean hgoods = gwc.get(id); //�������  �Ӽ����л�ȡ��
    			hgoods.setNum(hgoods.getNum()+1);
    			gwc.put(id, hgoods); //���·���
    			System.out.println("�������һ��������"+id);
    			//����JSON
    			JSONObject result=new JSONObject();
    			result.put("st",1);
    			ResponseUtil.write(response, result);
    		}else{
    			int userId = usersDao.nameIsId(username); //��ȡ�û�ID
    			int i = shoppingCartDao.count(userId, Integer.parseInt(id));
    			shoppingCartDao.updateNum(new ShoppingCart(userId, Integer.parseInt(id), 1, goodsPrice,null));
    			//����JSON
    			JSONObject result=new JSONObject();
    			result.put("st",1);
    			ResponseUtil.write(response, result);
    			System.out.println("��ӹ��ﳵ�����������ɹ�");
    		}
    		
    	}
    	
    	/**
    	 * ���ٹ��ﳵ����������
    	 * @param request
    	 * @param response
    	 * @throws ServletException
    	 * @throws IOException
    	 */
    	public void jianCart(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		System.out.println("��ӹ��ﳵ����������");
    		String username=(String) request.getSession().getAttribute("userName");
    		String id=request.getParameter("id"); //��ȡID
    		double goodsPrice=Integer.decode(request.getParameter("goodsPrice")); //��ȡID
    		if(username==null){
    			System.out.println("�����˹��ﳵ��������");
    			HashMap<String, GoodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
    			GoodsBean hgoods = gwc.get(id); //�������  �Ӽ����л�ȡ��
    			hgoods.setNum(hgoods.getNum()+1);
    			gwc.put(id, hgoods); //���·���
    			System.out.println("�������һ��������"+id);
    			//����JSON
    			JSONObject result=new JSONObject();
    			result.put("st",1);
    			ResponseUtil.write(response, result);
    		}else{
    			int userId = usersDao.nameIsId(username); //��ȡ�û�ID
    			int i = shoppingCartDao.count(userId, Integer.parseInt(id));
    			shoppingCartDao.updateNum(new ShoppingCart(userId, Integer.parseInt(id), -1, goodsPrice,null));
    			//����JSON
    			JSONObject result=new JSONObject();
    			result.put("st",1);
    			ResponseUtil.write(response, result);
    			System.out.println("��ӹ��ﳵ�����������ɹ�");
    		}
    		
    	}
     	
}
