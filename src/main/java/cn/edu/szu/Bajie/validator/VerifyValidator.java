package cn.edu.szu.Bajie.validator;

import cn.edu.szu.Bajie.annotation.ValidatorDiy;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Predicate;

/**
 * @author Whitence
 * @description: 自定义参数校验
 * @date 2022/10/20 21:41
 */

public class VerifyValidator implements ConstraintValidator<ValidatorDiy,Object> {

    Class<?>[] predicts;

    String[] methodNames;

    @Override
    public void initialize(ValidatorDiy constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        this.predicts = constraintAnnotation.predicts();

        this.methodNames = constraintAnnotation.methodNames();
    }


    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if(predicts.length != methodNames.length){
            throw new IllegalArgumentException("方法和类参数数量不一致");
        }

        try {
            for (int i = 0; i < methodNames.length; i++) {

                String methodName = methodNames[i];

                Class<?> predict = predicts[i];


                Method method = ReflectUtil.getMethodByName(predict, methodName);

                if(ObjectUtil.isNull(method)){
                    throw new IllegalArgumentException("第 "+(i+1)+" 个校验方法不存在");
                }

                Object newInstance = ReflectUtil.newInstance(predict);

                boolean invoke = (boolean) method.invoke(newInstance, o);
                if(!invoke){
                    return false;
                }

            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }
}

