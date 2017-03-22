package com.devefx.validation.constraints;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

/**
 * Created with antnest-platform
 * User: chenyuan
 * Date: 12/24/14
 * Time: 10:39 AM
 */
public class HttpHelper {
	
	public static byte[] getBodyByte(ServletRequest request) throws Exception{
		int len = request.getContentLength();
		if(len <=0) return null;
		ServletInputStream iii;
		iii = request.getInputStream();
		byte[] buffer = new byte[len];
		iii.read(buffer, 0, len);
		return buffer;
	}

}
