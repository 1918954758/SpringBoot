# Spring Boot 配置文件

## 1. 外部配置源
- application.properties
- application.yaml
- 环境变量
- 命令行参数

## 2. 配置文件查找位置
- 1. classpath根路径(java包下或者resources包下，都是类路径的根路径)
- 2. classpath根路径下的config目录
- 3. jar包所在目录
- 4. jar包所在目录的config目录下
- 5. jar包所在目录的config下的第一层子目录（linux系统下可行）
    
## 3. 配置文件加载顺序
- 1. 当前jar包内部的application.properties和application.yaml
- 2. 当前jar包内部的application-{profile}.properties 和 application-{profile}.yaml
- 3. 引用的外部jar包的application.properties 和 application.yaml
- 4. 引用的外部jar包application-{profile}.properties 和 application-{profile}.yaml

## 4. 后面的可以覆盖前面的通名配置项