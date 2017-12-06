package me.ilt.goods.bean;

public class GoodsBean {
	private int id; //��Ʒid
	private String name;  //��Ʒ����
	private double price;  //��Ʒ�۸�
	private String proPic;  //��ƷͼƬ
	private String brand;  //��ƷƷ��
	private int sales;  //��Ʒ����
	private int views;  //��Ʒ�����
	private int stock;  //��Ʒ���
	private String contents;  //��Ʒ����
	private int bigTypeId;  //����ID
	private String bigTypeName;  //��������
	private int smallTypeId;  //С��ID
	private String smallTypeName;  //С������
	private String state;  //״̬
	private int num; //���ﳵ����Ʒ������
	private double total;//���ﳵ�е�С��
	private String color;//��ɫ
	private String ordetId;//otderitem orderId
	private int order;//orderitemID
	private String ordetIdd;//otderitem orderId
	

	public String getOrdetIdd() {
		return ordetIdd;
	}

	public void setOrdetIdd(String ordetIdd) {
		this.ordetIdd = ordetIdd;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getOrdetId() {
		return ordetId;
	}

	public void setOrdetId(String ordetId) {
		this.ordetId = ordetId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public GoodsBean() {
	}
	
	public GoodsBean(int id,String name, double price, String proPic, String brand,
			int sales, int views, int stock, String contents,int bigTypeId, int smallTypeId,String color,String state) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.proPic = proPic;
		this.brand = brand;
		this.sales = sales;
		this.views = views;
		this.stock = stock;
		this.contents = contents;
		this.bigTypeId = bigTypeId;
		this.smallTypeId = smallTypeId;
		this.color =color;
		this.state = state;
	}
	public GoodsBean(String name, double price, String proPic, String brand,
			int sales, int views, int stock, String contents,int bigTypeId, int smallTypeId,String color,String state) {
		this.name = name;
		this.price = price;
		this.proPic = proPic;
		this.brand = brand;
		this.sales = sales;
		this.views = views;
		this.stock = stock;
		this.contents = contents;
		this.smallTypeId = smallTypeId;
		this.bigTypeId = bigTypeId;
		this.color =color;
		this.state = state;
	}
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getBigTypeId() {
		return bigTypeId;
	}
	public void setBigTypeId(int bigTypeId) {
		this.bigTypeId = bigTypeId;
	}
	public String getBigTypeName() {
		return bigTypeName;
	}
	public void setBigTypeName(String bigTypeName) {
		this.bigTypeName = bigTypeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getSmallTypeId() {
		return smallTypeId;
	}
	public void setSmallTypeId(int smallTypeId) {
		this.smallTypeId = smallTypeId;
	}
	public String getSmallTypeName() {
		return smallTypeName;
	}
	public void setSmallTypeName(String smallTypeName) {
		this.smallTypeName = smallTypeName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	

}
