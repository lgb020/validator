package com.devefx.validation.constraints.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.devefx.validation.Script;
import com.devefx.validation.annotation.BindScript;
import com.devefx.validation.constraints.FieldValidator;
import com.devefx.validation.script.JavaScript;

/**
 * NullValidator
 * Created by YYQ on 2016/5/26.
 */
@BindScript("NullValidator.js")
public class NullValidator extends FieldValidator implements Script {

    private final Script script;

    public NullValidator(String field, String errorCode, String errorMessage) {
        super(field, errorCode, errorMessage);
        script = JavaScript.create(this, field, errorCode, errorMessage);
    }

    @Override
    public boolean isValid(Map<String,Object> requestBody) {
        return requestBody.get(field) == null;
    }

    @Override
    public void output(Writer out) throws IOException {
        script.output(out);
    }
}
