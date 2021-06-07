package com.zichen.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;
import java.util.function.BiConsumer;

@SpringBootApplication
public class Boot01Web01AdmindemoApplication {

    private static final Logger log = LoggerFactory.getLogger(Boot01Web01AdmindemoApplication.class);
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Boot01Web01AdmindemoApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        Map<String, Object> systemProperties = environment.getSystemProperties();
        for (Map.Entry<String, Object> systemPropertie : systemProperties.entrySet()) {
            log.info(systemPropertie.getKey() + " - " + (String) systemPropertie.getValue());
        }
    }

}
