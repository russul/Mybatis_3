package cn.scut.pojo;

/*
 *原始的order类不能之间封装该功能的数据，需要自己创建pojo
 pojo继承字段多的那个类，订单字段多就继承他，用户字段多就继承他，把没有继承的部分，添加进pojo，这里 创建一个新的pojo OrderEx 
 * 
 */
public class OrderEx extends Order {

	// 封装原始订单中没有的属性（用户表中的待映射属性）
	private String username;
	private String address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "OrderEx [id=" + this.getId() + ",user_id=" + this.getUser_id()
				+ ",number" + this.getNumber() + ",createtime="
				+ this.getCreatetime() + ",note=" + this.getNote()
				+ ",username=" + username + ", address=" + address + "]";
	}
}
