package com.devefx.validation.support.spring;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.feimz.utils._JsonUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.devefx.validation.constraints.BodyReaderHttpServletRequestWrapper;
import com.devefx.validation.constraints.HttpHelper;
import com.devefx.validation.support.Interceptor;

public class ValidatorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		Map<String,Object> requestBody = new HashMap<>();
		
	   	// 防止流读取一次后就没有了, 所以需要将流继续写出去
		BodyReaderHttpServletRequestWrapper requestWrapper = null;
		try {
			requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
			String body = new String(HttpHelper.getBodyByte(requestWrapper),"UTF-8");
			String body2 = new String(HttpHelper.getBodyByte(requestWrapper),"UTF-8");
			System.out.println("body:"+body);
			System.out.println("body2:"+body2);
			if(StringUtils.isNotBlank(body)){
				requestBody = _JsonUtil.JsonToMaps(body);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			if (!Interceptor.valid(handlerMethod.getBeanType(), request, response,requestBody)) {
				return false;
			}
			if (!Interceptor.valid(handlerMethod.getMethod(), request, response,requestBody)) {
				return false;
			}
        } else {
            if (!Interceptor.valid(handler.getClass(), request, response,requestBody)) {
                return false;
            }
        }
		if(requestWrapper != null){
			return super.preHandle(requestWrapper, response, handler);
		}else{
			return super.preHandle(request, response, handler);
		}
	}
	
}
