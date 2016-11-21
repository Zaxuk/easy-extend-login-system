package com.zaxk.study.controller;

import com.zaxk.study.context.UserContext;
import com.zaxk.study.pojo.User;
import com.zaxk.study.service.UserService;
import com.zaxk.study.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZhuXu on 2016/10/18 0018.
 */
@RestController
public class IndexController {

    public static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "/index"})
    public ModelAndView index() {

        User user = UserContext.getUser();
        if (user == null) {
            return new ModelAndView("index");
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User user) {

        Result result = userService.login(user);

        if (!result.isSuccess()) {
            return new ModelAndView("index").addObject("message", result.getMessage());
        }

        return new ModelAndView("redirect:/index").addObject("user", result.get("user"));
    }
}
