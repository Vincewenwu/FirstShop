package me.ilt.web.goods.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import me.ilt.sys.bean.SmallTypeBean;
import me.ilt.sys.dao.SmallTypeDao;
import me.ilt.sys.dao.impl.SmallTypeDaoImpl;
import me.ilt.utils.ResponseUtil;

public class SmallTypeServlet extends HttpServlet {

 	private SmallTypeDao smallTypeDao = new SmallTypeDaoImpl();
	
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
		}catch (Exception e) {
			 e.printStackTrace();
		}
	}
	/**
	 * ����û�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException    {
		String name = request.getParameter("productSmallType.name");
		String remarks = request.getParameter("productSmallType.remarks");
		String bigTypeId = request.getParameter("productSmallType.bigTypeId");
		System.out.println("����û����յ���"+name+remarks+bigTypeId);
		SmallTypeBean b = new SmallTypeBean(name,Integer.parseInt(bigTypeId),remarks);
		int i=smallTypeDao.add(b);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "���ʧ��");
		}else{
			result.put("success", "true");
		}
		ResponseUtil.write(response, result);
	}
	public void sel(HttpServletRequest request, HttpServletResponse response){
		System.out.println("������С���ѯ");
		String p =request.getParameter("page");
		String rows=request.getParameter("rows");
		String sel=request.getParameter("s_productSmallTypeName");
		System.out.println("�յ�����"+p+"  "+rows+"  "+sel);
		if(sel==null){
			JSONObject result = new JSONObject();
			String sql="select count(*) from  t_smalltype";
			int count=smallTypeDao.count(sql);
			JSONArray jsonArray = smallTypeDao.selAll(Integer.parseInt(p), Integer.parseInt(rows)); //��ȡdao���ص�json����
			
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}else{
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_smallType where sname like '%"+sel+"%'";
			System.out.println("��ѯ����sqlΪ��"+sql);
			int count = smallTypeDao.count(sql); //��ȡ����
			JSONArray jsonArray = smallTypeDao.nameSel(Integer.parseInt(p), Integer.parseInt(rows),sel); //��ȡdao���ص�json����
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}
	}
	public void update( HttpServletRequest  request,HttpServletResponse response){
		System.out.println("��������Ʒ����");
		String name = request.getParameter("productSmallType.name");
		String remarks = request.getParameter("productSmallType.remarks");
		int bigId = Integer.parseInt(request.getParameter("productSmallType.bigTypeId"));
		int id = Integer.parseInt(request.getParameter("productSmallTypeId"));
		SmallTypeBean b=new SmallTypeBean(name,bigId,remarks);
		b.setId(id);
		int i=smallTypeDao.update(b);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "����ʧ��");
		}else{
			result.put("success", "true");
		}
		ResponseUtil.write(response, result);
	}
	public void del( HttpServletRequest  request,HttpServletResponse response){
		String id=request.getParameter("ids");
		int s=id.indexOf(",");
		int i=0;
		if(s!=-1){
			i=smallTypeDao.manyDel(id);
		}else{
			i=smallTypeDao.del(Integer.parseInt(id));
		}
		System.out.println("���յ���Ϊ��"+id);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "ɾ��ʧ��");
		}else{
			result.put("success", "true");
		}
		ResponseUtil.write(response, result);
	}
	
	public void selList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("sname", "����ѡ�����...");
		jsonArray.add(jsonObject);
		ResponseUtil.write(response, jsonArray);
		
	}
	//���ˢ��С�������
	public void idSelList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bigTypeId = request.getParameter("bigTypeId");
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		if(bigTypeId.equals("")){
			jsonObject.put("id", "");
			jsonObject.put("sname", "����ѡ�����...");
			jsonArray.add(jsonObject);
		}else{
			jsonObject.put("id", "");
			jsonObject.put("sname", "��ѡ��...");
			jsonArray.add(jsonObject);
			JSONArray jsonArray2 = smallTypeDao.bigTypeIdsel(Integer.parseInt(bigTypeId)); //��ȡdao���ص�json����
			jsonArray.addAll(jsonArray2);
		}
		ResponseUtil.write(response, jsonArray);
		
	}
}
