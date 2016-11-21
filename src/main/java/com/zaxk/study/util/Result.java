package com.zaxk.study.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhuXu on 2016/11/17 0017.
 */
public class Result {

    public static final Logger log = LoggerFactory.getLogger(Result.class);

    /**
     * 请求是否成功处理
     */
    private boolean success = true;
    /**
     * 处理成功或失败时的相关信息
     */
    private String message = null;
    /**
     * 返回值
     */
    private Map<String, Object> data = new HashMap<String, Object>();

    public Result() {
    }

    //////////////////////////////////////////////

    public Result(Map<String, Object> data) {
        this.data = data;
    }

    public Result(final String name, final Object value) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(name, value);
        this.data = data;
    }

    public static Result success() {
        return new Result();
    }

    public static Result fail(final String message) {
        Result Result = new Result();
        Result.setSuccess(false);
        Result.setMessage(message);
        return Result;
    }

    public static Result fail() {
        Result Result = new Result();
        Result.setSuccess(false);
        return Result;
    }

    public static Result fail(String message, int code) {
        return fail(message).put("code", code);
    }

    public static Result fail(String message, String code) {
        return fail(message).put("code", code);
    }

    public boolean isSuccess() {
        return success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    /////////////////////////////////////////

    public Result put(String name, Object value) {
        data.put(name, value);
        return this;
    }

    public Object get(String name) {
        return data.get(name);
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
