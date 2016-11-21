package com.zaxk.study.servlet.processor.impl;

import com.zaxk.study.pojo.User;
import com.zaxk.study.servlet.processor.AuthCenterProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * API Token 验证方式
 * Created by ZhuXu on 2016/11/17 0017.
 */
public class APIAuthCenterProcessor extends AuthCenterProcessor {
    @Override
    public User authenticate(HttpServletRequest request, HttpServletResponse responsee) {
        return null;
    }
}
