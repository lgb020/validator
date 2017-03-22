package com.devefx.validation.constraints.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import net.feimz.utils._StringUtils;

import com.devefx.validation.Script;
import com.devefx.validation.annotation.BindScript;
import com.devefx.validation.constraints.FieldValidator;
import com.devefx.validation.script.JavaScript;

/**
 * NotBlankValidator
 * Created by YYQ on 2016/5/26.
 */
@BindScript("NotBlankValidator.js")
public class NotBlankValidator extends FieldValidator implements Script {

    private final Script script;

    public NotBlankValidator(String field, String errorCode, String errorMessage) {
        super(field, errorCode, errorMessage);
        script = JavaScript.create(this, field, errorCode, errorMessage);
    }
    /**
     * 验证通过（非空）返回true
     * 验证不通过（空）返回false
     */
    @Override
    public boolean isValid(Map<String,Object> requestBody) {
//        String value = request.getParameter(field);
    	logger.debug("需要验证的字段："+field);
    	logger.debug("request body:"+requestBody);
    	Object value = requestBody.get(field);
    	boolean result = false;
    	try{
    		result = _StringUtils.isNotBlank((String)value); 
    	}catch(Exception e){
    		result = _StringUtils.isNotBlank(String.valueOf(value));
    	}
    	logger.debug("验证结果："+result);
        return result;
    }

    @Override
    public void output(Writer out) throws IOException {
        script.output(out);
    }
}
