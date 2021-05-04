## SpringBoot2核心技术-核心功能

### 1. 配置文件
#### 1.1. 简介
- YAML 是“YAML Ain't Markup Language”(YAML不是一种标记语言)的递归缩写。在开发这种语言时，YAML的意思其实是：“Yet Another MarkupLanguage”(仍是一种标记语言)。
- 非常适合用来做以数据中心的配置文件

#### 1.2. 基本语法
- key: value; kv之间有空格
- 大小写敏感
- 使用缩进表示层级关系
- 缩进不允许使用tab，只允许空格
- 缩进的空格数不重要，只要相同层级的元素对齐即可
- '#'表示注释
- ''与""表示字符串内容 表示'转义'与"不转义"

#### 1.3. 数据类型
- 字面量：单个的、不可再分的值。date、boolean、string、number、null
```yaml
k: v
```
- 对象：键值对的集合。map、hash、set、object
```yaml
行内写法: k: {k1: v1,k2: v2, k3: v3}
#或
k:
    k1: v1
    k2: v2
    k3: v3
```
- 数组：一组按次依序排列的值。array、list、queue
```yaml
行内写法：k: [v1, v2, v3]
#或
k:
  - v1
  - v2
  - v3
```

#### 1.4. 配置提示
- 导入配置提示jar包
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```
- 打包的时候排除配置提示jar包
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <!-- 打包的时候排除<excludes></excludes>里面的包 -->
                <excludes>
                    <exclude>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-configuration-processor</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### 2. web开发
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
#### 2.1. 简单功能分析
> 2.1.1. 静态资源访问


> 2.1.2. 欢迎页支持


> 2.1.3. 自定义 Favicon


#### 2.2. 请求参数处理
>

>


#### 2.3. 数据响应与内容协商
>

>


#### 2.4. 视图解析与模板引擎
>

>


#### 2.5. 拦截器
>

>


#### 2.6. 跨域
>

>


#### 2.7. 异常处理
>

>


#### 2.8. 原生servlet组件
>

>


#### 2.9. 嵌入式Web容器
>

>


#### 2.10. 定制化原理
>

>




### 3. 数据访问


### 4. 单元测试


### 5. 指标监控


### 6. 原理解析




