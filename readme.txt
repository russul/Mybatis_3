该项目实现高级映射的查询操作（多表查询）

以商品订单案例为模型需求

数据库设计
1、有四张表：用户表（user）、订单表（order）、订单详情表(orderdetail)、商品表(item)

2、分析过程
（1）各个表的记录的数据内容：
	user是记录参与商品购买的用户信息
	order记录用户下的订单信息
	orderdetail记录每个订单的详细信息
	item记录商品信息

（2）表的字段（重点主键，外键，非空字段）

	user：主键 id 
		其他字段：用户名（username）、生日（birthday）、性别（sex）、地址（address）
		外键：待定（待分析完表与表之间的关系再定） 
	order：主键 id
		其他字段：订单号（number）、创建订单时间（createtime）、备注（note）
		外键：待定（待分析完表与表之间的关系再定） 
	orderdetail：主键 id
		其他字段：商品购买数量（item_num）
		外键：待定（待分析完表与表之间的关系再定）
	item:主键：id
		其他字段：商品名称（name）、商品价格（price）、商品描述（detail）、商品图片（pic）、生产日期（product_time）
		外键：待定（待分析完表与表之间的关系再定）
（3）分析数据库级别的关系（表与表的关系）

	在分析表与表的业务关系时，一定要建立在某个业务的意义上
	先分析有外键关系的表
	user和order
	    user-->order 一个用户可以创建多个订单（业务的意义），一对多
	    order-->user 一个订单只能由一个用户创建，一对一
		换种业务意义描述：多个订单由一个用户创建，多对一
		这样 users和orders之间就是一对多关系
		这里就需要在多的那方（从表order）建立一个外键user_id,让它指向（主表）user的主键id
	
	
	order--->orderdetail    一个订单可以可以包含多个明细，因为一个订单里可以有很多商品 一对多
	orderdetail----》 order 一个明细只能包括在一个订单里  一对一
	
	这里就需要在orderdetail中建立外键order_id让它指向order的id
	
	
	orderdetail---》item   一个明细只对应一个商品信息  一对一
	item---》orderdetail  一个商品可以被包含在多个明细中  一对多
	这样orderdetail和item之间就是一对多关系，需要在orderdetail中建立外键item_id让它指向item的主键id
	
	
	
	没有外键关系的分析
	order和item
	两个之间没有直接的联系，
	
	但可以把它变成 order--》orderdetail(一对多)，orderdetail---》item（一对一）
	因此 order---》item 一对多
	
	同理
	item--》order  (item-->orderdetail 一对多 orderdetail---》order 一对一)是一对多
	
	因此，order和item是多对多关系
	
	同理
	user和item
	user--》item  一对多
	item--》user  一对多
	
	user 和item是多对多关系


建表（从没有外键的开始建，依次顺着关系建立）
	原则：主表先建，从表再建（这里才能满足外键约束）
	这里用户表和商品表都没有外键，可以从他们中选一个开始建表
	例如 商品表--订单详情--订单表--用户表
	或者 用户表（主）--订单表（从，主）---商品表(一对一，主)--订单详情（从，从）
	
需求：
	1、查询订单信息，并关联查询创建订单的用户信息
	这是一对一的查询
	一、用resultType实现


	确定查询主表：订单表
	确定查询关联表：用户表
	关联查询：内联、左外、右外？
	在orders中有一个外键user_id，通过外键关联查询用户名只能查询一条记录，因此直接使用内连接（他不会改变主表中的记录数）
	步骤：
		(1)sql语句
			 select 
			 o.*,
			 u.username,
			 u.address 
			 from `order` o,`user` u 
			 where o.user_id=u.id;
		（2）自定义创建pojo--->解决输出映射类型
			使用resultType必须要求列明和属性名一样
			
			原始的order类不能之间封装该功能的数据，需要自己创建pojo
			
			pojo继承字段多的那个类，订单字段多就继承他，用户字段多就继承他，把没有继承的部分，添加进pojo，这里 创建一个新的pojo OrderEx
			补充：针对复杂的查询条件（比如关联查询，一张表完成不了），可以封装一个Vo包装类解决输入映射类型（parameterType）将所有查询条件包装进去，命名为XXXQueryVo，比如针对用户的查询条件可以封装成UserQueryVo类
			这就是mybatis的特点，将输入映射和输出映射分离，分别做映射，通过两个标签parameterType、resultType(resultMap)
		(3)mapper.xml
			<mapper namespace="cn.scut.mapper.OrderExMapper">
			<!-- 使用resultType实现查询订单信息并关联用户信息 -->
				<select id="findOrderEx"  resultType="cn.scut.pojo.OrderEx">
					 select 
						 o.*,
						 u.username,
						 u.address 
						 from `order` o,`user` u 
						 where o.user_id=u.id;
				</select>
			</mapper>
		(4)mapper.java
		
			public interface OrderExMapper {
				//查询订单关联用户信息
				public List<OrderEx> findOrderEx() throws Exception;
			}
		(5)测试类
		。。。。。。
		
		二、用resultMap实现（无需创建新的pojo）
		将另一个对象当做一个属性注入主表所对应的pojo中
		（1）sql
		(2)修改pojo Order 添加user属性
		（3）mapper.xml
		
		<mapper namespace="cn.scut.mapper.OrderMapperResultMap">
			<!-- 定义resultMap具体的映射关系 -->
			<resultMap type="cn.scut.pojo.Order" id="orderResultMap">
				<!-- 主键映射 -->
				<id column="id" property="id"/>
				<!-- 其他字段映射 -->
				<result column="user_id" property="user_id"/>
				<result column="number" property="number"/>
				<result column="createtime" property="createtime"/>
				<result column="note" property="note"/>
				<!-- 配置关联的用户信息 -->
				<association property="user" javaType="cn.scut.pojo.User">
					<!-- 根据查询到的列数进行映射，比如用user_id去映射user对象的id属性，
					还比如没有完全把user的属性全映射完是因为查询的就是user表的部分信息而非全部信息 -->
					<id column="user_id" property="id"/>
					<result column="username" property="username"/>
					<result column="address" property="address"/>
				</association>
					
			</resultMap>
		<!-- 使用resultType实现查询订单信息并关联用户信息 -->
			<select id="findOrderResultMap"  resultMap="orderResultMap">
				 select 
					 o.*,
					 u.username,
					 u.address 
					 from `order` o,`user` u 
					 where o.user_id=u.id;
			</select>
		</mapper>
		(4)mapper.java
		public interface OrderMapperResultMap {
			public List<Order> findOrderResultMap() throws Exception;
		}
		
2、实现一对多查询
需求：查询订单以及订单明细信息
（1）sql
	select 
	o.*, od.id od_id,od.order_id,od.item_id,od.item_num 
	from `order` o,`orderdetail` od 
	where o.id=od.order_id;
	通过内连接查询出来的永远是和多的哪一方（订单明细，他不会重复）的记录数，显然这和订单（一方）不能重复，这样如果使用resultType,就的pojo中的订单信息就会重复
	因此，选择用resultMap,将明细信息List<Orderdetail>(由于是一对多，使用List集合)作为一个属性注入Order中，这样在Order对象中就不会出现重复信息
3、实现多对多的查询
需求：查询用户以及所购买商品的信息，商品和用户是多对多的关系
分析：
主表：用户表
关联表：由于用户和商品没有直接关联，要通过订单、明细 关联，还有商品表
（1）sql
	
	select `user`.*,
    `order`.id o_id,`order`.number,`order`.user_id,`order`.createtime,`order`.note,
     `orderdetail`.id od_id,`orderdetail`.item_id,`orderdetail`.order_id,`orderdetail`.item_num,
     `item`.id item_id,`item`.itemname,`item`.detail,`item`.price,`item`.createtime item_creatime
     from `user`,`order`,`orderdetail`,`item`
     where `order`.user_id=user.id and `orderdetail`.order_id=`order`.id and `orderdetail`.item_id=item.id;	
    使用resultMap实现
    按照 上诉的sql查询的必然是四张表中数据最多的记录行数，如果直接用resultType，那么作为主表的用户表将用很多重复记录（同一个id有多条记录），
    这里使用resultMap，将重复的记录层层的注入隐藏在User对象中（类似文件的树结构），注入的顺序按照表与表的关系进行
    User--》Order 一对多 在User中注入属性List<Order>
    Order--》OrderDetail 一对多 在Order中 注入属性List<OrderDetail>
    OrderDetail --》Item 一对一 在OrderDetail中注入属性Item
		