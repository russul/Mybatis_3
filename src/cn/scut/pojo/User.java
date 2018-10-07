package cn.scut.pojo;

import java.util.Date;
import java.util.List;

/***
 * 
 * 原始的pojo类的属性和数据库表的字段一一对应
 * 
 * @author Lenovo
 *
 */
public class User {
	private int id;
	private String username;
	private Date birthday;
	private String sex;
	private String address;

	// 注入List<Order>属性
	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", birthday="
				+ birthday + ", sex=" + sex + ", address=" + address
				+ ", orders=" + orders + "]";
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", username=" + username + ", birthday="
//				+ birthday + ", sex=" + sex + ", address=" + address + "]";
//	}

}
