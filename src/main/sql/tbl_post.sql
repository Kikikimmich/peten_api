DROP TABLE IF EXISTS `tbl_post`;

CREATE TABLE `tbl_post` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
  `content` longtext COMMENT 'TEXT内容',
  `author` varchar(20) NOT NULL COMMENT '作者ID',
  `images` varchar(200) COMMENT '图片id集，;分割',
  `comments` int(11) NOT NULL DEFAULT '0' COMMENT '评论统计',
  `groupId` varchar(20) NOT NULL COMMENT '圈子ID',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='话题表';