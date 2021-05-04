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
- 将欢迎页放到静态资源路径下
- 编写Controller
```java
//@RestController
@Controller
public class HelloController {

    @RequestMapping("/index")
    public String index() {
        return "index.html";
    }
}
```
- 这里注意：使用@Controller和@RequestMapping("/)
- @RequestBody注解是将字符串直接返回给页面
- 测试

![image-index](../image/index.png)


> 2.1.3. 自定义 Favicon
- 使用图标名字固定 favicon.ico
- 将favicon.ico放到静态资源访问路径下
- 重启
- 访问：http://localhost:8080/hello

![image-favicon](../image/favicon.png)

*注意：* 显示ico图标有可能会有缓存影响，禁用尝试
> 2.1.3. 静态资源配置原理
- SpringB启动默认加载 xxxAautoConfiguration类（自动配置类）
- SpringMVC功能的自动配置类WebMVCAutoConfiguration
- 看这个配置类是否生效
```java
@Configuration(
    proxyBeanMethods = false
)
@ConditionalOnWebApplication(
    type = Type.SERVLET
)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
@AutoConfigureOrder(-2147483638)
@AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
public class WebMvcAutoConfiguration {
    //...
}
```
- 给容器中配置了什么
```java
@Configuration(
    proxyBeanMethods = false
)
@Import({WebMvcAutoConfiguration.EnableWebMvcConfiguration.class})
@EnableConfigurationProperties({WebMvcProperties.class, ResourceProperties.class, WebProperties.class})//
@Order(0)
public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer {
    //...
}
@ConfigurationProperties(prefix = "spring.mvc")
public class WebMvcProperties {
    //...
}
@Deprecated
@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties extends Resources {
    //...
}
@ConfigurationProperties("spring.web")
public class WebProperties {
    //...
}
```
- 配置文件的相关属性和什么进行了绑定。WebMvcProperties = **spring.mvc** 、ResourceProperties = **spring.resources**、WebProperties = **spring.web**



**扩展：**
- 1. 配置类只有一个有参构造器，所有参数的值都会从容器中确定
```java
    @Configuration(
            proxyBeanMethods = false
    )
    @EnableConfigurationProperties({WebProperties.class})
    public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration implements ResourceLoaderAware {
    private static final Log logger = LogFactory.getLog(WebMvcConfigurer.class);
    private final Resources resourceProperties;
    private final WebMvcProperties mvcProperties;
    private final WebProperties webProperties;
    private final ListableBeanFactory beanFactory;
    private final WebMvcRegistrations mvcRegistrations;
    private final WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer resourceHandlerRegistrationCustomizer;
    private ResourceLoader resourceLoader;

    // ResourceProperties resourceProperties    prefix = "spring.resources"
    // WebMvcProperties mvcProperties   prefix = "spring.mvc"
    // WebProperties webProperties   prefix = "spring.web"
    // ObjectProvider<WebMvcRegistrations> mvcRegistrationsProvider
    // ObjectProvider<WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider 找ResourceHandlerRegistrationCustomizer资源处理器自定义器
    //ListableBeanFactory beanFactory IOC容器工厂中获得
    public EnableWebMvcConfiguration(ResourceProperties resourceProperties, WebMvcProperties mvcProperties, WebProperties webProperties, ObjectProvider<WebMvcRegistrations> mvcRegistrationsProvider, ObjectProvider<WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider, ListableBeanFactory beanFactory) {
        this.resourceProperties = (Resources) (resourceProperties.hasBeenCustomized() ? resourceProperties : webProperties.getResources());
        this.mvcProperties = mvcProperties;
        this.webProperties = webProperties;
        this.mvcRegistrations = (WebMvcRegistrations) mvcRegistrationsProvider.getIfUnique();
        this.resourceHandlerRegistrationCustomizer = (WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer) resourceHandlerRegistrationCustomizerProvider.getIfAvailable();
        this.beanFactory = beanFactory;
    }
}
```
- 资源处理的默认规则
```java
@Configuration(
        proxyBeanMethods = false
)
@EnableConfigurationProperties({WebProperties.class})
public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration implements ResourceLoaderAware {
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        // resourceProperties =  ["classpath:/haha...", "classpath:/abc/", "classpath:/stat...", "classpath:/temp...", ...]
        if (!this.resourceProperties.isAddMappings()) {
            logger.debug("Default resource handling disabled");
        } else {
            ServletContext servletContext = this.getServletContext();
            // 处理webjars资源寻找路径 classpath:/META-INF/resources/webjars/**
            this.addResourceHandler(registry, "/webjars/**", "classpath:/META-INF/resources/webjars/");
            // 处理默认资源路径  /**   registration里面有默认访问路径/**，以及我们自己配置的访问路径：。。。
            this.addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
                registration.addResourceLocations(this.resourceProperties.getStaticLocations());
                if (servletContext != null) {
                    registration.addResourceLocations(new Resource[]{new ServletContextResource(servletContext, "/")});
                }
            });
        }
    }
}

public static class Resources {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/",
            "classpath:/resources/", "classpath:/static/", "classpath:/public/"};

    /**
     * Locations of static resources. Defaults to classpath:[/META-INF/resources/,
     * /resources/, /static/, /public/].
     */
    private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;

    /**
     * Whether to enable default resource handling.
     */
    private boolean addMappings = true;

    private boolean customized = false;

    private final Chain chain = new Chain();

    private final Cache cache = new Cache();

    public String[] getStaticLocations() {
        return this.staticLocations;
    }
}
```
- 欢迎页处理规则
*HandlerMapping* : 处理映射器，保存了每一个Handler能处理那些请求。
```java
@EnableConfigurationProperties({WebProperties.class})
public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration implements ResourceLoaderAware {
    @Bean
    public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext, FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
        WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(new TemplateAvailabilityProviders(applicationContext), applicationContext, this.getWelcomePage(), this.mvcProperties.getStaticPathPattern());
        welcomePageHandlerMapping.setInterceptors(this.getInterceptors(mvcConversionService, mvcResourceUrlProvider));
        welcomePageHandlerMapping.setCorsConfigurations(this.getCorsConfigurations());
        return welcomePageHandlerMapping;
    }
}
```

### 2.2. 请求参数处理
> 请求映射
- @xxxMapping
- Rest风格支持（使用HTTP请求方式动词来表示对资源的操作）
    - 以前：/getUser 获取用户  /deleteUser 删除用户  /editUser  修改用户   /saveUser 保存用户
    - 现在：/user  GET-获取请求  DELETE-删除用户  PUT-修改用户  POST-保存用户
    - 核心Filter：需要配置HiddenHttpMethodFilter，才可以使用现在的Rest风格
        - 用法：表单method=post，隐藏域 _method=put
        - SpringBoot中需要手动开启Rest风格
```java
@Configuration(
        proxyBeanMethods = false
)
@ConditionalOnWebApplication(
        type = Type.SERVLET
)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
@AutoConfigureOrder(-2147483638)
@AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
public class WebMvcAutoConfiguration {
    public static final String DEFAULT_PREFIX = "";
    public static final String DEFAULT_SUFFIX = "";
    private static final String SERVLET_LOCATION = "/";

    public WebMvcAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean({HiddenHttpMethodFilter.class})//当HiddenHttpMethodFilter没有生效的时候，下面方法才生效
    @ConditionalOnProperty(
            prefix = "spring.mvc.hiddenmethod.filter",
            name = {"enabled"},
            matchIfMissing = false
    )//默认是没有开启提交方法过滤的，需要我们手动开启，在配置文件中配置
    public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter();
    }
}
```
```yaml
spring:
#  mvc:
#    # 访问静态资源添加前缀，静态资源前缀对于访问欢迎页不支持，因为底层已经写死了，直接找静态资源下的index.html
#    static-path-pattern: /res/**
  web:
    resources:
      # 改变静态资源访问路径
      #static-locations: classpath:/haha/
      # 改变资源访问路径可以使用数组，改变多个资源访问路径
      static-locations: ["classpath:/haha/", "classpath:/abc/", "classpath:/templates"]
      # false是禁用静态资源，都访问不了了，  默认是true不禁用
      # add-mappings: false
  # 手动开启请求方法
  mvc:
    hiddenmethod:
      filter:
        enabled: true
```
- 页面提交的时候注意DELETE/PUT/等方式，from的method属性还得写post
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>com.zichen，welcome page</h1>
    <!--测试REST风格-->
    <form action="/user" method="get">
        <input value="REST-GET 提交" type="submit"/>
    </form>
    <br/>
    <form action="/user" method="post">
        <input value="REST-POST 提交" type="submit"/>
    </form>
    <br/>
    <form action="/user" method="post">
        <input name="_method" type="hidden" value="DELETE"/>
        <input value="REST-DELETE 提交" type="submit"/>
    </form>
    <br/>
    <form action="/user" method="post">
        <input name="_method" type="hidden" value="PUT">
        <input value="REST-PUT 提交" type="submit"/>
    </form>
    <br/>
    <hr/>
    <!-- 测试基本注解 -->
</body>
</html>
```
```java
public class HiddenHttpMethodFilter extends OncePerRequestFilter {
    private static final List<String> ALLOWED_METHODS;
    public static final String DEFAULT_METHOD_PARAM = "_method";
    private String methodParam = "_method";

    public HiddenHttpMethodFilter() {
    }

    public void setMethodParam(String methodParam) {
        Assert.hasText(methodParam, "'methodParam' must not be empty");
        this.methodParam = methodParam;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //拿到用户发起的请求
        HttpServletRequest requestToUse = request;
        //这里也就说明了HTML页面中，如果是PUT/DELETE/等提交，from的method属性为什么还得写post，由于底层代码会拿到页面的请求方法参数，进行和"POST"比较，相等的情况下，才去拿到我们隐藏的那个真正的提交方法。
        if ("POST".equals(request.getMethod()) && request.getAttribute("javax.servlet.error.exception") == null) {
            //获取请求参数    _method的请求参数
            String paramValue = request.getParameter(this.methodParam);
            if (StringUtils.hasLength(paramValue)) {
                String method = paramValue.toUpperCase(Locale.ENGLISH);
                if (ALLOWED_METHODS.contains(method)) {
                    requestToUse = new HiddenHttpMethodFilter.HttpMethodRequestWrapper(request, method);
                }
            }
        }
        filterChain.doFilter((ServletRequest) requestToUse, response);
    }
    static {
        ALLOWED_METHODS = Collections.unmodifiableList(Arrays.asList(HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.PATCH.name()));
    }

    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {
        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

        public String getMethod() {
            return this.method;
        }
    }
}
```
- Rest原理（表单提交要是用REST的时候）
    - 表单提交会带上 _method = PUT
    - 请求过来会被HiddenHttpMethodFilter拦截
        - 判断请求是否正常，并且是post方式
            - 获取到_method的值。
            - 兼容以下请求：PUT/DELETE/PATCH
            - 原生request(post)，包装模式，包装了一个requestWrapper重写了getMethod犯法，返回的是传入的值。
            - 下面过滤器链放行的是wrapper。以后的方法调用getMethod是调用requestWrapper的。

- REST使用客户端工具，就和上面的没关系了
    - 如果使用客户端工具（postman）发送，在HttpServletRequest requestToUse = request;（拿到请求的时候就已经是PUT/DELETE/PATCH请求了），直接进入filterChain.doFilter((ServletRequest) requestToUse, response);（过滤器链放行）
    - 所以springboot才有了选择开启请求方法功能

- 以下四组，每一组都是等价的
- @RequestMapping(value = "/user", method = RequestMethod.GET)
  @GetMapping("/user")
- @RequestMapping(value = "/user", method = RequestMethod.POST)
  @PostMapping("/user")
- @RequestMapping(value = "/user", method = RequestMethod.PUT)
  @PutMapping("/user")
- @RequestMapping(value = "/user", method = RequestMethod.DELETE)
  @DeleteMapping("/user")

**可以将 _method 变成自己喜欢的名字：**
- 由于springboot底层是使用@ConditionalOnMissingBean({HiddenHttpMethodFilter.class})注解，如果容器中没有HiddenHttpMethodFilter组件，则会执行方法中的内容，如果容器中有了@ConditionalOnMissingBean({HiddenHttpMethodFilter.class})注解，就不会执行方法中的内容，他也就不会使用默认的"_method"来找对应的方法。
```java
@Configuration(
        proxyBeanMethods = false
)
@ConditionalOnWebApplication(
        type = Type.SERVLET
)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
@AutoConfigureOrder(-2147483638)
@AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
public class WebMvcAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean({HiddenHttpMethodFilter.class})
    @ConditionalOnProperty(
            prefix = "spring.mvc.hiddenmethod.filter",
            name = {"enabled"},
            matchIfMissing = false
    )
    public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter();
    }
}
```
- 我们就可以在容器中添加一个HiddenHttpMethodFilter组件即可
    - 创建一个WebConfig.java
    - 添加HiddenHttpMethodFilter组件
```java
@Configuration(proxyBeanMethods = false)
public class WebConfig {

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_m");
        return hiddenHttpMethodFilter;
    }

}
```
```java
public class HiddenHttpMethodFilter extends OncePerRequestFilter {
    private static final List<String> ALLOWED_METHODS;
    public static final String DEFAULT_METHOD_PARAM = "_method";
    private String methodParam = "_method";

    public HiddenHttpMethodFilter() {
    }
    // 底层HiddenHttpMethodFilter类提供了设置方法参数的方法，所以我们可以来设置自己的方法参数hiddenHttpMethodFilter.setMethodParam("_m");
    public void setMethodParam(String methodParam) {
        Assert.hasText(methodParam, "'methodParam' must not be empty");
        this.methodParam = methodParam;
    }
}
```
- 此时我们把请求参数方法改成了 "_m" ，那么我们也需要修改HTML文件内容
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action="/user" method="post">
        <!-- 原生方法参数 -->
        <input name="_method" type="hidden" value="DELETE"/>
        <!-- 自定义方法参数 -->
        <input name="_m" type="hidden" value="DELETE"/>
        <input value="REST-DELETE 提交" type="submit"/>
    </form>
    <br/>
    <form action="/user" method="post">
        <!-- 原生方法参数 -->
        <input name="_method" type="hidden" value="PUT">
        <!-- 自定义方法参数 -->
        <input name="_m" type="hidden" value="PUT">
        <input value="REST-PUT 提交" type="submit"/>
    </form>
</body>
</html>
```

  
> 普通参数与基本注解
 

 

> POJO封装过程




> 参数处理原理





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




