package cn.edu.szu.Bajie.util;

import cn.edu.szu.Bajie.entity.Collection;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;

import java.util.Arrays;
import java.util.List;
/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/21 16:50
 * @version 1.0
 */
public class CustomizeValidator {

    /**
     * 校验收藏类型
     * @param value
     * @return
     */
    public static boolean checkCollectType(int value){
        return Arrays.stream(Collection.CollectionType.values())
                .anyMatch((collectionType -> collectionType.getValue() == value));
    }
    public static boolean checkCollectTypes(List<Integer> types){
        return types.stream().anyMatch(CustomizeValidator::checkCollectType);
    }


    public static boolean checkZeroOrOne(int value){
        return value == 0 || value == 1;
    }

    /**
     * 校验餐厅列表排序类型
     * @param value
     * @return
     */
    public static boolean checkCanteenListSortType(int value){
        return value == 0 || value == 1 || value == 2 || value == 3;
    }

    public static boolean checkCollectListSortType(int value){
        return value == 0 || value == 1 || value == 2 || value == 3 || value == 4;
    }


}
