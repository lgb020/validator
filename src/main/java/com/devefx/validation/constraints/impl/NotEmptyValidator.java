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
 * NotEmptyValidator
 * Created by YYQ on 2016/5/26.
 */
@BindScript("NotEmptyValidator.js")
public class NotEmptyValidator extends FieldValidator implements Script {

    private final Script script;

    public NotEmptyValidator(String field, String errorCode, String errorMessage) {
        super(field, errorCode, errorMessage);
        script = JavaScript.create(this, field, errorCode, errorMessage);
    }

    @Override
    public boolean isValid(Map<String,Object> requestBody) {
        Object v = requestBody.get(field);
        if(v != null){
        	String value = v.toString();
        	return _StringUtils.isNotBlank(value);
        }
        return false;
    }

    @Override
    public void output(Writer out) throws IOException {
        script.output(out);
    }
}
