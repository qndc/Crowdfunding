# Crowdfunding
基于SSM+Quartz+Activiti5的众筹网站

Atcrowdfunding-bean 	实体类工程

Atcrowdfunding-common	工具类工程

Atcrowdfunding-main	web工程，存放 前后台页面及拦截器和配置文件

Atcrowdfunding-manager-api	后台dao、service

Atcrowdfunding-manager-impl	后台controller、serviceImpl

Atcrowdfunding-parent	父工程，pom依赖管理

Atcrowdfunding-potal-api	前台dao、service

Atcrowdfunding-potal-impl	前台controller、serviceImpl

# 项目主要功能

1. 后台管理系统
   1. 用户管理：
      1. 普通用户管理
      2. 管理员管理
   2. 认证管理：Activiti5流程框架
      1. 实名认证审批：
      2. 项目审批：
         1. 阿里云短信通知
         2. 定时任务开启：
            1. 项目（通过时间+众筹天数）到期核算众筹金额是否到达制定标准
            2. 成功向发起公司打款
            3. 失败自动向支持者退款
   3. 权限管理：RBAC权限模型
   4. 广告管理：
   5. 参数管理：
   6. 订单管理
2. 前台
   1. 登录、注册：阿里云短信验证码
   2. 门户项目展示：
   3. 项目详情：
   4. 支持项目：支付宝支付、退款
   5. 认证：
      1. 实名认证流程：
         1. 基本信息
         2. 资质图片上传
         3. 邮件发送
         4. 邮箱验证
      2. 项目发起流程：
         1. 项目基本信息
         2. 项目回报
         3. 项目所属公司
            1. 采用已认证的公司信息
            2. 委托书
   6. 我的资产
      1. 我的支持
      2. 我的关注
      3. 我的发起
