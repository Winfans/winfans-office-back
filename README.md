# winfans-office-back

> 这是一个云办公系统的后端项目。它是基于maven构建的Java前后端项目，其包含了 Springboot & Spring & Spring Mvc & Spring Data Jpa & MySQL

[前端项目](https://github.com/Winfans/winfans-office-front)

[线上地址](http://office.wffanshao.top)

目前版本为 `v1.0.0`,仅用于学习使用

## 项目描述
1.该项目是作者本人大学的期末作业，为了方便大家参考和学习，便在此开源，但由于时间关系`v1.0.0`仅实现了部分功能。

2.该项目是基于maven构建的springboot项目，遵循restful开发规范，前后端之间通过json数据进行交互。

3.云办公系统实现了基本的用户登录注册功能，有系统管理员、普通用户、团队成员、团队管理员四种角色（普通用户、团队成员、团队管理员统称用户）。用户和管理员有相对应的系统，没有对应权限的角色无法通过后端的拦截而进行资源访问，用户系统中包含了CRM系统、团队管理系统，管理员系统中仅实现了用户管理系统。

## 项目启动
下载该项目后，进入到 office/src/main/java/top/wffanshao/office/ 目录下，找到Springboot启动类，运行即可



