package com.jerry.web.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/3
 * Time: 14:49
 * Description: 模拟的在线程之间传递消息的对象
 */
@Component
public class DeferredResultHolder {

    // String放的是订单，DeferredResult放的就是订单的处理结果
    private Map<String, DeferredResult> map = new HashMap<>();

    public Map<String, DeferredResult> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult> map) {
        this.map = map;
    }
}
