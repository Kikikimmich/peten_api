DROP TABLE IF EXISTS `tbl_image_url`;
CREATE TABLE `tbl_image_url` (
  `id` varchar(20) NOT NULL COMMENT '图片ID',
  `md5` varchar(32) NOT NULL COMMENT '图片MD5',
  `url` varchar(50) COMMENT '网图URL',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `image_md5` (`md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='图片表';