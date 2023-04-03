CREATE TABLE `tbl_collect` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `userId` varchar(20) NOT NULL COMMENT '用户id',
  `productId` varchar(20) NOT NULL COMMENT '商品id',
  `createTime` datetime NOT NULL COMMENT '发布时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='收藏表'