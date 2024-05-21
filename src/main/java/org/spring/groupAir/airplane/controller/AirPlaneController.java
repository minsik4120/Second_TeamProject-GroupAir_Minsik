package org.spring.groupAir.airplane.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/airplane")
@RequiredArgsConstructor
public class AirPlaneController {

    @GetMapping({"", "/", "/index"})
    public String index(){

        return "airplane/index";
    }

}
