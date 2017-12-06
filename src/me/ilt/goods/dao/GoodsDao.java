package me.ilt.goods.dao;

import java.util.List;

import me.ilt.goods.bean.GoodsBean;
import net.sf.json.JSONArray;

public interface GoodsDao {
/**
 * 查询商品行数
 */
	public int count(String sql);
	
	/**
	 * 查询商品
	 */
	public JSONArray selAll(int p,int pageSize);
/**
 * 查询返回json集合
 * @param sql
 * @return
 */
	JSONArray sel(String sql);
/**
 * 询返回json集合带名字的查询
 * @param p
 * @param pageSize
 * @param name
 * @return
 */
JSONArray nameSel(int p, int pageSize, String name);
/**
 * 新增商品
 * @param u
 * @return
 */
int add(GoodsBean u);
/**
 * 修改的如果有图片就修，没有图片路径的就新增
 * @param u
 * @return
 */
int update(GoodsBean u);

/**
 * 删除
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

//減少庫存
void stockJian(int id, int num);

//查询OrderId
String selectOrderId(String orderId);

String selectOrdernew(String id);

String selectOrderIdbyorderidd(String orderIdd);

int OrderId(String orderId);

String selectdangezifu(String id);



}
