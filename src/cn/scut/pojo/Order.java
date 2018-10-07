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
public class Order {
	private int id;
	private int user_id;
	private String number;
	private Date createtime;
	private String note;
	// 使用resultMap解决输出映射，需要向order里注入user（一对一查询）
	private User user;
	// 使用resultMap解决输出映射，解决一对多问题,也可以解决多对多问题
	private List<OrderDetail> orderDetails;

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user_id=" + user_id + ", number="
				+ number + ", createtime=" + createtime + ", note=" + note
				+ ", orderDetails=" + orderDetails + "]";
	}

//	@Override
//	public String toString() {
//		return "Order [id=" + id + ", user_id=" + user_id + ", number="
//				+ number + ", createtime=" + createtime + ", note=" + note
//				+ ", user=" + user.toString() + "]";
//	}

}
