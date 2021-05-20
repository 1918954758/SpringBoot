## 原生组件注入_servlet_filter_listener

- /css/*   servlet写法
- /css/**  spring写法

### 注入方式一

- 注入servlet原生组件
    - 扫描包 @SpringComponentScan("com.zichen.admin")
    - 测试Servlet类，继承HttpServlet（众多Servlet中的一个） 重写doGet()    use @WebServlet(urlPatterns = {"/test"})
        - /test 请求被拦截
    - 实现Filter（众多Filter中的一个） 重写init() doFilter()  destroy()  use @WebFilter(urlPatterns = {"/css/*", "/js/*"})测试Filter类，
        - /css/* 和 /js/*  资源被拦截
    - 测试Listener类，实现ServletContextListener（众多Listener中的一个） 重写 contextInitialized()   contextDestroyed()  use @WebListener

![image-servlet三大组件注入](../image/servlet三大组件注入.png)


### 注入方式二

- use ServletRegistrationBean, FilterRegistrationBean, and ServletListenerRegistrationBean classes for complete control

- 先把方式一注释掉

```java
@Slf4j
@Configuration
public class MyRegistrationBeanConfig {

    @Bean
    public ServletRegistrationBean myServlet() {
        MyServlet myServlet = new MyServlet();
        return new ServletRegistrationBean(myServlet, "/myServlet");
    }

    @Bean
    public FilterRegistrationBean myFilter() {
        return new FilterRegistrationBean(new MyFilter(), myServlet());
    }
    
    /*
    @Bean
    public FilterRegistrationBean myFilter02() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/myFilter", "/css/*"));
        return filterRegistrationBean;
    }      
     */

    @Bean
    public ServletListenerRegistrationBean myListener() {
        return new ServletListenerRegistrationBean(new MyServletContextListener());
    }
}
```

- 细节 @Configuration(proxyBeanMethods = false)
    - false的时候，每次调用 myServlet()的时候，都会创建一个对象new MyServlet()
    - true的时候，创建一次，多次使用（保证依赖的组件是单例的）
    

- 为什么自己写的Servlet映射的路径，不会被拦截器拦截？
    - @WebServlet(urlPatterns = {"/test"})
    
- DispatcherServlet
    - 使用@Bean放到容器中
            - @Bean(name = DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
              public DispatcherServlet dispatcherServlet(WebMvcProperties webMvcProperties) {}
            - DEFAULT_DISPATCHER_SERVLET_BEAN_NAME = dispatcherServlet
    - 通过DispatcherServletRegistrationBean 将servlet注册到容器中
        - 映射路径是  "/";

- Tomcat-Servlet
    - 多个servlet都能处理到同一层路径，使用精确优先原则
