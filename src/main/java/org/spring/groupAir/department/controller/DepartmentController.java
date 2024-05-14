package org.spring.groupAir.department.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.department.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/index")
    public String department(){

        return "department/index";
    }

}
