package com.devefx.validation.support.spring;

import java.lang.reflect.AnnotatedElement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.feimz.utils._JsonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.devefx.validation.constraints.BodyReaderHttpServletRequestWrapper;
import com.devefx.validation.constraints.HttpHelper;
import com.devefx.validation.support.Interceptor;

public class ValidatorInterceptor extends HandlerInterceptorAdapter {
	private final Logger logger = LoggerFactory.getLogger(ValidatorInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		AnnotatedElement ae = null;
		boolean isNeedInterceptor = false;//是否需要拦截
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			if(Interceptor.isNeedInterceptor(handlerMethod.getBeanType())){
				isNeedInterceptor = true;
				ae = handlerMethod.getBeanType();
			}else if(Interceptor.isNeedInterceptor(handlerMethod.getMethod())){
				isNeedInterceptor = true;
				ae = handlerMethod.getMethod();
			}
        }else{
        	ae = handler.getClass();
        	isNeedInterceptor = Interceptor.isNeedInterceptor(ae);
        }
		BodyReaderHttpServletRequestWrapper requestWrapper = null;
		if(isNeedInterceptor){//需要被拦截
			Map<String,Object> requestBody = new HashMap<>();
		 	// 防止流读取一次后就没有了, 所以需要将流继续写出去
			try {
				requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
				String body = new String(HttpHelper.getBodyByte(requestWrapper),"UTF-8");
				if(StringUtils.isNotBlank(body)){
					requestBody = _JsonUtil.JsonToMaps(body);
				}
			} catch (Exception e) {
				logger.warn("验证参数异常：",e);
			}
			if (!Interceptor.valid(ae, request, response,requestBody)) {
				return false;
			}
		}
		if(isNeedInterceptor){
			return super.preHandle(requestWrapper, response, handler);
		}else{
			return super.preHandle(request, response, handler);
		}
	}
	
}
