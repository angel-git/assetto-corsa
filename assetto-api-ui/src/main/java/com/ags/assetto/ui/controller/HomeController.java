package com.ags.assetto.ui.controller;

import com.ags.assetto.connector.service.ACService;
import com.ags.assetto.ui.thymeleaf.annotation.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Gavalda on 12/13/2014.
 */
@Controller
@Layout("layouts/index")
public class HomeController {

    @Autowired
    private ACService acService;

    @RequestMapping("/")
    public String getIndexPage() {
        return "intro";
    }


    @RequestMapping("/start")
    public @ResponseBody String start() {
        acService.connect("127.0.0.1");
        return "";
    }


    @RequestMapping("/checkConnection")
    public @ResponseBody boolean isConnected() {
        return acService.isConnected();
    }




}
