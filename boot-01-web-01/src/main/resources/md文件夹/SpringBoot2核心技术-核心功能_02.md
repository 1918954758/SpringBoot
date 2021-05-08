# SpringBoot2核心技术-核心功能
## 1. 数据响应

-----
> 响应页面

> 数据响应：JSON  XML  xls  图片、音视频..  自定义协议数据

-----
- 响应JSON

1) 需要在pom.xml中引入 *-start-web 场景
```xml
<dependencys>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- web 场景自动引入了json场景 (有的没有自动引入，需要手动引入) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-json</artifactId>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.datatype</groupId>
        <artifactId>jackson-datatype-jdk8</artifactId>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.datatype</groupId>
        <artifactId>jackson-datatype-jsr310</artifactId>
    </dependency>
</dependencys>
```
**测试响应json**
- @Controller
- @ResponseBody
- @xxxMapping("/.../...")
```java
@Controller
public class ResponseTestController {
    @ResponseBody
    @GetMapping("/test/person")
    public Person getPerson() {
        Person person = new Person();
        person.setAge(28);
        person.setBirth(new Date());
        person.setUserName("zhangsan");
        return person;
    }
}
```
- 结果：{"userName":"zhangsan","age":28,"birth":"2021-05-08T08:53:34.106+00:00","pet":null}

## 2. 内容协商


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


### 2.8. 原生servlet组件（原生组件注入）
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




