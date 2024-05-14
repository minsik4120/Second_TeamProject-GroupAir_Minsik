package org.spring.groupAir.department.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.department.service.DepartmentService;
import org.spring.groupAir.department.service.TopDepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/topDepartment")
public class TopDepartmentController {

    private final TopDepartmentService topDepartmentService;

    @GetMapping("/index")
    public String topDepartment(){

        return "topDepartment/index";
    }
}
