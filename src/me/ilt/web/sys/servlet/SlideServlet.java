package me.ilt.web.sys.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;


import me.ilt.sys.dao.SlideDao;
import me.ilt.sys.dao.impl.SlideDaoImpl;
import me.ilt.utils.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SlideServlet extends HttpServlet {

     SlideDao slidedao=new SlideDaoImpl();
  
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String MethodName = request.getServletPath();
		MethodName = MethodName.substring(1, MethodName.length() -6);
		System.out.println("MethodName��"+MethodName);
		try {
			Method method = getClass().getDeclaredMethod(MethodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

	public void sel(HttpServletRequest request,HttpServletResponse response){
		  JSONObject result=new JSONObject();
 		  JSONArray jsonArray=slidedao.selAll();//����dao  json����
 		 result.put("rows", jsonArray);
			result.put("total", 5);
			ResponseUtil.write(response, result);
	}
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SmartUpload su=new SmartUpload();
		PageContext pc=JspFactory.getDefaultFactory().getPageContext(this, request, response,null, true, 8192, true);
		su.initialize(pc);//��ʼ��
		su.setMaxFileSize(1024*2048);//���ƴ�С2M
		try {
			su.setAllowedFilesList("jpg,png,bmp,gif,jpeg,txt,doc,xls,ppt");//�����ϴ�����
			su.setDeniedFilesList("exe,bat,jsp,html,htm,java,class");//�������ϴ�������
			su.upload();//�ϴ������
			String name=su.getRequest().getParameter("name");//��ȡ�����nam��ֵ
			System.out.println("���»õƽ��յ��������ǣ�"+name);
			String url=su.getRequest().getParameter("url");
			int id = Integer.parseInt(request.getParameter("id"));
			Files fs=su.getFiles();//��ȡ�����ϴ����ļ�����
			File f=fs.getFile(0);//��ȡ�ϴ����ļ�
			if(f.getSize()==0){
				int i = slidedao.update(id, name, url, null);
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
				String url2 = "images/slide/"+sdf.format(new Date())+"."+f.getFileExt();
				f.saveAs(url2);//����
				int i = slidedao.update(id, name, url, url2);
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
	}

}
	