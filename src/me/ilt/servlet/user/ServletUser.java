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
		System.out.println("�յ�����");
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
	 * ����Id��ѯһ���û�
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
			result.put("errorMsg", "ɾ��ʧ��");
		}else{
			result.put("success", "true");
		}
		ResponseUtil.write(response, result);
	}
	
	/**
	 * ����û�
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
		System.out.println("����û����յ���"+userName+trueName+sex+birthday+statusID+phone+address+email+ password);
		String proPic=null;
		UserBean u = new UserBean(userName, trueName, sex, birthday, statusID, phone, address, email, 1+"", password,proPic); 
	   int i=userdao.add(u);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "ɾ��ʧ��");
		}else{
			result.put("success", "true");
		}
		ResponseUtil.write(response, result);
	}
	/**
	 * ɾ���û�
	 */
	public void delete(HttpServletRequest request,HttpServletResponse response){
		String  id=request.getParameter("ids");
		int s = id.indexOf(",");
		int i=0;
		if(s!=-1){
			//�Ǹ�����
			  i= userdao.manyDel(id);
		}else{
			i = userdao.del(Integer.parseInt(id));
		}
		   
		    JSONObject result=new JSONObject();
			if(i==0){
				result.put("errorMsg", "ɾ��ʧ��");
			}else{
				result.put("success", "true");
			}
			ResponseUtil.write(response, result);
	}
	/**
	 * ��ѯ�����û�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String p = request.getParameter("page"); //����ҳ��
		String rows = request.getParameter("rows"); //ÿҳ������
		String sel = request.getParameter("s_userName"); //����ǲ�ѯ�ⲻΪ��
			System.out.println("�յ�����"+p+"  "+rows+"  "+sel);
		if(sel==null){
			JSONObject result=new JSONObject();
			String sql="select count(*) count from t_user";
			int count = userdao.count(sql); //��ȡ����
			JSONArray jsonArray= userdao.selAll(Integer.parseInt(p), Integer.parseInt(rows));
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}else{
			JSONObject result = new JSONObject();
			String sql="select count(*) count from t_user where userName like'%"+sel+"%'";
			System.out.println("��ѯ����sqlΪ��"+sql);
			int count = userdao.count(sql); //��ȡ����
			JSONArray jsonArray = userdao.nameSel(Integer.parseInt(p),Integer.parseInt(rows),sel); //��ȡdao���ص�json����
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}
		
	}	
	
	/**
	 * ����Ա��¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void adminlogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		System.out.println("��½�ɹ�");
		String dlyzm=request.getParameter("dlyzm");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		HttpSession session = request.getSession();
	   String yzm=(String) session.getAttribute("vCode");
	   if(dlyzm.equalsIgnoreCase(yzm)){
			System.out.println("��֤����֤ͨ��");
			UserBean user= userdao.adminlogin(userName, password);
			System.out.println("���յ������֣�"+userName);
			System.out.println("���յ�����Ϊ��"+password);
			System.out.println("���صĶ���"+user);
			if(null!=user){
				//������ȷ
				request.getSession().setAttribute("stateOK", 0);
				response.sendRedirect("admin/index.jsp");
				request.getSession().setAttribute("userName", userName);
			}else{
				//������� ���߷ǹ���Ա
				request.getSession().setAttribute("state", 2);
				response.sendRedirect("/FirstShop/admin/main.jsp");
			}
	   }else{
		   request.getSession().setAttribute("state", 1);
		   response.sendRedirect("/FirstShop/admin/main.jsp");
	   }	
	}
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//		System.out.println("��½�ɹ�");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
			UserBean user= userdao.login(userName, password);
			System.out.println("���յ������֣�"+userName);
			System.out.println("���յ�����Ϊ��"+password);
			System.out.println("���صĶ���"+user);
			if(null!=user){
				//������ȷ
				request.getSession().setAttribute("stateOK", 0);
				response.sendRedirect("main.jsp");
				request.getSession().setAttribute("userName", userName);
				request.getSession().setAttribute("font", ",�˳�");
			}else{
				//������� ���߷ǹ���Ա
				request.getSession().setAttribute("state", 1);
				response.sendRedirect("login.jsp");
			}
	}
	public void regeuser(HttpServletRequest request, HttpServletResponse response){
		System.out.println("������ǰ̨ע����֤");
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
	 * ͼƬ��¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void genImage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//���ò�����ͼƬ
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		request.setCharacterEncoding("UTF-8");
		//ָ�����ɵ���ӦͼƬ,һ������ȱ����仰,�������.
		response.setContentType("image/jpeg");

		// ����ͼ��
		int width = 163, height = 64;
		//����BufferedImage����,�������൱��һͼƬ
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// ����ͼ�㣬��û���
		Graphics g = image.getGraphics();
	    //������ɫ
		g.setColor(Color.BLACK);
	    //��������
	    g.fillRect(0, 0, width, height);
		//�����߿�
	    g.setColor(Color.WHITE);
	    g.fillRect(1, 1, width-2, height-2);
	    
		  //����ַ�
	    String data = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefhjkmnpqrstuvwxyz2345678";
	    Random random=new Random();
	    g.setFont(new Font("����", Font.BOLD, 30)); //����������ʽ
	    StringBuffer buff = new StringBuffer();
	    //�������4���ַ�
	    for (int i = 0; i < 4; i++) {
	        int index = random.nextInt(53);
	        String str = data.substring(index, index + 1);
	        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
	        g.drawString(str, 30 * i, 40);
	        buff.append(str);
	    }
	    
	    //���õ����ַ������浽session��
	    HttpSession session = request.getSession();
	    session.setAttribute("vCode", buff.toString());
		
	    //����10��������
	    for (int i = 0; i < 10; i++) {
	    	g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
	        g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
	    }
		
		g.dispose();	//�ͷ�g��ռ�õ�ϵͳ��Դ
		ImageIO.write(image, "jpg", response.getOutputStream()); //���ͼƬ
	}

	public void rege(HttpServletRequest request,HttpServletResponse response) throws IOException{
   System.out.println("������");
   
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
						System.out.println("��֤����֤ͨ��");
						UserBean u = new UserBean(name, trueName, sex, birthday, statusID, phone, address, email, 1+"", password,proPic);
						String sql="select count(*) count from t_user where userName ='"+name+"'";
						int z=userdao.count(sql);
						if(z!=1){
							int i= userdao.add(u);
							System.out.println("���յ������֣�"+name);
							System.out.println("���յ�����Ϊ��"+password);
							if(i==1 ){
								//������ȷ
								request.getSession().setAttribute("stateOK", 0);
								response.sendRedirect("login.jsp");
								request.getSession().setAttribute("adminName", name);
							}else{
								//������� ���߷ǹ���Ա
								request.getSession().setAttribute("state", 2);
								response.sendRedirect("/FirstShop/rege.jsp");
							}
						}else{
							//������� ���߷ǹ���Ա
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
	 * �˳���¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//invalidate() ����session 
		//�ض�����ҳ
		
		request.getSession().invalidate();
		response.sendRedirect("/FirstShop/main.jsp");
		
	}
	/**
	 * ��ѯ
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
