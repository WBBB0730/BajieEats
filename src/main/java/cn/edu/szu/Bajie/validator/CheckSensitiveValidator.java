package cn.edu.szu.Bajie.validator;

import cn.edu.szu.Bajie.annotation.CheckSensitive;
import cn.edu.szu.Bajie.annotation.ValidatorDiy;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Whitence
 * @description: 自定义参数校验
 * @date 2022/10/20 21:41
 */

public class CheckSensitiveValidator implements ConstraintValidator<CheckSensitive,String> {

    private static final Set<String> SENSITIVE_WORDS;

    static {
        InputStream stream = new ClassPathResource("sensitive_words.txt").getStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        SENSITIVE_WORDS=reader
                .lines()
                .collect(Collectors.toSet());

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public void initialize(CheckSensitive constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

//        Result words = TokenizerUtil.createEngine().parse(s);
//
//        while (words.hasNext()){
//            String text = words.next().getText();
//            if(sensitiveWords.contains(text)){
//                return false;
//            }
//        }

        return SENSITIVE_WORDS
                .stream()
                .noneMatch(s::contains);

    }

}

