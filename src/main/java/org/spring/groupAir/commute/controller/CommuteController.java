package org.spring.groupAir.commute.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/commute")
public class CommuteController {

    @GetMapping("/index")
    public String index(){

        return "commute/index";
    }

}
