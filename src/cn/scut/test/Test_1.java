package cn.scut.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.scut.mapper.OrderExMapper;
import cn.scut.mapper.OrderMapperResultMap;
import cn.scut.mapper.OrderOrderDetailMapperResultMap;
import cn.scut.mapper.UserItemMapper;
import cn.scut.pojo.Order;
import cn.scut.pojo.OrderDetail;
import cn.scut.pojo.OrderEx;
import cn.scut.pojo.User;

public class Test_1 {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

//	@Test
//	public void testfindOrderEx() throws Exception {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		OrderExMapper orderExMapper = sqlSession.getMapper(OrderExMapper.class);
//		List<OrderEx> list = orderExMapper.findOrderEx();
//		for (OrderEx orderEx : list) {
//			System.out.println(orderEx);
//		}
//		sqlSession.close();
//	}

//	@Test
//	public void testfindOrderResultMap() throws Exception {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		OrderMapperResultMap orderMapperResultMap = sqlSession
//				.getMapper(OrderMapperResultMap.class);
//		List<Order> list = orderMapperResultMap.findOrderResultMap();
//		Iterator<Order> iterator = list.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
//		sqlSession.close();
//
//	}
	
//	@Test
//	public void testfindOrderResultMap1() throws Exception {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		OrderOrderDetailMapperResultMap orderOrderDetailMapperResultMap = sqlSession.getMapper(OrderOrderDetailMapperResultMap.class);
//		List<Order> list = orderOrderDetailMapperResultMap.findOrderResultMap();
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i));
//			
//		}
//		sqlSession.close();
//
//	}
	
	@Test
	public void testfindUserItemResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserItemMapper userItemMapper = sqlSession.getMapper(UserItemMapper.class);
		List<User> list = userItemMapper.findUserItemResultMap();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
			
		}
		sqlSession.close();

	}
}
