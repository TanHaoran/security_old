package com.jerry.security.core.authentication.mobile;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/12
 * Time: 20:56
 * Description: 短信校验码认证Provider
 */
@Data
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 这里强转成我们自己定义的SmsCodeAuthenticationToken
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        SmsCodeAuthenticationToken authenticationResult =
                new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());

        // 还需要将开始认证时的Details复制到新的SmsCodeAuthenticationToken中
        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 只支持SmsCodeAuthenticationToken类型的Token
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
