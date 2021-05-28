# Ajax 请求测试
## 1. 测试类
AjaxTestController.java

## 2. 问题
- 页面发送get或者post请求时，controller层需要有注解 @ResponseBody
    - 如果没有 @ResponseBody 注解，会报错，模板引擎无法解析
    - org.thymeleaf.exceptions.TemplateInputException: Error resolving template [test/ajax_get], template might not exist or might not be accessible by any of the configured Template Resolvers

- 跳转页面
    - return "ajax_test/ajax";      正确
    - return "/ajax_test/ajax";     错误