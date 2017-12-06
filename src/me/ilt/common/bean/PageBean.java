package me.ilt.common.bean;

import java.util.ArrayList;
import java.util.List;

import me.ilt.goods.bean.GoodsBean;

	public class PageBean {
	private int pagesize;//ÿҳ��ʾ������
	private int pagetotal;//��ҳ��
	private int p;//��ǰҳ��
	private int count;//������
	private List data;//��ű�ҳ���ݵļ���
	
	public PageBean(int pagesize, int pagetotal, int p, int count, List data) {
		this.pagesize = pagesize;
		this.pagetotal = pagetotal;
		this.p = p;
		this.count = count;
		this.data = data;
	}
	public PageBean() {
		pagesize = 10;
		data = new ArrayList<GoodsBean>();
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPagetotal() {
		return pagetotal;
	}
	public void setPagetotal(int pagetotal) {
		this.pagetotal = pagetotal;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
		if(p<1){
			this.p=1;
		}else if(p>pagetotal){
			if(pagetotal!=0){
			this.p=pagetotal;
		}}
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		pagetotal = (int)Math.ceil(count*1.0/pagesize); //��������������ҳ��
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "PageBean [pagesize=" + pagesize + ", pagetotal=" + pagetotal
				+ ", p=" + p + ", count=" + count + ", data=" + data + "]";
	}

}
