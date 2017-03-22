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
 * EqualFieldValidator
 * Created by YYQ on 2016/5/26.
 */
@BindScript("EqualFieldValidator.js")
public class EqualFieldValidator extends FieldValidator implements Script {

    private final Script script;
    private String field2;

    public EqualFieldValidator(String field1, String field2, String errorCode, String errorMessage) {
        super(field1, errorCode, errorMessage);
        this.field2 = field2;
        script = JavaScript.create(this, field1, field2, errorCode, errorMessage);
    }

    @Override
    public boolean isValid(Map<String,Object> requestBody) {
        Object v1 = requestBody.get(field);
        Object value2 = requestBody.get(field2);
    	if(v1 != null){
    		String value1 = v1.toString();
    		if(_StringUtils.isNotBlank(value1)){
    			 if ((value1 == value2) || (value2 != null && value1.equals(value2))) {
    		            return true;
    		        }
    		}
    	}
       
        return false;
    }

    @Override
    public void output(Writer out) throws IOException {
        script.output(out);
    }
}
