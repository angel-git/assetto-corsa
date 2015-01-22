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

    private static final String LOCALHOST_IP = "127.0.0.1";
    @Autowired
    private ACService acService;

    @RequestMapping("/")
    public String getIndexPage() {
        acService.prepareConnection(LOCALHOST_IP);
        return "intro";
    }


    @RequestMapping("/start")
    public @ResponseBody String start() {
        acService.connect();
        return "";
    }


    @RequestMapping("/checkConnection")
    public @ResponseBody boolean isConnected() {
        return acService.isConnected();
    }




}
