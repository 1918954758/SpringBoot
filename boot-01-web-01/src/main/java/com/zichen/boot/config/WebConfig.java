package com.zichen.boot.config;

import com.zichen.boot.bean.Pet;
import com.zichen.boot.converter.ZiChenMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @name: Filter
 * @description:
 * @author: zichen
 * @date: 2021/5/4  23:49
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class WebConfig /*implements WebMvcConfigurer*/ {

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_m");
        return hiddenHttpMethodFilter;
    }

    // 1. 自己写一个@Bean  // WebMvcConfigurer  容器定制配置
    // 2. 实现WebMvcConfigurer
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {

            /**
             * 开启矩阵模式，使分号以及分号后面的内容不删除
             * @param configurer
             */
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }

            /**
             * 配置自定义的参数值解析
             * @param registry
             */
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Pet>() {

                    @Override
                    public Pet convert(String source) {
                        Pet pet = new Pet();
                        String[] split = source.split(",");
                        pet.setName(split[0].trim());
                        pet.setAge(split[1].trim());
                        return pet;
                    }
                });
            }

            // 覆盖默认的MessageConverter
            /*@Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

            }*/

            // 扩展MessageConverter
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new ZiChenMessageConverter());
            }
        };
    }


    /*@Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }*/

    /*@Bean
    public void updateFormat2Other() {
        log.info("updateFormat2Other 进来了。。。。");
        MediaType mediaType = new MediaType("*", "*");
        Map<String, MediaType> map = new HashMap<>();
        map.put("formatter", mediaType);
        new ParameterContentNegotiationStrategy(map);
        log.info("updateFormat2Other 出去了。。。。");
    }*/
}
