package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class RepositoryController {

    @RequestMapping(value = "/projectRepository/main")
    public ModelAndView projectRepository(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("projectRepository/main");
    }

}
