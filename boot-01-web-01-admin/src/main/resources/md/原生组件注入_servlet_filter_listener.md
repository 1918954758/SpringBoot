## 原生组件注入_servlet_filter_listener

- /css/*   servlet写法
- /css/**  spring写法

- 注册servlet
    - 扫描包 @SpringComponentScan("com.zichen.admin")
    - 测试Servlet类，继承HttpServlet（众多Servlet中的一个） 重写doGet()    use @WebServlet(urlPatterns = {"/test"})
        - /test 请求被拦截
    - 测试Filter类，实现Filter（众多Filter中的一个） 重写init() doFilter()  destroy()  use @WebFilter(urlPatterns = {"/css/*", "/js/*"})
        - /css/* 和 /js/*  资源被拦截
    - 测试Listener类，实现ServletContextListener（众多Listener中的一个） 重写 contextInitialized()   contextDestroyed()  use @WebListener

![image-servlet三大组件注入](../image/servlet三大组件注入.png)
