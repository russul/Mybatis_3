package cn.scut.mapper;

import java.util.List;

import cn.scut.pojo.OrderEx;

public interface OrderExMapper {
	//查询订单关联用户信息
	public List<OrderEx> findOrderEx() throws Exception;
}
