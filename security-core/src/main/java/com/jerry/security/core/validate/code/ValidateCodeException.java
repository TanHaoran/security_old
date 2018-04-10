package com.jerry.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/10
 * Time: 22:04
 * Description:
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
