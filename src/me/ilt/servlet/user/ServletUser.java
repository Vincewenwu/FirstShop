package me.ilt.servlet.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import me.ilt.user.bean.UserBean;
import me.ilt.user.dao.UsersDao;
import me.ilt.user.dao.impl.UserDaoImpl;
import me.ilt.utils.ResponseUtil;

public class ServletUser extends HttpServlet {

	
                  UsersDao userdao=new UserDaoImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("收到请求");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String MethodName=request.getServletPath();
		MethodName=MethodName.substring(1,MethodName.length()-5);
		System.out.println("MethodName"+MethodName);
		try{
			Method method=getClass().getDeclaredMethod(MethodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 根据Id查询一个用户
	 */
	public void update(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("user.userName");
		String trueName = request.getParameter("user.trueName");
		String sex = request.getParameter("user.sex");
		String birthday = request.getParameter("user.birthday");
		String statusID = request.getParameter("user.statusID");
		String phone = request.getParameter("user.phone");
		String address = request.getParameter("user.address");
		String email = request.getParameter("user.email");
		String password = request.getParameter("user.password");
		int id =Integer.parseInt(request.getParameter("user.id"));
		String proPic=null;
		UserBean u = new UserBean(userName, trueName, sex, birthday, statusID, phone, address, email, 1+"", password, proPic);
		u.setId(id);
		int i=userdao.update(u);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "删除失败");
		}else{
			result.put("success", "true");
		}
		ResponseUtil.write(response, result);
	}
	
	/**
	 * 添加用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("user.userName");
		String trueName = request.getParameter("user.trueName");
		String sex = request.getParameter("user.sex");
		String birthday = request.getParameter("user.birthday");
		String statusID = request.getParameter("user.statusID");
		String phone = request.getParameter("user.phone");
		String address = request.getParameter("user.address");
		String email = request.getParameter("user.email");
		String password = request.getParameter("user.password");
		System.out.println("添加用户接收到："+userName+trueName+sex+birthday+statusID+phone+address+email+ password);
		String proPic=null;
		UserBean u = new UserBean(userName, trueName, sex, birthday, statusID, phone, address, email, 1+"", password,proPic); 
	   int i=userdao.add(u);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "删除失败");
		}else{
			result.put("success", "true");
		}
		ResponseUtil.write(response, result);
	}
	/**
	 * 删除用户
	 */
	public void delete(HttpServletRequest request,HttpServletResponse response){
		String  id=request.getParameter("ids");
		int s = id.indexOf(",");
		int i=0;
		if(s!=-1){
			//是个数组
			  i= userdao.manyDel(id);
		}else{
			i = userdao.del(Integer.parseInt(id));
		}
		   
		    JSONObject result=new JSONObject();
			if(i==0){
				result.put("errorMsg", "删除失败");
			}else{
				result.put("success", "true");
			}
			ResponseUtil.write(response, result);
	}
	/**
	 * 查询所有用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String p = request.getParameter("page"); //需求页码
		String rows = request.getParameter("rows"); //每页多少条
		String sel = request.getParameter("s_userName"); //如果是查询这不为空
			System.out.println("收到请求："+p+"  "+rows+"  "+sel);
		if(sel==null){
			JSONObject result=new JSONObject();
			String sql="select count(*) count from t_user";
			int count = userdao.count(sql); //获取条数
			JSONArray jsonArray= userdao.selAll(Integer.parseInt(p), Integer.parseInt(rows));
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}else{
			JSONObject result = new JSONObject();
			String sql="select count(*) count from t_user where userName like'%"+sel+"%'";
			System.out.println("查询行数sql为："+sql);
			int count = userdao.count(sql); //获取条数
			JSONArray jsonArray = userdao.nameSel(Integer.parseInt(p),Integer.parseInt(rows),sel); //获取dao返回的json集合
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}
		
	}	
	
	/**
	 * 管理员登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void adminlogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		System.out.println("登陆成功");
		String dlyzm=request.getParameter("dlyzm");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		HttpSession session = request.getSession();
	   String yzm=(String) session.getAttribute("vCode");
	   if(dlyzm.equalsIgnoreCase(yzm)){
			System.out.println("验证码验证通过");
			UserBean user= userdao.adminlogin(userName, password);
			System.out.println("接收到的名字："+userName);
			System.out.println("接收的密码为："+password);
			System.out.println("返回的对象："+user);
			if(null!=user){
				//密码正确
				request.getSession().setAttribute("stateOK", 0);
				response.sendRedirect("admin/index.jsp");
				request.getSession().setAttribute("userName", userName);
			}else{
				//密码错误 或者非管理员
				request.getSession().setAttribute("state", 2);
				response.sendRedirect("/FirstShop/admin/main.jsp");
			}
	   }else{
		   request.getSession().setAttribute("state", 1);
		   response.sendRedirect("/FirstShop/admin/main.jsp");
	   }	
	}
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//		System.out.println("登陆成功");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
			UserBean user= userdao.login(userName, password);
			System.out.println("接收到的名字："+userName);
			System.out.println("接收的密码为："+password);
			System.out.println("返回的对象："+user);
			if(null!=user){
				//密码正确
				request.getSession().setAttribute("stateOK", 0);
				response.sendRedirect("main.jsp");
				request.getSession().setAttribute("userName", userName);
				request.getSession().setAttribute("font", ",退出");
			}else{
				//密码错误 或者非管理员
				request.getSession().setAttribute("state", 1);
				response.sendRedirect("login.jsp");
			}
	}
	public void regeuser(HttpServletRequest request, HttpServletResponse response){
		System.out.println("进入了前台注册验证");
     String username=request.getParameter("name");
     String sql="select count(*) count from t_user where userName ='"+username+"'";
     int i=userdao.count(sql);
     JSONObject result=new JSONObject();
     if(i==0){
			result.put("success", "true");
		}else{
			result.put("success", "false");
		}
		ResponseUtil.write(response, result);
	}
	/**
	 * 图片登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void genImage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		request.setCharacterEncoding("UTF-8");
		//指定生成的响应图片,一定不能缺少这句话,否则错误.
		response.setContentType("image/jpeg");

		// 创建图像
		int width = 163, height = 64;
		//创建BufferedImage对象,其作用相当于一图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 创建图层，获得画笔
		Graphics g = image.getGraphics();
	    //设置颜色
		g.setColor(Color.BLACK);
	    //画出矩形
	    g.fillRect(0, 0, width, height);
		//画出边框
	    g.setColor(Color.WHITE);
	    g.fillRect(1, 1, width-2, height-2);
	    
		  //填充字符
	    String data = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefhjkmnpqrstuvwxyz2345678";
	    Random random=new Random();
	    g.setFont(new Font("宋体", Font.BOLD, 30)); //定义字体样式
	    StringBuffer buff = new StringBuffer();
	    //随机生成4个字符
	    for (int i = 0; i < 4; i++) {
	        int index = random.nextInt(53);
	        String str = data.substring(index, index + 1);
	        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
	        g.drawString(str, 30 * i, 40);
	        buff.append(str);
	    }
	    
	    //将得到的字符串保存到session中
	    HttpSession session = request.getSession();
	    session.setAttribute("vCode", buff.toString());
		
	    //画出10条干扰线
	    for (int i = 0; i < 10; i++) {
	    	g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
	        g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
	    }
		
		g.dispose();	//释放g所占用的系统资源
		ImageIO.write(image, "jpg", response.getOutputStream()); //输出图片
	}

	public void rege(HttpServletRequest request,HttpServletResponse response) throws IOException{
   System.out.println("我来啦");
   
   String querenmima=request.getParameter("querenmima");
	String yzm=request.getParameter("yzm");
	
		String name=request.getParameter("userName");
		String password=request.getParameter("password");
		String trueName = null;
		String sex = null;
		String birthday =  null;
		String statusID = null;
		String phone = null;
		String address =  null;
		String email = null;
		String proPic=null;
		
			HttpSession session = request.getSession();
		   String yzmser=(String) session.getAttribute("vCode");
		   if(name!=" "|| password!=" "){
			   if(name!= null|| password!=null){
				   if(yzm.equalsIgnoreCase(yzmser)){
						System.out.println("验证码验证通过");
						UserBean u = new UserBean(name, trueName, sex, birthday, statusID, phone, address, email, 1+"", password,proPic);
						String sql="select count(*) count from t_user where userName ='"+name+"'";
						int z=userdao.count(sql);
						if(z!=1){
							int i= userdao.add(u);
							System.out.println("接收到的名字："+name);
							System.out.println("接收的密码为："+password);
							if(i==1 ){
								//密码正确
								request.getSession().setAttribute("stateOK", 0);
								response.sendRedirect("login.jsp");
								request.getSession().setAttribute("adminName", name);
							}else{
								//密码错误 或者非管理员
								request.getSession().setAttribute("state", 2);
								response.sendRedirect("/FirstShop/rege.jsp");
							}
						}else{
							//密码错误 或者非管理员
							request.getSession().setAttribute("state", 2);
							response.sendRedirect("/FirstShop/rege.jsp");
						}
						
				   }else{
					   request.getSession().setAttribute("state", 1);
					   response.sendRedirect("/FirstShop/rege.jsp");
				   }
				  
			   }else{
				   request.getSession().setAttribute("state",3);
				   response.sendRedirect("/FirstShop/rege.jsp");
				   return; 
		     	}
		   }else{
			   request.getSession().setAttribute("state",3);
			   response.sendRedirect("/FirstShop/rege.jsp");
			   return; 
		   }
    }
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//invalidate() 销毁session 
		//重定向到首页
		
		request.getSession().invalidate();
		response.sendRedirect("/FirstShop/main.jsp");
		
	}
	/**
	 * 查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void select(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("userName");
		int i=userdao.selectid(username);
		UserBean userbean=null;
			String sql="select * from t_user where id="+i;
			userbean= userdao.select(sql);
		request.setAttribute("user", userbean);
		request.getRequestDispatcher("personal.jsp").forward(request, response);
	}
}
