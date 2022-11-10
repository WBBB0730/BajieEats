package cn.edu.szu.Bajie.annotation;

import cn.edu.szu.Bajie.validator.VerifyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Whitence
 * @description: 自定义参数校验
 * @date 2022/10/20 21:40
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {VerifyValidator.class})
public @interface ValidatorDiy {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    String[] methodNames() default {};

    Class<?>[] predicts() default {};

}

