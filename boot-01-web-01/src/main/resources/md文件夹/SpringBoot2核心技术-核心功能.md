# SpringBoot2核心技术-核心功能

## 2. web开发
~~~~~
1 - SpringMVC自动配置预览
2 - 简单功能分析
3 - 请求参数处理
4 - 数据响应与内容协商
5 - 视图解析与模板引擎
6 - 拦截器
7 - 跨域
8 - 异常处理
9 - 原生servlet组件
10 - 嵌入式Web容器
11 - 定制化原理
~~~~~
### 2.1. 简单功能分析
> 2.1.1. 静态资源访问

**静态资源目录：**
- /static
- /public
- /resources
- /META-INF/resources
> 图片、视频、js、css等这些资源放到/static or /public or /resources or /META-INF/resources目录中，就可以直接访问根路径来访问静态资源了

访问：http://localhost:8080/xxx.jpg

原理：静态资源映射的是 /**，也就是说拦截所有请求；当请求进来，先去找Controller，如果Controller可以处理，则去处理；如果Controller不能处理，则去找静态资源，如果静态资源不能处理，则返回404

- 静态资源访问前缀

静态资源默认无前缀，我们可以在配置文件中配置一个前缀
```yaml
spring:
  mvc:
    # 访问静态资源添加前缀
    static-path-pattern: /res/**
```
访问的话就需要加上前缀来访问了：http://localhost:8080/res/xxx.xxx

- 改变默认的静态资源路径
```yaml
spring:
  mvc:
    # 访问静态资源添加前缀
    static-path-pattern: /res/**
  web:
    resources:
      # 改变静态资源访问路径
      #static-locations: classpath:/haha/
      # 改变资源访问路径可以使用数组，改变多个资源访问路径
      static-locations: ["classpath:/haha/", "classpath:/abc/"]
#数组中的双引号不是必须的，可以不加，也可以加上
```
- 支持 webjars (webjars.org) 自动映射
```xml
<!-- 添加一个webjars:jquery:1.6.0-->
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.6.0</version>
</dependency>
```
访问路径是：http://localhost:8080/webjars/jquery/3.6.0/xxx.xx

> 2.1.2. 欢迎页支持
- 静态资源路径下 放一个 index.html 页面
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎页</title>
</head>
<body>
<h1>子辰，欢迎您</h1>
</body>
</html>
```
访问路径：http://localhost:8080

![image-index](../image/index.png)

*注意1：* 如果有静态资源访问前缀，这个静态资源欢迎页不能直接访问根路径访问到，（可能使springboot的一个bug吧）
*注意2：* 加上静态资源访问前缀，得使用全路径访问欢迎页，如：http://localhost:8080/res/index.html
![image-index](../image/prefix-index.png)

```yaml
spring:
# 会导致 welcome page 功能失效
#  mvc:
#    # 访问静态资源添加前缀
#    static-path-pattern: /res/**
  web:
    resources:
      # 改变静态资源访问路径
      #static-locations: classpath:/haha/
      # 改变资源访问路径可以使用数组，改变多个资源访问路径
      static-locations: ["classpath:/haha/", "classpath:/abc/"]
```
- controller能处理/index请求的




> 2.1.3. 自定义 Favicon
- 使用图标名字固定 favicon.ico
- 将favicon.ico放到静态资源访问路径下
- 重启
- 访问：http://localhost:8080/hello

![image-favicon](../image/favicon.png)

### 2.2. 请求参数处理
>

>


### 2.3. 数据响应与内容协商
>

>


### 2.4. 视图解析与模板引擎
>

>


### 2.5. 拦截器
>

>


### 2.6. 跨域
>

>


### 2.7. 异常处理
>

>


### 2.8. 原生servlet组件
>

>


### 2.9. 嵌入式Web容器
>

>


### 2.10. 定制化原理
>

>




## 3. 数据访问


## 4. 单元测试


## 5. 指标监控


## 6. 原理解析




