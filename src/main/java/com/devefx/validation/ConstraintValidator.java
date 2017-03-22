package com.devefx.validation;

import java.util.Map;


/**
 * ConstraintValidator
 * Created by YYQ on 2016/5/26.
 */
public abstract class ConstraintValidator {

    protected final Error error;

    public ConstraintValidator(String errorCode, String errorMessage) {
        error = new Error(errorCode, errorMessage);
    }

    public Error getError() {
        return error;
    }

    /**
     * 判断是否验证通过，通过返回true。不通过返回false
     * @param body
     * @return
     * @throws Exception
     */
    public abstract boolean isValid(Map<String,Object> body) throws Exception;
}
