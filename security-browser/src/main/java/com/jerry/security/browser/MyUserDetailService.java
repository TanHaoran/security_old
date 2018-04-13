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
 * Description: 自定义用户信息获取逻辑
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

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

        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码是: " + password);

        // 三个参数：用户名，密码，权限
        return new User(username, password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
