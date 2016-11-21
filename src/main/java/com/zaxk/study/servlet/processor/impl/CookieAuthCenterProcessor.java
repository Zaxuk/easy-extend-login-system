package com.zaxk.study.servlet.processor.impl;

import com.zaxk.study.common.Constant;
import com.zaxk.study.pojo.User;
import com.zaxk.study.service.UserService;
import com.zaxk.study.servlet.Application;
import com.zaxk.study.servlet.processor.AuthCenterProcessor;
import com.zaxk.study.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie验证方式
 * Created by ZhuXu on 2016/11/17 0017.
 */
@Service
public class CookieAuthCenterProcessor extends AuthCenterProcessor {

    public static final Logger LOG = LoggerFactory.getLogger(CookieAuthCenterProcessor.class);

    public static final String COOKIE_KEY = "vGR@2oHoSq1c!2%$";

    @Override
    public User authenticate(HttpServletRequest request, HttpServletResponse response) {

        String cookieUser = CookieUtil.getCookieValue(request, Constant.USER);
        User user = getUserFromCookie(cookieUser);
        return user;

    }

    private User getUserFromCookie(String cookieUser) {

        if (StringUtils.isEmpty(cookieUser)) {
            return null;
        }

        try {
            String[] strs = cookieUser.split(UserService.SPLIT_CHAR);
            int userId = Integer.valueOf(strs[0]);
            long expireTime = Long.valueOf(strs[1]);
            String hash = strs[2];

            if (System.currentTimeMillis() > expireTime) {
                return null;
            }

            UserService userService = Application.getBean("userService", UserService.class);

            LOG.info("userService:{}", userService);

            User user = userService.loginByCookie(userId, expireTime, hash);
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}