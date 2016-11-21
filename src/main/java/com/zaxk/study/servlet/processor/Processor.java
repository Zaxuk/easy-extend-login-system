package com.zaxk.study.servlet.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器任务接口
 * Created by ZhuXu on 2016/11/17 0017.
 */
public interface Processor {

    boolean execute(HttpServletRequest request, HttpServletResponse responsee);

}

