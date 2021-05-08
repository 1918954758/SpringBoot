# SpringBoot2核心技术-核心功能
## 1. 数据响应

- SpringBoot2核心技术-核心功能_02.md

### 2.3. 内容协商
> SpringBoot响应xml数据

- 引入SpringBoot支持xml的依赖
```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```
- 只需要引入SpringBoot支持xml的依赖，不需要改动任何代码
**返回结果**
```xml
<Person>
    <userName>zhangsan</userName>
    <age>28</age>
    <birth>2021-05-08T15:20:12.517+00:00</birth>
    <pet/>
</Person>
```
- 没有引入SpringBoot支持xml依赖之前
**返回结果**
```json
{"userName":"zhangsan","age":28,"birth":"2021-05-08T15:22:18.400+00:00","pet":null}
```
- 这种情况的原因就是浏览器接收数据，响应优先级的原因
```text
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
```

> postman分别测试返回json和xml
- 只需要改变请求头中 Accept 字段。Http协议中规定的，告诉服务器，本客户端可以接收的数据类型

![image-SpringBoot内容协商响应浏览器xml测试](../image/SpringBoot内容协商响应浏览器xml测试.png)
![image-SpringBoot内容协商响应浏览器xml测试](../image/SpringBoot内容协商响应浏览器json测试.png)

**内容协商原理**

```java
class a{
    protected <T> void writeWithMessageConverters(@Nullable T value, MethodParameter returnType,
                                                  ServletServerHttpRequest inputMessage, ServletServerHttpResponse outputMessage)
            throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
        // 主备一个浏览其可以接受的数据类型
        MediaType selectedMediaType = null;
        // 获取被处理过 数据类型  比如拦截器等处理
        MediaType contentType = outputMessage.getHeaders().getContentType();
        // 判断浏览器可以接收的数据类型是否为空，并且是否是具体的媒体类型
        // contentType.isConcrete() 判断数据类型是不是有 通配符 * or *+
        // 如果 contentType是 */* or */*+  则返回false
        boolean isContentTypePreset = contentType != null && contentType.isConcrete();
        if (isContentTypePreset) {
            /**
             * 第一种情况，获取到浏览器具体的数据类型
             */
            if (logger.isDebugEnabled()) {
                logger.debug("Found 'Content-Type:" + contentType + "' in response");
            }
            // 如果数据类型是具体的，比如 application/json or application/xml等
            // 此时就会进这里面来执行，服务器也会响应浏览器具体的数据类型
            // 不会执行 else 的内容
            selectedMediaType = contentType;
            // 这种情况的内容协商也就结束
        }
        else {
            /**
             * 第二种情况，没有获取到浏览器具体的响应数据类型
             */
            // 获取请求数据
            HttpServletRequest request = inputMessage.getServletRequest();
            // 准备一个浏览器可以接收的数据类型 List<MediaType>
            List<MediaType> acceptableTypes;
            try {
                // 从请求数据中的Accept中获取浏览器支持的数据类型有哪些
                // Accept = text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
                acceptableTypes = getAcceptableMediaTypes(request);
                // getAcceptableMediaTypes(request); 核心方法 request.getHeaderValues(HttpHeaders.ACCEPT);
            }
            catch (HttpMediaTypeNotAcceptableException ex) {
                int series = outputMessage.getServletResponse().getStatus() / 100;
                if (body == null || series == 4 || series == 5) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Ignoring error response content (if any). " + ex);
                    }
                    return;
                }
                throw ex;
            }
            // 获取服务器可以提供的数据类型
            // 遍历服务器中所有的消息转换器，判断那种消息转换器可以处理当前的返回值
            // 核心方法：converter.canWrite(targetType)
            // producibleTypes = ["application/json", "application/*+json", "application/json", "application/*+json", "application/xml;charset=UTF-8",
            //                    "test/xml;charset=UTF-8", "application/*+xml;charset=UTF-8", "application/xml;charset=UTF-8",
            //                    "test/xml;charset=UTF-8", "application/*+xml;charset=UTF-8"]
            List<MediaType> producibleTypes = getProducibleMediaTypes(request, valueType, targetType);

            if (body != null && producibleTypes.isEmpty()) { // 判断返回值有没有
                throw new HttpMessageNotWritableException(
                        "No converter found for return value of type: " + valueType);
            }
            // 准备一个空的list，存放要响应页面的数据类型
            List<MediaType> mediaTypesToUse = new ArrayList<>();
            // 遍历浏览器可以接收的数据类型   acceptableTypes.size() = 8
            for (MediaType requestedType : acceptableTypes) {
                // 遍历服务器可以提供的数据类型   producibleTypes.size() = 10
                for (MediaType producibleType : producibleTypes) {
                    // 判断浏览器可以接收的数据类型 是否兼容 服务器提供的数据类型
                    if (requestedType.isCompatibleWith(producibleType)) {
                        // 在选择最具体的数据类型，全部放到准备好的响应页面的数据类型得list中
                        mediaTypesToUse.add(getMostSpecificMediaType(requestedType, producibleType));
                    }
                }
            }
            
            // 如果没有匹配到响应页面的数据类型，则会报错   No match for acceptableTypesMessageConverter, supported
            if (mediaTypesToUse.isEmpty()) {
                if (body != null) {
                    throw new HttpMediaTypeNotAcceptableException(producibleTypes);
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("No match for " + acceptableTypes + ", supported: " + producibleTypes);
                }
                return;
            }
            
            // 
            MediaType.sortBySpecificityAndQuality(mediaTypesToUse);

            for (MediaType mediaType : mediaTypesToUse) {
                if (mediaType.isConcrete()) {// 判断是否是具体的数据类型，，而非 */*  or  */*+
                    // 返回具体的数据类型
                    selectedMediaType = mediaType;
                    break;
                }
                else if (mediaType.isPresentIn(ALL_APPLICATION_MEDIA_TYPES)) {
                    selectedMediaType = MediaType.APPLICATION_OCTET_STREAM;
                    break;
                }
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Using '" + selectedMediaType + "', given " +
                        acceptableTypes + " and supported " + producibleTypes);
            }
        }

        if (selectedMediaType != null) {
            // 移除 Accept 中的权重   "q"
            selectedMediaType = selectedMediaType.removeQualityValue();
            for (HttpMessageConverter<?> converter : this.messageConverters) {
                // 找到 通用的Http消息转换器
                GenericHttpMessageConverter genericConverter = (converter instanceof GenericHttpMessageConverter ?
                        (GenericHttpMessageConverter<?>) converter : null);
                if (genericConverter != null ?
                        // 该 通用的Http消息转换器 能否适配当前返回值类型(Person)
                        ((GenericHttpMessageConverter) converter).canWrite(targetType, valueType, selectedMediaType) :
                        converter.canWrite(valueType, selectedMediaType)) {
                    // 到这里，说明已经找到 适配当前返回值类型的消息转换器   genericConverter = MappingJackson2XmlHttpMessageConverter@1947
                    body = getAdvice().beforeBodyWrite(body, returnType, selectedMediaType,
                            (Class<? extends HttpMessageConverter<?>>) converter.getClass(),
                            inputMessage, outputMessage);
                    if (body != null) {
                        Object theBody = body;
                        LogFormatUtils.traceDebug(logger, traceOn ->
                                "Writing [" + LogFormatUtils.formatValue(theBody, !traceOn) + "]");
                        addContentDispositionHeader(inputMessage, outputMessage);
                        if (genericConverter != null) {
                            // 向页面写数据  outputMessage 已经拼装好格式
                            // <Person><userName>zhangsan</userName><age>28</age><birth>2021-05-08T15:59:03.556+00:00</birth><pet/></Person>
                            genericConverter.write(body, targetType, selectedMediaType, outputMessage);
                        }
                        else {
                            ((HttpMessageConverter) converter).write(body, selectedMediaType, outputMessage);
                        }
                    }
                    else {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Nothing to write: null body");
                        }
                    }
                    return;
                }
            }
        }
    }
}
```


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




