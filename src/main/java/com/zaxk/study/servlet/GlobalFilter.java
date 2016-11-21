package com.zaxk.study.servlet;

import com.zaxk.study.common.Page;
import com.zaxk.study.context.UserContext;
import com.zaxk.study.pojo.User;
import com.zaxk.study.servlet.processor.AuthCenterProcessor;
import com.zaxk.study.servlet.processor.Processor;
import com.zaxk.study.servlet.processor.impl.APIAuthCenterProcessor;
import com.zaxk.study.servlet.processor.impl.BasicAuthCenterProcessor;
import com.zaxk.study.servlet.processor.impl.CookieAuthCenterProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ZhuXu on 2016/11/17 0017.
 */
public class GlobalFilter implements Filter {

    public static final Logger LOG = LoggerFactory.getLogger(GlobalFilter.class);

    public static final List<Processor> PROCESSORS = Arrays.asList(

    );

    private static final List<AuthCenterProcessor> AUTH_CENTER_PROCESSORS = Arrays.asList(
            new CookieAuthCenterProcessor(),
            new BasicAuthCenterProcessor(),
            new APIAuthCenterProcessor()
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");

        Page.setRequest(request);
        Page.setResponse(response);

        for (Processor processor : PROCESSORS) {
            if (!processor.execute(request, response)) {
                return;
            }
        }

        User user = null;
        for (AuthCenterProcessor authCenterProcessor : AUTH_CENTER_PROCESSORS) {
            user = authCenterProcessor.authenticate(request, response);
            if (user != null) {
                break;
            }
        }

        try (UserContext userContext = new UserContext(user)) {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}

