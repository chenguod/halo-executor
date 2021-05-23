# xxl-job-executor

#### 介绍
1. 主要用于主动推送百度收录所需的url，配置文件都放在nacos上。
2. 用户名admin（pwd:admin）账号是演示账号，无法增删改任务，只有查看权限
3. 未来将会增加谷歌收录定时任务

#### 软件架构
- 主要技术栈：nacos、springboot、mybatis、dom4j等技术。

- 建库语句：

  ```mysql
  /*
  SQLyog Ultimate v12.09 (64 bit)
  MySQL - 8.0.18 : Database - executor
  *********************************************************************
  */
  
  
  /*!40101 SET NAMES utf8 */;
  
  /*!40101 SET SQL_MODE=''*/;
  
  /*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
  /*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
  /*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
  /*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
  CREATE DATABASE /*!32312 IF NOT EXISTS*/`executor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
  
  USE `executor`;
  
  /*Table structure for table `website` */
  
  DROP TABLE IF EXISTS `website`;
  
  CREATE TABLE `website` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `site_map` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '站点地图地址',
    `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '站点',
    `token` varchar(200) NOT NULL COMMENT '百度token',
    `import_time` datetime DEFAULT NULL COMMENT '导入时间',
    `num` int(11) DEFAULT NULL COMMENT '数量',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique` (`url`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
  
  /*Table structure for table `website_detail` */
  
  DROP TABLE IF EXISTS `website_detail`;
  
  CREATE TABLE `website_detail` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `p_id` int(11) NOT NULL COMMENT '需推送网站',
    `url` varchar(200) NOT NULL COMMENT '需提交收录的网址',
    `create_time` date NOT NULL COMMENT '链接生成时间',
    `push_flag` int(11) DEFAULT '0' COMMENT '0 未推送  1 已推送',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique` (`url`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
  
  /*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
  /*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
  /*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
  /*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
  ```

1. 第一步：根据website表每日定时更新detail表中的数据。
2. 第二步：每天晚上10定时向百度推送新增的网址


#### 安装教程

1.  导入数据库脚本
2.  导入nacos数据

#### 使用说明

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
