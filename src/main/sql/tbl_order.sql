CREATE TABLE `tbl_order` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `orderId` varchar(20) NOT NULL COMMENT '订单号',
  `userId` varchar(20) NOT NULL COMMENT '用户id',
  `productId` varchar(20) NOT NULL COMMENT '商品id',
  `count` int(10) NOT NULL COMMENT '商品数量',
  `totalCost` varchar(20) NOT NULL COMMENT '费用',
  `addressId` varchar(20) NOT NULL COMMENT '地址id',
  `status` int(10) NOT NULL COMMENT '订单状态',
  `createTime` datetime NOT NULL COMMENT '发布时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单表'