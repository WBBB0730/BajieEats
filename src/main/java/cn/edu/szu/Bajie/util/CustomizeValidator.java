package cn.edu.szu.Bajie.util;

import cn.edu.szu.Bajie.entity.Collection;

import java.util.Arrays;
import java.util.List;
/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/21 16:50
 * @version 1.0
 */
public class CustomizeValidator {

    public static boolean checkCollectType(int value){
        return Arrays.stream(Collection.CollectionType.values())
                .anyMatch((collectionType -> collectionType.getValue() == value));
    }

    public static boolean checkIsCollected(int value){
        return value == 0 || value == 1;
    }


    public static boolean checkCollectTypes(List<Integer> types){
        if(types.size() == 0){
            return false;
        }

        return types
                .stream()
                .anyMatch(type -> type == 0 || type == 1 || type == 2);

    }
}
