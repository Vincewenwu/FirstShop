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
		System.out.println("进入了商品查询");
		String p=request.getParameter("page");
		String rows=request.getParameter("rows");
		String sel=request.getParameter("name");
		
		System.out.println("收到请求："+p+"  "+rows+"  "+sel);
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
			System.out.println("查询行数sql为："+sql);
			int count = goodsdao.count(sql); //获取条数
			JSONArray jsonArray = goodsdao.nameSel(Integer.parseInt(p), Integer.parseInt(rows),sel); //获取dao返回的json集合
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}
		
	}
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入了商品添加");
		SmartUpload su=new SmartUpload();
		PageContext pc=JspFactory.getDefaultFactory().getPageContext(this, request, response,null, true, 8192, true);
		su.initialize(pc);//初始化
		su.setMaxFileSize(1024*2048);//限制大小2M
		try {
			su.setAllowedFilesList("jpg,JPG,png,PNG,bmp,gif,jpeg,txt,doc,xls,ppt");//允许上传类型
			su.setDeniedFilesList("exe,bat,jsp,html,htm,java,class");//不允许上传的类型
			su.upload();//上传及检测
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
		
			Files fs=su.getFiles();//获取所有上传的文件集合
			File f=fs.getFile(0);//获取上传的文件
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH-mm-ss");
			String url = "images/goodsImg/"+sdf.format(new Date())+"."+f.getFileExt();
			
			GoodsBean g = new GoodsBean(name, Double.parseDouble(price), url, brand, Integer.parseInt(sales), Integer.parseInt(views), Integer.parseInt(stock), contents, Integer.parseInt(bigTypeId), Integer.parseInt(smallTypeId),color, state);
			
			int i = goodsdao.add(g);
			JSONObject result=new JSONObject();
			if(i==0){
				result.put("errorMsg", "添加失败");
			}else{
				f.saveAs(url);//保存
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
        		su.initialize(pc);//初始化
        		su.setMaxFileSize(1024*2048);//限制大小2M
        		try {
        			su.setAllowedFilesList("jpg,png,bmp,gif,jpeg,txt,doc,xls,ppt");//允许上传类型
        			su.setDeniedFilesList("exe,bat,jsp,html,htm,java,class");//不允许上传的类型
        			su.upload();//上传及检测
        			
        			
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
        			Files fs=su.getFiles();//获取所有上传的文件集合
        			File f=fs.getFile(0);//获取上传的文件
        			if(f.getSize()==0){
        				GoodsBean g = new GoodsBean(name, Double.parseDouble(price), null, brand, Integer.parseInt(sales), Integer.parseInt(views), Integer.parseInt(stock), contents, Integer.parseInt(bigTypeId), Integer.parseInt(smallTypeId),color, state);
        				g.setId(id);
        				int i = goodsdao.update(g);
        				JSONObject result=new JSONObject();
        				if(i==0){
        					result.put("errorMsg", "更新失败");
        				}else{
        					result.put("success", "true");
        				}
        				ResponseUtil.write(response, result);
        				//未接收到图片
        				return;
        			}else{
        				//接收到图片
        				SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH-mm-ss");
        				String url = "images/goodsImg/"+sdf.format(new Date())+"."+f.getFileExt();
        				f.saveAs(url);//保存
        				GoodsBean g = new GoodsBean(name, Double.parseDouble(price), url, brand, Integer.parseInt(sales), Integer.parseInt(views), Integer.parseInt(stock), contents, Integer.parseInt(bigTypeId), Integer.parseInt(smallTypeId),color, state);
        				g.setId(id);
        				
        				int i = goodsdao.update(g);
        				JSONObject result=new JSONObject();
        				if(i==0){
        					result.put("errorMsg", "更新失败");
        				}else{
        					result.put("success", "true");
        				}
        				ResponseUtil.write(response, result);
        			}
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
      }/**
     	 * 单个删除 and 多个删除
     	 * @param request
     	 * @param response
     	 * @throws ServletException
     	 * @throws IOException
     	 */
     	public void del(HttpServletRequest request, HttpServletResponse response)
     			throws ServletException, IOException {
     		String id = request.getParameter("ids");
     		
     		System.out.println("接收到的为："+id);
     		int i = goodsdao.manyDel(id);
     		JSONObject result=new JSONObject();
     		if(i==0){
     			result.put("errorMsg", "删除失败");
     		}else{
     			result.put("success", "true");
     		}
     		ResponseUtil.write(response, result);
     	}
     	public void addShoppingCart(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
     		String username = (String)request.getSession().getAttribute("userName"); //获取登录的用户名
    		String id = request.getParameter("id"); //商品ID
    		int num =Integer.parseInt( request.getParameter("num")); //商品当前数量
    		double price = Double.parseDouble(request.getParameter("price")); //商品单价
    		String color = request.getParameter("color"); //商品颜色
    		
    		String exsit = ""; // 存在是1  不存在是0
    		double fee = 0; //购物车全部合计
    		int status = 1;//现状   1
    		int sum = 0; //购物车中全部商品的数量
    		String msg = "";
    		DecimalFormat df=new DecimalFormat(".##");
    		int userId=0;
    		int newrow=0;
//    		System.out.println(username+id+num+price+color);
    		HashMap<String, GoodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
    		if(username==null){
    			//没有登录
    			System.out.println("用户没有登录  购物车放入session中");
    			if(!gwc.containsKey(id)){
    				GoodsBean goods = new GoodsBean();  //实例化一个商品对象
    				goods.setId(Integer.parseInt(id));
    				goods.setNum(num);
    				goods.setPrice(price);
    				gwc.put(id, goods);
    				exsit = "0"; //不存在
    				msg = "本无，插入成功";
    			}else{
    				exsit = "1";
    				GoodsBean hgoods = gwc.get(id); //如果存在  从集合中获取到
    				num = hgoods.getNum()+num;
    				hgoods.setNum(num);
    				gwc.put(id, hgoods); //重新放入
    				msg = "本有，插入成功";
    			}
    			
    			Set keyList = gwc.keySet();
    			Iterator it = keyList.iterator();
    			
    			while(it.hasNext()){
    				String hid = (String)it.next();
    				GoodsBean hgoods = gwc.get(hid);
    				sum+=hgoods.getNum(); //获取所有商品的数量
    				fee+=hgoods.getNum()*hgoods.getPrice(); //购物车合计
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
    		//已经登录
			System.out.println("用户已经登录  用户名为："+username);
			 userId = usersDao.nameIsId(username); //获取用户ID
			int i = shoppingCartDao.count(userId, Integer.parseInt(id));
			
			if(i>0){
				//存在购物项  增加数量
				exsit = "1"; //存在
				newrow=shoppingCartDao.updateNum(new ShoppingCart(userId, Integer.parseInt(id), num, 0,color));
				num=num+i; //更新当前购物项目数量
			}else{
				//不存在  添加购物项
				exsit = "0"; //不存在
				newrow=	shoppingCartDao.add(new ShoppingCart(userId, Integer.parseInt(id), num, price,color));
			}
			}
			//获取购物车中所有购物项目
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
    	 * 添加购物车购物项数量
    	 * @param request
    	 * @param response
    	 * @throws ServletException
    	 * @throws IOException
    	 */
    	public void jiaCart(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		System.out.println("添加购物车购物项数量");
    		String username=(String) request.getSession().getAttribute("userName");
    		String id=request.getParameter("id"); //获取ID
    		double goodsPrice=Integer.decode(request.getParameter("goodsPrice")); //获取ID
    		if(username==null){
    			System.out.println("进入了购物车增加数量");
    			HashMap<String, GoodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
    			GoodsBean hgoods = gwc.get(id); //如果存在  从集合中获取到
    			hgoods.setNum(hgoods.getNum()+1);
    			gwc.put(id, hgoods); //重新放入
    			System.out.println("完成增加一个数量："+id);
    			//返回JSON
    			JSONObject result=new JSONObject();
    			result.put("st",1);
    			ResponseUtil.write(response, result);
    		}else{
    			int userId = usersDao.nameIsId(username); //获取用户ID
    			int i = shoppingCartDao.count(userId, Integer.parseInt(id));
    			shoppingCartDao.updateNum(new ShoppingCart(userId, Integer.parseInt(id), 1, goodsPrice,null));
    			//返回JSON
    			JSONObject result=new JSONObject();
    			result.put("st",1);
    			ResponseUtil.write(response, result);
    			System.out.println("添加购物车购物项数量成功");
    		}
    		
    	}
    	
    	/**
    	 * 减少购物车购物项数量
    	 * @param request
    	 * @param response
    	 * @throws ServletException
    	 * @throws IOException
    	 */
    	public void jianCart(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		System.out.println("添加购物车购物项数量");
    		String username=(String) request.getSession().getAttribute("userName");
    		String id=request.getParameter("id"); //获取ID
    		double goodsPrice=Integer.decode(request.getParameter("goodsPrice")); //获取ID
    		if(username==null){
    			System.out.println("进入了购物车增加数量");
    			HashMap<String, GoodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
    			GoodsBean hgoods = gwc.get(id); //如果存在  从集合中获取到
    			hgoods.setNum(hgoods.getNum()+1);
    			gwc.put(id, hgoods); //重新放入
    			System.out.println("完成增加一个数量："+id);
    			//返回JSON
    			JSONObject result=new JSONObject();
    			result.put("st",1);
    			ResponseUtil.write(response, result);
    		}else{
    			int userId = usersDao.nameIsId(username); //获取用户ID
    			int i = shoppingCartDao.count(userId, Integer.parseInt(id));
    			shoppingCartDao.updateNum(new ShoppingCart(userId, Integer.parseInt(id), -1, goodsPrice,null));
    			//返回JSON
    			JSONObject result=new JSONObject();
    			result.put("st",1);
    			ResponseUtil.write(response, result);
    			System.out.println("添加购物车购物项数量成功");
    		}
    		
    	}
     	
}
