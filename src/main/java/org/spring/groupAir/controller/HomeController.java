package org.spring.groupAir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/role/admin")
    public String adminPage(){

        return "role/admin";
    }
    @GetMapping("/role/manager")
    public String managerPage(){

        return "role/manager";
    }
    @GetMapping("/role/member")
    public String memberPage(){

        return "role/member";
    }
}
