## 更改SpringBoot配置
- 配置文件固定名字 application.properties

- 更改端口号
```properties
server.port=8888
```
#### 我们可以更改哪些配置，需要参考官方文档
https://docs.spring.io/spring-boot/docs/current/reference/html/index.html
    该链接找到 Application Properties
![image-springboot-doc](image/springboot-doc.png)

#### 使用插件打jar包
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```
- 在jar包目录使用cmd可以直接启动
java -jar boot-01-helloworld-1.0-SNAPSHOT.jar
- 启动中会出现 ``boot-01-helloworld-1.0-SNAPSHOT.jar中没有主清单属性`` 问题
- 在pom文件中的插件中加入配置节课
```xml
<...>
<executions>
    <execution>
        <goals>
            <goal>repackage</goal>
        </goals>
    </execution>
</executions>
<...>
```
注意点：取消cmd的快速编辑模式（可能会影响启动）

#### 修改依赖的版本号
- 可以在pom.xml中添加属性，来覆盖父工程自动仲裁的版本号
```xml
<properties>
    <mysql.version>5.1.43</mysql.version>
    <!-- 注意这里的mysql.version名称要和父工程中的名称一致 -->
</properties>
```
