package me.ilt.web.sys.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.ilt.common.utils.Dic;
import me.ilt.common.utils.DictionaryMemoryUtils;
import net.sf.json.JSONArray;

/**
  
 http://localhost:8080/zhaishop/servlet/DictionarysParamServlet?nickName=yesorno
var yesornoJson;
$.getJSON("servlet/DictionarysParamServlet?nickName=yesorno", function(json){
	yesornoJson=json;
});

function yesornoFormatter(value){
	for(var i=0; i<yesornoJson.length; i++){		
		if (yesornoJson[i].value == value) return yesornoJson[i].name;
	}
	return value;
}
 */
public class DictionarysParamServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String nickName = request.getParameter("nickName");
		DictionaryMemoryUtils dictionaryUtils = DictionaryMemoryUtils.getInstance();
		Map<String,String> maps = dictionaryUtils.getDictionarysMap().get(nickName);
		if(null!=maps){
			List<Dic> dics = new ArrayList<Dic>();
			Set<String> keys = maps.keySet();
			for(Iterator<String> iter=keys.iterator();iter.hasNext();){
				String value = iter.next();
				Dic dic = new Dic();
				dic.setValue(value);
				dic.setName(maps.get(value));
				dics.add(dic);
			}
			//JSONO bject
			JSONArray json = JSONArray.fromObject(dics);
			//System.out.println(json.toString());
			this.outJsonPlainString(response, json.toString());
		}else{
			this.outJsonPlainString(response, "[]");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	/** 
	  * @Title: outJsonPlainString 
	  * @Description: ContentType为text/plain 
	  * @param @param response 输出
	  * @param @param json json格式字符串
	  * @return void
	  * @throws 
	  */
	protected void outJsonPlainString(HttpServletResponse response, String json){
		response.setContentType("text/plain;charset=UTF-8");
		try {
			outString(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	  * @Title: outString 
	  * @Description: 输出字符串
	  * @param response 返回
	  * @param str 传回的字符串
	  * @return void
	  * @throws 
	  */
	private void outString(HttpServletResponse response, String str) {
		try {
			PrintWriter out = response.getWriter();
			out.println(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
