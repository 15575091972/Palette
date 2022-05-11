/*
Navicat MySQL Data Transfer

Source Server         : Palette
Source Server Version : 80028
Source Host           : localhost:3306
Source Database       : palette_db

Target Server Type    : MYSQL
Target Server Version : 80028
File Encoding         : 65001

Date: 2022-04-10 01:19:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for art
-- ----------------------------
DROP TABLE IF EXISTS `art`;
CREATE TABLE `art` (
  `art_id` bigint NOT NULL COMMENT '艺术品ID',
  `art_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '艺术品名称',
  `art_file` bigint DEFAULT NULL COMMENT '电子艺术品源文件',
  `art_type` int NOT NULL COMMENT '艺术品类型 0：电子图片艺术品 1：实体艺术品 2:音乐 3：影视 4：游戏 5：模型 6：绘画',
  `art_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '艺术品简介',
  `art_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '在区块链中的token',
  `art_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '佚名' COMMENT '艺术品作者',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`art_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='电子艺术品表';

-- ----------------------------
-- Table structure for art_check
-- ----------------------------
DROP TABLE IF EXISTS `art_check`;
CREATE TABLE `art_check` (
  `check_id` bigint NOT NULL COMMENT '检查ID',
  `art_id` bigint NOT NULL COMMENT '审查艺术品ID',
  `hava_pass` tinyint(1) DEFAULT NULL COMMENT '是否通过审查',
  `check_result` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '审查意见',
  `checker` bigint NOT NULL COMMENT '审核人',
  `end_time` datetime NOT NULL COMMENT '审核截止时间',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`check_id`),
  KEY `foreign_check_art_id` (`art_id`),
  CONSTRAINT `foreign_check_art_id` FOREIGN KEY (`art_id`) REFERENCES `art` (`art_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='艺术品检查';

-- ----------------------------
-- Table structure for art_comment
-- ----------------------------
DROP TABLE IF EXISTS `art_comment`;
CREATE TABLE `art_comment` (
  `art_id` bigint NOT NULL COMMENT '艺术品ID',
  `comment_id` bigint NOT NULL COMMENT '评论ID',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`art_id`,`comment_id`),
  KEY `foreign_art_commnet_commnet_id` (`comment_id`),
  CONSTRAINT `foreign_art_commnet_art_id` FOREIGN KEY (`art_id`) REFERENCES `art` (`art_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_art_commnet_commnet_id` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='艺术品评论区';

-- ----------------------------
-- Table structure for art_label
-- ----------------------------
DROP TABLE IF EXISTS `art_label`;
CREATE TABLE `art_label` (
  `art_id` bigint NOT NULL COMMENT '艺术品ID',
  `art_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`art_id`,`art_label`),
  CONSTRAINT `foreign_label_art_id` FOREIGN KEY (`art_id`) REFERENCES `art` (`art_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='艺术品标签';

-- ----------------------------
-- Table structure for art_show_file
-- ----------------------------
DROP TABLE IF EXISTS `art_show_file`;
CREATE TABLE `art_show_file` (
  `art_id` bigint NOT NULL COMMENT '艺术品ID',
  `file_id` bigint NOT NULL DEFAULT '2' COMMENT '文件Id',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`art_id`,`file_id`),
  KEY `foreign_show_file_id` (`file_id`),
  CONSTRAINT `foreign_show_art_id` FOREIGN KEY (`art_id`) REFERENCES `art` (`art_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_show_file_id` FOREIGN KEY (`file_id`) REFERENCES `file` (`file_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='艺术品展示文件，用于实体艺术品的展示文件';

-- ----------------------------
-- Table structure for auction
-- ----------------------------
DROP TABLE IF EXISTS `auction`;
CREATE TABLE `auction` (
  `auction_id` bigint NOT NULL COMMENT '拍卖ID',
  `auction_room_id` int NOT NULL COMMENT '拍卖房间号码',
  `auction_goods_id` bigint NOT NULL COMMENT '拍卖商品',
  `base_price` decimal(10,2) NOT NULL COMMENT '拍卖基础价',
  `min_addprice` decimal(10,2) NOT NULL COMMENT '每次最少加价',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `top_price` decimal(10,2) NOT NULL COMMENT '当前价格',
  `have_stay` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否留拍',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`auction_id`),
  KEY `foreign_auction_goods_id` (`auction_goods_id`),
  CONSTRAINT `foreign_auction_goods_id` FOREIGN KEY (`auction_goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='拍卖表';

-- ----------------------------
-- Table structure for auction_addprice
-- ----------------------------
DROP TABLE IF EXISTS `auction_addprice`;
CREATE TABLE `auction_addprice` (
  `aution_addprice_id` bigint NOT NULL COMMENT '加价记录ID',
  `aution_id` bigint NOT NULL COMMENT '参与的拍卖场次',
  `bid_price` decimal(10,2) NOT NULL COMMENT '喊价，即当前价格',
  `user_id` bigint NOT NULL COMMENT '喊价人',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`aution_addprice_id`),
  KEY `foreign_addprice_aution_id` (`aution_id`),
  KEY `foreign_addprice_user_id` (`user_id`),
  CONSTRAINT `foreign_addprice_aution_id` FOREIGN KEY (`aution_id`) REFERENCES `auction` (`auction_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_addprice_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='拍卖的加价表';

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `chat_id` bigint NOT NULL COMMENT '聊天记录ID',
  `is_file` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为文件',
  `sender` bigint NOT NULL COMMENT '聊天发送者',
  `recipient` bigint NOT NULL COMMENT '聊天接收者',
  `chat_content` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '聊天内容，如果时文件，内容保存问文件ID',
  `file_id` bigint DEFAULT NULL COMMENT '如果发送为文件是，拥有文件值',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`chat_id`),
  KEY `foreign_chat_sender_id` (`sender`),
  KEY `foreign_char_recipient_id` (`recipient`),
  KEY `foreign_chat_file_id` (`file_id`),
  CONSTRAINT `foreign_char_recipient_id` FOREIGN KEY (`recipient`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_chat_file_id` FOREIGN KEY (`file_id`) REFERENCES `file` (`file_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_chat_sender_id` FOREIGN KEY (`sender`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='聊天内容表，聊天内容可以为文字，也可能为文件';

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` bigint NOT NULL COMMENT '评论ID',
  `comment_content` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '评论内容',
  `commnetator_id` bigint NOT NULL COMMENT '评论者',
  `comment_reply` bigint DEFAULT NULL COMMENT '回复(对于另一条评论）',
  `comment_like` int NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`comment_id`),
  KEY `comment_commnetator_id` (`commnetator_id`),
  CONSTRAINT `comment_commnetator_id` FOREIGN KEY (`commnetator_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='评论表';

-- ----------------------------
-- Table structure for comment_like
-- ----------------------------
DROP TABLE IF EXISTS `comment_like`;
CREATE TABLE `comment_like` (
  `comment_id` bigint NOT NULL COMMENT '评论ID',
  `like_user_id` bigint NOT NULL COMMENT '点赞者',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`comment_id`,`like_user_id`),
  KEY `comment_like_user_id` (`like_user_id`),
  CONSTRAINT `comment_like_id` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_like_user_id` FOREIGN KEY (`like_user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='评论点赞记录';

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `file_id` bigint NOT NULL DEFAULT '2' COMMENT '文件Id',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件名称',
  `file_format` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件格式（.txt .png）',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件保存路径',
  `file_hash` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='文件表（保存各类文件，如艺术品的文件，上传的头像的文件，其他文件等）';

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` bigint NOT NULL COMMENT '商品ID',
  `art_id` bigint NOT NULL COMMENT '商品艺术品ID',
  `trade_mode` int NOT NULL COMMENT '交易模式（1:所有权买卖 2:租赁）',
  `goods_quantity` decimal(3,2) NOT NULL COMMENT '商品数量（所有权：0.01-1.00 租赁 1）',
  `goods_seller_id` bigint NOT NULL COMMENT '商品卖家ID',
  `rent_start_time` datetime DEFAULT NULL COMMENT '如果trade_mode为2，记录租赁开始时间',
  `rent_end_time` datetime DEFAULT NULL COMMENT '如果trade_mode为2，记录租赁结束时间',
  `trade_fun` int NOT NULL DEFAULT '1' COMMENT '交易方法 1：零售 2: 拍卖 3：盲盒',
  `resale_price` decimal(10,2) DEFAULT NULL COMMENT '零售价',
  `selled` tinyint(1) NOT NULL COMMENT '是否被售出',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`goods_id`),
  KEY `foreign_goods_art_id` (`art_id`),
  KEY `foreign_goods_seller_id` (`goods_seller_id`),
  CONSTRAINT `foreign_goods_art_id` FOREIGN KEY (`art_id`) REFERENCES `art` (`art_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_goods_seller_id` FOREIGN KEY (`goods_seller_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='商品表';

-- ----------------------------
-- Table structure for goods_rent
-- ----------------------------
DROP TABLE IF EXISTS `goods_rent`;
CREATE TABLE `goods_rent` (
  `rent_id` bigint NOT NULL COMMENT '租赁ID',
  `goods_id` bigint NOT NULL,
  `leaseholder_id` bigint NOT NULL COMMENT '租赁人 收钱的一方',
  `tenant_id` bigint NOT NULL COMMENT '租客 出钱的一方',
  `charter_money` decimal(10,2) NOT NULL COMMENT '租金',
  `service_charge` decimal(10,2) NOT NULL COMMENT '手续费',
  `trade_type` int NOT NULL COMMENT '交易方式（1:零售 2:拍卖 3:盲盒）',
  `type_id` bigint NOT NULL COMMENT '如果为非零售，存储其方式ID，如果为零售，存储其商品ID',
  `public_welfare_id` bigint NOT NULL COMMENT '公益',
  `rent_start_time` datetime NOT NULL COMMENT '租期开始时间',
  `rent_end_time` datetime DEFAULT NULL COMMENT '租期结束时间',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`rent_id`),
  KEY `foreign_rent_leaseholder_id` (`leaseholder_id`),
  KEY `foreign_rent_tenant_id` (`tenant_id`),
  KEY `foreign_rent_goods_id` (`goods_id`),
  KEY `foreign_rent_public_welfare_id` (`public_welfare_id`),
  CONSTRAINT `foreign_rent_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_rent_leaseholder_id` FOREIGN KEY (`leaseholder_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_rent_public_welfare_id` FOREIGN KEY (`public_welfare_id`) REFERENCES `public_welfare` (`publicwelfare_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_rent_tenant_id` FOREIGN KEY (`tenant_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='商品租赁';

-- ----------------------------
-- Table structure for goods_trade
-- ----------------------------
DROP TABLE IF EXISTS `goods_trade`;
CREATE TABLE `goods_trade` (
  `trade_id` bigint NOT NULL COMMENT '交易记录ID',
  `goods_id` bigint NOT NULL COMMENT '商品的ID',
  `seller_id` bigint NOT NULL COMMENT '卖方ID',
  `buyer_id` bigint NOT NULL COMMENT '买方ID',
  `coin` decimal(10,2) NOT NULL COMMENT '涉及金额',
  `service_charge` decimal(10,2) NOT NULL COMMENT '手续费',
  `trade_type` int NOT NULL COMMENT '交易方式（1:零售 2:拍卖 3:盲盒）',
  `type_id` bigint NOT NULL COMMENT '如果为非零售，存储其方式ID(拍卖、盲盒)，如果为零售，存储其商品ID',
  `public_welfare_id` bigint NOT NULL COMMENT '公益',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`trade_id`),
  KEY `foreign_trade_seller_id` (`seller_id`),
  KEY `foreign_trade_buyer_id` (`buyer_id`),
  KEY `foreign_trade_goods_id` (`goods_id`),
  KEY `foreign_trade_public_welfare_id` (`public_welfare_id`),
  CONSTRAINT `foreign_trade_buyer_id` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE,
  CONSTRAINT `foreign_trade_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON UPDATE CASCADE,
  CONSTRAINT `foreign_trade_public_welfare_id` FOREIGN KEY (`public_welfare_id`) REFERENCES `public_welfare` (`publicwelfare_id`) ON UPDATE CASCADE,
  CONSTRAINT `foreign_trade_seller_id` FOREIGN KEY (`seller_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='商品交易记录表';

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `location_id` bigint NOT NULL COMMENT '地址ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收货人名字',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `province` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '省级行政区',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '市级行政区',
  `region` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '区级行政区',
  `detailed` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '详细地址',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`location_id`),
  KEY `foreign_location_user_id` (`user_id`),
  CONSTRAINT `foreign_location_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='收货地址（主要是实体艺术品的交易）';

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` bigint NOT NULL COMMENT '日志ID',
  `user_id` bigint DEFAULT NULL COMMENT '此操作的用户',
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户操作',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求方法',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '请求参数',
  `time` bigint DEFAULT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'IP地址',
  `result_code` int DEFAULT NULL COMMENT '返回状态码',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`log_id`),
  KEY `foreign_log_user_id` (`user_id`),
  CONSTRAINT `foreign_log_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='日志表';

-- ----------------------------
-- Table structure for mystery_box
-- ----------------------------
DROP TABLE IF EXISTS `mystery_box`;
CREATE TABLE `mystery_box` (
  `box_id` bigint NOT NULL COMMENT '盲盒ID',
  `box_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `box_price` decimal(10,2) NOT NULL COMMENT '盲盒价格',
  `box_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '盲盒简介',
  `open_box_time` datetime NOT NULL COMMENT '盲盒开盒时间',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`box_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='盲盒';

-- ----------------------------
-- Table structure for mystery_box_goods
-- ----------------------------
DROP TABLE IF EXISTS `mystery_box_goods`;
CREATE TABLE `mystery_box_goods` (
  `box_id` bigint NOT NULL COMMENT '盲盒ID',
  `goods_id` bigint NOT NULL COMMENT '商品ID',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`box_id`,`goods_id`),
  KEY `foreign_box_goods_goods_id` (`goods_id`),
  CONSTRAINT `foreign_box_goods_box_id` FOREIGN KEY (`box_id`) REFERENCES `mystery_box` (`box_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_box_goods_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='盲盒内容商品';

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` bigint NOT NULL DEFAULT '0' COMMENT '通知ID',
  `notice_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '通知标题',
  `notice_content` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '通知内容',
  `publish_time` datetime NOT NULL COMMENT '通知发布时间',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='通知，只包含通知其本身内容（前端将整个标记语言以文字形式发送到数据库保存）';

-- ----------------------------
-- Table structure for public_welfare
-- ----------------------------
DROP TABLE IF EXISTS `public_welfare`;
CREATE TABLE `public_welfare` (
  `publicwelfare_id` bigint NOT NULL COMMENT '公益ID',
  `publicwelfare_coin` decimal(10,2) NOT NULL COMMENT '捐赠金额（人民币）',
  `publicwelfare_object` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '公益对象',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`publicwelfare_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='公益记录表';

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `report_id` bigint NOT NULL COMMENT '举报记录ID',
  `report_object_type` int NOT NULL COMMENT '举报对象类型（1:用户  2:聊天记录 3:商品 4:交易记录 5:测试问题 6:拍卖 7:盲盒 8:其他）',
  `report_object` bigint NOT NULL COMMENT '举报对象',
  `report_content` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '举报内容',
  `reporter` bigint NOT NULL COMMENT '举报者',
  `report_result` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '举报结果',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='举报记录列表';

-- ----------------------------
-- Table structure for report_evidence
-- ----------------------------
DROP TABLE IF EXISTS `report_evidence`;
CREATE TABLE `report_evidence` (
  `report_id` bigint NOT NULL COMMENT '举报记录ID',
  `report_evidence_file` bigint NOT NULL COMMENT '举报证据',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`report_id`,`report_evidence_file`),
  KEY `foreign_evidence_file_id` (`report_evidence_file`),
  CONSTRAINT `foreign_evidence_file_id` FOREIGN KEY (`report_evidence_file`) REFERENCES `file` (`file_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_evidence_report_id` FOREIGN KEY (`report_id`) REFERENCES `report` (`report_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='举报证据（包括各种文件，如图片、视频等）';

-- ----------------------------
-- Table structure for search
-- ----------------------------
DROP TABLE IF EXISTS `search`;
CREATE TABLE `search` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `search_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '搜索内容',
  `search_num` int NOT NULL DEFAULT '0' COMMENT '搜索次数',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`,`search_text`),
  CONSTRAINT `search_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='用户搜索记录';

-- ----------------------------
-- Table structure for shopping_car
-- ----------------------------
DROP TABLE IF EXISTS `shopping_car`;
CREATE TABLE `shopping_car` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `goods_id` bigint NOT NULL COMMENT '商品ID',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`,`goods_id`,`create_time`),
  KEY `foreign_shopping_car_goods_id` (`goods_id`),
  CONSTRAINT `foreign_shopping_car_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON UPDATE CASCADE,
  CONSTRAINT `foreign_shopping_car_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='购物车';

-- ----------------------------
-- Table structure for take_exam
-- ----------------------------
DROP TABLE IF EXISTS `take_exam`;
CREATE TABLE `take_exam` (
  `test_paper_id` bigint NOT NULL COMMENT '试卷号',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `results` int DEFAULT NULL,
  `hadn_advance` tinyint(1) DEFAULT '0' COMMENT '是否提前交卷',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`test_paper_id`,`user_id`),
  KEY `take_exam_user_id` (`user_id`),
  CONSTRAINT `take_exam_paper_id` FOREIGN KEY (`test_paper_id`) REFERENCES `test_paper` (`test_paper_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `take_exam_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='参加考试记录表';

-- ----------------------------
-- Table structure for test_paper
-- ----------------------------
DROP TABLE IF EXISTS `test_paper`;
CREATE TABLE `test_paper` (
  `test_paper_id` bigint NOT NULL COMMENT '试卷号',
  `test_paper_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '试卷名称',
  `test_paper_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '试卷简介',
  `test_paper_start_time` datetime NOT NULL COMMENT '试卷开始时间',
  `test_paper_end_time` datetime NOT NULL COMMENT '考试结束时间',
  `had_advance` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否允许提前交卷',
  `questions_num` int NOT NULL COMMENT '试卷题目数',
  `max_results` int NOT NULL COMMENT '满分值',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '答案，试卷发下去时生成',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`test_paper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='试卷表';

-- ----------------------------
-- Table structure for test_paper_problem
-- ----------------------------
DROP TABLE IF EXISTS `test_paper_problem`;
CREATE TABLE `test_paper_problem` (
  `problem_id` bigint NOT NULL COMMENT '问题ID',
  `test_paper_id` bigint NOT NULL COMMENT '试卷号',
  `score` int DEFAULT NULL COMMENT '分值',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`problem_id`,`test_paper_id`),
  KEY `test_paper_id` (`test_paper_id`),
  CONSTRAINT `test_paper_id` FOREIGN KEY (`test_paper_id`) REFERENCES `test_paper` (`test_paper_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `test_paper_problem_id` FOREIGN KEY (`problem_id`) REFERENCES `test_questions` (`problem_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='试卷试题表';

-- ----------------------------
-- Table structure for test_questions
-- ----------------------------
DROP TABLE IF EXISTS `test_questions`;
CREATE TABLE `test_questions` (
  `problem_id` bigint NOT NULL COMMENT '问题ID',
  `problem_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '问题描述',
  `disturb_a` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '干扰选项A',
  `disturb_b` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '干扰选项B',
  `disturb_c` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '干扰选项C',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '答案',
  `author_id` bigint NOT NULL COMMENT '作者',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`problem_id`),
  KEY `test_question_author_id` (`author_id`),
  CONSTRAINT `test_question_author_id` FOREIGN KEY (`author_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='审核员考察问题';

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token` (
  `token` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `create_time` datetime NOT NULL,
  `deadline_time` datetime NOT NULL,
  KEY `token_user_id` (`user_id`),
  CONSTRAINT `token_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='token表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `user_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户账号',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码，数据库中通过保存通过SHA-256处理过的值',
  `user_phone` char(13) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  `user_head_image_id` bigint DEFAULT NULL COMMENT '用户头像图片文件的ID',
  `skip_course` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已经跳过教程',
  `eth_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '以太坊地址',
  `key_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'key文件名',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  KEY `foreign_user_head_image_id` (`user_head_image_id`),
  CONSTRAINT `foreign_user_head_image_id` FOREIGN KEY (`user_head_image_id`) REFERENCES `file` (`file_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Table structure for user_art_view
-- ----------------------------
DROP TABLE IF EXISTS `user_art_view`;
CREATE TABLE `user_art_view` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `art_id` bigint NOT NULL COMMENT '艺术品ID',
  `view_num` int NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`,`art_id`),
  KEY `view_art_id` (`art_id`),
  CONSTRAINT `view_art_id` FOREIGN KEY (`art_id`) REFERENCES `art` (`art_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `view_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='用户浏览作品次数';

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow` (
  `follower_id` bigint NOT NULL COMMENT '关注者',
  `be_follower_id` bigint NOT NULL COMMENT '被关注者',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`follower_id`,`be_follower_id`),
  KEY `foreign_follow_be_follower_id` (`be_follower_id`),
  CONSTRAINT `foreign_follow_be_follower_id` FOREIGN KEY (`be_follower_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE,
  CONSTRAINT `foreign_follow_follower_id` FOREIGN KEY (`follower_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='关注者';

-- ----------------------------
-- Table structure for user_identity
-- ----------------------------
DROP TABLE IF EXISTS `user_identity`;
CREATE TABLE `user_identity` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `user_identity` int NOT NULL COMMENT '用户身份(1:审核员 2:管理员 3:工作人员)',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`,`user_identity`),
  CONSTRAINT `foreign_odentity_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='身份表';

-- ----------------------------
-- Table structure for user_notice
-- ----------------------------
DROP TABLE IF EXISTS `user_notice`;
CREATE TABLE `user_notice` (
  `notice_id` bigint NOT NULL DEFAULT '0' COMMENT '通知ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `version` int NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `deleted` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`notice_id`,`user_id`),
  KEY `user_notice_user_id` (`user_id`),
  CONSTRAINT `user_notice_notice_id` FOREIGN KEY (`notice_id`) REFERENCES `notice` (`notice_id`) ON UPDATE CASCADE,
  CONSTRAINT `user_notice_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
