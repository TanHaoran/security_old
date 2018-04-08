package com.jerry.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/4
 * Time: 9:35
 * Description: 处理用户信息获取逻辑
 */
// @Component
public class MyUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理用户加密解密：
     * 这个PasswordEncoder会将每次生成的密码加密后在拼接一个随机生成的盐混在一起进行加密，
     * 所以每次生成的密码串都是不固定的
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 处理用户校验逻辑
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("登陆用户名: " + username);

        // 这里可以从数据库中根据用户名查找用户信息
        // 根据查找到的用户信息判断用户是否被冻结

        // 三个参数：用户名，密码，授权
//        return new User(username, "123456",
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        // 中间四个参数：用户是否可用，账号是否在有效期内，证书是否有效，账号是否没被锁定
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码是: " + password);
        return new User(username, password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
