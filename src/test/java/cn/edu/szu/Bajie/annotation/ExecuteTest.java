package cn.edu.szu.Bajie.annotation;

import cn.edu.szu.Bajie.validator.CheckSensitiveValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecuteTest {

    boolean execute() default false;
}
