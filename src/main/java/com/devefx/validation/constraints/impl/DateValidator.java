package com.devefx.validation.constraints.impl;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import net.feimz.utils._StringUtils;

import com.devefx.validation.Script;
import com.devefx.validation.annotation.BindScript;
import com.devefx.validation.constraints.FieldValidator;
import com.devefx.validation.kit.DateKit;
import com.devefx.validation.script.JavaScript;

/**
 * DateValidator
 * Created by YYQ on 2016/5/30.
 */
@BindScript("DateValidator.js")
public class DateValidator extends FieldValidator implements Script {

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    private final Script script;
    private Date minDate;
    private Date maxDate;
    private String pattern;

    // for Date
    public DateValidator(String field, Date minDate, Date maxDate, String pattern, String errorCode, String errorMessage) {
        super(field, errorCode, errorMessage);
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.pattern = pattern;
        script = JavaScript.create(this, field, DateKit.format(pattern, minDate),
                DateKit.format(pattern, maxDate), pattern, errorCode, errorMessage);
    }

    public DateValidator(String field, Date minDate, Date maxDate, String errorCode, String errorMessage) {
        this(field, minDate, maxDate, DEFAULT_DATE_PATTERN, errorCode, errorMessage);
    }

    // for String
    public DateValidator(String field, String minDate, String maxDate, String pattern, String errorCode,
                         String errorMessage) throws ParseException {
        super(field, errorCode, errorMessage);
        this.minDate = DateKit.parse(pattern, minDate);
        this.maxDate = DateKit.parse(pattern, maxDate);
        this.pattern = pattern;
        script = JavaScript.create(this, field, minDate, maxDate, pattern, errorCode, errorMessage);
    }

    public DateValidator(String field, String minDate, String maxDate, String errorCode,
                         String errorMessage) throws ParseException {
        this(field, minDate, maxDate, DEFAULT_DATE_PATTERN, errorCode, errorMessage);
    }

    @Override
    public boolean isValid(Map<String,Object> requestBody) throws Exception {
        
        Object value = requestBody.get(field);
    	if(value != null){
    		String v = value.toString();
    		if(_StringUtils.isNotBlank(v)){
    			Date date = DateKit.parse(pattern != null ? pattern : DEFAULT_DATE_PATTERN, v);
            	return !(date.before(minDate) || date.after(maxDate));
    		}
    	}
        return true;
        
    }

    @Override
    public void output(Writer out) throws IOException {
        script.output(out);
    }
}
