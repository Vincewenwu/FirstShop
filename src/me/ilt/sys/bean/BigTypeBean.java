package me.ilt.sys.bean;

import java.util.ArrayList;

import me.ilt.goods.bean.GoodsBean;

public class BigTypeBean {
	private int id;
	private String bname; //��������
	private String remarks;  //��������
	private String imgUrl; //����ͼƬ
	private ArrayList<SmallTypeBean> smallTypeList; //С�༯��
	private ArrayList<GoodsBean> goods; //����ǰʮ��Ʒ
public BigTypeBean(String bname, String remarks) {
	this.bname = bname;
	this.remarks = remarks;
}
public BigTypeBean() {
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return bname;
}
public void setName(String bname) {
	this.bname = bname;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public String getImgUrl() {
	return imgUrl;
}
public void setImgUrl(String imgUrl) {
	this.imgUrl = imgUrl;
}
public ArrayList<SmallTypeBean> getSmallTypeList() {
	return smallTypeList;
}
public void setSmallTypeList(ArrayList<SmallTypeBean> smallTypeList) {
	this.smallTypeList = smallTypeList;
}

public ArrayList<GoodsBean> getGoods() {
	return goods;
}
public void setGoods(ArrayList<GoodsBean> goods) {
	this.goods = goods;
}

}
