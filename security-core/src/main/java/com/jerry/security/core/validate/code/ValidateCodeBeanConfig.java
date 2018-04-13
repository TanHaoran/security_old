package com.jerry.security.core.validate.code;

import com.jerry.security.core.properties.SecurityProperties;
import com.jerry.security.core.validate.code.image.ImageCodeGenerator;
import com.jerry.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.jerry.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/11
 * Time: 11:34
 * Description: 校验码Bean的配置类
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 检查是否自定义了imageCodeGenerator，没有就使用这里创建的校验码生成器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    /**
     * 检查是否自定义了短信发送器，没有就用这里创建的短信发送器。
     * 下面两种写法都生效：一种是是否自定义了名为smsCodeSender的短信发送器，一种是是否自定义实现了SmsCodeSender的接口
     *
     * @return
     */
    @Bean
    // @ConditionalOnMissingBean(name = "smsCodeSender")
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
