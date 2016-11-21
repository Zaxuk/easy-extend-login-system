package com.zaxk.study.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by ZhuXu on 2016/11/18 0018.
 */
public class CookieUtil {

    public static final Logger LOG = LoggerFactory.getLogger(CookieUtil.class);

    public static final int SECOND = 1;
    public static final int MINUTES = 60 * SECOND;
    public static final int HOUR = 60 * MINUTES;
    public static final int DAY = 24 * HOUR;
    public static final int MONTH = 30 * DAY;

    public static String getCookieValue(HttpServletRequest request, String key) {

        if (request.getCookies() == null) {
            return null;
        }

        List<Cookie> cookies = Arrays.asList(request.getCookies());
        cookies.forEach(cookie -> LOG.info("{}:{}", cookie.getName(), cookie.getValue()));
        Optional<Cookie> optional = cookies.stream().filter(c -> c.getName().equals(key)).findFirst();
        if (!optional.isPresent()) {
            return null;
        }
        return optional.get().getValue();
    }

    public static void setCookieValue(HttpServletResponse response, String key, String value, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath(".");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static boolean exists(HttpServletRequest request, String key) {

        if (request.getCookies() == null) {
            return false;
        }

        List<Cookie> cookies = Arrays.asList(request.getCookies());
        Optional<Cookie> optional = cookies.stream().filter(c -> c.getName().equals(key)).findFirst();
        return optional.isPresent();
    }
}
