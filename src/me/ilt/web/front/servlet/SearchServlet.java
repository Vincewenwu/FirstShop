package me.ilt.web.front.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.ilt.common.bean.PageBean;
import me.ilt.goods.dao.GoodsDao;
import me.ilt.goods.dao.impl.GoodsDaoImpl;

public class SearchServlet extends HttpServlet {

	private GoodsDao goodsDao = new GoodsDaoImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
              doGet(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s = request.getParameter("name"); //获取搜索的商品名称
		int p = Integer.parseInt(request.getParameter("p")); //获取需求的页数
		String order = request.getParameter("order"); //获取排序方式
		System.out.println("获取默认的排序方式是:"+order);
		String sid = request.getParameter("sid");//所属小分类
		PageBean pageData = null;
		if(sid!=null){
			//根据小类ID查询
			/*pageData = goodsDao.sidPageSel(sid, p, order);
			request.setAttribute("smallTypeName", smallTypeDao.sidIsName(sid)); */ //小类名称放入请求
		}else{
			//商品名称查询
			/*
			1:按热度排序
			2:按发布时间
			3:按价格排序
			4:按销量排序
			 */
		/*	pageData = goodsDao.pageSel(s, p,order); //得到查询页的数据
*/			}
	}

}
