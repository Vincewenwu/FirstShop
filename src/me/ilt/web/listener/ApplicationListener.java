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
//servlet����/servlet������
//ServletContextListener  ServletContext�ļ�����  
public class ApplicationListener implements ServletContextListener  {
	// web������װ��webӦ���ǵ���
		public void contextInitialized(ServletContextEvent sce) {
			
			BigTypeDao bigTypeDao= new BigTypeDaoImpl();
			SlideDao slideDao = new SlideDaoImpl();
			// TODO �Զ����ɵķ������
					System.out.println("application������ʼ��");
					//��ȡ�����Ķ���  ������ҳ������
					List<BigTypeBean> floor = bigTypeDao.selList(); //��ȡ¥����༯��
        		//	List<BigTypeBean> bigTypes = bigTypeDao.bigselList(); //��ȡ���༰С�༶��
      				List<SlideBean> slideList = slideDao.selList();  //��ȡ�õ�ͼƬ����
					//���浽ȫ�ֵ�servletContext������
					ServletContext application = sce.getServletContext();
					application.setAttribute("floor", floor);
					application.setAttribute("slideList", slideList);
     			//	application.setAttribute("bigTypes", bigTypes);
     			
					System.out.println("�ѷ���application");
			
		}

		public void contextDestroyed(ServletContextEvent sce) {
			// TODO �Զ����ɵķ������
					System.out.println("application��������");
			
		}

}
