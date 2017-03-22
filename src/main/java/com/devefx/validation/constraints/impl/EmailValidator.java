package com.devefx.validation.constraints.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.feimz.utils._StringUtils;

import com.devefx.validation.Script;
import com.devefx.validation.annotation.BindScript;
import com.devefx.validation.constraints.FieldValidator;
import com.devefx.validation.script.JavaScript;

/**
 * EmailValidator
 * Created by YYQ on 2016/5/26.
 */
@BindScript("EmailValidator.js")
public class EmailValidator extends FieldValidator implements Script {

    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", Pattern.CASE_INSENSITIVE);
    private static final Pattern emailMultiPattern = Pattern.compile("^([a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+;)+$", Pattern.CASE_INSENSITIVE);

    private final Script script;
    private final boolean multi;

    public EmailValidator(String field, boolean multi, String errorCode, String errorMessage) {
        super(field, errorCode, errorMessage);
        this.multi = multi;
        script = JavaScript.create(this, field, multi, errorCode, errorMessage);
    }

    public EmailValidator(String field, String errorCode, String errorMessage) {
        this(field, false, errorCode, errorMessage);
    }

    @Override
    public boolean isValid(Map<String,Object> requestBody) {
        Object value = requestBody.get(field);
    	if(value != null){
    		String v = value.toString();
    		if(_StringUtils.isNotBlank(v)){
    			if (multi) {
                    if (!v.endsWith(";")) {
                        v = v + ";";
                    }
                    Matcher matcher = emailMultiPattern.matcher(v);
                    return matcher.matches();
                }
                Matcher matcher = emailPattern.matcher(v);
                return matcher.matches();
    		}
    	}
        return true;
    }

    @Override
    public void output(Writer out) throws IOException {
        script.output(out);
    }
}
