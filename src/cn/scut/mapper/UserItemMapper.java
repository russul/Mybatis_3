package cn.scut.mapper;

import java.util.List;

import cn.scut.pojo.User;

//查询用户以及关联的购买的产品信息（多对多的查询）
public interface UserItemMapper {
	public List<User> findUserItemResultMap() throws Exception;
}
