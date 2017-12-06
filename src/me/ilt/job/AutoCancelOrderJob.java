package me.ilt.job;

import java.util.Date;
import java.util.List;

import me.ilt.goods.bean.OrderBean;
import me.ilt.goods.dao.OrderDao;
import me.ilt.goods.dao.impl.OrderDaoImpl;
import me.ilt.utils.GoodsConstCode;
import me.ilt.utils.ObjectFormatUtil;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AutoCancelOrderJob implements Job{
	
	private static Logger log = Logger.getLogger(AutoCancelOrderJob.class);
	public static boolean running = false;	
	
	OrderDao orderDao = new OrderDaoImpl();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		if(running)
		{
			log.info("��һ�γ�������������....");
			return;
		}
		running = true;
		log.info("�Զ��ر���Ч����");	
		
		try {
			List<OrderBean> orderBeanList =  orderDao.selQueryList();
			if(orderBeanList!=null && orderBeanList.size()>0){
				for(OrderBean orderBean : orderBeanList){
					if(ObjectFormatUtil.daysBetween(orderBean.getOrderTime(), new Date())>=3){
						System.out.println(orderBean);
						//���¶���״̬  �û��µ���������δ֧�� �Զ��رոö���
						orderDao.update(orderBean.getId(), orderBean.getAddressId()+"", GoodsConstCode.CUST_ORDER_STATUS_TRADE_CANCEL, orderBean.getPayType(), orderBean.getRemarks());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		running = false;	
	}

}
