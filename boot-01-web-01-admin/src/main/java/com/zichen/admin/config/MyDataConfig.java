package com.zichen.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @name: MyDataConfig
 * @description:
 * @author: zichen
 * @date: 2021/5/22  10:37
 */
@Configuration
public class MyDataConfig {

    /*
     * 配置druid数据源
     * @return
     */
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        // 添加监控和防火墙
        //druidDataSource.setFilters("wall,stat");  这里的set方法也可以在yaml中配置，暂时注掉
        return druidDataSource;
    }

    /*
     * 配置druid监控页
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");
        //允许清空统计数据
        registrationBean.addInitParameter("resetEnable", "true");
        //用户名
        registrationBean.addInitParameter("loginUsername", "druid");
        //密码
        registrationBean.addInitParameter("loginPassword", "druid");
        return registrationBean;
    }

    /*
     * 配置web监控页
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        // sessionStatMaxCount配置
        registrationBean.addInitParameter("sessionStatMaxCount", "1000");
        //sessionStatEnable配置
        registrationBean.addInitParameter("sessionStatEnable", "false");
        return registrationBean;
    }
}
