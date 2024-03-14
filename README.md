# springboot-crud-templete

✨ **基于 Java SpringBoot3, Mysql, Redis 的信息管理系统**：实现信息的增删改查，文件的对象存储，权限管理，令牌的签验。

[个人网站：www.bytesc.top](http://www.bytesc.top) 含轻量化版本在线演示

[个人博客：blog.bytesc.top](http://blog.bytesc.top)

🔔 如有项目相关问题，欢迎在本项目提出`issue`，我一般会在 24 小时内回复。

## 功能简介

- 信息的增删改查
- 文件的对象存储
- 权限管理，令牌的签验

## 如何使用

### 环境

- Java21
- maven-3.9.6
- docker 24.0.6
- mysql 8.0.31
- redis 7.2.4

### Mysql

登录mysql
```bash
mysql –uroot –p123456
# 用户名和密码根据实际情况替换
```

创建数据库，执行创建数据库脚本
```sql
create database java_crud_start;
use java_crud_start;
source ./db/database.sql;
```

根据实际情况填写配置文件: `.\src\main\resources\application.yml`
```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/java_crud_start
    username: root
    password: 123456
```

### Redis

```bash
docker run --name myredis -it -p 6379:6379 -v /data/redis-data  redis --requirepass "123456"
```

根据实际情况填写配置文件: `.\src\main\resources\application.yml`
```yml
  data:
    redis:
      host: 127.0.0.1
      port: 6379
      password: 123456
```

### 阿里云OSS文件对象存储

如果无需使用文件上传功能，可忽略此配置。

相关服务获取：[阿里云oss](https://oss.console.aliyun.com/overview)

根据实际情况填写配置文件: `.\src\main\resources\application.yml`
```yml
oss:
  key-id:
  key-secret:
  bucket-name:
  end-point:
```

### 启动项目

```bash
mvn spring-boot:run

# mvn package -DskipTests
#  java -jar .\helloworld-1.0-SNAPSHOT.jar
```


## 接口说明

./posyman-api/spring.postman_collection.json




