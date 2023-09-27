# 基于SpringCloud的抽奖系统
## 介绍
&#160;&#160;本项目是一个基于SpringCloud的抽奖系统，前端框架使用Vue + element Ui，后端框架使用Spring Boot、Spring Cloud、Spring Cloud Alibaba、Mybatis plus

## 微服务架构技术选型
|   注册中心  |   nacos   |

|   网 关    |   gateway |

| 声明式客户端 | OpenFeign |

|   服务熔断  |  Hystrix  |


## 模块介绍
|       模块名         |   模块介绍    |        端口        | 启动顺序 |
| :-----------------: | :----------: |:----------------:|:----:|
|      nacos集群       |   服务注册    |  8848 8850 8852  |  1   |
|      gateway        |     网关      |        80        |  2   |
|     service-user集群 |  用户管理服    |    8770 8771     |  3   |
|  service-lottery集群 |    抽奖服务   |    8772 8773     |  4   |
|      common         |    公共模块    |        无         |  无   |
|       myVue         |    前端模块    |       8080       |  5   |
