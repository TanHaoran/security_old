package com.jerry.security.core.support;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/4
 * Time: 10:32
 * Description: 简单的响应对象
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
