package com.jerry.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.jerry.validator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/2
 * Time: 17:07
 * Description:
 */
public class User {

    // 使用这两个接口可以方便对返回对象的json字符串做不同处理
    public interface UserSimple {
    }

    public interface UserDetail extends UserSimple {
    }

    /**
     * 校验规则：
     *
     * @NotNull:值不能为空
     * @Null:值必须为空
     * @Pattern(regex=):字符串必须匹配正则表达式
     * @Size(min=,max=):集合的元素数量必须在min和max之间
     * @CreditCardNumber(ignoreNonDigitCharacters=):字符串必须是信用卡号（美国标准）
     * @Emain:字符串必须是Email地址
     * @Length(min=,max=):检查字符串的长度
     * @NotBlank:字符串必须有字符
     * @NotEmpty:字符串不为null，集合有元素
     * @Range(min=,max=):数字必须大于等于min，小于等于max
     * @SafeHtml:字符串是安全的html
     * @URL:字符串是合法的URL
     * @AssertFalse:值必须是false
     * @AssertTrue:值必须是true
     * @DecimalMax(value=,inclusive=):值必须小于等于(inclusive=true)/小于(inclusive=false)value属性指定的值，可以注解在字符串类型的属性上
     * @DecimalMin(value=,inclusive=):值必须大于等于(inclusive=true)/大于(inclusive=false)value属性指定的值，可以注解在字符串类型的属性上
     * @Future:之必须是未来的日期
     * @Past:值必须是过去的日期
     * @Max(value=):值必须小于等于value指定的值，不能注解在字符串类型的属性上
     * @Min(value=):值必须大于等于value指定的值，不能注解在字符串类型的属性上
     */

    private String id;

    @MyConstraint(message = "这是一个测试")
    private String username;

    // 校验不允许为空
    @NotBlank(message = "密码不能为空")
    private String password;

    @Past(message = "生日必须是过去的时间")
    private Date birthday;

    @JsonView(UserSimple.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetail.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimple.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimple.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
