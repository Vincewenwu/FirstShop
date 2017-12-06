package me.ilt.goods.dao;

import java.util.List;

import me.ilt.goods.bean.GoodsBean;
import net.sf.json.JSONArray;

public interface GoodsDao {
/**
 * ��ѯ��Ʒ����
 */
	public int count(String sql);
	
	/**
	 * ��ѯ��Ʒ
	 */
	public JSONArray selAll(int p,int pageSize);
/**
 * ��ѯ����json����
 * @param sql
 * @return
 */
	JSONArray sel(String sql);
/**
 * ѯ����json���ϴ����ֵĲ�ѯ
 * @param p
 * @param pageSize
 * @param name
 * @return
 */
JSONArray nameSel(int p, int pageSize, String name);
/**
 * ������Ʒ
 * @param u
 * @return
 */
int add(GoodsBean u);
/**
 * �޸ĵ������ͼƬ���ޣ�û��ͼƬ·���ľ�����
 * @param u
 * @return
 */
int update(GoodsBean u);

/**
 * ɾ��
 * @param ids
 * @return
 */
int manyDel(String ids);
/**
 * 
 * @param bigTypeId
 * @return
 */
List<GoodsBean> bigTypeIdSel(int bigTypeId);

GoodsBean goodsIdSel(int id);

List<GoodsBean> bigTypeIdSelxg(int bigTypeId);

void addViews(int id);

GoodsBean gwcGoodsIdSel(int id);

public void addSales(int goodsId, int sum);

//�p�َ��
void stockJian(int id, int num);

//��ѯOrderId
String selectOrderId(String orderId);

String selectOrdernew(String id);

String selectOrderIdbyorderidd(String orderIdd);

int OrderId(String orderId);

String selectdangezifu(String id);



}
