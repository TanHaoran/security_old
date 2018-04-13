package com.jerry.security.core.validate.code.sms;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/11
 * Time: 23:43
 * Description: 短信发送器接口
 */
public interface SmsCodeSender {

    /**
     * 像手机发送短信
     *
     * @param mobile
     * @param code
     */
    void send(String mobile, String code);
}
