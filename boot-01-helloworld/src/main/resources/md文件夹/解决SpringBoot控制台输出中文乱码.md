```xml
<!-- spring-boot:run 中文乱码 -->
<configuration>
    <fork>true</fork>
    <!-- 增加jvm参数 -->
    <jvmArguments>-Dfile.encoding=UTF-8</jvmArguments>
</configuration>
```