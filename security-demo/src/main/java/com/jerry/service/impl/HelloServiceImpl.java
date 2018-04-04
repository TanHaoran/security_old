package com.jerry.service.impl;

import com.jerry.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/3
 * Time: 9:38
 * Description:
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greeting(String name) {
        System.out.println("greeting");
        return "Hello, " + name;
    }
}
