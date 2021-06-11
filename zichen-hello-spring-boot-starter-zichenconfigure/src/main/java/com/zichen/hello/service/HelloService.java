package com.zichen.hello.service;

import com.zichen.hello.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @name: HelloService
 * @description:
 * @author: zichen
 * @date: 2021/6/12  0:56
 */

/**
 * 默认不要放在容器中
 */
public class HelloService {

    @Autowired
    HelloProperties helloProperties;

    public String sayHello(String userName) {
        return helloProperties.getPrefix() + ": " + userName + ">" + helloProperties.getSuffix();
    }
}
