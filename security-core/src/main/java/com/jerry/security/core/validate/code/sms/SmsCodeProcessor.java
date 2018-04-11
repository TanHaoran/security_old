package com.jerry.security.core.validate.code.sms;

import com.jerry.security.core.validate.code.ValidateCode;
import com.jerry.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/12
 * Time: 0:45
 * Description: 短信验证码处理器
 */
@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {


    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode smsCode) throws Exception {
        String mobile = ServletRequestUtils.getStringParameter(request.getRequest(), "mobile");

        // 发送短信验证码
        smsCodeSender.send(mobile, smsCode.getCode());
    }
}
