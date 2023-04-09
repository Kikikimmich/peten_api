DROP TABLE IF EXISTS `tbl_follow`;

CREATE TABLE `tbl_follow`
(
    `id`          varchar(20)  NOT NULL COMMENT '主键',
    `userId`        varchar(20)  NOT NULL COMMENT '用户id',
    `follow`        varchar(20)  NOT NULL COMMENT '关注用户id',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `modify_time` datetime              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX userId_follow (userId, follow)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户关系表'