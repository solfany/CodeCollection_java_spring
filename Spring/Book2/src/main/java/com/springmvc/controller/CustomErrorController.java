package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController {

    @RequestMapping("/error/404")
    public ModelAndView handle404Error() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404");
        return modelAndView;
    }
    
}
