package me.ilt.user.bean;

public class UserBean {
	private int id;
	private String userName;
	private String trueName;
	private String sex;
	private String birthday;
	private String idCard;
	private String phone;
	private String address;
	private String email;
	private String userType;
	private String password;
	private String proPic;
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	public UserBean() {
	}
	public UserBean(String userName, String trueName, String sex,
			String birthday, String idCard, String phone, String address,
			String email, String userType, String password,String proPic) {
		this.userName = userName;
		this.trueName = trueName;
		this.sex = sex;
		this.birthday = birthday;
		this.idCard = idCard;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.userType = userType;
		this.password = password;
		this.proPic=proPic;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "userBean [id=" + id + ", userName=" + userName + ", trueName="
				+ trueName + ", sex=" + sex + ", birthday=" + birthday
				+ ", idCard=" + idCard + ", phone=" + phone + ", address="
				+ address + ", email=" + email + ", userType=" + userType
				+ ", password=" + password + "]";
	}
	
	
}
