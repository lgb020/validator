package com.devefx.validation.constraints.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.devefx.validation.Script;
import com.devefx.validation.annotation.BindScript;
import com.devefx.validation.constraints.FieldValidator;
import com.devefx.validation.script.JavaScript;

/**
 * BooleanValidator
 * 判断是否布尔值，如果是返回true，否则返回false
 * Created by YYQ on 2016/5/26.
 */
@BindScript("BooleanValidator.js")
public class BooleanValidator extends FieldValidator implements Script {

    private final Script script;

    public BooleanValidator(String field, String errorCode, String errorMessage) {
        super(field, errorCode, errorMessage);
        script = JavaScript.create(this, field, errorCode, errorMessage);
    }

    @Override
    public boolean isValid(Map<String,Object> requestBody) {
    	Object value = requestBody.get(field);
    	if(value != null){
    		String v = value.toString();
    		return "1".equals(v) || "0".equals(v) ||
            "true".equalsIgnoreCase(v) || "false".equalsIgnoreCase(v);
    	}
        return false;
    }

    @Override
    public void output(Writer out) throws IOException {
        script.output(out);
    }
}
