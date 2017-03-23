package com.devefx.validation.constraints;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created with antnest-platform
 * User: chenyuan
 * Date: 12/31/14
 * Time: 8:49 PM
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;
//    private final HttpServletRequest request;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws Exception {
        super(request);
//        this.request = request;
        body = HttpHelper.getBodyByte(request);
//        if(body == null){
//        	Enumeration<String> en = request.getParameterNames();
//        	while(en.hasMoreElements()){
//        		String k = en.nextElement();
//        		this.setAttribute(k, request.getParameter(k));
//        	}
//        }
    }
    
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        ServletInputStream sis = new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
        
        
        return sis;
    }
}