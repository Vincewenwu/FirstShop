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
		String s = request.getParameter("name"); //��ȡ��������Ʒ����
		int p = Integer.parseInt(request.getParameter("p")); //��ȡ�����ҳ��
		String order = request.getParameter("order"); //��ȡ����ʽ
		System.out.println("��ȡĬ�ϵ�����ʽ��:"+order);
		String sid = request.getParameter("sid");//����С����
		PageBean pageData = null;
		if(sid!=null){
			//����С��ID��ѯ
			/*pageData = goodsDao.sidPageSel(sid, p, order);
			request.setAttribute("smallTypeName", smallTypeDao.sidIsName(sid)); */ //С�����Ʒ�������
		}else{
			//��Ʒ���Ʋ�ѯ
			/*
			1:���ȶ�����
			2:������ʱ��
			3:���۸�����
			4:����������
			 */
		/*	pageData = goodsDao.pageSel(s, p,order); //�õ���ѯҳ������
*/			}
	}

}
