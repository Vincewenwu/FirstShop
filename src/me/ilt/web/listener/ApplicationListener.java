package me.ilt.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import me.ilt.sys.bean.BigTypeBean;
import me.ilt.sys.bean.SlideBean;
import me.ilt.sys.dao.BigTypeDao;
import me.ilt.sys.dao.SlideDao;
import me.ilt.sys.dao.impl.BigTypeDaoImpl;
import me.ilt.sys.dao.impl.SlideDaoImpl;
//servlet容器/servlet服务器
//ServletContextListener  ServletContext的监听器  
public class ApplicationListener implements ServletContextListener  {
	// web服务器装载web应用是调用
		public void contextInitialized(ServletContextEvent sce) {
			
			BigTypeDao bigTypeDao= new BigTypeDaoImpl();
			SlideDao slideDao = new SlideDaoImpl();
			// TODO 自动生成的方法存根
					System.out.println("application容器初始化");
					//获取上下文对象  放入首页的内容
					List<BigTypeBean> floor = bigTypeDao.selList(); //获取楼层大类集合
        		//	List<BigTypeBean> bigTypes = bigTypeDao.bigselList(); //获取大类及小类级联
      				List<SlideBean> slideList = slideDao.selList();  //获取幻灯图片集合
					//保存到全局的servletContext容器中
					ServletContext application = sce.getServletContext();
					application.setAttribute("floor", floor);
					application.setAttribute("slideList", slideList);
     			//	application.setAttribute("bigTypes", bigTypes);
     			
					System.out.println("已放入application");
			
		}

		public void contextDestroyed(ServletContextEvent sce) {
			// TODO 自动生成的方法存根
					System.out.println("application容器销毁");
			
		}

}
