package cn.edu.szu.Bajie.handler;


import cn.edu.szu.Bajie.common.IErrorCode;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/7 22:13
 * @version 1.0
 */
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}

