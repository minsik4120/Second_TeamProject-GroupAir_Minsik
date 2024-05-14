package org.spring.groupAir.commute.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.commute.service.CommuteService;
import org.spring.groupAir.department.entity.DepartmentEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/commute")
public class CommuteController {

    private final CommuteService commuteService;


    @GetMapping("/index")
    public String index(){

        return "commute/index";
    }
    @GetMapping("/work")
    public String work(){

        return "commute/work";
    }

    @GetMapping("/vacation")
    public String vacation(){

        return "commute/vacation";
    }


}
