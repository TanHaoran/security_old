package com.jerry.validator;

import com.jerry.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/3
 * Time: 9:33
 * Description: 自定义注解进行校验，只要该类实现了ConstraintValidator接口，那么该类自动就是bean类，不用添加Component注解
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("My validator init");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        helloService.greeting("Jerry");
        System.out.println(o);
        return false;
    }
}
