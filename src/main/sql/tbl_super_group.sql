DROP TABLE IF EXISTS `tbl_super_group`;

CREATE TABLE `tbl_super_group`
(
    `id`          varchar(20)  NOT NULL COMMENT '主键',
    `name`        varchar(50)  NOT NULL DEFAULT '' COMMENT '名称',
    `total`      int(10) NOT NULL DEFAULT 0 COMMENT '总圈子数',
    `create_time` datetime     NOT NULL COMMENT '发布时间',
    `modify_time` datetime              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='圈子类别表'