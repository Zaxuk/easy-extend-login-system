package com.zaxk.study.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZhuXu on 2016/11/18 0018.
 */
public class Page {

    private static final Logger log = LoggerFactory.getLogger(Page.class);

    private static ThreadLocal<HttpServletRequest> requestTL = new ThreadLocal<HttpServletRequest>();

    private static ThreadLocal<HttpServletResponse> responseTL = new ThreadLocal<HttpServletResponse>();

    public static HttpServletRequest getRequest() {
        return requestTL.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestTL.set(request);
    }

    public static HttpServletResponse getResponse() {
        return responseTL.get();
    }

    public static void setResponse(HttpServletResponse response) {
        responseTL.set(response);
    }
}
