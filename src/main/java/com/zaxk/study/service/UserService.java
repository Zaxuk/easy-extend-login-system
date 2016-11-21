package com.zaxk.study.service;

import com.zaxk.study.common.Constant;
import com.zaxk.study.common.Page;
import com.zaxk.study.dao.UserDao;
import com.zaxk.study.pojo.User;
import com.zaxk.study.pojo.UserExample;
import com.zaxk.study.servlet.processor.impl.CookieAuthCenterProcessor;
import com.zaxk.study.util.CookieUtil;
import com.zaxk.study.util.MD5Util;
import com.zaxk.study.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhuXu on 2016/11/18 0018.
 */
@Service
public class UserService {

    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public static final String SPLIT_CHAR = ":";

    @Autowired
    UserDao userDao;

    public Result login(User user) {

        UserExample userExample = new UserExample();
        String password = MD5Util.string2MD5(user.getPassword());
        userExample.createCriteria().andUserNameEqualTo(user.getUserName()).andPasswordEqualTo(password);

        List<User> userList = userDao.selectByExample(userExample);
        if (userList.isEmpty()) {
            return Result.fail(Error.USER_NOT_EXIST.getValue());
        }

        user = userList.get(0);
        createSessionUser(user);
        return Result.success().put("user", user);

    }

    public User loginByCookie(int userId, long expireTime, String cookieHash) {
        User user = userDao.selectByPrimaryKey(userId);
        String hash = createHash(userId, user.getPassword(), expireTime);
        if (hash.equals(cookieHash)) {
            return user;
        } else {
            return null;
        }
    }

    private void createSessionUser(User user) {

        int userId = user.getUserId();
        String password = user.getPassword();
        long expireTime = System.currentTimeMillis() + (long) CookieUtil.MONTH * 1000;

        String hash = createHash(userId, password, expireTime);

        StringBuilder cookieBuilder = new StringBuilder();
        cookieBuilder.append(userId).append(SPLIT_CHAR);
        cookieBuilder.append(expireTime).append(SPLIT_CHAR);
        cookieBuilder.append(hash);
        CookieUtil.setCookieValue(Page.getResponse(), Constant.USER, cookieBuilder.toString(), CookieUtil.MONTH);

    }

    private String createHash(int userId, String password, long expireTime) {
        StringBuilder hashBuilder = new StringBuilder();
        hashBuilder.append(userId).append(SPLIT_CHAR);
        hashBuilder.append(password).append(SPLIT_CHAR);
        hashBuilder.append(expireTime).append(SPLIT_CHAR);
        hashBuilder.append(CookieAuthCenterProcessor.COOKIE_KEY);
        String hash = MD5Util.string2MD5(hashBuilder.toString());
        return hash;
    }

    private enum Error {

        SUCCESS(0, "登录成功"),
        USER_NOT_EXIST(1, "用户名或密码错误");

        private int code;
        private String value;

        Error(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

}
