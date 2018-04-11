package com.jerry.security.core.validate.code.sms;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/11
 * Time: 23:45
 * Description:
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机【" + mobile + "】发送验证码【" + code + "】");
    }
}
