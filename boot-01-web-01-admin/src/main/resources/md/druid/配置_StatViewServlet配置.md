## 配置_StatViewServlet配置
- https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatViewServlet%E9%85%8D%E7%BD%AE

温绍锦 edited this page on 17 Feb · 20 revisions
Druid内置提供了一个StatViewServlet用于展示Druid的统计信息。

这个StatViewServlet的用途包括：

提供监控信息展示的html页面
提供监控信息的JSON API
注意：使用StatViewServlet，建议使用druid 0.2.6以上版本。

1. 配置web.xml
   StatViewServlet是一个标准的javax.servlet.http.HttpServlet，需要配置在你web应用中的WEB-INF/web.xml中。

  <servlet>
      <servlet-name>DruidStatView</servlet-name>
      <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
  </servlet>
  <servlet-mapping>
      <servlet-name>DruidStatView</servlet-name>
      <url-pattern>/druid/*</url-pattern>
  </servlet-mapping>
根据配置中的url-pattern来访问内置监控页面，如果是上面的配置，内置监控页面的首页是/druid/index.html

例如：
http://110.76.43.235:9000/druid/index.html
http://110.76.43.235:8080/mini-web/druid/index.html

1.1 使用StatViewFilter替代StatViewServlet
注意，如果在某些场景使用StatViewServlet无法不生效，在1.2.5版本之后，提供了Filter的配置方式，参见 https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatViewFilter

1.2 配置监控页面访问密码
需要配置Servlet的 loginUsername 和 loginPassword这两个初始参数。

具体可以参考: 为Druid监控配置访问权限(配置访问监控信息的用户与密码)

示例如下:

<!-- 配置 Druid 监控信息显示页面 -->  
<servlet>  
    <servlet-name>DruidStatView</servlet-name>  
    <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>  
    <init-param>  
	<!-- 允许清空统计数据 -->  
	<param-name>resetEnable</param-name>  
	<param-value>true</param-value>  
    </init-param>  
    <init-param>  
	<!-- 用户名 -->  
	<param-name>loginUsername</param-name>  
	<param-value>druid</param-value>  
    </init-param>  
    <init-param>  
	<!-- 密码 -->  
	<param-name>loginPassword</param-name>  
	<param-value>druid</param-value>  
    </init-param>  
</servlet>  
<servlet-mapping>  
    <servlet-name>DruidStatView</servlet-name>  
    <url-pattern>/druid/*</url-pattern>  
</servlet-mapping>  
2. 配置allow和deny
StatViewSerlvet展示出来的监控信息比较敏感，是系统运行的内部情况，如果你需要做访问控制，可以配置allow和deny这两个参数。比如：

  <servlet>
      <servlet-name>DruidStatView</servlet-name>
      <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
  	<init-param>
  		<param-name>allow</param-name>
  		<param-value>128.242.127.1/24,128.242.128.1</param-value>
  	</init-param>
  	<init-param>
  		<param-name>deny</param-name>
  		<param-value>128.242.127.4</param-value>
  	</init-param>
  </servlet>
判断规则
deny优先于allow，如果在deny列表中，就算在allow列表中，也会被拒绝。
如果allow没有配置或者为空，则允许所有访问
ip配置规则
配置的格式

  <IP>
  或者
  <IP>/<SUB_NET_MASK_size>
其中

128.242.127.1/24
24表示，前面24位是子网掩码，比对的时候，前面24位相同就匹配。

不支持IPV6
由于匹配规则不支持IPV6，配置了allow或者deny之后，会导致IPV6无法访问。

3. 配置resetEnable
   在StatViewSerlvet输出的html页面中，有一个功能是Reset All，执行这个操作之后，会导致所有计数器清零，重新计数。你可以通过配置参数关闭它。

  <servlet>
      <servlet-name>DruidStatView</servlet-name>
      <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
  	<init-param>
  		<param-name>resetEnable</param-name>
  		<param-value>false</param-value>
  	</init-param>
  </servlet>
4. 按需要配置Spring和Web的关联监控
Web关联监控配置
https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_%E9%85%8D%E7%BD%AEWebStatFilter
Spring关联监控配置
https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_Druid%E5%92%8CSpring%E5%85%B3%E8%81%94%E7%9B%91%E6%8E%A7%E9%85%8D%E7%BD%AE