package com.jerry.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jerry.dto.User;
import com.jerry.dto.UserQueryCondition;
import com.jerry.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/2
 * Time: 17:04
 * Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping("/queryParam")
    public List<User> queryParam(@RequestParam(value = "name", required = false, defaultValue = "thr") String username) {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/queryCondition")
    public List<User> queryCondition(UserQueryCondition condition) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/queryPaged")
    @JsonView(User.UserSimple.class)
    @ApiOperation(value = "用户查询服务")
    public List<User> queryPaged(UserQueryCondition condition,
                                 // 这里的sort指的是默认升序
                                 @PageableDefault(page = 1, size = 10, sort = "username") Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        // 打印分页的每页大小、页数和排序
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    // 使用正则表达式对id进行判断，只能输入数字
    @GetMapping("/{id:\\d+}")
    // @GetMapping("/{id}")
    @JsonView(User.UserDetail.class)
    public User getInfo(@ApiParam(value = "用户id") @PathVariable("id") String id) {

        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUsername("Jerry");
        return user;
    }

    @PostMapping
    // 使用@RequestBody才能正确接收字段对应值
    // 如果想要捕捉校验错误信息，那么必须加入BindingResult对象
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error ->
                    System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
//                FieldError fieldError = (FieldError) error;
//                String message = fieldError.getField() + " " + error.getDefaultMessage();
//                System.out.println(message);
                System.out.println(error.getDefaultMessage());
            });
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }

    @GetMapping("/getUserError/{id:\\d+}")
    @JsonView(User.UserDetail.class)
    public User getInfoError(@PathVariable("id") String id) {
        throw new UserNotExistException(id);
    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @PostMapping("/register")
    public void register(User user, HttpServletRequest request) {
        // 不论注册还是绑定，都会拿到一个唯一的用户标识
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }

}
