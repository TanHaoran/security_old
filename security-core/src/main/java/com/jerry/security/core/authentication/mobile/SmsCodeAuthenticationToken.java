package com.jerry.security.core.authentication.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/12
 * Time: 20:35
 * Description: 短信校验码认证Token
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 这里实际保存的是手机号
     */
    private final Object principal;

    /**
     * 第一次认证调用这个构造方法
     *
     * @param mobile
     */
    public SmsCodeAuthenticationToken(Object mobile) {
        super(null);
        this.principal = mobile;
        // 第一次设置认证未通过
        setAuthenticated(false);
    }

    /**
     * 认证通过后调用这个构造方法
     *
     * @param mobile
     * @param authorities
     */
    public SmsCodeAuthenticationToken(Object mobile, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = mobile;
        // 必须调用父类的setAuthenticated(true)方法
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    /**
     * 设置认证是否通过
     *
     * @param isAuthenticated
     * @throws IllegalArgumentException
     */
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    /**
     * 抹去认证
     */
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
