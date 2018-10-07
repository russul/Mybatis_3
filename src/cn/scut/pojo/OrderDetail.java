package cn.scut.pojo;

/***
 * 
 * 原始的pojo类的属性和数据库表的字段一一对应
 * 
 * @author Lenovo
 *
 */
public class OrderDetail {
	private int id;
	private int order_id;
	private int item_id;
	private int item_num;

	// 注入Item属性
	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getItem_num() {
		return item_num;
	}

	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", order_id=" + order_id
				+ ", item_id=" + item_id + ", item_num=" + item_num + "]";
	}
}
