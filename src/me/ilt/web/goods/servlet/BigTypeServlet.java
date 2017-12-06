package me.ilt.web.goods.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sun.awt.image.PixelConverter.Bgrx;

import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.mysql.fabric.xmlrpc.base.Data;

import me.ilt.sys.bean.BigTypeBean;
import me.ilt.sys.dao.BigTypeDao;
import me.ilt.sys.dao.impl.BigTypeDaoImpl;
import me.ilt.utils.ResponseUtil;

public class BigTypeServlet extends HttpServlet {

	BigTypeDao bigtypedao=new BigTypeDaoImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String MethodName = request.getServletPath();
		MethodName = MethodName.substring(1, MethodName.length() - 4);
		System.out.println("MethodName："+MethodName);
		try {
			Method method = getClass().getDeclaredMethod(MethodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	public void add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
        request.setCharacterEncoding("UTF-8");// 设置编码方式，保证从前台到后台中文不乱码  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=utf-8");// 设置编码方式，保证返回到前台中文不乱码  
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            fileUpload.setHeaderEncoding("UTF-8");
            List<FileItem> items = fileUpload.parseRequest(request);
            String name = null;
            String remarks = null;
            String url = null;
            for(FileItem item : items) {
                //文件域
                if(!item.isFormField()){
                    String fileName  = item.getName();
                    if(fileName==null || fileName.trim().equals("")){
                        continue;
                    }
                    fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
                  //得到上传文件的扩展名
					String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
                	SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH-mm-ss");
        			//jpg,png,bmp,gif,jpeg
        			if(!fileExtName.equals("jpg") && !fileExtName.equals("png") 
        					&& !fileExtName.equals("gif") && !fileExtName.equals("bmp")
        					&& !fileExtName.equals("jpeg") ){
						continue;
					}
        			//获取文件保存的路径
        			url = "images/bigTypeImg/"+sdf.format(new Date())+"."+fileExtName;
        			String	savePath = this.getServletContext().getRealPath("/images/bigTypeImg")+"\\"+sdf.format(new Date())+"."+fileExtName;
        			InputStream in = item.getInputStream();
					byte [] buffer = new byte[1024];
					int len = 0;
					OutputStream os= new FileOutputStream(savePath);
					while((len=in.read(buffer))!=-1){
						os.write(buffer, 0, len);
					}
					in.close();
					os.close();
                }else{
                	//获取其他表单数据
                	String fieldName  = item.getFieldName();
                	if(fieldName.equals("productBigType.bname")){
                		name = item.getString("UTF-8");
                	}else if(fieldName.equals("productBigType.remarks")){
                		remarks = item.getString("UTF-8");
                	}
                }
            }
            BigTypeBean b = new BigTypeBean(name,remarks);
			b.setImgUrl(url);
			int i =bigtypedao.add(b);
            JSONObject result=new JSONObject();
			if(i==0){
				result.put("errorMsg", "添加失败");
			}else{
				result.put("success", "true");
			}
			ResponseUtil.write(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
	public void sel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入了大类查询");
		String p = request.getParameter("page"); //需求页码
		String rows = request.getParameter("rows"); //每页多少条
		String sel = request.getParameter("s_productBigTypeName"); //如果是查询这不为空
		System.out.println("收到请求："+p+"  "+rows+"  "+sel);
		if(sel==null){
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_bigType";
			int count = bigtypedao.count(sql); //获取条数
			
			JSONArray jsonArray = bigtypedao.selAll(Integer.parseInt(p), Integer.parseInt(rows)); //获取dao返回的json集合
			
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}else{
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_bigType where bname like '%"+sel+"%'";
			System.out.println("查询行数sql为："+sql);
			int count = bigtypedao.count(sql); //获取条数
			JSONArray jsonArray = bigtypedao.nameSel(Integer.parseInt(p), Integer.parseInt(rows),sel); //获取dao返回的json集合
			result.put("rows", jsonArray);
			result.put("total", count);
			ResponseUtil.write(response, result);
		}
	}
	
	public void update(HttpServletRequest request,HttpServletResponse response) throws ServletException{
		SmartUpload su=new SmartUpload();
		PageContext pc=JspFactory.getDefaultFactory().getPageContext(this, request, response,null, true, 8192, true);
		su.initialize(pc);//初始化
		su.setMaxFileSize(1024*2048);//限制大小2M
		try {
			su.setAllowedFilesList("jpg,png,bmp,gif,jpeg,txt,doc,xls,ppt");//允许上传类型
			su.setDeniedFilesList("exe,bat,jsp,html,htm,java,class");//不允许上传的类型
			su.upload();//上传及检测
			String name=su.getRequest().getParameter("productBigType.bname");//获取表单组件nam的值
			String remarks=su.getRequest().getParameter("productBigType.remarks");
			int id = Integer.parseInt(request.getParameter("productBigType.id"));
			Files fs=su.getFiles();//获取所有上传的文件集合
			com.jspsmart.upload.File f=fs.getFile(0);//获取上传的文件
			if(f.getSize()==0){
				BigTypeBean b = new BigTypeBean(name,remarks);
				b.setId(id);
				int i = bigtypedao.update(b);
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
				String url = "images/bigTypeImg/"+sdf.format(new Date())+"."+f.getFileExt();
				f.saveAs(url);//保存
				BigTypeBean b = new BigTypeBean(name,remarks);
				b.setId(id);
				b.setImgUrl(url);
				int i = bigtypedao.update(b);
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
	}
	public void del(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("ids");
		int s = id.indexOf(",");
		int i = 0;
		if(s!=-1){
			//是个数组
			i = bigtypedao.manyDel(id);
			/*String [] ids = id.split(",");
			for(String j : ids){
				bigTypeDao.del(Integer.parseInt(j));
			}
			i = 1;*/
		}else{
			i = bigtypedao.del(Integer.parseInt(id));
		}
		System.out.println("接收到的为："+id);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "删除失败");
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
		jsonObject.put("bname", "请选择...");
		jsonArray.add(jsonObject);
		JSONArray jsonArray2 = bigtypedao.selAll(1, 100); //获取dao返回的json集合
		jsonArray.addAll(jsonArray2);
		ResponseUtil.write(response, jsonArray);
		
	}
}
