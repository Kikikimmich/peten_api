CREATE TABLE `tbl_product` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `title` varchar(100) NOT NULL COMMENT '商品标题',
  `introduction` text NOT NULL COMMENT '简介',
  `cover`varchar(50) NOT NULL COMMENT '商品封面',
  `categoryId` varchar(20) NOT NULL COMMENT '商品种类',
  `price` decimal(10,2) NOT	NULL COMMENT '价格',
  `specialPrice` decimal(10,2) NOT NULL COMMENT '优惠价',
  `stock` int(10) NOT NULL COMMENT '库存',
  `sales` int(10) NOT NULL COMMENT '销量',
  `createTime` datetime NOT NULL COMMENT '发布时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品表'