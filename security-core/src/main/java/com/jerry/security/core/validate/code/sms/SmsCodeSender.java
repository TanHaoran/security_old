package com.jerry.security.core.validate.code.sms;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/11
 * Time: 23:43
 * Description:
 */
public interface SmsCodeSender {

    void send(String mobile, String code);
}
