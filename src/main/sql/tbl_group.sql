DROP TABLE IF EXISTS `tbl_group`;

CREATE TABLE `tbl_group`
(
    `id`          varchar(20)  NOT NULL COMMENT '主键',
    `name`        varchar(50)  NOT NULL DEFAULT '' COMMENT '名称',
    `cover`       varchar(50)  NOT NULL COMMENT '封面',
    `slogan`      varchar(255) NOT NULL DEFAULT '' COMMENT '封面',
    `follow`      int(10) NOT NULL DEFAULT 0 COMMENT '关注人数',
    `heat`        int(10) NOT NULL DEFAULT 0 COMMENT '热度',
    `superGroup`  varchar(20)  NOT NULL COMMENT '分类',
    `create_time` datetime     NOT NULL COMMENT '发布时间',
    `modify_time` datetime              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='圈子表'