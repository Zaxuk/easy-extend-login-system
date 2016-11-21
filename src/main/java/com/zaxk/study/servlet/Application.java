package com.zaxk.study.servlet;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * 访问 ServletContext 的类
 *
 * @author yiding.he
 */
@SuppressWarnings("unchecked")
public class Application {

    private static ServletContext context;

    public static void setContext(ServletContext context) {
        Application.context = context;
    }

    /**
     * 设置 ServletContext 属性
     *
     * @param key   属性名
     * @param value 属性值
     */
    public static void setAttribute(String key, String value) {
        context.setAttribute(key, value);
    }

    /**
     * 获取绝对路径
     *
     * @param path 应用内路径
     * @return 绝对路径
     */
    public static String getRealPath(String path) {
        return context.getRealPath(path);
    }

    /**
     * 获取 web.xml 里的应用初始化参数
     *
     * @param key 参数名
     * @return 参数值
     */
    public static String getInitParameter(String key) {
        return context.getInitParameter(key);
    }

    public static <T> T getBean(String name, Class<T> type) {
        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(context);
        return cxt.getBean(name, type);
    }
}
