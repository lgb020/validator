package com.devefx.validation.constraints.impl;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

import net.feimz.utils._StringUtils;

import com.devefx.validation.Script;
import com.devefx.validation.annotation.BindScript;
import com.devefx.validation.constraints.FieldValidator;
import com.devefx.validation.script.JavaScript;

/**
 * URLValidator
 * Created by YYQ on 2016/5/26.
 */
@BindScript("URLValidator.js")
public class URLValidator extends FieldValidator implements Script {

    private final Script script;

    public URLValidator(String field, String errorCode, String errorMessage) {
        super(field, errorCode, errorMessage);
        script = JavaScript.create(this, field, errorCode, errorMessage);
    }

    @Override
    public boolean isValid(Map<String,Object> requestBody) {
        Object v = requestBody.get(field);
        if(v != null){
        	String value = v.toString();
	        if (_StringUtils.isNotBlank(value)) {
	            try {
	                new URL(value);
	            } catch (Exception e) {
	                return false;
	            }
	        }
        }
        return true;
    }

    @Override
    public void output(Writer out) throws IOException {
        script.output(out);
    }
}
