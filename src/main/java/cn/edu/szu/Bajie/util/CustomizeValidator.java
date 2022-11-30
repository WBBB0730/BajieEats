package cn.edu.szu.Bajie.util;

import cn.edu.szu.Bajie.entity.Collection;

import java.util.Arrays;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/21 16:50
 * @version 1.0
 */
public class CustomizeValidator {

//    public static boolean checkCollectType(int value){
//        return Arrays.stream(Collection.CollectionType.values())
//                .anyMatch((collectionType -> collectionType.getValue() == value));
//    }

    public static boolean checkIsCollected(int value){
        return value == 0 || value == 1;
    }

    public static boolean checkCanteenListSortType(int value){
        return value == 0 || value == 1 || value == 2 || value == 3;
    }
}
