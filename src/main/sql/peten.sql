/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50740
 Source Host           : localhost:3306
 Source Schema         : peten

 Target Server Type    : MySQL
 Target Server Version : 50740
 File Encoding         : 65001

 Date: 05/04/2023 23:16:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_article
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article`;
CREATE TABLE `tbl_article`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'markdown内容',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者ID',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '封面',
  `create_time` datetime(0) NOT NULL COMMENT '发布时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_article
-- ----------------------------
INSERT INTO `tbl_article` VALUES ('1621766677939929090', '测试', '测试', '1619965295545135105', 'https://i.328888.xyz/2023/01/31/8XK9p.png', '2023-02-04 15:04:42', NULL);
INSERT INTO `tbl_article` VALUES ('1621811040644562945', '测试', '测试![Snipaste_20230131_163108.png](https://i.328888.xyz/2023/01/31/8XK9p.png)\n测试成功！', '1619965295545135105', 'https://i.328888.xyz/2023/01/31/8XK9p.png', '2023-02-04 18:00:59', NULL);
INSERT INTO `tbl_article` VALUES ('1622866272442998785', '测试', '![gouzi.jpg](http://localhost:8000/image/ad64456d-9e46-4dc8-a081-2800cedc5b0bi.jpg)', '1622243684553383938', 'http://localhost:8000/image/2cc2eb74-2883-4eef-b0bb-211ed8c18d8bi.jpg', '2023-02-07 15:54:05', NULL);
INSERT INTO `tbl_article` VALUES ('1642409305211465730', '17种最适合家庭的小型犬', '\n17种最适合家庭的小型犬\n舶来品商城BuyIMP\n舶来品商城BuyIMP\n965 人赞同了该文章\n选择养什么品种的狗对很多人来说一直都是特别难的决定，如果涉及到一个家庭养汪，需要考量的因素就更多了，可选择的品种范围自然也就小了。但经过舶来品商城深度挖掘，其实还是有很多适合大人小孩一起玩的小型犬哦。\n\n动图封面\n虽然每只狗狗各有各的特点和性格，但下面这份名单会让你了解到哪些品种是更有耐心、更温和，同时还能满足你养狗的需求。但无论是什么类型的犬类，出于安全起见，较小和容易兴奋的孩子跟狗狗一起玩的时候，都应该有大人在旁边照看。\n\n蝴蝶犬\n\n作为拥有大心脏的小狗，蝴蝶犬很容易与人亲热，但也多少有点较弱，非常适合性格温和、有爱心的孩子与家庭。它们比较黏人，与主人分开时比较容易焦虑。蝴蝶犬的缺点是独处的时候会比较爱叫，在密集的住宅区内可能会对主人和邻居造成烦恼。\n\n\n无毛犬\n\n无毛犬喜欢人，有耐心，作为小型犬也总是关注的中心，所以很适合小朋友。只是这个品种的小狗也很黏人，所以最好不要让它们独处太久，否则会引起它们的焦虑哦。\n\n\n马尔济斯\n\n马尔济斯性格外向大胆，而外表又给人一种甜美的感觉。这类小狗性格温和，但作为主人要明确界限和纪律，否则容易被宠坏哦。如果主人不能经常溜它也没关系，因为这个品种的小狗不太需要通过大量锻炼、散步和玩耍来保持身心愉悦。\n\n\n比熊犬\n\n比熊犬是理想的家庭犬类，它们温顺，对大人、孩子以及其它宠物也很友好。他们没有体力要求，只需要定期轻度运动以保持身体健康。比熊犬脱毛很少，是过敏症患者的完美选择。\n\n\n哈瓦那犬\n\n哈瓦那犬性格随和，并喜欢与人玩耍，属于伴侣型宠物。它们虽然很活跃，但不需要经常在外锻炼。相对而言，哈瓦那犬需要长期稳定的毛发护理以保持皮毛健康，除此以外，它是最适合家庭的理想犬类之一。\n\n\n意大利灵缇犬\n\n灵缇犬既干净又安静，非常适合小朋友。虽然平常略显平静，但他们其实是充满非侵略性的能量的。这种品种相对畏寒，天凉时记得为它们添置毛衣和毛毯。灵缇犬会与平静温和的家人孩子是超合适的配对。\n\n\n查理王小腊犬\n\n查理王小腊犬表现良好，易于训练，无论在任何充满爱与感情的环境中，都是最好的小型犬种之一。这个品种得小狗很有趣，热爱冒险，如果没有足够的锻炼和注意力会产生焦虑。对于任何规模的家庭来说，查理王都是一个很好的选择。\n\n\n诺福克梗\n\n诺福克梗容易与整个家庭紧密联系起来，它们通常很安静，只是需要每天锻炼。它们有无穷的能量，喜欢在家里跑来跑去，不管家里有几个孩子都很适合。\n\n\n西施犬\n\n西施是一个亲切温和的品种。它们需要定期毛发梳理，但它们的运动需求很少。只要家庭成员在附近，西施犬就能随时随地地保持愉快的心情。\n\n\n西藏腊犬\n\n不要被这名字吓到，西藏猎犬对孩子友好，对家人忠诚，是有小孩的家庭的理想选择。比起自娱自乐，它们更喜欢和人一起玩耍，所以长时间独处容易感到焦虑。以及这类小狗也需要定期打理毛发。\n\n\n法国斗牛犬\n\n法斗性格温和有趣，不需要太多的空间，只要有人对它保持持续的关注就可以了。这个品种的小狗容易中暑，天气较热时体温容易上升，所以得注意温度。\n\n\n比格犬\n\n比格犬以亲热与好奇的性格出名，它们对孩子有耐心，并且有耐力能跟上孩子们无穷的体力。比格犬的叫声较大，这点需要注意一下哦。\n\n\n巴哥犬（哈巴狗）\n\n哈巴狗是甜美有趣的小狗，跟顽皮的小孩也很适合，并且相当有耐心。它们喜欢玩，但能量相当低，容易过热。哈巴狗对婴儿和幼儿都很温柔。\n\n\n可卡犬\n\n可卡犬拥有忠诚和深情的性格，也特别容忍其它狗，适合家里有不止一只狗的家庭。他们有点敏感的，需要定期毛发梳理，体质容易适应不同的气候。\n\n\n波士顿梗\n\n波士顿梗充满活力，适应能力强，是一个活跃家庭的美妙宠物。它们通常与主人的情绪紧密联系着，并会调整自己的行为以满足主人的需求。它们的叫声有点大，但这使得他们成为了很好的守卫犬，不过要注意可能会招致邻居的投诉。\n\n\n西部高地白梗\n\n这种品种是适合小孩最好的小型犬种之一， 但如果家里已经有猫或者其它小宠物的话，可能不是很好的选择。西高地喜欢玩，并且有耐力跟上甚至非常活跃的孩子，这些狗是家庭中高能量家庭的完美选择，只是需要拥有足够的空间来跑步和玩耍。\n\n\n喜乐蒂牧羊犬\n\n喜乐蒂聪明又顽皮，需要大量的精神和体育锻炼。它们充满爱心，对大声喧哗比较敏感，使他们更适合有大孩子的家庭。但喜乐蒂脱毛比较严重，需要经常进行毛发护理。\n\n\n舶来品商城BuyIMP提供优质宠物粮食和宠物用品，由海外商铺直接入驻平台，更有保障。海外商家皆有境外认证，货源可以追溯，而且沟通透明。平台按中国和海外商铺所属国的法律来保障正品，海外售假后果严重，一旦发现假货平台将会利用法律途径向商铺追究到底，所以各位爱宠小主们大可放心。舶来品商城BuyIMP提供优质宠物粮食和宠物用品，由海外商铺直接入驻平台，更有保障。海外商家皆有境外认证，货源可以追溯，而且沟通透明。平台按中国和海外商铺所属国的法律来保障正品，海外售假后果严重，一旦发现假货平台将会利用法律途径向商铺追究到底，所以各位爱宠小主们大可放心。\n\n作为综合型跨境电商平台，舶来品商城也涵盖了丰富多元的品类，除了宠物食品外，还有母婴用品、美容彩妆、家居生活、营养保健、数码家电等等。欢迎到http://BuyIMP.com上浏览舶来品商城的入驻商家和商品哦！\n\n', '1622243684553383938', 'http://localhost:8000/image/d951480f-97f3-4214-80f7-a32c16ad3792狗.png', '2023-04-02 14:11:08', NULL);
INSERT INTO `tbl_article` VALUES ('1642411551991066626', '狗狗品种大全', '![狗狗品种.png](http://localhost:8000/image/29bb44a8-83d0-488c-a847-84f91a774f2c种.png)\norg.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException: The field source exceeds its maximum permitted size of 1048576 bytes.', '1622243684553383938', 'http://localhost:8000/image/465e3a86-fe3d-4217-b29d-0ae066c3f909种.png', '2023-04-02 14:20:03', NULL);

-- ----------------------------
-- Table structure for tbl_billboard
-- ----------------------------
DROP TABLE IF EXISTS `tbl_billboard`;
CREATE TABLE `tbl_billboard`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '公告时间',
  `show` tinyint(1) NULL DEFAULT NULL COMMENT '1：展示中，0：过期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '全站公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_billboard
-- ----------------------------
INSERT INTO `tbl_billboard` VALUES (2, 'R1.0 开始已实现护眼模式 ,妈妈再也不用担心我的眼睛了。', '2020-11-19 17:16:19', 0);
INSERT INTO `tbl_billboard` VALUES (4, '系统已更新至最新版1.0.1', NULL, 1);

-- ----------------------------
-- Table structure for tbl_category
-- ----------------------------
DROP TABLE IF EXISTS `tbl_category`;
CREATE TABLE `tbl_category`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品总类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_category
-- ----------------------------
INSERT INTO `tbl_category` VALUES ('1622527031221522433', '狗粮');

-- ----------------------------
-- Table structure for tbl_collect
-- ----------------------------
DROP TABLE IF EXISTS `tbl_collect`;
CREATE TABLE `tbl_collect`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `productId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `createTime` datetime(0) NOT NULL COMMENT '发布时间',
  `modifyTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_collect
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_comment
-- ----------------------------
DROP TABLE IF EXISTS `tbl_comment`;
CREATE TABLE `tbl_comment`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者id',
  `articleId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章id',
  `rootCommentId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复评论id',
  `type` int(11) NOT NULL COMMENT '评论类别',
  `createTime` datetime(0) NOT NULL COMMENT '发布时间',
  `modifyTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_comment
-- ----------------------------
INSERT INTO `tbl_comment` VALUES ('1622423855038214145', '这是一个测试', '1622243684553383938', '1621766677939929090', NULL, 1, '2023-02-06 10:36:05', NULL);
INSERT INTO `tbl_comment` VALUES ('1622423916950335490', '我来回复你', '1622243684553383938', '1621766677939929090', '1622423855038214145', 1, '2023-02-06 10:36:20', NULL);
INSERT INTO `tbl_comment` VALUES ('1622424008528769026', '这是三级评论', '1622243684553383938', '1621766677939929090', '1622423916950335490', 1, '2023-02-06 10:36:41', NULL);
INSERT INTO `tbl_comment` VALUES ('1622428640260927489', '二级', '1622243684553383938', '1621766677939929090', '1622423855038214145', 1, '2023-02-06 10:55:06', NULL);
INSERT INTO `tbl_comment` VALUES ('1622435987595309057', '三级', '1622243684553383938', '1621766677939929090', '1622424008528769026', 1, '2023-02-06 11:24:17', NULL);
INSERT INTO `tbl_comment` VALUES ('1622438012127129602', '测试', '1622243684553383938', '1621766677939929090', NULL, 1, '2023-02-06 11:32:20', NULL);
INSERT INTO `tbl_comment` VALUES ('1622438039973113858', '测试', '1622243684553383938', '1621766677939929090', '1622438012127129602', 1, '2023-02-06 11:32:27', NULL);
INSERT INTO `tbl_comment` VALUES ('1622438493348016129', '测试', '1622243684553383938', '1621766677939929090', NULL, 1, '2023-02-06 11:34:15', NULL);
INSERT INTO `tbl_comment` VALUES ('1622438521617625090', '测试', '1622243684553383938', '1621766677939929090', '1622438493348016129', 1, '2023-02-06 11:34:22', NULL);
INSERT INTO `tbl_comment` VALUES ('1622438683500982274', '测试', '1622243684553383938', '1621766677939929090', '1622438521617625090', 1, '2023-02-06 11:35:00', NULL);
INSERT INTO `tbl_comment` VALUES ('1622440094070583298', '666', '1622243684553383938', '1621766677939929090', '1622435987595309057', 1, '2023-02-06 11:40:37', NULL);
INSERT INTO `tbl_comment` VALUES ('1622441236028567553', '000', '1622243684553383938', '1621766677939929090', '1622438039973113858', 1, '2023-02-06 11:45:09', NULL);
INSERT INTO `tbl_comment` VALUES ('1622441289107484673', '真棒', '1622243684553383938', '1621766677939929090', '1622438683500982274', 1, '2023-02-06 11:45:21', NULL);
INSERT INTO `tbl_comment` VALUES ('1627154215483539458', '333', '1622243684553383938', '1622866272442998785', NULL, 1, '2023-02-19 11:52:51', NULL);
INSERT INTO `tbl_comment` VALUES ('1627154254578647041', '666', '1622243684553383938', '1622866272442998785', '1627154215483539458', 1, '2023-02-19 11:53:00', NULL);
INSERT INTO `tbl_comment` VALUES ('1640371831412969474', 'ww', '1622243684553383938', '1621811040644562945', NULL, 1, '2023-03-27 23:14:56', NULL);
INSERT INTO `tbl_comment` VALUES ('1640371877009248257', '22', '1622243684553383938', '1621811040644562945', NULL, 1, '2023-03-27 23:15:07', NULL);

-- ----------------------------
-- Table structure for tbl_follow
-- ----------------------------
DROP TABLE IF EXISTS `tbl_follow`;
CREATE TABLE `tbl_follow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '被关注人ID',
  `follower_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关注人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户关注' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_follow
-- ----------------------------
INSERT INTO `tbl_follow` VALUES (65, '1329723594994229250', '1317498859501797378');
INSERT INTO `tbl_follow` VALUES (85, '1332912847614083073', '1332636310897664002');
INSERT INTO `tbl_follow` VALUES (129, '1349290158897311745', '1349618748226658305');

-- ----------------------------
-- Table structure for tbl_group
-- ----------------------------
DROP TABLE IF EXISTS `tbl_group`;
CREATE TABLE `tbl_group`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `cover` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '封面',
  `slogan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '封面',
  `follow` int(10) NOT NULL DEFAULT 0 COMMENT '关注人数',
  `heat` int(10) NOT NULL DEFAULT 0 COMMENT '热度',
  `superGroup` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类',
  `create_time` datetime(0) NOT NULL COMMENT '发布时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '圈子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_group
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_image_url
-- ----------------------------
DROP TABLE IF EXISTS `tbl_image_url`;
CREATE TABLE `tbl_image_url`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片ID',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片MD5',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网图URL',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `image_md5`(`md5`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_image_url
-- ----------------------------
INSERT INTO `tbl_image_url` VALUES ('1620269728871043074', 'b63a94ff1f98d3080d461fb76e5aa439', 'https://i.328888.xyz/2023/01/31/8r8SJ.png');
INSERT INTO `tbl_image_url` VALUES ('1620339091011620865', '3f3920919aed5c6462e9eacbbfe0e308', 'https://i.328888.xyz/2023/01/31/8XK9p.png');
INSERT INTO `tbl_image_url` VALUES ('1621764165241458690', 'ae187c0812dc0e33e3ccf59de08ba51d', 'https://i.328888.xyz/2023/02/04/NpOZq.jpeg');
INSERT INTO `tbl_image_url` VALUES ('1622854026576908290', 'e1daa602457aefa2092d11cc968f91b9', 'https://i.328888.xyz/2023/02/07/rhU5w.jpeg');

-- ----------------------------
-- Table structure for tbl_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order`;
CREATE TABLE `tbl_order`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `orderId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `productId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `count` int(10) NOT NULL COMMENT '商品数量',
  `unitPrice` decimal(10, 2) NOT NULL COMMENT '单价',
  `totalCost` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '费用',
  `addressId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址id',
  `status` int(10) NOT NULL COMMENT '订单状态',
  `createTime` datetime(0) NOT NULL COMMENT '发布时间',
  `modifyTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_order
-- ----------------------------
INSERT INTO `tbl_order` VALUES ('1628232046766505986', '1628232046149943297', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:46', NULL);
INSERT INTO `tbl_order` VALUES ('1628232048079323138', '1628232048079323137', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:46', NULL);
INSERT INTO `tbl_order` VALUES ('1628232049274699777', '1628232049274699777', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:46', NULL);
INSERT INTO `tbl_order` VALUES ('1628232050432327682', '1628232050432327682', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:47', NULL);
INSERT INTO `tbl_order` VALUES ('1628232052000997378', '1628232052000997378', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:47', NULL);
INSERT INTO `tbl_order` VALUES ('1628232053284454401', '1628232053221539841', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:47', NULL);
INSERT INTO `tbl_order` VALUES ('1628232054437888002', '1628232054437888001', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:48', NULL);
INSERT INTO `tbl_order` VALUES ('1628232055658430466', '1628232055595515905', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:48', NULL);
INSERT INTO `tbl_order` VALUES ('1628232056748949505', '1628232056748949506', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:48', NULL);
INSERT INTO `tbl_order` VALUES ('1628232057826885634', '1628232057826885633', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:48', NULL);
INSERT INTO `tbl_order` VALUES ('1628232058976124929', '1628232058976124929', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:49', NULL);
INSERT INTO `tbl_order` VALUES ('1628232060070838273', '1628232060070838274', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:49', NULL);
INSERT INTO `tbl_order` VALUES ('1628232061161357313', '1628232061161357313', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:49', NULL);
INSERT INTO `tbl_order` VALUES ('1628232062256070658', '1628232062256070657', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:49', NULL);
INSERT INTO `tbl_order` VALUES ('1628232063543721986', '1628232063480807425', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:50', NULL);
INSERT INTO `tbl_order` VALUES ('1628232064764264450', '1628232064701349889', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:50', NULL);
INSERT INTO `tbl_order` VALUES ('1628232065917698049', '1628232065854783489', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:50', NULL);
INSERT INTO `tbl_order` VALUES ('1628232067008217090', '1628232067008217090', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:51', NULL);
INSERT INTO `tbl_order` VALUES ('1628232068228759554', '1628232068161650689', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:51', NULL);
INSERT INTO `tbl_order` VALUES ('1628232069847760898', '1628232069780652033', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:51', NULL);
INSERT INTO `tbl_order` VALUES ('1628232072049770498', '1628232072049770497', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:52', NULL);
INSERT INTO `tbl_order` VALUES ('1628232074125950977', '1628232074063036418', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:52', NULL);
INSERT INTO `tbl_order` VALUES ('1628232075686232065', '1628232075686232066', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:53', NULL);
INSERT INTO `tbl_order` VALUES ('1628232076973883393', '1628232076910968834', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:53', NULL);
INSERT INTO `tbl_order` VALUES ('1628232078198620161', '1628232078198620162', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:53', NULL);
INSERT INTO `tbl_order` VALUES ('1628232079553380354', '1628232079553380354', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:54', NULL);
INSERT INTO `tbl_order` VALUES ('1628232081113661442', '1628232081046552578', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:54', NULL);
INSERT INTO `tbl_order` VALUES ('1628232082271289346', '1628232082271289345', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:54', NULL);
INSERT INTO `tbl_order` VALUES ('1628232083504414722', '1628232083504414722', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:54', NULL);
INSERT INTO `tbl_order` VALUES ('1628232084720762881', '1628232084720762881', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:55', NULL);
INSERT INTO `tbl_order` VALUES ('1628232085945499649', '1628232085945499650', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:55', NULL);
INSERT INTO `tbl_order` VALUES ('1628232087166042113', '1628232087166042113', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:55', NULL);
INSERT INTO `tbl_order` VALUES ('1628232088394973185', '1628232088394973186', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:56', NULL);
INSERT INTO `tbl_order` VALUES ('1628232089686818818', '1628232089623904258', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:56', NULL);
INSERT INTO `tbl_order` VALUES ('1628232091112882178', '1628232091112882177', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:56', NULL);
INSERT INTO `tbl_order` VALUES ('1628232092404727810', '1628232092404727810', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:57', NULL);
INSERT INTO `tbl_order` VALUES ('1628232093759488001', '1628232093696573442', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:57', NULL);
INSERT INTO `tbl_order` VALUES ('1628232094988419074', '1628232094988419073', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:57', NULL);
INSERT INTO `tbl_order` VALUES ('1628232096280264706', '1628232096280264706', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:58', NULL);
INSERT INTO `tbl_order` VALUES ('1628232097513390081', '1628232097513390082', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:58', NULL);
INSERT INTO `tbl_order` VALUES ('1628232098993979394', '1628232098993979393', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:58', NULL);
INSERT INTO `tbl_order` VALUES ('1628232100222910466', '1628232100222910466', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:58', NULL);
INSERT INTO `tbl_order` VALUES ('1628232101443452929', '1628232101443452929', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:59', NULL);
INSERT INTO `tbl_order` VALUES ('1628232102823378946', '1628232102760464385', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:59', NULL);
INSERT INTO `tbl_order` VALUES ('1628232104069087234', '1628232104069087233', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:15:59', NULL);
INSERT INTO `tbl_order` VALUES ('1628232105293824002', '1628232105293824001', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:00', NULL);
INSERT INTO `tbl_order` VALUES ('1628232106585669633', '1628232106585669634', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:00', NULL);
INSERT INTO `tbl_order` VALUES ('1628232107881709569', '1628232107881709569', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:00', NULL);
INSERT INTO `tbl_order` VALUES ('1628232109047726081', '1628232109047726082', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:01', NULL);
INSERT INTO `tbl_order` VALUES ('1628232110331183106', '1628232110331183106', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:01', NULL);
INSERT INTO `tbl_order` VALUES ('1628232111430090753', '1628232111430090754', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:01', NULL);
INSERT INTO `tbl_order` VALUES ('1628232112520609793', '1628232112520609794', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:01', NULL);
INSERT INTO `tbl_order` VALUES ('1628232113615323137', '1628232113615323137', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:02', NULL);
INSERT INTO `tbl_order` VALUES ('1628232114710036482', '1628232114710036482', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:02', NULL);
INSERT INTO `tbl_order` VALUES ('1628232115804749825', '1628232115804749825', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:02', NULL);
INSERT INTO `tbl_order` VALUES ('1628232117092401153', '1628232117025292290', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:02', NULL);
INSERT INTO `tbl_order` VALUES ('1628232118178725890', '1628232118115811330', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:03', NULL);
INSERT INTO `tbl_order` VALUES ('1628232119210524674', '1628232119210524674', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:03', NULL);
INSERT INTO `tbl_order` VALUES ('1628232120561090562', '1628232120561090562', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:03', NULL);
INSERT INTO `tbl_order` VALUES ('1628232121798410242', '1628232121798410241', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:04', NULL);
INSERT INTO `tbl_order` VALUES ('1628232123027341314', '1628232123027341314', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:04', NULL);
INSERT INTO `tbl_order` VALUES ('1628232124117860354', '1628232124117860354', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:04', NULL);
INSERT INTO `tbl_order` VALUES ('1628232125216768002', '1628232125216768001', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:04', NULL);
INSERT INTO `tbl_order` VALUES ('1628232126642831362', '1628232126441504769', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:05', NULL);
INSERT INTO `tbl_order` VALUES ('1628232127796264962', '1628232127796264961', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:05', NULL);
INSERT INTO `tbl_order` VALUES ('1628232128962281473', '1628232128962281473', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:05', NULL);
INSERT INTO `tbl_order` VALUES ('1628232130182823938', '1628232130119909378', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:06', NULL);
INSERT INTO `tbl_order` VALUES ('1628232131214622722', '1628232131214622721', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:06', NULL);
INSERT INTO `tbl_order` VALUES ('1628232132368056322', '1628232132305141762', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:06', NULL);
INSERT INTO `tbl_order` VALUES ('1628232133399855105', '1628232133399855105', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:06', NULL);
INSERT INTO `tbl_order` VALUES ('1628232134616203266', '1628232134616203266', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:07', NULL);
INSERT INTO `tbl_order` VALUES ('1628232135648002049', '1628232135648002050', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:07', NULL);
INSERT INTO `tbl_order` VALUES ('1628232137065676801', '1628232137002762242', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:07', NULL);
INSERT INTO `tbl_order` VALUES ('1628232138093281281', '1628232138093281282', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:07', NULL);
INSERT INTO `tbl_order` VALUES ('1628232139187994626', '1628232139187994626', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:08', NULL);
INSERT INTO `tbl_order` VALUES ('1628232140286902273', '1628232140219793409', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:08', NULL);
INSERT INTO `tbl_order` VALUES ('1628232141310312449', '1628232141310312450', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:08', NULL);
INSERT INTO `tbl_order` VALUES ('1628232142535049217', '1628232142535049217', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:09', NULL);
INSERT INTO `tbl_order` VALUES ('1628232143566848001', '1628232143566848002', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:09', NULL);
INSERT INTO `tbl_order` VALUES ('1628232144699310081', '1628232144699310081', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:09', NULL);
INSERT INTO `tbl_order` VALUES ('1628232145856937985', '1628232145794023425', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:09', NULL);
INSERT INTO `tbl_order` VALUES ('1628232147018760193', '1628232146955845634', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:10', NULL);
INSERT INTO `tbl_order` VALUES ('1628232148054753282', '1628232148054753281', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:10', NULL);
INSERT INTO `tbl_order` VALUES ('1628232149212381186', '1628232149212381186', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:10', NULL);
INSERT INTO `tbl_order` VALUES ('1628232150239985666', '1628232150239985665', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:10', NULL);
INSERT INTO `tbl_order` VALUES ('1628232151334699010', '1628232151271784449', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:11', NULL);
INSERT INTO `tbl_order` VALUES ('1628232152362303490', '1628232152362303489', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:11', NULL);
INSERT INTO `tbl_order` VALUES ('1628232153519931394', '1628232153519931394', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:11', NULL);
INSERT INTO `tbl_order` VALUES ('1628232154614644737', '1628232154614644738', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:11', NULL);
INSERT INTO `tbl_order` VALUES ('1628232155973599233', '1628232155906490369', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:12', NULL);
INSERT INTO `tbl_order` VALUES ('1628232157198336001', '1628232157131227137', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:12', NULL);
INSERT INTO `tbl_order` VALUES ('1628232158355963905', '1628232158355963906', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:12', NULL);
INSERT INTO `tbl_order` VALUES ('1628232159509397505', '1628232159446482946', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:13', NULL);
INSERT INTO `tbl_order` VALUES ('1628232160604110850', '1628232160604110849', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:13', NULL);
INSERT INTO `tbl_order` VALUES ('1628232161757544450', '1628232161694629890', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:13', NULL);
INSERT INTO `tbl_order` VALUES ('1628232162860646402', '1628232162860646402', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:13', NULL);
INSERT INTO `tbl_order` VALUES ('1628232163946971138', '1628232163946971137', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:14', NULL);
INSERT INTO `tbl_order` VALUES ('1628232165108793345', '1628232165108793346', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:14', NULL);
INSERT INTO `tbl_order` VALUES ('1628232166526468097', '1628232166459359234', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:14', NULL);
INSERT INTO `tbl_order` VALUES ('1628232167629570050', '1628232167629570049', '1622243684553383938', '1', 1, 99.00, '99', '123', 1, '2023-02-22 11:16:15', NULL);

-- ----------------------------
-- Table structure for tbl_post
-- ----------------------------
DROP TABLE IF EXISTS `tbl_post`;
CREATE TABLE `tbl_post`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'TEXT内容',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者ID',
  `images` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片id集，;分割',
  `groupId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '圈子ID',
  `create_time` datetime(0) NOT NULL COMMENT '发布时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '帖子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_post
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_post_tag
-- ----------------------------
DROP TABLE IF EXISTS `tbl_post_tag`;
CREATE TABLE `tbl_post_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签ID',
  `topic_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '话题ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tag_id`(`tag_id`) USING BTREE,
  INDEX `topic_id`(`topic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '话题-标签 中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_post_tag
-- ----------------------------
INSERT INTO `tbl_post_tag` VALUES (36, '1332650453377708034', '1332650453142827009');
INSERT INTO `tbl_post_tag` VALUES (37, '1332681213568589825', '1332681213400817665');
INSERT INTO `tbl_post_tag` VALUES (38, '1332681213631504385', '1332681213400817665');
INSERT INTO `tbl_post_tag` VALUES (39, '1332682473218744321', '1332682473151635458');
INSERT INTO `tbl_post_tag` VALUES (40, '1332913064463794178', '1332913064396685314');
INSERT INTO `tbl_post_tag` VALUES (41, '1332913064530903041', '1332913064396685314');
INSERT INTO `tbl_post_tag` VALUES (42, '1333432347107143681', '1333432347031646209');
INSERT INTO `tbl_post_tag` VALUES (43, '1333432347107143682', '1333432347031646209');
INSERT INTO `tbl_post_tag` VALUES (44, '1333447953697177602', '1333447953558765569');
INSERT INTO `tbl_post_tag` VALUES (45, '1332913064463794178', '1333668258587750401');
INSERT INTO `tbl_post_tag` VALUES (46, '1333676096320106498', '1333676096156528641');
INSERT INTO `tbl_post_tag` VALUES (47, '1333695976742268930', '1333695976536748034');
INSERT INTO `tbl_post_tag` VALUES (48, '1334481725519429634', '1334481725322297346');
INSERT INTO `tbl_post_tag` VALUES (49, '1333447953697177602', '1335149981733449729');
INSERT INTO `tbl_post_tag` VALUES (50, '1349362401597775874', '1349362401438392322');
INSERT INTO `tbl_post_tag` VALUES (51, '1349631541306732545', '1349631541260595202');
INSERT INTO `tbl_post_tag` VALUES (52, '1619966016583741441', '1619966016453718017');
INSERT INTO `tbl_post_tag` VALUES (53, '1619966016583741441', '1620364064451272705');
INSERT INTO `tbl_post_tag` VALUES (54, '1619966016583741441', '1620364703050838017');

-- ----------------------------
-- Table structure for tbl_product
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product`;
CREATE TABLE `tbl_product`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品标题',
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品封面',
  `categoryId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品种类',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `specialPrice` decimal(10, 2) NOT NULL COMMENT '优惠价',
  `stock` int(10) NOT NULL COMMENT '库存',
  `limitCount` int(10) NOT NULL COMMENT '限购',
  `sales` int(10) NOT NULL COMMENT '销量',
  `images` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详情图片，;分割',
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '状态',
  `createTime` datetime(0) NOT NULL COMMENT '发布时间',
  `modifyTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_product
-- ----------------------------
INSERT INTO `tbl_product` VALUES ('1', '名称', '标题', '简介', 'http://localhost:8000/image/fa20f6cd-75b1-4457-9ba0-2e8322ec2b82i.jpg', '1', 10000.00, 9999.99, 600, 10, 0, 'http://localhost:8000/image/fa20f6cd-75b1-4457-9ba0-2e8322ec2b82i.jpg;http://localhost:8000/image/fa20f6cd-75b1-4457-9ba0-2e8322ec2b82i.jpg', 1, '2023-02-07 15:45:53', NULL);

-- ----------------------------
-- Table structure for tbl_promotion
-- ----------------------------
DROP TABLE IF EXISTS `tbl_promotion`;
CREATE TABLE `tbl_promotion`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告标题',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告链接',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '广告推广表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_promotion
-- ----------------------------
INSERT INTO `tbl_promotion` VALUES (1, '开发者头条', 'https://juejin.cn/', '开发者头条');
INSERT INTO `tbl_promotion` VALUES (2, '并发编程网', 'https://juejin.cn/', '并发编程网');
INSERT INTO `tbl_promotion` VALUES (3, '掘金', 'https://juejin.cn/', '掘金');

-- ----------------------------
-- Table structure for tbl_shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `tbl_shopping_cart`;
CREATE TABLE `tbl_shopping_cart`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `productId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `count` int(10) NOT NULL DEFAULT 1 COMMENT '数量',
  `createTime` datetime(0) NOT NULL COMMENT '发布时间',
  `modifyTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_shopping_cart
-- ----------------------------
INSERT INTO `tbl_shopping_cart` VALUES ('1627216282869628929', '1622243684553383938', '1', 8, '2023-02-19 15:59:29', NULL);

-- ----------------------------
-- Table structure for tbl_super_group
-- ----------------------------
DROP TABLE IF EXISTS `tbl_super_group`;
CREATE TABLE `tbl_super_group`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `total` int(10) NOT NULL DEFAULT 0 COMMENT '总圈子数',
  `create_time` datetime(0) NOT NULL COMMENT '发布时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '圈子类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_super_group
-- ----------------------------
INSERT INTO `tbl_super_group` VALUES ('1643630246360805377', '热门', 0, '2023-04-05 23:02:42', '2023-04-05 23:02:42');
INSERT INTO `tbl_super_group` VALUES ('1643630505363271682', '狗狗', 0, '2023-04-05 23:03:44', '2023-04-05 23:03:44');
INSERT INTO `tbl_super_group` VALUES ('1643630582769152002', '猫猫', 0, '2023-04-05 23:04:03', '2023-04-05 23:04:03');
INSERT INTO `tbl_super_group` VALUES ('1643630613379182594', '其他', 0, '2023-04-05 23:04:10', '2023-04-05 23:04:10');

-- ----------------------------
-- Table structure for tbl_tag
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tag`;
CREATE TABLE `tbl_tag`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标签',
  `topic_count` int(11) NOT NULL DEFAULT 0 COMMENT '关联话题',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_tag
-- ----------------------------
INSERT INTO `tbl_tag` VALUES ('1332650453377708034', 'java', 1);
INSERT INTO `tbl_tag` VALUES ('1332681213568589825', 'css', 1);
INSERT INTO `tbl_tag` VALUES ('1332681213631504385', 'mongodb', 1);
INSERT INTO `tbl_tag` VALUES ('1332682473218744321', 'python', 1);
INSERT INTO `tbl_tag` VALUES ('1332913064463794178', 'vue', 2);
INSERT INTO `tbl_tag` VALUES ('1332913064530903041', 'react', 1);
INSERT INTO `tbl_tag` VALUES ('1333432347107143681', 'node', 1);
INSERT INTO `tbl_tag` VALUES ('1333432347107143682', 'mysql', 1);
INSERT INTO `tbl_tag` VALUES ('1333447953697177602', 'flask', 2);
INSERT INTO `tbl_tag` VALUES ('1333676096320106498', 'spring', 1);
INSERT INTO `tbl_tag` VALUES ('1333695976742268930', 'django', 1);
INSERT INTO `tbl_tag` VALUES ('1334481725519429634', 'security', 1);
INSERT INTO `tbl_tag` VALUES ('1349362401597775874', 'tensorflow', 1);
INSERT INTO `tbl_tag` VALUES ('1349631541306732545', 'pytorch', 1);
INSERT INTO `tbl_tag` VALUES ('1619966016583741441', '测试', 3);

-- ----------------------------
-- Table structure for tbl_tip
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tip`;
CREATE TABLE `tbl_tip`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '作者',
  `type` tinyint(4) NOT NULL COMMENT '1：使用，0：过期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '每日赠言' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_tip
-- ----------------------------
INSERT INTO `tbl_tip` VALUES (1, '多锉出快锯，多做长知识。', '佚名', 1);
INSERT INTO `tbl_tip` VALUES (2, '未来总留着什么给对它抱有信心的人。', '佚名', 1);
INSERT INTO `tbl_tip` VALUES (3, '一个人的智慧不够用，两个人的智慧用不完。', '谚语', 1);
INSERT INTO `tbl_tip` VALUES (4, '十个指头按不住十个跳蚤', '傣族', 1);
INSERT INTO `tbl_tip` VALUES (5, '言不信者，行不果。', '墨子', 1);
INSERT INTO `tbl_tip` VALUES (6, '攀援而登，箕踞而遨，则几数州之土壤，皆在衽席之下。', '柳宗元', 1);
INSERT INTO `tbl_tip` VALUES (7, '美德大都包含在良好的习惯之内。', '帕利克', 1);
INSERT INTO `tbl_tip` VALUES (8, '人有不及，可以情恕。', '《晋书》', 1);
INSERT INTO `tbl_tip` VALUES (9, '明·吴惟顺', '法不传六耳', 1);
INSERT INTO `tbl_tip` VALUES (10, '真正的朋友应该说真话，不管那话多么尖锐。', '奥斯特洛夫斯基', 1);
INSERT INTO `tbl_tip` VALUES (11, '时间是一切财富中最宝贵的财富。', '德奥弗拉斯多', 1);
INSERT INTO `tbl_tip` VALUES (12, '看人下菜碟', '民谚', 1);
INSERT INTO `tbl_tip` VALUES (13, '如果不是怕别人反感，女人决不会保持完整的严肃。', '拉罗什福科', 1);
INSERT INTO `tbl_tip` VALUES (14, '爱是春暖花开时对你满满的笑意', '佚名', 1);
INSERT INTO `tbl_tip` VALUES (15, '希望是坚韧的拐杖，忍耐是旅行袋，携带它们，人可以登上永恒之旅。', '罗素', 1);
INSERT INTO `tbl_tip` VALUES (18, '天国般的幸福，存在于对真爱的希望。', '佚名', 1);
INSERT INTO `tbl_tip` VALUES (19, '我们现在必须完全保持党的纪律，否则一切都会陷入污泥中。', '马克思', 1);
INSERT INTO `tbl_tip` VALUES (20, '在科学上没有平坦的大道，只有不畏劳苦沿着陡峭山路攀登的人，才有希望达到光辉的顶点。', '马克思', 1);
INSERT INTO `tbl_tip` VALUES (21, '懒惰的马嫌路远', '蒙古', 1);
INSERT INTO `tbl_tip` VALUES (22, '别忘记热水是由冷水烧成的', '非洲', 1);

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `score` int(11) NOT NULL DEFAULT 0 COMMENT '积分',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'token',
  `bio` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否激活，1：是，0：否',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态，1：使用，0：停用',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '用户角色',
  `create_time` datetime(0) NOT NULL COMMENT '加入时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name`(`username`) USING BTREE,
  INDEX `user_email`(`email`) USING BTREE,
  INDEX `user_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1349290158897311745', 'admin', 'admin', '$2a$10$8qx711TBg/2hxfL7N.sxf.0ROMhR/iuPhQx33IFqGd7PLgt5nGJTO', 'https://s3.ax1x.com/2020/12/01/DfHNo4.jpg', '23456@qq.com', NULL, 2, '', '自由职业者', b'1', b'1', NULL, '2021-01-13 17:40:17', NULL);
INSERT INTO `tbl_user` VALUES ('1349618748226658305', 'zhangsan', 'zhangsan', '$2a$10$7K3yYv8sMV5Xsc2facXTcuyDo8JQ4FJHvjZ7qtWYcJdei3Q6Fvqdm', 'https://s3.ax1x.com/2020/12/01/DfHNo4.jpg', '23456@qq.com', NULL, 0, '', '自由职业者', b'1', b'1', NULL, '2021-01-14 15:25:59', NULL);
INSERT INTO `tbl_user` VALUES ('1619965295545135105', 'user01', 'user01', 'b75705d7e35e7014521a46b532236ec3', 'https://s3.ax1x.com/2020/12/01/DfHNo4.jpg', 'user01@user01.com', NULL, 3, '', '自由职业者', b'1', b'1', NULL, '2023-01-30 15:46:39', NULL);
INSERT INTO `tbl_user` VALUES ('1622243684553383938', 'user02', 'user02', '8bd108c8a01a892d129c52484ef97a0d', 'https://s3.ax1x.com/2020/12/01/DfHNo4.jpg', 'user02@user02.com', '13138500758', 0, '', '自由职业者', b'1', b'1', NULL, '2023-02-05 22:40:09', NULL);

SET FOREIGN_KEY_CHECKS = 1;
