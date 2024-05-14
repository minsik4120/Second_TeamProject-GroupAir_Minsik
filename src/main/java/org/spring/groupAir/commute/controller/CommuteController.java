package org.spring.groupAir.commute.controller;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.commute.service.CommuteService;
import org.spring.groupAir.department.entity.DepartmentEntity;
import org.spring.groupAir.member.dto.MemberDto;
import org.spring.groupAir.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/commute")
public class CommuteController {

    private final CommuteService commuteService;
    private final MemberService memberService;


    @GetMapping("/index")
    public String index(){

        return "commute/index";
    }

    @GetMapping("/vacation")
    public String vacation(){

        return "commute/vacation";
    }



}
