package com.jerry.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/22
 * Time: 16:37
 * Description:
 */
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
