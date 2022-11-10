package cn.edu.szu.Bajie.handler;

import cn.edu.szu.Bajie.common.IErrorCode;
/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/7 22:13
 * @version 1.0
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
