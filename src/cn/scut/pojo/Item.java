package cn.scut.pojo;

import java.util.Date;


/***
 * 
 * 原始的pojo类的属性和数据库表的字段一一对应
 * @author Lenovo
 *
 */
public class Item {
	private int id;
	private String itemname;
	private float price;
	private String detail;
	private Date createtime;
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemname=" + itemname + ", price=" + price
				+ ", detail=" + detail + ", createtime=" + createtime + "]";
	}

}
