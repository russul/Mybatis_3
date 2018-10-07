drop database item_order;
create database item_order;
use item_order;
#创建用户表，在最后定义 AUTO_INCREMENT=27的意思是自增长从27开始
create table `user`(
	`id` int 	NOT NULL AUTO_INCREMENT,
	`username` varchar(50) NOT NULL COMMENT '用户名称',
	`birthday` date DEFAULT NULL COMMENT '生日',
	`sex` varchar(20) DEFAULT NULL COMMENT '性别',
	`address` varchar(256) DEFAULT NULL COMMENT '地址',
	PRIMARY KEY(`id`)
	
)ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

#创建商品表
create table `item`(
	`id` int 	NOT NULL AUTO_INCREMENT,
	`itemname` varchar(32) NOT NULL COMMENT '商品名称',
    `price` float(10,1) NOT NULL COMMENT '商品定价',
    `detail` text COMMENT '商品描述',
    `pic` varchar(64) DEFAULT NULL COMMENT '商品图片',
    `createtime` datetime NOT NULL COMMENT '生产日期',
    PRIMARY KEY (`id`)
);

#创建订单表
create table `order`(
	`id` int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL COMMENT '下单用户id',
    `number` varchar(32) NOT NULL COMMENT '订单号',
    `createtime` datetime NOT NULL COMMENT '创建订单时间',
    `note` varchar(100) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_order_id` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

#创建订单详情表

create table `orderdetail`(
	`id` int NOT NULL AUTO_INCREMENT,
    `order_id` int NOT NULL COMMENT '订单id',
    `item_id` int NOT NULL COMMENT '商品id',
    `item_num` int DEFAULT NULL COMMENT '商品购买数量',
    PRIMARY KEY(`id`),
    CONSTRAINT `FK_orderdetail_1` FOREIGN KEY (`order_id`) REFERENCES `order`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `FK_orderdetail_2` FOREIGN KEY (`item_id`) REFERENCES `item`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
