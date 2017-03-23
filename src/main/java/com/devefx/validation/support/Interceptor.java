package com.devefx.validation.support;

import java.lang.reflect.AnnotatedElement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devefx.validation.Cache;
import com.devefx.validation.Validator;

/**
 * Interceptor
 * Created by YYQ on 2016/9/19.
 */
public class Interceptor {
	
	static Cache validCahce = new CacheImpl();
	
	public static void setCache(Cache cache) {
		validCahce = cache;
	}
	
	public static Cache getCache() {
	    return validCahce;
	}
	
	/**
	 * 判断是否需要拦截(包含注解就需要拦截)
	 * @param annotatedElement
	 * @return
	 */
	public static boolean isNeedInterceptor(AnnotatedElement annotatedElement){
		List<Validator> validSet = validCahce.get(annotatedElement);
		if(validSet != null){
			return true;
		}
		return false;
	}
	
	public static boolean valid(AnnotatedElement annotatedElement, HttpServletRequest request
			, HttpServletResponse response,Map<String,Object> requestBody) {
		List<Validator> validSet = validCahce.get(annotatedElement);
		if (validSet != null) {
			Iterator<Validator> it = validSet.iterator();
			while (it.hasNext()) {
				Validator valid = it.next();
				valid.reset();
				if (!valid.process(request, response,requestBody)) {
					return false;
				}
			}
		}
		return true;
	}
	
}
