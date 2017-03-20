package com.devefx.validation;

import net.feimz.system.enu.MyCode;

/**
 * Result
 * Created by YYQ on 2016/6/1.
 * update by bill on 2017/3/20
 * 修改为符合我框架的结果
 */
public class Result<T> {

	private Integer code;
	private T result;
	private String msg;
	
    public Result(MyCode c, T result) {
        this.code = c.getCode();
        this.msg = c.getMsg();
        this.result = result;
    }

    public Result(MyCode c) {
        this.code = c.getCode();
        this.msg = c.getMsg();
    }
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
