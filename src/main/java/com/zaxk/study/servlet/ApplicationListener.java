package com.zaxk.study.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

/**
 * 应用侦听器
 *
 * @author yiding.he
 */
public class ApplicationListener implements HttpSessionListener, ServletContextListener, ServletRequestListener {

    private static final Log log = LogFactory.getLog(ApplicationListener.class);

    public static Map<String, String> AUTHENTICATION_MAP;

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    }

    /////////////////////////////////////////

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            initApplication(servletContextEvent);
        } catch (Exception e) {
            log.error("项目初始化失败", e);
        }
    }

    private void initApplication(ServletContextEvent event) {
        Application.setContext(event.getServletContext());
    }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
    }

}