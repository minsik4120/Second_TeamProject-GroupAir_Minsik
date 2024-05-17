package org.spring.groupAir.salary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/salary")
public class SalaryController {
    @GetMapping({"/","/index",""})
    public String salaryIndex(){

        return "salary/index";
    }
}
