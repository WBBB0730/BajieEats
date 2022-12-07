package cn.edu.szu.Bajie.annotation;

import cn.edu.szu.Bajie.validator.CheckSensitiveValidator;
import cn.edu.szu.Bajie.validator.VerifyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Whitence
 * @description: 自定义参数校验
 * @date 2022/10/20 21:40
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CheckSensitiveValidator.class})
public @interface CheckSensitive {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

